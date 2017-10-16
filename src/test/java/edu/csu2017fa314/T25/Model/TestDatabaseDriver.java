package edu.csu2017fa314.T25.Model;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestDatabaseDriver {
    private DatabaseDriver test;

    @Before
    public void setup() throws ClassNotFoundException {
           test = new DatabaseDriver("mlyonsru", "830721900", "jdbc:mysql://localhost:8080/cs314?useLegacyDatetimeCode=false&serverTimezone=UTC"); // todo Doesn't work with travis
    }

    @Test
    public void queryPageTest() {
        // Test 1 - Swedish Hospitals
        Result result = test.queryPage("swe");
        assertEquals(true,         result.result[0][0].equals("0CD9"));
        assertEquals(true,         result.result[1][0].equals("15CO"));

        // Test 2 - Limit
        result = test.queryPage("a");
        assertEquals(true, result.result[0][0].equals("KBJC"));
        assertEquals(true, result.result[49][0].equals("1CO2"));

        // Test 3 - Denver
        result = test.queryPage("denver");
        assertEquals(true, result.result[0][0].equals("KBJC"));
        assertEquals(true, result.result[7][0].equals("9CO0"));
        assertEquals(true, result.result[25][0].equals("US-0073"));
    }

    @Test
    public void queryAlgorithmTest() {
        // Test 1 - Small Input Size
        ArrayList<String> idList = new ArrayList<>();
        idList.add("0CD9");
        idList.add("15CO");
        idList.add("KTAD");
        idList.add("KTEX");
        Result result = test.queryAlgorithm(idList);
        assertEquals(true, result.result[0][0].equals("0CD9"));
        assertEquals(true, result.result[1][0].equals("15CO"));
        assertEquals(true, result.result[2][0].equals("KTAD"));
        assertEquals(true, result.result[3][0].equals("KTEX"));

        // Test 2 - No input
        idList = new ArrayList<>();
        result = test.queryAlgorithm(idList);
        assertEquals(null, result);
    }
}
