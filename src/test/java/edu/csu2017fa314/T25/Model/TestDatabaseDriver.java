package edu.csu2017fa314.T25.Model;

import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDatabaseDriver {
    private DatabaseDriver test;

    @Before
    public void setup() throws ClassNotFoundException {
       // test = new DatabaseDriver("mlyonsru", "830721900"); // won't work with travis
    }

    @Test
    public void queryTest(){
     /*
        try {
            ResultSet result = test.query("heli");
            test.printResults(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    */
    }
}
