package edu.csu2017fa314.T25.Model;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestDatabaseDriver {
    private DatabaseDriver test;


    @Before
    public void setup() throws ClassNotFoundException {
        test = new DatabaseDriver("travis", "", "jdbc:mysql://localhost/TripCoTestDB");
    }

    @Test
    public void queryPageTest() {
        // Test 1 - Swedish Hospitals
        Result result = test.queryPage("swe");
        assertEquals(true, result.points.get(0).id.equals("0CD9"));
        assertEquals(true, result.points.get(1).id.equals("15CO"));

        // Test 2 - Limit
        result = test.queryPage("a");
        assertEquals(true, result.points.get(0).id.equals("KBJC"));
        assertEquals(true, result.points.get(49).id.equals("1CO2"));

        // Test 3 - Denver
        result = test.queryPage("denver");
        assertEquals(true, result.points.get(0).id.equals("KBJC"));
        assertEquals(true, result.points.get(7).id.equals("9CO0"));
        assertEquals(true, result.points.get(25).id.equals("US-0073"));
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
        assertEquals(true, result.points.get(0).id.equals("0CD9"));
        assertEquals(true, result.points.get(1).id.equals("15CO"));
        assertEquals(true, result.points.get(2).id.equals("KTAD"));
        assertEquals(true, result.points.get(3).id.equals("KTEX"));

        // Test 2 - No input
        idList = new ArrayList<>();
        result = test.queryAlgorithm(idList);
        assertEquals(null, result);
    }
}
