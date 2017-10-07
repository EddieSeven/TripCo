package edu.csu2017fa314.T25.Model;

import java.util.ArrayList;


public class NearestNeighbor {
    private int problemSize;
    private Point points[];
    private int distanceMatrix[][];

    public NearestNeighbor(Point points[], int problemSize) {
        this.problemSize = problemSize;
        this.points = points;
        distanceMatrix = new int[problemSize][problemSize];
    }

    public Point[] getPoints() {
        return points;
    }

    public int computeNearestNeighbor(int i, Path path, boolean visited[]) {

        int indexLowest = 0;
        int lowestDistance = Integer.MAX_VALUE;

        for (int j = 0; j < problemSize; j++) {
            int currentDistance = getDistance(i, j);

            if (i != j && currentDistance < lowestDistance && !visited[j]) {
                indexLowest = j;
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
        boolean visited[] = new boolean[problemSize]; // All entries default to false

        int i = startIndex;
        path.add(points[i]);

        visited[i] = true;

        for (int k = 0; k < problemSize - 1; k++){
            int j = computeNearestNeighbor(i, path, visited);
            path.add(points[j]);
            visited[j] = true;
            i = j;
        }

        returnHome(path, startIndex);

        return path;
    }

    private void returnHome(Path path, int start) {
        int pathSize = path.size();
        int end = pathSize - 1;
        int distance = getDistance(start, end);

        path.add(points[start]);
        path.addCost(distance);
    }

    private int getDistance(int i, int j){
        if (i == j)
            return 0;
        else if (distanceMatrix[i][j] != 0) // assumed to be zero if it hasn't been calculated yet
            return distanceMatrix[i][j];
        else {
            distanceMatrix[i][j] = Model.computeDistance(points[i], points[j], true);
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
