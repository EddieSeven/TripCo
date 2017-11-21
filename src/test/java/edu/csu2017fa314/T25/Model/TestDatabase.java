package edu.csu2017fa314.T25.Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class TestDatabase {
    private Database test;
    private boolean isTravis = false;
    final private boolean atMichaels = false; // todo SET TO FALSE WHEN DONE WITH LOCAL TESTING

    @Before
    public void setup() throws ClassNotFoundException {
        if (System.getenv("TRAVIS") != null)
            isTravis = true;

        if (isTravis) {
            test = new Database("travis", "", "jdbc:mysql://localhost/TripCoTestDB");
        } else if (atMichaels) {
                test = new Database("mlyonsru", "830721900", "jdbc:mysql://localhost:8080/cs314?useLegacyDatetimeCode=false&serverTimezone=UTC");
        }
    }

    @Test
    public void queryPageTest1() {
        if (isTravis) {
            Result result = test.queryPage("Urb");
            assertEquals(true, result.points[0].attributes[2].equals("Urbino"));
        } else if (atMichaels) {
            Result result = test.queryPage("london");
        }
    }

    @Test
    public void queryPageTest2() {
        if (isTravis) {
            Result result = test.queryPage("thir");
            assertEquals(true, result.points[0].attributes[2].equals("Thirfle"));

        }
    }

    @Test
    public void queryPageTest3() {
        if (isTravis) {
            Result result = test.queryPage("mor");
            assertEquals(true, result.points[0].attributes[2].equals("None"));

        }
    }

    @Test
    public void queryAlgorithmTest1() {
        if (isTravis) {
            ArrayList<String> idList = new ArrayList<>();
            idList.add("TL");
            idList.add("NN");
            idList.add("UB");
            Result result = test.queryAlgorithm(idList);
            assertEquals(true, result.points[0].attributes[0].equals("TL"));
            assertEquals(true, result.points[1].attributes[0].equals("NN"));
            assertEquals(true, result.points[2].attributes[0].equals("UB"));
        }
    }

    @Test
    public void queryAlgorithmTest2() {
        if (isTravis) {
            ArrayList<String> idList = new ArrayList<>();
            Result result = test.queryAlgorithm(idList);
            assertEquals(0, result.size);
        }
    }
}
