package edu.csu2017fa314.T25.Model;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestDatabaseDriver {
    private DatabaseDriver test;


    @Before
    public void setup() throws ClassNotFoundException {
        String isTravis = System.getenv("TRAVIS");

        if (isTravis != null) {
            test = new DatabaseDriver("travis", "", "jdbc:mysql://localhost/TripCoTestDB");
        }
    }

    @Test
    public void queryPageTest() {
        String isTravis = System.getenv("TRAVIS");

        if (isTravis != null) {

            // Test 1 - Cass Field
            Result result = test.queryPage("cass");
            assertEquals(true, result.points.get(0).id.equals("00555CO")); // todo remove 555
            

            // Test 2 - Buckley Air
            result = test.queryPage("buckley");
            assertEquals(true, result.points.get(0).id.equals("KBKF"));

            // Test 3 - Mc Cullough
            result = test.queryPage("mc cullough");
            assertEquals(true, result.points.get(0).id.equals("02CO"));
        }
    }

    @Test
    public void queryAlgorithmTest() { // todo no currently being used
        /*
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
        */
    }
}
