package week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints
{
	private LineSegment[] lineSegments;
	private int count=0;
	public FastCollinearPoints(Point[] points)
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
		Point[] pointCopy = Arrays.copyOf(points,points.length);
		lineSegments = new LineSegment[pointCopy.length*(pointCopy.length-1)];
		for(int i=0;i<pointCopy.length-1;i++)
		{
			Double[] slopes=new Double[pointCopy.length-i-1];
			Comparator<Point> slopeComparator = pointCopy[i].slopeOrder();
			Arrays.sort(pointCopy,i+1,pointCopy.length,slopeComparator);
			int count=0;
			Double prevSlope = pointCopy[i].slopeTo(pointCopy[i+1]);
			int min=i;
			int max=i;
			for(int j=i+1;j<pointCopy.length;j++)
			{
				Double slope = pointCopy[i].slopeTo(pointCopy[j]);
				if(slope.compareTo(Double.NEGATIVE_INFINITY ) == 0)
				{
					throw new IllegalArgumentException();
				}
				if(prevSlope.compareTo(slope) == 0)
				{
					count++;
				}
				else
				{
					if(count >= 3)
					{
						lineSegments[this.count]=new LineSegment(pointCopy[min],pointCopy[max]);
						this.count++;
					}
					min=i;
					max=i;
					count=1;
					prevSlope=pointCopy[i].slopeTo(pointCopy[j]);
				}
				if(pointCopy[min].compareTo(pointCopy[j]) < 0)
				{
					min = j;
				}
				if(pointCopy[max].compareTo(pointCopy[j]) > 0)
				{
					max = j;
				}
			}
			if(count >= 3)
			{
				lineSegments[this.count]=new LineSegment(pointCopy[min],pointCopy[max]);
				this.count++;
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
		FastCollinearPoints collinear = new FastCollinearPoints(points);
		for (Integer i =0;i< collinear.numberOfSegments();i++) {
			StdOut.println(collinear.segments()[i]);
			collinear.segments()[i].draw();
		}
		StdDraw.show();
	}
}
