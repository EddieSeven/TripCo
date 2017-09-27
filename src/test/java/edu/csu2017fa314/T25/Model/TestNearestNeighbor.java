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
        Point a = new Point("37°20'32.9\" N","108°35'10.5\" W");
        a.id = "a";
        Point b = new Point("40°34'18.9\" N","105°07'18.4\" W");
        b.id = "b";
        Point c = new Point("28°43'45.0\" N","81°08'43.3\" W");
        c.id = "c";
        Point d = new Point("60°03'54.7\" N","151°30'34.6\" W");
        d.id = "d";
        Point e = new Point("35°07'53.0\" N","119°10'55.0\" W");
        e.id = "e";
        points.add(a);
        points.add(b);
        points.add(c);
        points.add(d);
        points.add(e);

        testNN = new NearestNeighbor(points);
    }

    @Test
    public void testComputeNearestNeighbor(){

    }

    @Test
    public void testComputePath(){

    }

    @Test
    public void testComputeShortestPath(){
        testNN.computeShortestPath();
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
