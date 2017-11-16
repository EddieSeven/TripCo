package edu.csu2017fa314.T25.View;

import edu.csu2017fa314.T25.Model.TripLeg;
import edu.csu2017fa314.T25.Model.Point;

import java.util.ArrayList;
import java.util.Arrays;


public class ServerResponse {
    private String response = "queryA";
    private ArrayList<TripLeg> locations;
	private Point [] points;
    private String svg;
    private int svgHeight;
    private int svgWidth;
    private ArrayList<String> ids = new ArrayList<>();

    public ServerResponse(ArrayList locations, String svg, int svgHeight, int svgWidth) {
        this.locations = locations;
        this.svg = svg;
        this.svgHeight = svgHeight;
        this.svgWidth = svgWidth;
        System.out.println(this.toString());
    }
	public ServerResponse(Point [] pts){
		points = pts;
		locations = null;
		svg ="";
		svgHeight = svgWidth = 0;
		toIDList();
	}

    private void toIDList(){
	ArrayList<Point> temp = new ArrayList<>(Arrays.asList(this.points));

	for(int i = 0; i < temp.size(); i++){
		this.ids.add(temp.get(i).attributes[0]);
	}
    }
    @Override
    public String toString() {
        return "ServerResponse{" +
                "response='" + response + '\'' +
                ", svg=" + svg + '\'' +
                ", svgHeight=" + svgHeight + '\'' +
                ", svgWidth=" + svgWidth + '\'' +
                ", locations=" + locations + '\'' +
		", ids=" + ids + '\'' +
                '}';
    }
}
