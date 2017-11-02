package edu.csu2017fa314.T25.Model;

import java.util.ArrayList;

public class Path {
    public Point path[];
    private int totalCost = 0;
    private int index = 0;
    private int N;

    public Path(int N) {
        this.N = N + 1;
        path = new Point[this.N];
    }

    public Path() {
        totalCost = Integer.MAX_VALUE;
    }

    public int getCost() {
		if (totalCost == Integer.MAX_VALUE) {
			return totalCost;
		}
		int tCost = 0;
		for (int i = 0; i < path.length - 1; i++) {
			tCost += NearestNeighbor.computeDistance(path[i], path[i+1], true);
		}
		return tCost;
    }

    public Point[] getPath() {
        return path;
    }

	public ArrayList<TripLeg> getLegs() {
		ArrayList<TripLeg> legs = new ArrayList<TripLeg>();
		int tCost = 0;
		printPath();
		for (int i = 0; i < path.length - 1; i++) {
			tCost = NearestNeighbor.computeDistance(path[i], path[i+1], true);
			legs.add(new TripLeg(path[i], path[i+1], tCost));
		}
		return legs;

	}

	private void printPath() {
		for (Point p : path) {
			System.out.println(p.id);
		}
	}

    public void add(Point point, int cost) {
		totalCost += cost;
        path[index] = point;

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
