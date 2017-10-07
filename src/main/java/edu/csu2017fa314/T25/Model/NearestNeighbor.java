package edu.csu2017fa314.T25.Model;


public class NearestNeighbor {
    private int N;
    private Point points[];
    private int distanceMatrix[][];

    public NearestNeighbor(Point points[], int N) {
        this.N = N;
        this.points = points;
        distanceMatrix = new int[N][N];
    }

    public Point[] getPoints() {
        return points;
    }

    public int computeNearestNeighbor(int i, Path path, boolean visited[]) {
        int indexLowest = 0;
        int lowestDistance = Integer.MAX_VALUE;

        for (int j = 0; j < N; j++) {
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
        Path shortestPath = new Path();
        Path current;

        for (int i = 0; i < N; i++) {
            current = computePath(i);

            if (current.getCost() < shortestPath.getCost()) {
                shortestPath = current;
            }
        }

        return shortestPath;
    }

    public Path computePath(int startIndex) {
        Path path = new Path(N);
        boolean visited[] = new boolean[N]; // All entries default to false

        int i = startIndex;
        path.add(points[i]);

        visited[i] = true;

        for (int k = 0; k < N - 1; k++){
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
        int end = pathSize - 2;
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
    private Point path[];
    private int totalCost = 0;
    private int index = 0;
    private int N;

    public Path(int N){
        this.N = N + 1;
        path = new Point[this.N];
    }

    public Path(){
        totalCost = Integer.MAX_VALUE;
    }

    public void addCost(int cost) {
        totalCost += cost;
    }

    public int getCost() {
        return totalCost;
    }

    public Point[] getPath() {
        return path;
    }

    public void add(Point point) {
        path[index] = point;
        index++;
    }

    public Point getPoint(int i) {
        return path[i];
    }

    public int size() {
        return N;
    }

}
