package edu.csu2017fa314.T25.Model;

import static org.junit.Assert.*;

import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;

public class TestNearestNeighbor {
    private NearestNeighbor testNN;
    private DistanceMap testDM;

    @Before
    public void setup(){
        testNN = new NearestNeighbor();
        testDM = new DistanceMap();
    }

    @Test
    public void testComputeNearestNeighbor(){

    }

    @Test
    public void testComputePath(){

    }

    @Test
    public void testGetDistance(){

    }

    @Test
    public void testWrite(){
        Node a = new Node("a", 32.0, 42.0);
        Node b = new Node("b", 44.3, 62.0);
        Pair ab = new Pair(a, b);
        testDM.write(ab, 300);
        assertEquals(testDM.getDistance(a, b), 300.0, 0);

    }
}
