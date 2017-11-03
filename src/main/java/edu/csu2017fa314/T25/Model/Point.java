package edu.csu2017fa314.T25.Model;

public class Point {
    public String attributes[];
    public double latitude;
    public double longitude;
	private int index;

    public Point(String attributes[]) {
		this.attributes = attributes;
		latitude = Double.parseDouble(attributes[3]);
        longitude = Double.parseDouble(attributes[4]);
    }
	
	// This is mostly needed for test purposes.
	public Point(double lat, double lon, String id) {
		attributes = new String[1];
		attributes[0] = id;
    	latitude = lat;
		longitude = lon;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}
}
