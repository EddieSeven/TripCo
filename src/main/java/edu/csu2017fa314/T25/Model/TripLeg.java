package edu.csu2017fa314.T25.Model;

public class TripLeg {
	public Point start, end;
    int distance;

    public TripLeg(Point start, Point end, int distance) {
		this.start = start;
		this.end = end;
		this.distance = distance;
    }
}
