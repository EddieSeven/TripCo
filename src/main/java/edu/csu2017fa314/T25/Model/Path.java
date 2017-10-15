package edu.csu2017fa314.T25.Model;

public class Path {
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
