package edu.csu2017fa314.T25.Model;

public class Result {
	public Point points[];
    public int size;
    private int index = 0;

	public Result(Point points[], int size) {
		this.points = points;
		this.size = size;
	}

	public void add(Point point) {
		points[index] = point;
		index++;
	}

	public void removeNull(){
		int newSize = 0;

		for (Point p: points){
		    if (p != null)
		        newSize++;
        }

        Point newPoints[] = new Point[newSize];
        int index = 0;

        for (Point p: points){
            if (p != null)
                newPoints[index] = p;
            index++;
        }

        this.points = newPoints;
        this.size = newSize;
	}
}
