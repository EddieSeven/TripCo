package edu.csu2017fa314.T25.Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Scanner;

public class TestThreeOpt {

	Point a, b, c, d, e, f;
	Path ideal;

	@Before
	public void setup() {
		a = new Point(-1.0, -1.0, "a");
		b = new Point(-1.0, 1.0, "b");
		c = new Point(0.0, 2.0, "c");
		d = new Point(1.0, 1.0, "d");
		e = new Point(1.0, -1.0, "e");
		f = new Point(0.0, -2.0, "f");
		
		Point [] test = {a,b,c,d,e,f};
		ideal = new Path(test, 6);
		ideal.returnHome();
	}
	@Test
	public void testThreeOptCase0() {
		Point [] test = {a,b,c,d,e,f};
		NearestNeighbor nn = new NearestNeighbor(test, 6, false);
		Path route = new Path(test, 6);
		route.returnHome();

		int cost = nn.threeOptDistance(route, 0,1,2,3,4,5);

		int c = nn.threeOptCase(route, cost, 0,2,4);
		assertEquals(0,c);
	}
	@Test
	public void testThreeOpt0() {
		Point [] test = {a,b,c,d,e,f};
		NearestNeighbor nn = new NearestNeighbor(test, 6, false);
		Path route = new Path(test, 6);
		route.returnHome();

		nn.threeOpt(route);
		assertTrue(route.equals(ideal));
	}

	@Test
	public void testThreeOptCase1() {
		Point [] test = {a,c,b,d,e,f};
		NearestNeighbor nn = new NearestNeighbor(test, 6, false);
		Path route = new Path(test, 6);
		route.returnHome();

		int cost = nn.threeOptDistance(route, 0,1,2,3,4,5);

		int c = nn.threeOptCase(route, cost, 0,2,4);
		assertEquals(1,c);
	}
	@Test
	public void testThreeOpt1() {
		Point [] test = {a,c,b,d,e,f};
		NearestNeighbor nn = new NearestNeighbor(test, 6, false);
		Path route = new Path(test, 6);
		route.returnHome();

		nn.threeOpt(route);
		assertTrue(route.equals(ideal));
	}
	@Test
	public void testThreeOptCase2() {
		Point [] test = {a,b,c,e,d,f};
		NearestNeighbor nn = new NearestNeighbor(test, 6, false);
		Path route = new Path(test, 6);
		route.returnHome();

		int cost = nn.threeOptDistance(route, 0,1,2,3,4,5);

		int c = nn.threeOptCase(route, cost, 0,2,4);
		assertEquals(2, c);
	}
	@Test
	public void testThreeOpt2() {
		Point [] test = {a,b,c,e,d,f};
		NearestNeighbor nn = new NearestNeighbor(test, 6, false);
		Path route = new Path(test, 6);
		route.returnHome();

		nn.threeOpt(route);
		assertTrue(route.equals(ideal));
	}
	@Test
	public void testThreeOptCase3() {
		Point [] test = {a,e,d,c,b,f};
		NearestNeighbor nn = new NearestNeighbor(test, 6, false);
		Path route = new Path(test, 6);
		route.returnHome();

		int cost = nn.threeOptDistance(route, 0,1,2,3,4,5);
		
		int c = nn.threeOptCase(route, cost, 0,2,4);
		assertEquals(3,c);
	}
	@Test
	public void testThreeOpt3() {
		Point [] test = {a,e,d,c,b,f};
		NearestNeighbor nn = new NearestNeighbor(test, 6, false);
		Path route = new Path(test, 6);
		route.returnHome();

		nn.threeOpt(route);
		assertTrue(route.equals(ideal));
	}

	@Test
	public void testThreeOptCase4() {
		Point [] test = {a,c,b,e,d,f};
		NearestNeighbor nn = new NearestNeighbor(test, 6, false);
		Path route = new Path(test, 6);
		route.returnHome();

		int cost = nn.threeOptDistance(route, 0,1,2,3,4,5);
		
		int c = nn.threeOptCase(route, cost, 0,2,4);
		assertEquals(4, c);
	}
	@Test
	public void testThreeOpt4() {
		Point [] test = {a,c,b,e,d,f};
		NearestNeighbor nn = new NearestNeighbor(test, 6, false);
		Path route = new Path(test, 6);
		route.returnHome();

		nn.threeOpt(route);
		assertTrue(route.equals(ideal));
	}
	@Test
	public void testThreeOptCase5() {
		Point [] test = {a,d,e,c,b,f};
		NearestNeighbor nn = new NearestNeighbor(test, 6, false);
		Path route = new Path(test, 6);
		route.returnHome();

		int cost = nn.threeOptDistance(route, 0,1,2,3,4,5);

		int c = nn.threeOptCase(route, cost, 0,2,4);
		assertEquals(5, c);
	}
	@Test
	public void testThreeOpt5() {
		Point [] test = {a,d,e,c,b,f};
		NearestNeighbor nn = new NearestNeighbor(test, 6, false);
		Path route = new Path(test, 6);
		route.returnHome();

		nn.threeOpt(route);
		assertTrue(route.equals(ideal));
	}
	@Test
	public void testThreeOptCase6() {
		Point [] test = {a,e,d,b,c,f};
		NearestNeighbor nn = new NearestNeighbor(test, 6, false);
		Path route = new Path(test, 6);
		route.returnHome();

		int cost = nn.threeOptDistance(route, 0,1,2,3,4,5);

		int c = nn.threeOptCase(route, cost, 0,2,4);
		assertEquals(6,c);
	}
	@Test
	public void testThreeOpt6() {
		Point [] test = {a,e,d,b,c,f};
		NearestNeighbor nn = new NearestNeighbor(test, 6, false);
		Path route = new Path(test, 6);
		route.returnHome();

		nn.threeOpt(route);
		assertTrue(route.equals(ideal));
	}
	@Test
	public void testThreeOptCase7() {
		Point [] test = {a,d,e,b,c,f};
		NearestNeighbor nn = new NearestNeighbor(test, 6, false);
		Path route = new Path(test, 6);
		route.returnHome();

		int cost = nn.threeOptDistance(route, 0,1,2,3,4,5);

		int c = nn.threeOptCase(route, cost, 0,2,4);
		assertEquals(7,c);
	}
	@Test
	public void testThreeOpt7() {
		Point [] test = {a,d,e,b,c,f};
		NearestNeighbor nn = new NearestNeighbor(test, 6, false);
		Path route = new Path(test, 6);
		route.returnHome();

		nn.threeOpt(route);
		assertTrue(route.equals(ideal));
	}

	@Test
	public void threeOptBenchMark() throws Exception {
		Point [] test = readCSV();
		NearestNeighbor nn = new NearestNeighbor(test, test.length, false);
		System.out.println("Starting 3-opt");
		long start = System.currentTimeMillis();
		Path p = nn.computeShortestPath(3);
		System.out.println("3-OPT Time: " + (System.currentTimeMillis()-start));
	}

	public Point[] readCSV() throws Exception{
		Point [] pts = new Point[294];
		int i = 0;
		
		Scanner s = new Scanner(new File("data/airports.csv"));
		s.useDelimiter("\n");
		s.next();
		while (s.hasNext()) {
			String [] tokens = s.next().split(",");
			double lat = Double.parseDouble(tokens[3]);
			double lon = Double.parseDouble(tokens[4]);
			pts[i] = new Point(lat, lon, tokens[0]);
			i++;
		}
		return pts;
	}
}
