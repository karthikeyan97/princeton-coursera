package week1;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats
{
	private final double[] results;
	private final double mean;
	private final double stddev;
	private final int trials;
	public PercolationStats(int n, int trials)
	{
		if(n<=0 || trials <= 0)
		{
			throw new IllegalArgumentException();
		}
		this.results=new double[trials];
		for(int i=0;i<trials;i++)
		{
			Percolation per=new Percolation(n);
			boolean isPercolated = false;
			while(isPercolated == false)
			{
				Integer cell = StdRandom.uniform(n*n);
				PercolationVisualizer.draw(per, n);
				StdDraw.show();
				StdDraw.pause(100);
				System.out.printf("%d %d\n",(cell/n)+1,(cell%n)+1);
				per.open((cell/n)+1,(cell%n)+1);
				isPercolated = per.percolates();
			}
			PercolationVisualizer.draw(per, n);
			StdDraw.show();
			StdDraw.pause(100);
			results[i] = ( new Double(per.numberOfOpenSites())/(n*n));
		}
		this.mean = StdStats.mean(results);
		this.stddev = StdStats.stddev(results);
		this.trials = trials;
	}

	// sample mean of percolation threshold
	public double mean()
	{
		return this.mean;
	}

	// sample standard deviation of percolation threshold
	public double stddev()
	{
		return this.stddev;
	}

	// low endpoint of 95% confidence interval
	public double confidenceLo()
	{
		return mean-(1.96*stddev/Math.sqrt(trials));
	}

	// high endpoint of 95% confidence interval
	public double confidenceHi()
	{
		return mean+(1.96*stddev/Math.sqrt(trials));
	}

	// test client (see below)
	public static void main(String[] args)
	{
		new PercolationStats(10,1);
	}
}
