package edu.csu2017fa314.T25.Model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import org.javatuples.Pair;


public class NearestNeighbor {
    int problemSize;
    ArrayList<Point> points = new ArrayList<>();
    DistanceMap distanceMap = new DistanceMap();

    public NearestNeighbor(ArrayList<Point> points, int n){
        problemSize = n;
        this.points = points;
    }

    /**
     * INPUT: current node
     * OUTPUT: nearest nodes index in the points arrayList
     * */
    private int computeNearestNeighbor(int index){
        Point current = points.get(index);

        int indexLowest = 0;
        int lowestDistance = distanceMap.getDistance(current, points.get(indexLowest));

        for (int i = 0; i < points.size(); i++){
            Point newPoint = points.get(i);
            if (distanceMap.getDistance(current, newPoint) < lowestDistance){
                indexLowest = i;
                lowestDistance = distanceMap.getDistance(current, newPoint);
            }
        }

        return indexLowest;
    }



    /**
    * INPUT: data structure containing all the stops in the trip
    * OUTPUT: either that same data structure or a path through all of the nodes
    * */
    private ArrayList<Point> computeShortestPath(){
        ArrayList<Point> shortestPath= new ArrayList<>();

        for (int i = 0; i < problemSize; i++){
            computePath(i);
        }

        return shortestPath;
    }

    private ArrayList<Point> computePath(int start){
        ArrayList<Point> unvisited = points;
        ArrayList<Point> path = new ArrayList<>();

        int current = start;
        int next;
        path.add(points.get(start));
        while (!unvisited.isEmpty()){
            next = computeNearestNeighbor(current);
            path.add(points.get(next));
            unvisited.remove(next);
            current = next;
        }
        path.add(points.get(start));

        return path;
    }

}

class DistanceMap {
    // Container class that adds functionality to check both permutations of a pair (ab, ba)
    HashMap<Pair, Integer> distanceMap = new HashMap<>();
    Model model = new Model();

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
            write(a, b, model.computeDistance(a, b));
            return distanceMap.get(ab);
        }


    }

    public boolean containsPair(Pair<Point, Point> pair){
        if (distanceMap.get(pair) != null) {
            return true;
        } else {
            return false;
        }
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

