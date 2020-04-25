package week3;

import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

	private final int x;     // x-coordinate of this point
	private final int y;     // y-coordinate of this point

	/**
	 * Initializes a new point.
	 *
	 * @param  x the <em>x</em>-coordinate of the point
	 * @param  y the <em>y</em>-coordinate of the point
	 */
	public Point(int x, int y) {
		/* DO NOT MODIFY */
		this.x = x;
		this.y = y;
	}

	/**
	 * Draws this point to standard draw.
	 */
	public void draw() {
		/* DO NOT MODIFY */
		StdDraw.point(x, y);
		StdDraw.circle(x,y,100);
		StdDraw.text(x,y,this.toString());
	}

	/**
	 * Draws the line segment between this point and the specified point
	 * to standard draw.
	 *
	 * @param that the other point
	 */
	public void drawTo(Point that) {
		/* DO NOT MODIFY */
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	/**
	 * Returns the slope between this point and the specified point.
	 * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
	 * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
	 * +0.0 if the line segment connecting the two points is horizontal;
	 * Double.POSITIVE_INFINITY if the line segment is vertical;
	 * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
	 *
	 * @param  that the other point
	 * @return the slope between this point and the specified point
	 */

	public int compareTo(Point input)
	{
		if(this.y<input.y)
		{
			return -1;
		}
		else if(this.y>input.y)
		{
			return 1;
		}
		else
		{
			if(this.x<input.x)
			{
				return -1;
			}
			else if(this.x>input.x)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
	}
	public double slopeTo(Point input)
	{
		if(input.y == this.y)
		{
			if(input.x == this.x)
			{
				return Double.NEGATIVE_INFINITY;
			}
			else
			{
				return 0;
			}
		}
		else if(input.x == this.x)
		{
			return Double.POSITIVE_INFINITY;
		}
		else
		{
			return ((double) (input.y-this.y))/(input.x-this.x);
		}
	}
	public Comparator<Point> slopeOrder()
	{
		Comparator<Point> comp = new Comparator<Point>()
		{
			@Override public int compare(Point o1, Point o2)
			{
				Double s1=slopeTo(o1);
				Double s2=slopeTo(o2);
				if(s1 == Double.NEGATIVE_INFINITY)
				{
					if(s2 == Double.NEGATIVE_INFINITY)
					{
						return 0;
					}
					else
					{
						return -1;
					}
				}
				else if (s1 == Double.POSITIVE_INFINITY)
				{
					if(s2 == Double.POSITIVE_INFINITY)
					{
						return 0;
					}
					else
					{
						return 1;
					}
				}
				else if(s2.isInfinite())
				{
					if(s2 == Double.POSITIVE_INFINITY)
					{
						return -1;
					}
					else
					{
						return 1;
					}
				}
				else
				{
					return s1.compareTo(s2);
				}
			}
		};
		return comp;
	}


	/**
	 * Returns a string representation of this point.
	 * This method is provide for debugging;
	 * your program should not rely on the format of the string representation.
	 *
	 * @return a string representation of this point
	 */
	public String toString() {
		/* DO NOT MODIFY */
		return "(" + x + ", " + y + ")";
	}

	/**
	 * Unit tests the Point data type.
	 */
	public static void main(String[] args) {
		/* YOUR CODE HERE */
	}
}
