package edu.csu2017fa314.T25.View;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.util.ArrayList;

import edu.csu2017fa314.T25.Model.NearestNeighbor;
import edu.csu2017fa314.T25.Model.TripLeg;
import edu.csu2017fa314.T25.Model.DatabaseDriver;
import edu.csu2017fa314.T25.Model.Point;

import spark.Request;
import spark.Response;
import static spark.Spark.post;
import static spark.Spark.port;

public class Server {
	Gson g;
	DatabaseDriver dbDriver;

	public Server(DatabaseDriver dbd) {
		g = new Gson();
		dbDriver = dbd;
	}
	public void serve() {
		post("/search", this::serveSearch, g::toJson);
	}

	private Object serveSearch(Request rec, Response resp) {
		setHeaders(resp);
		
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(rec.body());
	
		SearchQuery sq = g.fromJson(je, SearchQuery.class);
		System.out.println("Querying for " + sq.getQuery());

		ArrayList<Point> pts = dbDriver.query(sq.getQuery());
		Point[] points = new Point[pts.size()];
		points = pts.toArray(points);
		NearestNeighbor algorithm = new NearestNeighbor(points, points.length);
		ArrayList<TripLeg> legs = algorithm.computeShortestPath().getLegs();
		
		// Get itinerary from database
		return g.toJson(legs, ArrayList.class);

	}

	private void setHeaders(Response resp) {
		resp.header("Content-Type", "application/json");
		
		resp.header("Access-Control-Allow-Origin", "*");
		resp.header("Access-Control-Allow-Headers", "*");
	}
}

class SearchQuery {
	private String query;
	public SearchQuery(String q) {
		query = q;
	}

	public String getQuery() {
		return query;
	}
	public void setQuery(String q) {
		query = q;
	}
}
