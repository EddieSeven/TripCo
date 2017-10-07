package edu.csu2017fa314.T25.Model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class TestNearestNeighbor {
    private NearestNeighbor testNN;

    @Before
    public void setup(){
        Point points[] = new Point[5];
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
        points[0] = a;
        points[1] = b;
        points[2] = c;
        points[3] = d;
        points[4] = e;


        testNN = new NearestNeighbor(points, 5);
    }

    @Test
    public void testComputeNearestNeighbor1(){
        // Checks that given a point, its nearest neighbor is returned.
        Path dummy0 = new Path();
        boolean visited[] = new boolean[5];

        assertEquals(0, testNN.computeNearestNeighbor(1, dummy0, visited));

        assertEquals(4, testNN.computeNearestNeighbor(3, dummy0, visited));
        assertEquals(0, testNN.computeNearestNeighbor(4, dummy0, visited));
        assertEquals(1, testNN.computeNearestNeighbor(2, dummy0, visited));
    }

    @Test
    public void testComputePath(){
        // Given a small set of five point, checks that from a given starting point the path matches the expected path
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

        // Test 1
        Path expectedPath = new Path();
        Path actualPath = testNN.computePath(1);
        expectedPath.add(b);
        expectedPath.add(a);
        expectedPath.add(e);
        expectedPath.add(d);
        expectedPath.add(c);
        expectedPath.add(b);

        for (int i = 0; i < actualPath.size(); i++){
            assertEquals(expectedPath.getPoint(i).id, actualPath.getPoint(i).id);
        }

        // Test 2
        expectedPath = new Path();
        actualPath = testNN.computePath(3);
        expectedPath.add(d);
        expectedPath.add(e);
        expectedPath.add(a);
        expectedPath.add(b);
        expectedPath.add(c);
        expectedPath.add(d);

        for (int i = 0; i < actualPath.size(); i++){
            assertEquals(expectedPath.getPoint(i).id, actualPath.getPoint(i).id);
        }
    }

    @Test
    public void testComputeShortestPath(){
    }
}
