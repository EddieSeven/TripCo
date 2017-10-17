package edu.csu2017fa314.T25.Model;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class TestDatabaseDriver {
    private DatabaseDriver test;

    @Before
    public void setup() throws ClassNotFoundException {
           test = new DatabaseDriver("travis", "", "jdbc:mysql://TripCoTestDB"); // todo Doesn't work with travis
    }

    @Test
    public void queryTest() {
        // Test 1 - Swedish Hospitals
        Result result = test.query("swe");
        assertEquals(true,         result.result[0][0].equals("0CD9"));
        assertEquals(true,         result.result[1][0].equals("15CO"));

        // Test 2 - Limit
        result = test.query("a");
        assertEquals(true, result.result[0][0].equals("KBJC"));
        assertEquals(true, result.result[49][0].equals("1CO2"));

        // Test 3 - Denver
        result = test.query("denver");
        assertEquals(true, result.result[0][0].equals("KBJC"));
        assertEquals(true, result.result[7][0].equals("9CO0"));
        assertEquals(true, result.result[25][0].equals("US-0073"));
    }
}
