package edu.csu2017fa314.T25.Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class TestDatabaseDriver {
    private DatabaseDriver test;
    private String isTravis;
    final private boolean atMichaels = false; // todo SET TO FALSE WHEN DONE WITH LOCAL TESTING

    @Before
    public void setup() throws ClassNotFoundException {
        isTravis = System.getenv("TRAVIS");

        if (isTravis != null) {
            test = new DatabaseDriver("travis", "", "jdbc:mysql://localhost/TripCoTestDB");
        } else if (atMichaels) {
                test = new DatabaseDriver("mlyonsru", "830721900", "jdbc:mysql://localhost:8080/cs314?useLegacyDatetimeCode=false&serverTimezone=UTC");
        }
    }

    @Test
    public void queryPageTest() {
        if (isTravis != null) {
            // Test 1 - Cass Field
            Result result = test.queryPage("Urb");
            assertEquals(true, result.points[0].attributes[0].equals("UB"));

            // Test 2 - Buckley Air
            result = test.queryPage("thir");
            assertEquals(true, result.points[0].attributes[0].equals("TL"));

            // Test 3 - Mc Cullough
            result = test.queryPage("mor");
            assertEquals(true, result.points[0].attributes[0].equals("NN"));

        } else if (atMichaels) {
            Result result = test.queryPage("london");
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
