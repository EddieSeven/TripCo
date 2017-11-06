package edu.csu2017fa314.T25.View;

import edu.csu2017fa314.T25.Model.TripLeg;

import java.util.ArrayList;
import java.util.Arrays;


public class ServerResponse {
    private String response = "queryA";
    private ArrayList<TripLeg> locations;
    private String svg;
    private int svgHeight;
    private int svgWidth;

    public ServerResponse(ArrayList locations, String svg, int svgHeight, int svgWidth) {
        this.locations = locations;
        this.svg = svg;
        this.svgHeight = svgHeight;
        this.svgWidth = svgWidth;
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "ServerResponse{" +
                "response='" + response + '\'' +
                ", svg=" + svg + '\'' +
                ", svgHeight=" + svgHeight + '\'' +
                ", svgWidth=" + svgWidth + '\'' +
                ", locations=" + locations + '\'' +
                '}';
    }
}
