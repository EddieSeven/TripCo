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

    /**
     * INPUT: current node
     * OUTPUT: nearest nodes index in the points arrayList
     * */
    private int computeNearestNeighbor(int index, Path path, ArrayList<Point> unvisited){
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

    /**
    * INPUT: data structure containing all the stops in the trip
    * OUTPUT: either that same data structure or a path through all of the nodes
    * */
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

    private Path computePath(int start){
        Path path = new Path();
        ArrayList<Point> unvisited = (ArrayList<Point>)points.clone();
        int current = start;
        int nextIndex;
        Point next;
        path.add(points.get(start));


        while (!unvisited.isEmpty()){
            nextIndex = computeNearestNeighbor(current, path, unvisited);
            next = points.get(nextIndex);
            path.add(next);
            unvisited.remove(next);
            current = nextIndex;
        }
        path.add(points.get(start));

        return path;
    }
}

class DistanceMap {
    // Container class that adds functionality to check both permutations of a pair (ab, ba)
    HashMap<Pair, Integer> distanceMap = new HashMap<>();

    /**
     * INPUT: two nodes
     * OUTPUT: the distance between the two nodes, or uses model to compute the distance
     * COMPLEXITY: constant
     */
    public int getDistance(Point a, Point b){
        Pair ab = new Pair(a, b);
        Pair ba = new Pair(b, a);

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

    /**
     * INPUT: pair of nodes, distance in double form
     * EFFECT: adds newly computed distance to map
     */
    public void write(Point a, Point b, int distance){
        Pair ab = new Pair(a, b);
        Pair ba = new Pair(b, a);

        if (!containsPair(ab) || !containsPair(ba)) {
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

    public Path(Path obj){
        this.totalCost = obj.totalCost;
        this.path = obj.path;
    }
}
