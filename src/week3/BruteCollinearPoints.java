package week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BruteCollinearPoints {
	private LineSegment[] lineSegments;
	private int count=0;

	public BruteCollinearPoints(Point[] points)
	{
		if(points == null)
		{
			throw new IllegalArgumentException();
		}
		for(int i=0;i<points.length;i++)
		{
			if(points[i] == null)
			{
				throw new IllegalArgumentException();
			}
		}
		lineSegments = new LineSegment[points.length];
		int[] used = new int[points.length];
		int max;
		int min;
		for(int i=0;i<points.length;i++)
		{
			max=i;
			min=i;
			for(int j=i+1;j<points.length;j++)
			{
				Double jslope = points[i].slopeTo(points[j]);
				if(Double.NEGATIVE_INFINITY == jslope)
				{
					throw new IllegalArgumentException();
				}
				for(int k=j+1;k<points.length;k++)
				{
					Double kslope = points[j].slopeTo(points[k]);
					if(Double.NEGATIVE_INFINITY == kslope)
					{
						throw new IllegalArgumentException();
					}
					for(int l=k+1;l<points.length;l++)
					{
						Double lslope = points[k].slopeTo(points[l]);
						if(Double.NEGATIVE_INFINITY == lslope)
						{
							throw new IllegalArgumentException();
						}
						if(lslope.compareTo(kslope) == 0 && kslope.compareTo(jslope) == 0)
						{
							List<Point> list= Arrays.asList(new Point[]{points[i],points[j],points[k],points[l]});
							Collections.sort(list);
							lineSegments[count]= new LineSegment(list.get(0),list.get(3));
							count++;
						}
					}
				}
			}
		}
		LineSegment[] properLineSegement = new LineSegment[count];
		for(int i=0;i<count;i++)
		{
			properLineSegement[i] = lineSegments[i];
		}
		lineSegments = properLineSegement;
	}
	public int numberOfSegments()
	{
		return count;
	}
	public LineSegment[] segments()
	{
		return Arrays.copyOf(lineSegments,count);
	}

	public static void main(String[] args)
	{
		// read the n points from a file
		In in = new In(args[0]);
		int n = in.readInt();
		Point[] points = new Point[n];
		for (int i = 0; i < n; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		}

		// draw the points
		StdDraw.enableDoubleBuffering();
		StdDraw.setXscale(0,25000);
		StdDraw.setYscale(0,25000);
		for (Point p : points) {
			p.draw();
		}
		StdDraw.show();

		// print and draw the line segments
		BruteCollinearPoints collinear = new BruteCollinearPoints(points);
		for (Integer i =0;i< collinear.numberOfSegments();i++) {
			StdOut.println(collinear.segments()[i]);
			collinear.segments()[i].draw();
		}
		StdDraw.show();
	}
}