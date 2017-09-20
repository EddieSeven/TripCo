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
    private Node computeNearestNeighbor(Pair legPair){
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

}

class DistanceMap {
    HashMap<Pair, Double> distanceMap;

    /**
     * INPUT: two nodes
     * OUTPUT: the distance between the two nodes
     * COMPLEXITY: constant
     */
    private double getDistance(Node a, Node b){
        return 3.14;
    }

    /**
     * INPUT: two nodes
     * OUTPUT: true if the distance has already been computed
     */
    private boolean isComputed(Node a, Node b){
        return false;
    }

}