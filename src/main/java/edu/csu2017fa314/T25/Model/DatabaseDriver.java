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
        String query = "SELECT * FROM airports WHERE type LIKE '%" +
                searchString + "%' OR name LIKE '%" +
                searchString + "%' OR municipality LIKE '%" +
                searchString + "%' LIMIT " + MAX_QUERY_SIZE + ";";

        return query;
    }

    private String formAlgorithmQuery(String idList) {
        String query = "SELECT id, latitude, longitude FROM airports WHERE id IN " + idList + ";";

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

    public Result queryAlgorithm(ArrayList<String> id){
        String idList = toList(id);
        Result result = null;

        String query = formAlgorithmQuery(idList);
        try {
            if (idList != null) {
                ResultSet resultSet = statement.executeQuery(query);
                result = constructResult(resultSet, id.size());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
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
        Result result = null;

        try {
            total = getTotal(searchString);
            String query = formPageQuery(searchString);
            ResultSet resultSet = statement.executeQuery(query);
            result = constructResult(resultSet, total);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private Result constructResult(ResultSet resultSet, int total) throws SQLException {
        String stringArray[][] = new String[MAX_QUERY_SIZE][3];

        int counter = 0;
        String id;
        String latitude;
        String longitude;

        while (resultSet.next() && counter < 50) {
            id = resultSet.getString("id");
            latitude = resultSet.getString("latitude");
            longitude = resultSet.getString("longitude");

            stringArray[counter][0] = id;
            stringArray[counter][1] = latitude;
            stringArray[counter][2] = longitude;

            counter++;
        }

        Result result = new Result(stringArray, total);

        return result;
    }
}
