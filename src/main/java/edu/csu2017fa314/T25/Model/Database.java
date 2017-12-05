package edu.csu2017fa314.T25.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private final int MAX_QUERY_SIZE = 100;
    private final int NUMBER_OF_ATTRIBUTES = 11;
    private String userName;
    private String password;
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url;


    private Connection connection;
    private Statement statement;

    public Database(){}

    public Database(String userName, String password, String url) throws ClassNotFoundException {
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
            points = constructResult(resultSet, total);
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

    public Result queryAlgorithm(ArrayList<String> codes){
        int total = codes.size();
        Point points[] = new Point[total];
        Result result = new Result(points, total);
        int partitions = (total / 100);
        ArrayList<String> queries = new ArrayList<>();

        if (total == 0){
            return new Result(null, 0);
        }

        dividePartitions(codes, partitions, queries);

        try {
            runQueries(queries, result);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        result.removeNull();

        return result;
    }

    private void dividePartitions(ArrayList<String> codes, int partitions, ArrayList<String> queries) {
        for (int i = 0; i < partitions; i++){
            int start = i * 100;
            int end = start + 100;
            List partition = codes.subList(start, end);
            String codeList = toList(partition);
            queries.add(formAlgorithmQuery(codeList));
        }

        List lastPartition = codes.subList(partitions * 100, codes.size());
        String lastCodeList = toList(lastPartition);
        queries.add(formAlgorithmQuery(lastCodeList));
    }

    private void runQueries(ArrayList<String> queries, Result result) throws SQLException {
        for (int i = 0; i < queries.size(); i++){
            ResultSet resultSet = statement.executeQuery(queries.get(i));
            while (resultSet.next()){
                Point newPoint = setAttributes(resultSet);
                result.add(newPoint);
            }
        }
    }

    private String formAlgorithmQuery(String idList) {
        String select = "SELECT airports.code, airports.type, airports.name, latitude, longitude, elevation, municipality, countries.name, regions.name, home_link, airports.wikipedia_link "; // Columns we want in the result set

        String from = "FROM airports INNER JOIN continents ON airports.continent = continents.code " +
                "INNER JOIN countries ON airports.iso_country = countries.code " +
                "INNER JOIN regions ON airports.iso_region = regions.code ";

        String where = "WHERE airports.code IN " + idList;

        return select + from + where + ";";
    }

    private Point[] constructResult(ResultSet resultSet, int total) throws SQLException {
        Point points[] = new Point[total];

        int counter = 0;
        while (resultSet.next() && counter < total) {
            Point newPoint = setAttributes(resultSet);
            points[counter] = newPoint;
            counter++;
        }

        return points;
    }

    private Point setAttributes(ResultSet resultSet) throws SQLException {
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

        return new Point(attributes);
    }

    private int getTotal(String searchString) throws SQLException {
        String query = "SELECT COUNT(*) FROM airports WHERE type LIKE '%" +
                searchString + "%' OR name LIKE '%" +
                searchString + "%' OR municipality LIKE '%" +
                searchString + "%';";

        ResultSet result = statement.executeQuery(query);

        int total;
        result.next();
        total = result.getInt(1);

        if (MAX_QUERY_SIZE < total)
            total = MAX_QUERY_SIZE;

        return total;
    }

    private String toList(List<String> id) {
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
