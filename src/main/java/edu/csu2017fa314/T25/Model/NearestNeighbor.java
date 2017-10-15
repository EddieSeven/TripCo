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

class Path {
    private Point path[];
	private ArrayList<TripLeg> legs;
    private int totalCost = 0;
    private int index = 0;
    private int N;

    public Path(int N) {
        this.N = N + 1;
        path = new Point[this.N];
		legs = new ArrayList<TripLeg>();
    }

    public Path() {
        totalCost = Integer.MAX_VALUE;
    }

    public int getCost() {
        return totalCost;
    }

    public Point[] getPath() {
        return path;
    }

	public ArrayList<TripLeg> getLegs() {
		return legs;
	}

    public void add(Point point, int cost) {
		totalCost += cost;
        path[index] = point;

		if (index > 0) {
			legs.add(new TripLeg(path[index-1], path[index], cost));
		}
        index++;
    }

	public void addPoint(Point p) {
		path[index] = p;
		index++;
	}

    public Point getPoint(int i) {
        return path[i];
    }

    public int size() {
        return N;
    }

    public void returnHome() {
        // todo hardcoded miles
        int distance = NearestNeighbor.computeDistance(path[0], path[N - 2], true);
		add(path[0], distance);
    }

}
