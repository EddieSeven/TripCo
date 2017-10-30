package edu.csu2017fa314.T25.Model;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseDriver {
    private final int MAX_QUERY_SIZE = 50;
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

    private String formPageQuery(String searchString) {
        String select = "SELECT world.id, world.name, latitude, longitude, world.continent, iso_country, iso_region "; // Columns we want in the result set

        String from = "FROM world INNER JOIN continents ON world.continent = continents.code " +
                "INNER JOIN countries ON world.iso_country = countries.code " +
                "INNER JOIN regions ON world.iso_region = region.code ";

        String where = "WHERE continents.name like '%" + searchString + "%' AND " +
                "continents.name like '%" + searchString + "%' AND " +
                "continents.code like '%" + searchString + "%' AND " +
                "countries.name like '%" + searchString + "%' AND " +
                "countries.code like '%" + searchString + "%' AND " +
                "countries.keywords like '%" + searchString + "%' AND " +
                "regions.name like '%" + searchString + "%' AND " +
                "regions.keywords like '%" + searchString + "%' AND " +
                "regions.code like '%" + searchString + "%' AND " +
                "world.name like '%" + searchString + "%' AND " +
                "world.code like '%" + searchString + "%' AND " +
                "world.type like '%" + searchString + "%' AND " +
                "world.municipality like '%" + searchString + "%' AND " +
                "world.keywords like '%" + searchString + "%'";

        return select + from + where + ";";
    }

    private String formAlgorithmQuery(String idList) {
        String query = "SELECT id, latitude, longitude FROM world WHERE id IN " + idList + ";"; // todo subject to change back to airports
        return query;
    }

    private int getTotal(String searchString) throws SQLException {
        String query = "SELECT COUNT(*) FROM world WHERE type LIKE '%" + // todo subject to change back to airports
                searchString + "%' OR name LIKE '%" +
                searchString + "%' OR municipality LIKE '%" +
                searchString + "%';";

        ResultSet result = statement.executeQuery(query);

        int total;
        result.next();
        total = result.getInt(1);

        return total;
    }

    private void startConnection() throws SQLException {
        connection = DriverManager.getConnection(url, userName, password);
        statement = connection.createStatement();
    }

    public Result queryAlgorithm(ArrayList<String> id){
        String idList = toList(id);
        ArrayList<Point> points = null;

        String query = formAlgorithmQuery(idList);
        try {
            if (idList != null) {
                ResultSet resultSet = statement.executeQuery(query);
                points = constructResult(resultSet, id.size());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

		points.trimToSize();
        return new Result(points);
    }

    private String toList(ArrayList<String> id){
        String idList = "(";
        for (int i = 0; i < id.size(); i++){
            idList += "'" + id.get(i) + "', ";
        }

        if (id.size() != 0) {
            idList = idList.substring(0, idList.length() - 2) + ")";
            return idList;
        } else
            return null;
    }

    public Result queryPage(String searchString) {
        int total;
		ArrayList<Point> points = null;

        try {
            total = getTotal(searchString);
            String query = formPageQuery(searchString);
            ResultSet resultSet = statement.executeQuery(query);
            points = constructResult(resultSet, total);
        } catch (SQLException e) {
            e.printStackTrace();
        }

		points.trimToSize();
        return new Result(points);
    }

    private ArrayList<Point> constructResult(ResultSet resultSet, int total) throws SQLException {
		ArrayList<Point> points = new ArrayList<Point>();

        int counter = 0;
        String id;
		String type;
		String name;
        String latitude;
        String longitude;
		String elevation;
		String municipality;
		String home_link;
		String wikipedia_link;

        while (resultSet.next() /*&& counter < MAX_QUERY_SIZE todo commented out for now*/ ) {
            id = resultSet.getString("id");
            type = resultSet.getString("type");
            name = resultSet.getString("name");
            latitude = resultSet.getString("latitude");
            longitude = resultSet.getString("longitude");
            elevation = resultSet.getString("elevation");
            municipality = resultSet.getString("municipality");
            home_link = resultSet.getString("home_link");
            wikipedia_link = resultSet.getString("wikipedia_link");

			points.add(new Point(id, type, name, latitude, longitude, elevation, municipality, home_link, wikipedia_link));

            counter++;
        }

        return points;
    }
}
