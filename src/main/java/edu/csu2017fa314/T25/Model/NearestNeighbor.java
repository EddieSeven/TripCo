package edu.csu2017fa314.T25.Model;

import java.util.ArrayList;


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

        path.add(points[indexLowest], lowestDistance);

        return indexLowest;
    }

    public Path computeShortestPath() {
        Path shortestPath = new Path();
        Path current;

        for (int i = 0; i < N; i++) {
            current = computePath(i);
            twoOptSwap(current, i , i+1);
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
        path.add(points[i], 0);

        visited[i] = true;

        for (int k = 0; k < N - 1; k++) {
            int j = computeNearestNeighbor(i, path, visited);
            visited[j] = true;
            i = j;
        }

        path.returnHome();

        return path;
    }

    private int getDistance(int i, int j) {
        if (i == j)
            return 0;
        else if (distanceMatrix[i][j] != 0) // assumed to be zero if it hasn't been calculated yet
            return distanceMatrix[i][j];
        else {
            distanceMatrix[i][j] = computeDistance(points[i], points[j], true);
            return distanceMatrix[i][j];
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

		double fLon = Math.toRadians(finish.longitude);
		double fLat = Math.toRadians(finish.latitude);
		double sLon = Math.toRadians(start.longitude);
		double sLat = Math.toRadians(start.latitude);

        double dY = Math.abs(sLon - fLon);
        double cosP2 = Math.cos(fLat);
        double sindY = Math.sin(dY);
        double cosP1 = Math.cos(sLat);
        double sinP2 = Math.sin(fLat);
        double sinP1 = Math.sin(sLat);
        double cosdY = Math.cos(dY);

        double numerator = Math.pow(cosP2 * sindY, 2) + Math.pow((cosP1 * sinP2) - (sinP1 * cosP2 * cosdY), 2);
        double denominator = (sinP1 * sinP2) + (cosP1 * cosP2 * cosdY);
        double dS = Math.atan2(Math.sqrt(numerator), denominator);
        return (int) (Math.round(metric * dS));
    }
    public void twoOptSwap(Path route, int i1, int k) { // swap in place
        while (i1 < k){
            Point temp = route.path[i1];
            route.path[i1] = route.path[k];
            route.path[k] = temp;
            i1++;
            k--;
        }
        boolean improvement = true;
        while (improvement){
            improvement = false;
            for (int i = 0; i <= N - 3; i++) { //checkn>4
                for (k = i + 2; k <= N - 1; k++) {
                    double delta = -computeDistance(route.path[i], route.path[i + 1],true) - computeDistance(route.path[k], route.path[k + 1],true) + computeDistance(route.path[i], route.path[k],true) + computeDistance(route.path[i + 1], route.path[k + 1],true);
                    if (delta < 0) {
                        twoOptSwap(route, i + 1, k);
                        improvement = true;
                    }
                }
            }
        }
    }
}
