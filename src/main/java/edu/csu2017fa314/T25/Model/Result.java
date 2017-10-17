package edu.csu2017fa314.T25.Model;

import java.util.ArrayList;

public class Result {
	public ArrayList<Point> points;
    int total;

	public Result(ArrayList<Point> pts) {
		points = pts;
		total = pts.size();
	}
}
