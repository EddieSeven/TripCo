package edu.csu2017fa314.T25.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Math;

public class Model {
    public static ArrayList<String> modelCategories = new ArrayList<>();
    public static ArrayList<ArrayList<String>> modelData = new ArrayList<>();
    public static String[] jsData;
    public static String jsArrayCode;
    public static ArrayList<Double> latcoordinates = new ArrayList<>();
    public static ArrayList<Double> longcoordinates = new ArrayList<>();


    public Model() {
    }

    public Model(ArrayList<String> categories) {
        Model.modelCategories = categories;
    }

    public static void addToModel(ArrayList<String> data) {
        modelData.add(data);
    }

    public static String stringForJavascriptArray(ArrayList<String> categories) {
        StringBuffer sb = new StringBuffer();
        // sb.append("[");
        for (int i = 0; i < categories.size(); ++i) {
            sb.append("\"").append(categories.get(i)).append("\"");
            if (i + 1 < categories.size()) {
                sb.append(",");
            }
        }
        // sb.append("]");
        return sb.toString();
    }

    public static String[] dataForJavascriptArray(ArrayList<ArrayList<String>> data) {
        jsData = new String[data.size()];
        StringBuffer sb = new StringBuffer();
        for (int j = 0; j < data.size(); ++j) {
            for (int i = 0; i < data.get(j).size(); ++i) {
                sb.append("\"").append(data.get(j).get(i)).append("\"");
                if (i + 1 < data.get(j).size()) {
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
        String[] firstLine = scanner.next().split(",");

        for (int i = 0; i < firstLine.length; i++) {
            modelCategories.add(firstLine[i].toLowerCase());
        }

        jsArrayCode = stringForJavascriptArray(modelCategories);

        scanner.nextLine();
        while (scanner.hasNext()) {
            ArrayList<String> temp = new ArrayList<>();
            String[] values = scanner.next().split(",");
            for (int j = 0; j < values.length; ++j) {
                temp.add(values[j]);
            }
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
            if (modelCategories.get(i).toLowerCase().equals("latitude")) {
                latIndex = i;
            }
            if (modelCategories.get(i).toLowerCase().equals("longitude")) {
                longIndex = i;
            }
            if (modelCategories.get(i).toLowerCase().equals("name")) {
                nameIndex = i;
            }
            if (modelCategories.get(i).toLowerCase().equals("id")) {
                idIndex = i;
            }
        }

        for (int j = 0; j < modelData.size(); j++) {
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

        algorithm = new NearestNeighbor(points, points.size());
        Path shortestPath = algorithm.computeShortestPath();
        ArrayList<ArrayList<String>> sortedData = new ArrayList<>();
        reconstructData(shortestPath, sortedData);

        for (int j = 0; j < sortedData.size() - 1; ++j) {
            String startId = sortedData.get(j).get(idIndex);
            String endId = sortedData.get(j + 1).get(idIndex);
            String startName = sortedData.get(j).get(nameIndex);
            String endName = sortedData.get(j + 1).get(nameIndex);
            String startLat = sortedData.get(j).get(latIndex);
            String startLong = sortedData.get(j).get(longIndex);
            String endLat = sortedData.get(j + 1).get(latIndex);
            String endLong = sortedData.get(j + 1).get(longIndex);
            Point start = new Point(startLat, startLong);
            Point end = new Point(endLat, endLong);
            Model.latcoordinates.add(Double.parseDouble(endLat));
            Model.longcoordinates.add(Double.parseDouble(endLong));
            // FOR NOW HARDCODED AS MILES
            legs.add(new TripLeg(startId, endId, computeDistance(start, end, true), startName, endName, startLat, endLat, startLong, endLong, jsData, jsArrayCode));
        }

        return legs;
    }

    private static void reconstructData(Path path, ArrayList<ArrayList<String>> sortedData) {
        for (int i = 0; i < path.size(); i++) {
            ArrayList<String> pointData = path.getPath().get(i).data;
            sortedData.add(pointData);
        }
    }

    public static int computeDistance(Point start, Point finish, boolean isMiles) {
        final double RADIUS_MILES = 3958.7613;
        final double RADIUS_KILOMETERS = 6371.0088;
        double metric;

        if (isMiles)
            metric = RADIUS_MILES;
        else
            metric = RADIUS_KILOMETERS;

        double dY = Math.abs(start.longitude - finish.longitude);
        double cosP2 = Math.cos(finish.latitude);
        double sindY = Math.sin(dY);
        double cosP1 = Math.cos(start.latitude);
        double sinP2 = Math.sin(finish.latitude);
        double sinP1 = Math.sin(start.latitude);
        double cosdY = Math.cos(dY);

        double numerator = Math.pow(cosP2 * sindY, 2) + Math.pow((cosP1 * sinP2) - (sinP1 * cosP2 * cosdY), 2);
        double denominator = (sinP1 * sinP2) + (cosP1 * cosP2 * cosdY);
        double dS = Math.atan2(Math.sqrt(numerator), denominator);
        return (int) (Math.round(metric * dS));
    }

}