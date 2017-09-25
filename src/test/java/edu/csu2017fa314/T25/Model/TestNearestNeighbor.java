package edu.csu2017fa314.T25.Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestNearestNeighbor {
    private NearestNeighbor testNN;

    @Before
    public void setup(){
        ArrayList<Point> points = new ArrayList<>();
        Point a = new Point("37°20'56.9\" N","108°35'47.3\" W");
        Point b = new Point("37°21'10.9\" N","108°32'56.5\" W");
        Point c = new Point("19°35'35.9\" N","99°06'58.2\" W");
        Point d = new Point("11°44'34.4\" N","70°13'35.0\" W");
        points.add(a);
        points.add(b);
        points.add(c);
        points.add(d);

        testNN = new NearestNeighbor(points, 4);
    }

    @Test
    public void testComputeNearestNeighbor(){

    }

    @Test
    public void testComputePath(){

    }

    public void testComputeShortestPath(){

    }

    @Test
    public void testGetDistance(){

    }

    @Test
    public void testWrite(){
        DistanceMap testDM = new DistanceMap();
        Point a1 = new Point("37°20'32.9\" N","108°35'10.5\" W");
        Point a2 = new Point("40°34'18.9\" N", "105°07'18.4\" W");
        Point b1 = new Point("28°43'45.0\" N","81°08'43.3\" W");
        Point b2 = new Point("60°03'54.7\" N", "151°30'34.6\" W");

        testDM.write(a1, a2, 345);
        assertEquals( 345, testDM.getDistance(a1, a2));

        assertEquals(3848, testDM.getDistance(b1, b2));

    }
}
