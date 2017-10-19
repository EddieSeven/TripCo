package edu.csu2017fa314.T25.View;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.util.ArrayList;

import edu.csu2017fa314.T25.Model.NearestNeighbor;
import edu.csu2017fa314.T25.Model.Result;
import edu.csu2017fa314.T25.Model.TripLeg;
import edu.csu2017fa314.T25.Model.DatabaseDriver;
import edu.csu2017fa314.T25.Model.Point;

import spark.Request;
import spark.Response;
import static spark.Spark.post;
import static spark.Spark.get;

public class Server {
	Gson g;
	DatabaseDriver dbDriver;
	boolean updateSVG;
	String svg;
	ArrayList<TripLeg> latestItinerary;

	public Server(DatabaseDriver dbd) {
		g = new Gson();
		dbDriver = dbd;

		updateSVG = false;
		View v = new View();
		try {
			v.getCoordinates();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		svg = v.getSVG();
		latestItinerary = null;
	}

	public void serve() {
		post("/search", this::serveSearch, g::toJson);
		get("/svg", this::serveSVG);
	}
	public void serveTest() {
		post("/search", this::serveSearchTest, g::toJson);
		get("/svg", this::serveSVG);
	}

	private Object serveSVG(Request rec, Response resp) {
		View v = new View();
		if (updateSVG) {
			try {
				System.out.println("Appending path to SVG: " + latestItinerary);
				svg = v.insertSVG(latestItinerary);
			} catch (Exception e) {
				e.printStackTrace();
			}
			updateSVG = false;
		}
		
		return svg;
	}

	// This is meant for testing to avoid having to connect to the database
	private Object serveSearchTest(Request rec, Response resp) {
		setHeaders(resp);
		
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(rec.body());
	
		ServerRequest sq = g.fromJson(je, ServerRequest.class);
		System.out.println("Querying for " + sq.getDescription());

		Point[] points = new Point[3];
		points[0] = new Point("0", "airport", "p1", "38.371", "-107.860", "1000", "munic1", "no home link", "no wiki link");
		points[1] = new Point("1", "heliport", "p2", "39.052", "-105.631", "2000", "munic2", "no home link", "no wiki link");
		points[2] = new Point("2", "heliport", "p3", "37.646", "-105.431", "2000", "munic3", "no home link", "no wiki link");
		NearestNeighbor algorithm = new NearestNeighbor(points, points.length);
		ArrayList<TripLeg> legs = algorithm.computeShortestPath().getLegs();

		updateSVG = true;
		latestItinerary = legs;
		
		// Get itinerary from database
		return g.toJson(legs, ArrayList.class);


	}
	private Object serveSearch(Request rec, Response resp) {
		setHeaders(resp);
		
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(rec.body());
	
		ServerRequest sq = g.fromJson(je, ServerRequest.class);
		System.out.println("Querying for " + sq.getDescription());

		Result r = dbDriver.queryPage(sq.getDescription());
		Point[] points = new Point[r.points.size()];
		points = r.points.toArray(points);
		NearestNeighbor algorithm = new NearestNeighbor(points, points.length);
		ArrayList<TripLeg> legs = algorithm.computeShortestPath().getLegs();

		updateSVG = true;
		latestItinerary = legs;
		
		// Get itinerary from database
		return g.toJson(legs, ArrayList.class);

	}

	private void setHeaders(Response resp) {
		resp.header("Content-Type", "application/json");
		
		resp.header("Access-Control-Allow-Origin", "*");
		resp.header("Access-Control-Allow-Headers", "*");
	}

	private void setImageHeaders(Response resp) {
		resp.header("Content-Type", "image/svg+xml");
		
		resp.header("Access-Control-Allow-Origin", "*");
		resp.header("Access-Control-Allow-Headers", "*");
	}
}

//class SearchQuery {
//	private String query;
//	public SearchQuery(String q) {
//		query = q;
//	}
//
//	public String getQuery() {
//		return query;
//	}
//	public void setQuery(String q) {
//		query = q;
//	}
//}
