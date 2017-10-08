package edu.csu2017fa314.T25.View;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.util.ArrayList;

import edu.csu2017fa314.T25.Model.Model;
import edu.csu2017fa314.T25.Model.TripLeg;

import spark.Request;
import spark.Response;
import static spark.Spark.post;
import static spark.Spark.port;

public class Server {
	static Gson g;
	public static void serve(int sPort) {
		g = new Gson();
		port(sPort);
		post("/search", Server::serveSearch, g::toJson);
	}

	private static Object serveSearch(Request rec, Response resp) {
		setHeaders(resp);
		
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(rec.body());
	
		SearchQuery sq = g.fromJson(je, SearchQuery.class);
		System.out.println("Querying for " + sq.getQuery());
		ArrayList<TripLeg> itinerary = new ArrayList<>();

		// Get itinerary from database
		return g.toJson(itinerary, ArrayList.class);

	}

	private static void setHeaders(Response resp) {
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
