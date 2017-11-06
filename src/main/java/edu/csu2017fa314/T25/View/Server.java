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
	View v = new View();
	boolean updateSVG;
	String svg;
	ArrayList<TripLeg> latestItinerary;

	public Server(DatabaseDriver dbd) {
		g = new Gson();
		dbDriver = dbd;

		updateSVG = false;
//		try {
//			v.getCoordinates();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
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
		if (updateSVG) {
			try {
//				System.out.println("Appending path to SVG: " + latestItinerary);
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

		String attributes[] = new String[5];
		attributes[0] = "aa";
		attributes[1] = "heliport";
		attributes[2] = "Alfred Aytpe Heliport";
		attributes[3] = "38.371";
		attributes[4] = "-107.860";
		points[0] = new Point(attributes);

		attributes = new String[5];
		attributes[0] = "cb";
		attributes[1] = "airport";
		attributes[2] = "Canton Bibel Airport";
		attributes[3] = "39.052";
		attributes[4] = "-105.631";
		points[1] = new Point(attributes);

		attributes = new String[5];
		attributes[0] = "dj";
		attributes[1] = "airport";
		attributes[2] = "Don John Airport";
		attributes[3] = "37.646";
		attributes[4] = "-105.431";
		points[2] = new Point(attributes);

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

		Result result = dbDriver.queryPage(sq.getDescription());

		NearestNeighbor algorithm = new NearestNeighbor(result.points, result.size);
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
