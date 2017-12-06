package edu.csu2017fa314.T25.View;

import edu.csu2017fa314.T25.Model.TripLeg;
import edu.csu2017fa314.T25.Model.Point;

import java.util.ArrayList;
import java.util.Arrays;


public class ServerResponse {
    private String response = "queryA";
    private ArrayList<TripLeg> locations;
	private Point [] points;

    private ArrayList<String> ids = new ArrayList<>();

    public ServerResponse(ArrayList locations) {
        this.locations = locations;
    }

	public ServerResponse(Point [] pts){
		points = pts;
		locations = null;
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
                ", locations=" + locations + '\'' +
				", ids=" + ids + '\'' +
                '}';
    }
}
