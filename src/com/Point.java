package com;

public class Point {

	double x;
	double y;
	

	public Point(double newx, double newy)
	{
		x = newx;
		y = newy;
	}
	
	public Point(int newx, int newy)
	{
		x = (double)newx;
		y = (double)newy;
	}
}
