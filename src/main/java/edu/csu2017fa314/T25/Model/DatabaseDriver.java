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

    private void startConnection() throws SQLException {
        connection = DriverManager.getConnection(url, userName, password);
        statement = connection.createStatement();
    }

    public ResultSet query(String searchString) throws SQLException {
        String query = formQuery(searchString);
        ResultSet result = statement.executeQuery(query);
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
