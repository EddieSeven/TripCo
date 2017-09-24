package edu.csu2017fa314.T25.Model;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Math;

public class Model {
    public static ArrayList<Model> breweriesList = new ArrayList<Model>();
    public static ArrayList<Model> itinerary = new ArrayList<Model>();
    public static ArrayList<String> modelCategories = new ArrayList<String>();
    public static ArrayList<ArrayList<String>> modelData = new ArrayList<ArrayList<String>>();

    public String studentID = "";
    public String name = "";
    public String city = "";
    public String latitude = "";
    public String longitude = "";
    public int elevation = 0;

    public Model(){}
    
    // The data may have fewer or less categories than in sprint 1
    public Model(String stuID,String brewName,String brewCi,String brewLat,String brewLong,int brewElev){
        studentID = stuID;
        name = brewName;
        city = brewCi;
        latitude = brewLat;
        longitude = brewLong;
        elevation = brewElev;
    }

    public Model(ArrayList<String> categories){
    	Model.modelCategories = categories;
    }
    
    public static void addToModel(ArrayList<String> data){
    	modelData.add(data);
    }

    public static void readCSV(String csvLocation) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(csvLocation));
		 scanner.useDelimiter("\n");
		 String[] firstline = scanner.next().split(",");
		 int iD = 0;
		 int name = 0;
		 int lat = 0;
		 int longi = 0;
		 int elev = 0;
		 int city = 0;

		 for(int i = 0; i < firstline.length;i++){
			modelCategories.add(firstline[i].toLowerCase());
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
			 ArrayList<String> temp = new ArrayList<String>();
			 String[] values = scanner.next().split(",");
			 for(int j = 0; j < values.length; ++j){
				 temp.add(values[j]);
				 if(values[j].contains("elev")){
					 
				 }
			 }
			 brewery.studentID = values[iD];
			 brewery.name = values[name];
			 brewery.city = values[city];
			 brewery.latitude = values[lat];
			 brewery.longitude = values[longi];
			 if(elev != 0) {//avoid parsing if elevation is not present
                String elevStrConv = values[elev].trim();
                brewery.elevation = Integer.parseInt(elevStrConv);
            }
			 breweriesList.add(brewery);
			 addToModel(temp);
		 }
        scanner.close();
   }
     
    public static ArrayList<TripLeg> calculateDistances() {
		int latIndex = 0;
		int longIndex = 0;
		int nameIndex = 0;
		int idIndex = 0;
		
		if (modelData.isEmpty()) {
			return null;
		}
		ArrayList<TripLeg> legs = new ArrayList<TripLeg>();
		
		for (int i = 0; i < modelCategories.size(); ++i) {
			if(modelCategories.get(i).toLowerCase().equals("latitude")){
				latIndex = i;
			}
			if (modelCategories.get(i).toLowerCase().equals("longitude")){
				longIndex = i;
			}
			if (modelCategories.get(i).toLowerCase().equals("name")){
				nameIndex = i;
			}
			if (modelCategories.get(i).toLowerCase().equals("id")){
				idIndex = i;
			}
		}
		
		for (int j = 0; j < modelData.size()-1; ++j) {
			String startId = modelData.get(j).get(idIndex);
			String endId = modelData.get(j+1).get(idIndex);
			String startName = modelData.get(j).get(nameIndex);
			String endName = modelData.get(j+1).get(nameIndex);
			String startLat = modelData.get(j).get(latIndex);
			String startLong = modelData.get(j).get(longIndex);
			String endLat = modelData.get(j+1).get(latIndex);
			String endLong = modelData.get(j+1).get(longIndex);
			Point start = new Point(startLat, startLong);
			Point end = new Point(endLat, endLong);
			
			legs.add(new TripLeg(startId, endId, computeDistance(start, end), startName, endName, startLat, endLat, startLong, endLong, modelData, modelCategories));
		}	
		return legs;
	}

	public static int computeDistance(Point start, Point finish) {
		final double RADIUS_MILES = 3958.7613;
		final double RADIUS_KILOMETERS = 6371.0088;

		double dP = Math.abs(start.latitude - finish.latitude);
		double dY = Math.abs(start.longitude - finish.longitude);
		double cosP2 = Math.cos(finish.latitude);
		double sindY = Math.sin(dY);
		double cosP1 = Math.cos(start.latitude);
		double sinP2 = Math.sin(finish.latitude);
		double sinP1 = Math.sin(start.latitude);
		double cosdY = Math.cos(dY);

		double numerator = Math.pow(cosP2 * sindY, 2) + Math.pow((cosP1 * sinP2) - (sinP1*cosP2*cosdY),2);
		double denominator = (sinP1 * sinP2) + (cosP1 * cosP2 * cosdY);
		double dS = Math.atan2(Math.sqrt(numerator) , denominator);
		return (int)(Math.round(RADIUS_MILES * dS));
	}

}

class Point {
	// [TODO] Account for east and south in lat and long
	String id;
	String DMSlatitude;
	String DMSlongitude;
	double latitude;
	double longitude;

	public Point(String slat, String slon) {
		DMSlatitude = slat;
		DMSlongitude = slon;
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
