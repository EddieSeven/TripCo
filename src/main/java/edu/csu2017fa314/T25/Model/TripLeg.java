package edu.csu2017fa314.T25.Model;
import java.util.ArrayList;

public class TripLeg {
    String start, end, startName, endName, startLat, endLat, startLong, endLong;
    int distance;

    public TripLeg(String start, 
    			   String finish, 
    			   int dist, 
    			   String startName, 
    			   String endName, 
    			   String startLat, 
    			   String endLat, 
    			   String startLong, 
    			   String endLong, 
    			   ArrayList<ArrayList<String>> data, 
    			   ArrayList<String> categories) 
    {
        this.start = start;
        this.end = finish;
        this.distance = dist;
        this.startName = startName;
        this.endName = endName;
        this.startLat = startLat;
        this.endLat = endLat;
        this.startLong = startLong;
        this.endLong = endLong;
    }
}