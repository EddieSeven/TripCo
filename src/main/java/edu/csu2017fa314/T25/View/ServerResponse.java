package edu.csu2017fa314.T25.View;

import edu.csu2017fa314.T25.Model.TripLeg;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by sswensen on 10/1/17.
 */

public class ServerResponse {
    private String response = "query";
    private ArrayList<TripLeg> locations;

    public ServerResponse(ArrayList locations) {
        this.locations = locations;
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "ServerResponse{" +
                "response='" + response + '\'' +
                ", locations=" + locations +
                '}';
    }
}
