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
    public static String [] jsData;
    public static String jsArrayCode;
    public static ArrayList<Double> latcoordinates = new ArrayList<Double>();
	public static ArrayList<Double> longcoordinates = new ArrayList<Double>();

    
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
    
    public static String stringForJavascriptArray(ArrayList<String> categories){
        StringBuffer sb = new StringBuffer();
        // sb.append("[");
        for(int i = 0; i < categories.size(); ++i){
            sb.append("\"").append(categories.get(i)).append("\"");
            if(i+1 < categories.size()){
                sb.append(",");
            }
        }
        // sb.append("]");
        return sb.toString();
    }
    
    public static String [] dataForJavascriptArray(ArrayList<ArrayList<String>> data){
        jsData = new String[data.size()];
        StringBuffer sb = new StringBuffer();
    	for(int j = 0; j <data.size(); ++j){ 
            for(int i = 0; i < data.get(j).size(); ++i){
                sb.append("\"").append(data.get(j).get(i)).append("\"");
                if(i+1 < data.get(j).size()){
                    sb.append(",");
                }
            }
            jsData[j] = sb.toString();
            sb.delete(0, sb.length());
        }
        return jsData;
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
		 jsArrayCode = stringForJavascriptArray(modelCategories);
		 
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
        jsData = dataForJavascriptArray(modelData);
   }
     
    public static ArrayList<TripLeg> calculateDistances() {
		ArrayList<Point> points = new ArrayList<>();
        NearestNeighbor algorithm;
        int latIndex = 0;
		int longIndex = 0;
		int nameIndex = 0;
		int idIndex = 0;
		
		if (modelData.isEmpty()) {
			return null;
		}
		ArrayList<TripLeg> legs = new ArrayList<>();
		
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

		for (int j = 0; j < modelData.size(); j++){
		    String latitude = modelData.get(j).get(latIndex);
		    String longitude = modelData.get(j).get(longIndex);
		    String id = modelData.get(j).get(idIndex);
		    String name = modelData.get(j).get(nameIndex);

			Point point = new Point(latitude, longitude);
		    point.id = id;
		    point.name = name;
            point.data = modelData.get(j);

			points.add(point);
		}

		algorithm = new NearestNeighbor(points);
        Path shortestPath = algorithm.computeShortestPath();
        ArrayList<ArrayList<String>> sortedData = new ArrayList<>();
        reconstructData(shortestPath, sortedData);

		for (int j = 0; j < sortedData.size()-1; ++j) {
			String startId = sortedData.get(j).get(idIndex);
			String endId = sortedData.get(j+1).get(idIndex);
			String startName = sortedData.get(j).get(nameIndex);
			String endName = sortedData.get(j+1).get(nameIndex);
			String startLat = sortedData.get(j).get(latIndex);
			String startLong = sortedData.get(j).get(longIndex);
			String endLat = sortedData.get(j+1).get(latIndex);
			String endLong = sortedData.get(j+1).get(longIndex);
			Point start = new Point(startLat, startLong);
			Point end = new Point(endLat, endLong);
			legs.add(new TripLeg(startId, endId, computeDistance(start, end), startName, endName, startLat, endLat, startLong, endLong, jsData, jsArrayCode));
		}
		return legs;
	}

	private static void reconstructData(Path path, ArrayList<ArrayList<String>> sortedData){
        for (int i = 0; i < path.size(); i++){
            ArrayList<String> pointData = path.getPath().get(i).data;
            sortedData.add(pointData);
        }
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