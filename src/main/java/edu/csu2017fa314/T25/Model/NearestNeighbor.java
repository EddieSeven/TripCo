package edu.csu2017fa314.T25.Model;

import java.util.ArrayList;
import java.util.HashMap;
import org.javatuples.Pair;


public class NearestNeighbor {
    private int problemSize;
    private ArrayList<Point> points = new ArrayList<>();
    private DistanceMap distanceMap = new DistanceMap();

    public NearestNeighbor(ArrayList<Point> points){
        this.problemSize = points.size();
        this.points = points;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    /**
     * INPUT: current node
     * OUTPUT: nearest nodes index in the points arrayList
     * */
    public int computeNearestNeighbor(int index, Path path, ArrayList<Point> unvisited){
        Point current = points.get(index);

        int indexLowest = 0;
        int lowestDistance = Integer.MAX_VALUE;

        for (int i = 0; i < points.size(); i++){
            Point newPoint = points.get(i);
            int currentDistance = distanceMap.getDistance(current, newPoint);

            if (current != newPoint && currentDistance < lowestDistance && unvisited.contains(newPoint)){
                indexLowest = i;
                lowestDistance = currentDistance;
            }
        }

        path.addCost(lowestDistance);

        return indexLowest;
    }

    public Path computeShortestPath(){
        Path shortestPath = new Path();
        shortestPath.addCost(Integer.MAX_VALUE);

        Path current;
        for (int i = 0; i < problemSize; i++){
            current = computePath(i);

            if (current.getCost() < shortestPath.getCost()) {
                shortestPath = current;
            }
        }

        return shortestPath;
    }

    public Path computePath(int startIndex){
        Path path = new Path();
        ArrayList<Point> unvisited = (ArrayList<Point>)points.clone();
        int current = startIndex;
        Point next;

        path.add(points.get(startIndex));
        unvisited.remove(points.get(startIndex));
        while (!unvisited.isEmpty()){
            int nextIndex = computeNearestNeighbor(current, path, unvisited);
            next = points.get(nextIndex);
            path.add(next);
            unvisited.remove(next);
            current = nextIndex;
        }
        returnHome(path, startIndex);

        return path;
    }

    private void returnHome(Path path, int startIndex){
        int pathSize = path.size();
        Point startPoint = points.get(startIndex);
        Point endPoint = path.getPoint(pathSize - 1);
        int distance = distanceMap.getDistance(startPoint, endPoint);


        path.add(points.get(startIndex));
        path.addCost(distance);
    }
}

class DistanceMap {
    // Container class that adds functionality to check both permutations of a pair (ab, ba)
    private HashMap<Pair, Integer> distanceMap = new HashMap<>();

    public int getDistance(Point a, Point b){
        Pair<Point, Point> ab = new Pair<>(a, b);
        Pair<Point, Point> ba = new Pair<>(b, a);

        if (distanceMap.get(ab) != null){
            return distanceMap.get(ab);
        } else if (distanceMap.get(ba) != null){
            return distanceMap.get(ba);
        } else {
            write(a, b, Model.computeDistance(a, b));
            return distanceMap.get(ab);
        }
    }

    public boolean containsPair(Pair<Point, Point> pair){
        return (distanceMap.get(pair) != null);
    }

    public void write(Point a, Point b, int distance){
        Pair<Point, Point> ab = new Pair<>(a, b);
        Pair<Point, Point> ba = new Pair<>(b, a);

        if (!containsPair(ab) || !containsPair(ba)){
            distanceMap.put(ab, distance);
        }
    }
}

class Path {
    private ArrayList<Point> path = new ArrayList<>();
    private int totalCost = 0;

    public Path(){}

    public void addCost(int cost){
        totalCost += cost;
    }

    public int getCost(){
        return totalCost;
    }

    public ArrayList<Point> getPath() {
        return path;
    }

    public void add(Point point){
        path.add(point);
    }

    public Point getPoint(int index){
        return path.get(index);
    }

    public int size(){
        return path.size();
    }

    public Path(Path obj){
        this.totalCost = obj.totalCost;
        this.path = obj.path;
    }
}
