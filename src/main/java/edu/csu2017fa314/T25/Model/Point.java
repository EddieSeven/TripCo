package edu.csu2017fa314.T25.Model;

import java.util.ArrayList;

public class Point {
    // [TODO] Account for east and south in lat and long (WEST IS NEGATIVE IN GEOGRAPHIC COORDINATES)
    String id;
    String name;
    String DMSlatitude;
    String DMSlongitude;
    double latitude;
    double longitude;
    ArrayList<String> data;

    public Point(String slat, String slon) {
        Boolean isWest = false;
        DMSlatitude = slat;
        DMSlongitude = slon;
        if(DMSlongitude.contains("W")){
            isWest = true;
        }
        String newSlat = trimNonNumeric(slat.replace(" ", "")).trim();
        String newSlon = trimNonNumeric(slon.replace(" ", "")).trim();
        String [] latPieces = newSlat.split(" ");
        String [] lonPieces = newSlon.split(" ");

        double dlat = 0.0;
        double dlon = 0.0;
        for (int i = 0; i < latPieces.length; i++) {
            dlat += Double.parseDouble(latPieces[i]) / Math.pow(60, i);
        }
        for (int i = 0; i < lonPieces.length; i++) {
            dlon += Double.parseDouble(lonPieces[i]) / Math.pow(60, i);
        }
        if(isWest){
            dlon = -1 * dlon;
        }
        Model.latcoordinates.add(dlat);
        Model.longcoordinates.add(dlon);
        latitude = Math.toRadians(dlat);
        longitude = Math.toRadians(dlon);
    }

    public String trimNonNumeric(String s) {
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