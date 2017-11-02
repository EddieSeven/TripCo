package edu.csu2017fa314.T25.Model;

import java.util.ArrayList;

public class Point {

	// These fields match the ones found in the database
    String id;
	String type;
    String name;
    public double latitude;
    public double longitude;
	int elevation;
	String municipality;
	String home_link;
	String wikipedia_link;

	// This is the point's initial position in the array.
	// It's used so that no matter where a point lies in a path,
	// its values in the distance matrix can still be found
	private int index;

    public Point(String id, String type, String name, String sLat, String sLon, String sElev, String munic, String homeL, String wikiL) {
		this.id = id;
		this.type = type;
		this.name = name;
		latitude = Double.parseDouble(sLat);
		longitude = Double.parseDouble(sLon);
		if (sElev != null)
		    elevation = Integer.parseInt(sElev);
		municipality = munic;
		home_link = homeL;
		wikipedia_link = wikiL;
    }
	
	// This is mostly needed for test purposes.
	public Point(double lat, double lon) {
		latitude = lat;
		longitude = lon;
	}

	public void setIndex(int ind) {
		index = ind;
	}
	
	public int getIndex() {
		return index;
	}

	// This method is no longer in use because the database stores
	// these as plain doubles, but is saved just in case.
	private double GeoToDouble(String geoCoord, String inverter) {
		Boolean isWest = false;
        if (geoCoord.contains(inverter)) {
            isWest = true;
        }
        String newGeo = trimNonNumeric(geoCoord.replace(" ", "")).trim();
        String[] pieces = newGeo.split(" ");

        double dGeo = 0.0;
        for (int i = 0; i < pieces.length; i++) {
            dGeo += Double.parseDouble(pieces[i]) / Math.pow(60, i);
        }
        if (isWest) {
            dGeo = -1 * dGeo;
        }

		return Math.toRadians(dGeo);
	}

	// This is a convenience method for GeoToDouble, also no longer in use
    private String trimNonNumeric(String s) {
        String ret = "";
        for (int c = 0; c < s.length(); c++) {
            char ch = s.charAt(c);
            if (ch == ' ' || ch == '.' || (ch >= '0' && ch <= '9')) {
                ret += ch;
            } else {
                ret += ' ';
            }
        }
        return ret;
    }
}
