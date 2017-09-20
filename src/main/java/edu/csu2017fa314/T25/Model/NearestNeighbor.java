package edu.csu2017fa314.T25.Model;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;


public class NearestNeighbor {
    ArrayList<Node> unvisited = new ArrayList<Node>();

    /**
     * INPUT: current node
     * OUTPUT: nearest node
     * */
    private Node computeNearestNeighbor(Node current){
        return null;
    }

    /**
    * INPUT: data structure containing all the stops in the trip
    * OUTPUT: either that same data structure or a path through all of the nodes
    * */
    private ArrayList<Node> computePath(){
        return null;
    }

}

class Node {
    // Container class for trip legs [TODO] Only has the basic 3 fields required for computation
    String id;
    double latitude;
    double longitude;

    public Node(String id, double latitude, double longitude){
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

class DistanceMap {
    // Container class that adds functionality to check both permutations of a pair (ab, ba)
    HashMap<Pair, Double> distanceMap = new HashMap<Pair, Double>();

    /**
     * INPUT: two nodes
     * OUTPUT: the distance between the two nodes, or -1.0 if it hasn't been computed yet [TODO] More elegant solution?
     * COMPLEXITY: constant
     */
    public double getDistance(Node a, Node b){
        Pair ab = new Pair(a, b);
        Pair ba = new Pair(b, a);

        if (distanceMap.get(ab) != null){
            return distanceMap.get(ab);
        } else if (distanceMap.get(ba) != null){
            return distanceMap.get(ba);
        } else {
            return -1.0;
        }

    }

    /**
     * INPUT: pair of nodes, distance in double form
     * EFFECT: adds newly computed distance to map
     */
    public void write(Pair<Node, Node> pair, double distance){
        distanceMap.put(pair, distance);
    }

}