package edu.csu2017fa314.T25.Model;

import java.util.ArrayList;


public class NearestNeighbor {
    private int N;
    private Point points[];
    private int distanceMatrix[][];
	private boolean miles;

    public NearestNeighbor(Point points[], int N, boolean miles) {
        this.N = N;
        this.points = points;
		for (int i = 0; i < N; i++) {
			this.points[i].setIndex(i);
		}
        distanceMatrix = new int[N][N];
		this.miles = miles;
		Path.setMiles(miles);
    }

    public Point[] getPoints() {
        return points;
    }

    public int computeNearestNeighbor(int i, Path path, boolean visited[]) {
        int indexLowest = 0;
        int lowestDistance = Integer.MAX_VALUE;

        for (int j = 0; j < N; j++) {
            int currentDistance = getDistance(points[i], points[j]);

            if (i != j && currentDistance < lowestDistance && !visited[j]) {
                indexLowest = j;
                lowestDistance = currentDistance;
            }
        }

        path.add(points[indexLowest], lowestDistance);

        return indexLowest;
    }

    public Path computeShortestPath(int optimization) {
		if (optimization == 0) {
			return computeNonOptPath();
		}
        Path shortestPath = new Path();
        Path current;

        for (int i = 0; i < N; i++) {
            current = computeNNPath(i);
			switch (optimization) {
				case 2:
					twoOpt(current);
					break;
				case 3:
					//3opt
					break;
			}
            if (current.getCost() < shortestPath.getCost()) {
                shortestPath = current;
            }
        }

        return shortestPath;
    }

	public Path computeNonOptPath() {
		return new Path(points, N);
	}

    public Path computeNNPath(int startIndex) {
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

    private int getDistance(Point pi, Point pj) {
		int i = pi.getIndex();
		int j = pj.getIndex();
        if (i == j)
            return 0;
        else if (distanceMatrix[i][j] != 0) // assumed to be zero if it hasn't been calculated yet
            return distanceMatrix[i][j];
        else {
            distanceMatrix[i][j] = computeDistance(pi, pj, miles);
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

	public void twoOptSwap(Path route, int i1, int k) {
		while (i1 < k) {
			Point temp = route.path[i1];
			route.path[i1] = route.path[k];
			route.path[k] = temp;
			i1++;
			k--;
		}
	}

    public void twoOpt(Path route) { // swap in place
        boolean improvement = true;
        while (improvement){
            improvement = false;
            for (int i = 0; i <= N - 3; i++) { //checkn>4
                for (int k = i + 2; k <= N - 1; k++) {
                    double delta = -getDistance(route.path[i], route.path[i + 1]) - getDistance(route.path[k], route.path[k + 1]) + getDistance(route.path[i], route.path[k]) + getDistance(route.path[i + 1], route.path[k + 1]);
                    if (delta < 0) {
                        twoOptSwap(route, i + 1, k);
                        improvement = true;
                    }
                }
            }
        }
    }

	public void threeOpt(Path route) {
		boolean improvement = true;
		while (improvement) {
			improvement = false;
			for (int i = 0; i <= N-5; i++) {
				for (int j = i+1; j <= N-3; j++) {
					for (int k = j+1; k <= N-1; k++) {
						int curDist = threeOptDistance(path, i,i+1,j,j+1,k,k+1);
						int caseNum = threeOptCase(path, i, j, k);

						if (caseNum != 0) {
							improvement = true;
						}

						switch (caseNum) {
							case 1:
								break;
							case 2:
								break;
							case 3:
								break;
							case 4:
								break;
							case 5:
								break;
							case 6:
								break;
							case 7:
								break;
						}
					}
				}
			}
		}
	}

	public int threeOptCase(Path route, int curDist, int i, int j, int k) {
		return 1;
	}

	public int threeOptDistance(Path route, int s1, int f1, int s2, int f2, int s3, int f3) {
		int sum = 0;
		sum += getDistance(route.path[s1], route.path[f1]);
		sum += getDistance(route.path[s2], route.path[f2]);
		sum += getDistance(route.path[s3], route.path[f3]);
		return sum;
	}
}
