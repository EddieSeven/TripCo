package edu.csu2017fa314.T25.Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TestNearestNeighbor {
    private NearestNeighbor testNN;

    @Before
    public void setup() {
        Point points[] = new Point[5];
        Point a = new Point(37.34247, -108.58625, "a");
        Point b = new Point(40.57191, -105.121777,"b");
        Point c = new Point(28.729166, -81.14536, "c");
        Point d = new Point(60.06519, -151.509611, "d");
        Point e = new Point(35.131388, -119.181944, "e");
        points[0] = a;
        points[1] = b;
        points[2] = c;
        points[3] = d;
        points[4] = e;


        testNN = new NearestNeighbor(points, 5, true);
    }

    @Test
    public void testComputeNearestNeighbor1() {
        Path path = new Path(5);
        boolean visited[] = new boolean[5];

        assertEquals(0, testNN.computeNearestNeighbor(1, path, visited));
    }

    @Test
    public void testComputeNearestNeighbor2() {
        Path path = new Path(5);
        boolean visited[] = new boolean[5];

        assertEquals(4, testNN.computeNearestNeighbor(3, path, visited));
    }

    @Test
    public void testComputeNearestNeighbor3() {
        Path path = new Path(5);
        boolean visited[] = new boolean[5];

        assertEquals(0, testNN.computeNearestNeighbor(4, path, visited));
    }

    @Test
    public void testComputeNearestNeighbor4() {
        Path path = new Path(5);
        boolean visited[] = new boolean[5];

        assertEquals(1, testNN.computeNearestNeighbor(2, path, visited));
    }


    @Test
    public void testComputePath1() {
        Point a = new Point(37.34247, -108.58625, "a");
        Point b = new Point(40.57191, -105.121777, "b");
        Point c = new Point(28.729166, -81.14536, "c");
        Point d = new Point(60.06519, -151.509611, "d");
        Point e = new Point(35.131388, -119.181944, "e");

        Path expectedPath = new Path(5);
        Path actualPath = testNN.computeNNPath(1);
        expectedPath.addPoint(b);
        expectedPath.addPoint(a);
        expectedPath.addPoint(e);
        expectedPath.addPoint(d);
        expectedPath.addPoint(c);
        expectedPath.addPoint(b);

        for (int i = 0; i < actualPath.size(); i++) {
            assertEquals(expectedPath.getPoint(i).attributes[0], actualPath.getPoint(i).attributes[0]);
        }
    }

    @Test
    public void testComputePath2() {
        Path expectedPath = new Path(5);
        Path actualPath = testNN.computeNNPath(3);

        Point a = new Point(37.34247, -108.58625, "a");
        Point b = new Point(40.57191, -105.121777, "b");
        Point c = new Point(28.729166, -81.14536, "c");
        Point d = new Point(60.06519, -151.509611, "d");
        Point e = new Point(35.131388, -119.181944, "e");

        expectedPath.addPoint(d);
        expectedPath.addPoint(e);
        expectedPath.addPoint(a);
        expectedPath.addPoint(b);
        expectedPath.addPoint(c);
        expectedPath.addPoint(d);

        for (int i = 0; i < actualPath.size(); i++) {
            assertEquals(expectedPath.getPoint(i).attributes[0], actualPath.getPoint(i).attributes[0]);
        }
    }


    @Test
	public void testComputeDistance1() {
        Point a1 = new Point(37.33914,-108.58291, "a");
        Point a2 = new Point(40.57191, -105.12177, "b");
        assertEquals(291, NearestNeighbor.computeDistance(a1, a2, true));
    }

    @Test
    public void testComputeDistance2() {
        Point b1 = new Point(28.729166,-81.14536, "a");
        Point b2 = new Point(60.06519, -151.509611, "b");
        assertEquals(3848, NearestNeighbor.computeDistance(b1, b2, true));
    }

    @Test
    public void testComputeDistance3() {
        Point c1 = new Point(35.131388, -119.181944, "a");
        Point c2 = new Point(45.23411, -67.92664, "b");
        assertEquals(2745, NearestNeighbor.computeDistance(c1, c2, true));
    }

    @Test
    public void testComputeDistance4(){
        Point c1 = new Point(56.041813, -87.514592, "a");
        Point c2 = new Point(43.644831, 78.951198, "b");
        assertEquals(5504, NearestNeighbor.computeDistance(c1, c2, true));
    }

    @Test
    public void testComputeDistance5(){
        Point c1 = new Point(43.761920, 11.315691, "a");
        Point c2 = new Point(-31.429020, -64.184464, "b");
        assertEquals(7041, NearestNeighbor.computeDistance(c1, c2, true));
    }

    @Test
    public void testComputeDistance6(){
        Point c1 = new Point(37.34913889, 108.5964722, "a");
        Point c2 = new Point(37.35302778, 108.5490278, "b");
        assertEquals(3, NearestNeighbor.computeDistance(c1, c2, true));
    }



	@Test
	public void test2Opt() {
		Point a = new Point(0.0, 0.0, "a");
		Point b = new Point(0.0, 1.0, "b");
		Point c = new Point(1.0, 1.0, "c");
		Point d = new Point(1.0, 0.0, "d");
		Point [] pts = {a, b, c, d};
		NearestNeighbor nn = new NearestNeighbor(pts, 4, true);
		Path test = new Path(4);

		test.addPoint(a);
		test.addPoint(c);
		test.addPoint(b);
		test.addPoint(d);
		test.addPoint(a);

		nn.twoOpt(test);
		assertEquals(276, test.getCost());
		
	}
}
