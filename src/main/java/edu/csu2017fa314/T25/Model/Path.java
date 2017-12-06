package edu.csu2017fa314.T25.Model;

import java.util.ArrayList;

public class Path {
    public Point path[];
    private int totalCost = 0;
    private int index;

	private static boolean miles = true;

    public Path(int N) {
        path = new Point[N+1];
		index = 0;
    }

	public Path(Point [] newPath, int n) {
		path = new Point[n+1];
		for (index = 0; index < newPath.length; index++){
			path[index] = newPath[index];
		}
	}

    public Path() {
        totalCost = Integer.MAX_VALUE;
    }

	public static void setMiles(boolean m) {
		miles = m;
	}

    public int getCost() {
		if (totalCost == Integer.MAX_VALUE) {
			return totalCost;
		}
		int tCost = 0;
		for (int i = 0; i < path.length - 1; i++) {
			tCost += NearestNeighbor.computeDistance(path[i], path[i+1], miles);
		}
		return tCost;
    }

    public Point[] getPath() {
        return path;
    }

	public ArrayList<TripLeg> getLegs() {
		ArrayList<TripLeg> legs = new ArrayList<TripLeg>();
		int tCost = 0;
		//printPath();
		for (int i = 0; i < path.length - 1; i++) {
			tCost = NearestNeighbor.computeDistance(path[i], path[i+1], miles);
			legs.add(new TripLeg(path[i], path[i+1], tCost));
		}
		return legs;

	}

	public void printPath() {
		for (int i = 0; i < index; i++) {
			Point p = path[i];
			if (p != null) {
				System.out.println(p.attributes[0]);
			}
			else {
				System.out.println("NULL ID");
			}
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
        return index;
    }

    public void returnHome() {
        // todo hardcoded miles
        //int distance = NearestNeighbor.computeDistance(path[0], path[N - 2], miles);
		addPoint(path[0]);
    }

	public boolean equalsPath(Path comp) {
		if (size() != comp.size()) {
			System.out.println("Unequal lengths");
			return false;
		}
		for (int i = 0; i < size(); i++) {
			if (!path[i].equals(comp.path[i])){
				return false;
			}
		}
		return true;
	}

}
