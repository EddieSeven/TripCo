package edu.csu2017fa314.T25.Model;

import javax.xml.crypto.Data;
import java.sql.*;

public class DatabaseDriver {
    private String userName;
    private String password;
    private String searchString;
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://faure.cs.colostate.edu/cs314";

    Connection connection;
    Statement statement;

    public DatabaseDriver(String userName, String password, String searchString) throws ClassNotFoundException {
        this.userName = userName;
        this.password = password;
        this.searchString = searchString;
        Class.forName(driver); // todo necessary?

        try {
            startConnection();
        } catch (SQLException e) {
            System.err.printf("Exception: ");
            System.err.printf(e.getMessage());
        }
    }

    private String formQuery() {
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

    private ResultSet queryDatabase(String query) throws SQLException {
        ResultSet result = statement.executeQuery(query);
        return result;
    }
}
