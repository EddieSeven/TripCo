package edu.csu2017fa314.T25.Model;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Math;

public class Model
{
    public static ArrayList<Model> breweriesList = new ArrayList<Model>();

    public String studentID = "";
    public String name = "";
    public String city = "";
    public String latitude = "";
    public String longitude = "";
    public int elevation = 0;

    public Model(){}

    public Model(String stuID,String brewName,String brewCi,String brewLat,String brewLong,int brewElev){
        studentID = stuID;
        name = brewName;
        city = brewCi;
        latitude = brewLat;
        longitude = brewLong;
        elevation = brewElev;
    }


    public static void readCSV(String csvLocation) throws FileNotFoundException {
         Scanner scanner = new Scanner(new File(csvLocation));
		 String[] firstline = scanner.next().split(",");
		 scanner.useDelimiter("\n");

		 int iD = 0;
		 int name = 0;
		 int lat = 0;
		 int longi = 0;
		 int elev = 0;
		 int city = 0;

		 for(int i = 0; i < firstline.length;i++){
		 	if(firstline[i].toLowerCase().contains("longi")){
		 		longi = i;
			}
			if(firstline[i].toLowerCase().contains("lati")){
				lat = i;
			}
			if(firstline[i].toLowerCase().contains("id")){
				iD = i;
			}
			if(firstline[i].toLowerCase().contains("name")){
				name = i;
			}
			if(firstline[i].toLowerCase().contains("elev")){
				elev = i;
			}
			if(firstline[i].toLowerCase().contains("city")){
				 city = i;
			}

		 }
		 scanner.nextLine();
         while (scanner.hasNext()) {
			 Model brewery = new Model();
			 String[] values = scanner.next().split(",");
			 brewery.studentID = values[iD];
			 brewery.name = values[name];
			 brewery.city = values[city];
			 brewery.latitude = values[lat];
			 brewery.longitude = values[longi];
			 String elevStrConv = values[elev].trim();
			 brewery.elevation = Integer.parseInt(elevStrConv);
			 breweriesList.add(brewery);
		 }
         scanner.close();
    }

	public static ArrayList<TripLeg> calculateDistances() {
		if (breweriesList.isEmpty()) {
			return null;
		}
		ArrayList<TripLeg> legs = new ArrayList<TripLeg>();
		for (int i = 0; i < breweriesList.size()-1;i++) {
			Model startB = breweriesList.get(i);
			Model endB = breweriesList.get(i+1);
			Point start = new Point(startB.latitude, startB.longitude);
			Point end = new Point(endB.latitude, endB.longitude);
			legs.add(new TripLeg(startB.studentID, endB.studentID, distance(start, end)));
		}
		return legs;
	}

	public static int distance(Point start, Point finish) {
		final double RADIUS_MILES = 3958.7613;

		double dP = Math.abs(start.lat - finish.lat);
		double dY = Math.abs(start.lon - finish.lon);
		double cosP2 = Math.cos(finish.lat);
		double sindY = Math.sin(dY);
		double cosP1 = Math.cos(start.lat);
		double sinP2 = Math.sin(finish.lat);
		double sinP1 = Math.sin(start.lat);
		double cosdY = Math.cos(dY);

		double numerator = Math.pow(cosP2 * sindY, 2) + Math.pow((cosP1 * sinP2) - (sinP1*cosP2*cosdY),2);
		double denominator = (sinP1 * sinP2) + (cosP1 * cosP2 * cosdY);
		double dS = Math.atan(Math.sqrt(numerator) / denominator);
		return (int)(Math.round(RADIUS_MILES * dS));
	}

}

class Point {
	double lat, lon;
	public Point(String slat, String slon) {
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
		lat = Math.toRadians(dlat);
		lon = Math.toRadians(dlon);

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
