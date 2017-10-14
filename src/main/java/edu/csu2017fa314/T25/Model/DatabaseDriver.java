package edu.csu2017fa314.T25.Model;

import java.sql.*;

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

    private String formQuery(String searchString) {
        String query = "SELECT * FROM airports WHERE type LIKE '%" +
                searchString + "%' OR name LIKE '%" +
                searchString + "%' OR municipality LIKE '%" +
                searchString + "%' LIMIT " + MAX_QUERY_SIZE + ";";

        return query;
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


        return total;
    }

    private void startConnection() throws SQLException {
        connection = DriverManager.getConnection(url, userName, password);
        statement = connection.createStatement();
    }

    public ArrayList<Point> query(String searchString) {
        int total;
		ArrayList<Point> points;

        try {
            total = getTotal(searchString);
            String query = formQuery(searchString);
            ResultSet resultSet = statement.executeQuery(query);
            points = constructResult(resultSet, total);
        } catch (SQLException e) {
            e.printStackTrace();
        }

		points.trimToSize();
        return points;
    }

    private ArrayList<Point> constructResult(ResultSet resultSet, int total) throws SQLException {
		ArrayList<Point> points;

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

        while (resultSet.next() && counter < MAX_QUERY_SIZE) {
            id = resultSet.getString("id");
            type = resultSet.getString("type");
            name = resultSet.getString("name");
            latitude = resultSet.getString("latitude");
            longitude = resultSet.getString("longitude");
            elevation = resultSet.getString("elevation");
            municipality = resultSet.getString("municipality");
            home_link = resultSet.getString("home_link");
            wikipedia_link = resultSet.getString("wikipedia_link");

			points.add(new Point(id, type, name, latitude, longitude, elevation, municipality, home_link, wikipedia_link);

            counter++;
        }

        return points;
    }
}

class Result {
    String result[][];
    int total;

    public Result(String result[][], int total) {
        this.result = result;
        this.total = total;
    }
}
