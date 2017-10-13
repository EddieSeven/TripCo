package edu.csu2017fa314.T25.Model;

import org.junit.Before;
import org.junit.Test;

public class TestDatabaseDriver {
    private DatabaseDriver test;

    @Before
    public void setup() throws ClassNotFoundException {
        //test = new DatabaseDriver("mlyonsru", "830721900"); // won't work with travis
    }

    @Test
    public void queryTest(){
        // Result result = test.query("heli");
    }
}
