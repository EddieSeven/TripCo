package edu.csu2017fa314.T25.View;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import edu.csu2017fa314.T25.Model.NearestNeighbor;
import edu.csu2017fa314.T25.Model.Result;
import edu.csu2017fa314.T25.Model.TripLeg;
import edu.csu2017fa314.T25.Model.Database;
import spark.Request;
import spark.Response;
import static spark.Spark.post;
import static spark.Spark.port;

public class Server {
    private Gson gson;
    private Database database;
    private View view = new View();
    private String svg;

    public Server(Database database) {
        gson = new Gson();
        this.database = database;
        svg = view.getSVG();
    }

    public void serve() {
        port(2530);
        post("/search", this::serveSearch, gson::toJson);
    }

    public String getSvg() {
        return svg;
    }

    private Object serveSearch(Request request, Response response) {
        setHeaders(response);

        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(request.body());

        ServerRequest serverRequest = gson.fromJson(jsonElement, ServerRequest.class);
        System.out.println("Querying for " + serverRequest.getDescription());

        ServerResponse serverResponse;
        String reqType = serverRequest.getRequest();
        if (reqType.equals("select")) {
            serverResponse = handleSelectionQuery(serverRequest);
        } else {
            serverResponse = handleItineraryQuery(serverRequest);
        }

        return gson.toJson(serverResponse, ServerResponse.class);
    }

    private ServerResponse handleItineraryQuery(ServerRequest serverRequest) {
        Result result = database.queryAlgorithm(serverRequest.getIDList());
        NearestNeighbor algorithm = new NearestNeighbor(result.points, result.size, serverRequest.getMiles());
        ArrayList<TripLeg> legs = algorithm.computeShortestPath(serverRequest.getOptimization()).getLegs();

        try {
            System.out.println("Appending path to SVG: " + legs);
            svg = view.insertSVG(legs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ServerResponse(legs, svg, 120, 100);
    }

    private ServerResponse handleSelectionQuery(ServerRequest serverRequest) {
        Result result = database.queryPage(serverRequest.getDescription());
        return new ServerResponse(result.points);
    }

    private void setHeaders(Response response) {
        response.header("Content-Type", "application/json");
        response.header("Access-Control-Allow-Origin", "*");
        response.header("Access-Control-Allow-Headers", "*");
    }

}
