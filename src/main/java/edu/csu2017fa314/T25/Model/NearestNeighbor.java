package edu.csu2017fa314.T25.Model;

import java.util.ArrayList;


public class NearestNeighbor {
    private int problemSize;
    private ArrayList<Point> points = new ArrayList<>();
    private int distanceMatrix[][];

    public NearestNeighbor(ArrayList<Point> points, int problemSize) {
        this.problemSize = problemSize;
        this.points = points;
        distanceMatrix = new int[problemSize][problemSize];
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public int computeNearestNeighbor(int index, Path path, ArrayList<Point> unvisited) {
        Point current = points.get(index);

        int indexLowest = 0;
        int lowestDistance = Integer.MAX_VALUE;

        for (int i = 0; i < points.size(); i++) {
            Point newPoint = points.get(i);
            int currentDistance = getDistance(index, i);

            if (current != newPoint && currentDistance < lowestDistance && unvisited.contains(newPoint)) {
                indexLowest = i;
                lowestDistance = currentDistance;
            }
        }

        path.addCost(lowestDistance);

        return indexLowest;
    }

    public Path computeShortestPath() {
        Path shortestPath = new Path(Integer.MAX_VALUE);

        Path current;
        for (int i = 0; i < problemSize; i++) {
            current = computePath(i);

            if (current.getCost() < shortestPath.getCost()) {
                shortestPath = current;
            }
        }

        return shortestPath;
    }

    public Path computePath(int startIndex) {
        Path path = new Path();
        ArrayList<Point> unvisited = (ArrayList<Point>) points.clone();
        int current = startIndex;
        Point next;

        path.add(points.get(startIndex));
        unvisited.remove(points.get(startIndex));
        while (!unvisited.isEmpty()) {
            int nextIndex = computeNearestNeighbor(current, path, unvisited);
            next = points.get(nextIndex);
            path.add(next);
            unvisited.remove(next);
            current = nextIndex;
        }
        returnHome(path, startIndex);

        return path;
    }

    private void returnHome(Path path, int start) {
        int pathSize = path.size();
        int end = pathSize - 1;
        int distance = getDistance(start, end);

        path.add(points.get(start));
        path.addCost(distance);
    }

    private int getDistance(int i, int j){
        if (i == j)
            return 0;
        else if (distanceMatrix[i][j] != 0) // assumed to be zero if it hasn't been calculated yet
            return distanceMatrix[i][j];
        else {
            distanceMatrix[i][j] = Model.computeDistance(points.get(i), points.get(j), true);
            return distanceMatrix[i][j];
        }
    }
}

class Path {
    private ArrayList<Point> path = new ArrayList<>();
    private int totalCost = 0;

    public Path() {
    }

    public Path(int initialCost){
        totalCost = initialCost;
    }

    public void addCost(int cost) {
        totalCost += cost;
    }

    public int getCost() {
        return totalCost;
    }

    public ArrayList<Point> getPath() {
        return path;
    }

    public void add(Point point) {
        path.add(point);
    }

    public Point getPoint(int index) {
        return path.get(index);
    }

    public int size() {
        return path.size();
    }

    public Path(Path obj) {
        this.totalCost = obj.totalCost;
        this.path = obj.path;
    }
}
