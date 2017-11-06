package edu.csu2017fa314.T25.Model;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseDriver {
    private final int MAX_QUERY_SIZE = 100;
    private final int NUMBER_OF_ATTRIBUTES = 11;
    private String userName;
    private String password;
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url;


    private Connection connection;
    private Statement statement;

    public DatabaseDriver(String userName, String password, String url) throws ClassNotFoundException {
        this.userName = userName;
        this.password = password;
        this.url = url;
        Class.forName(driver);

        try {
            startConnection();
        } catch (SQLException e) {
            System.err.printf("Exception: ");
            System.err.printf(e.getMessage());
        }
    }

    private void startConnection() throws SQLException {
        connection = DriverManager.getConnection(url, userName, password);
        statement = connection.createStatement();
    }

    public Result queryPage(String searchString) {
        int total = 0;
        Point points[] = null;

        try {
            total = getTotal(searchString);
            String query = formPageQuery(searchString);
            ResultSet resultSet = statement.executeQuery(query);
            points = constructPageResult(resultSet, total);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Result result = new Result(points, total);
        return result;
    }

    private String formPageQuery(String searchString) {
        String select = "SELECT airports.code, airports.type, airports.name, latitude, longitude, elevation, municipality, countries.name, regions.name, home_link, airports.wikipedia_link "; // Columns we want in the result set

        String from = "FROM airports INNER JOIN continents ON airports.continent = continents.code " +
                "INNER JOIN countries ON airports.iso_country = countries.code " +
                "INNER JOIN regions ON airports.iso_region = regions.code ";

        String where = "WHERE continents.name like '%" + searchString + "%' OR " +
                "continents.name like '%" + searchString + "%' OR " +
                "continents.code like '%" + searchString + "%' OR " +
                "countries.name like '%" + searchString + "%' OR " +
                "countries.code like '%" + searchString + "%' OR " +
                "countries.keywords like '%" + searchString + "%' OR " +
                "regions.name like '%" + searchString + "%' OR " +
                "regions.keywords like '%" + searchString + "%' OR " +
                "regions.code like '%" + searchString + "%' OR " +
                "airports.name like '%" + searchString + "%' OR " +
                "airports.code like '%" + searchString + "%' OR " +
                "airports.type like '%" + searchString + "%' OR " +
                "airports.municipality like '%" + searchString + "%' OR " +
                "airports.keywords like '%" + searchString + "%' ";

        String orderBy = "ORDER BY countries.name, regions.name, airports.municipality, " +
                "airports.name ASC ";

        return select + from + where + orderBy + "LIMIT " + MAX_QUERY_SIZE + ";";
    }

    private Point[] constructPageResult(ResultSet resultSet, int total) throws SQLException {
        Point points[] = new Point[total];

        int counter = 0;
        while (resultSet.next() && counter < total) {
            String attributes[] = new String[NUMBER_OF_ATTRIBUTES];
            attributes[0] = resultSet.getString("airports.code");
            attributes[1] = resultSet.getString("airports.type");
            attributes[2] = resultSet.getString("airports.name");
            attributes[3] = resultSet.getString("latitude");
            attributes[4] = resultSet.getString("longitude");
            attributes[6] = resultSet.getString("municipality");
            attributes[7] = resultSet.getString("countries.name");
            attributes[8] = resultSet.getString("regions.name");

            Point newPoint = new Point(attributes);
            points[counter] = newPoint;
            counter++;
        }

        return points;
    }

    public Result queryAlgorithm(ArrayList<String> id) {
        String idList = toList(id);
        Point points[] = null;
        int total = id.size();

        String query = formAlgorithmQuery(idList);
        try {
            if (idList != null) {
                ResultSet resultSet = statement.executeQuery(query);
                points = constructAlgorithmResult(resultSet, id.size());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Result result = new Result(points, total);
        return result;
    }

    private String formAlgorithmQuery(String idList) {
        String select = "SELECT airports.code, airports.type, airports.name, latitude, longitude, elevation, municipality, countries.name, regions.name, home_link, airports.wikipedia_link "; // Columns we want in the result set

        String from = "FROM airports INNER JOIN continents ON airports.continent = continents.code " +
                "INNER JOIN countries ON airports.iso_country = countries.code " +
                "INNER JOIN regions ON airports.iso_region = regions.code ";

        String where = "WHERE airports.code IN " + idList;

        return select + from + where + " LIMIT " + MAX_QUERY_SIZE + ";";
    }

    private Point[] constructAlgorithmResult(ResultSet resultSet, int total) throws SQLException {
        Point points[] = new Point[total];

        int counter = 0;
        while (resultSet.next() && counter < total) {
            String attributes[] = new String[NUMBER_OF_ATTRIBUTES];
            attributes[0] = resultSet.getString("airports.code");
            attributes[1] = resultSet.getString("airports.type");
            attributes[2] = resultSet.getString("airports.name");
            attributes[3] = resultSet.getString("latitude");
            attributes[4] = resultSet.getString("longitude");
            attributes[5] = resultSet.getString("elevation");
            attributes[6] = resultSet.getString("municipality");
            attributes[7] = resultSet.getString("countries.name");
            attributes[8] = resultSet.getString("regions.name");
            attributes[9] = resultSet.getString("home_link");
            attributes[10] = resultSet.getString("airports.wikipedia_link");

            Point newPoint = new Point(attributes);
            points[counter] = newPoint;
            counter++;
        }

        return points;
    }

    private int getTotal(String searchString) throws SQLException {
        String query = "SELECT COUNT(*) FROM airports WHERE type LIKE '%" +
                searchString + "%' OR name LIKE '%" +
                searchString + "%' OR municipality LIKE '%" +
                searchString + "%;'";

        ResultSet result = statement.executeQuery(query);

        int total;
        result.next();
        total = result.getInt(1);

        if (MAX_QUERY_SIZE < total)
            total = MAX_QUERY_SIZE;

        return total;
    }

    private String toList(ArrayList<String> id) {
        String idList = "(";
        for (int i = 0; i < id.size(); i++) {
            idList += "'" + id.get(i) + "', ";
        }

        if (id.size() != 0) {
            idList = idList.substring(0, idList.length() - 2) + ")";
            return idList;
        } else
            return null;
    }
}
