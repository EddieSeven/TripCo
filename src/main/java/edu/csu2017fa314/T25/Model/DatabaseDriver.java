package edu.csu2017fa314.T25.Model;

import java.sql.*;

public class DatabaseDriver {
    private String userName;
    private String password;
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:8080/cs314?useLegacyDatetimeCode=false&serverTimezone=UTC"; // todo for my (michael) testing purposes right now.


    Connection connection;
    Statement statement;

    public DatabaseDriver(String userName, String password) throws ClassNotFoundException {
        this.userName = userName;
        this.password = password;
        Class.forName(driver); // todo necessary?


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
                searchString + "%';";

        return query;
    }

    private int getTotal(String searchString) throws SQLException {
        String query = "SELECT COUNT(*) FROM airports WHERE type LIKE '%" +
                searchString + "%' OR name LIKE '%" +
                searchString + "%' OR municipality LIKE '%" +
                searchString + "%';";

        ResultSet result = statement.executeQuery(query);

        int total = 0;
        result.next();
        total = result.getInt(1);


        return total;
    }

    private void startConnection() throws SQLException {
        connection = DriverManager.getConnection(url, userName, password);
        statement = connection.createStatement();
    }

    public Result query(String searchString)  {
        int total = 0;
        Result result = null;

        try {
            total = getTotal(searchString);
            String query = formQuery(searchString);
            ResultSet resultSet = statement.executeQuery(query);
            result = constructResult(resultSet, total);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private Result constructResult(ResultSet resultSet, int total) throws SQLException {
        String stringArray[][] = new String[50][3]; // todo 50 is limit on number of returned queries allowed

        int counter = 0;
        String id;
        String latitude;
        String longitude;

        while (resultSet.next() && counter < 50){
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

    public void printResults(ResultSet result) throws SQLException { //todo debug, possibly delete
        String id;
        String name;

        while (result.next()){
            id = result.getString("id");
            name = result.getString("name");

            System.out.printf("%s, %s\n", id, name);
        }
    }
}

class Result {
    String result[][];
    int total;

    public Result(String result[][], int total){
        this.result = result;
        this.total = total;
    }
}
