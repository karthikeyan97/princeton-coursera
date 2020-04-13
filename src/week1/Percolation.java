package week1;

import edu.princeton.cs.algs4.QuickUnionUF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{
	private boolean [][][] state;
	private boolean isPercolates;
	private int noOfOpenSites = 0;
	private final WeightedQuickUnionUF qf;
	private final int n;
	public Percolation(int n)
	{
		if(n<=0)
		{
			throw new IllegalArgumentException();
		}
		this.state=new boolean[n][n][3];

		this.isPercolates=false;
		this.n = n;
		this.qf = new WeightedQuickUnionUF(n*n);
		for(int i=0;i<n;i++)
		{
			for (int j = 0; j <n; j++)
			{

				state[i][j]=new boolean[]{false,false,false};

			}
		}

	}

	// opens the site (row, col) if it is not open already
	public void open(int row, int col)
	{
		if (row <= 0 || col <= 0 || row > n || col > n)
		{
			throw new IllegalArgumentException();
		}

		if (isOpen(row,col))
		{
			return;
		}
		noOfOpenSites++;
		boolean isConnectedToTop = false;
		boolean isConnectedToBottom = false;
		if(row == 1)
		{
			isConnectedToTop = true;
		}
		if(row == n)
		{
			isConnectedToBottom = true;
		}

		if(row != 1 && isOpen(row-1,col))
		{
			qf.union((row-1)*n+col-1,(row-2)*n+col-1);
			isConnectedToTop = isConnectedToTop || state[row-2][col-1][1];
			isConnectedToBottom = isConnectedToBottom || state[row-2][col-1][2];

		}
		if(row != n && isOpen(row+1,col))
		{
			qf.union((row-1)*n+col-1,(row)*n+col-1);
			isConnectedToTop = isConnectedToTop || state[row][col-1][1];
			isConnectedToBottom = isConnectedToBottom || state[row][col-1][2];

		}
		if(col != 1 && isOpen(row,col-1))
		{
			qf.union((row-1)*n+col-1,(row-1)*n+col-2);
			isConnectedToTop = isConnectedToTop || state[row-1][col-2][1];
			isConnectedToBottom = isConnectedToBottom || state[row-1][col-2][2];

		}
		if(col != n && isOpen(row,col+1))
		{
			qf.union((row-1)*n+col-1,(row-1)*n+col);
			isConnectedToTop = isConnectedToTop || state[row-1][col][1];
			isConnectedToBottom = isConnectedToBottom || state[row-1][col][2];
		}
		if(row != 1)
		{
			int root = qf.find((row-2)*n+col-1);
			int rrow = root/n;
			int rcol = root%n;
			if(isConnectedToTop)
			{
				state[rrow][rcol][1]=true;
			}
			if(isConnectedToBottom)
			{
				state[rrow][rcol][2]=true;
			}

		}
		if(row != n)
		{
			int root = qf.find((row)*n+col-1);
			int rrow = root/n;
			int rcol = root%n;
			if(isConnectedToTop)
			{
				state[rrow][rcol][1]=true;
			}
			if(isConnectedToBottom)
			{
				state[rrow][rcol][2]=true;
			}
		}
		if(col != 1)
		{
			int root = qf.find((row-1)*n+col-2);
			int rrow = root/n;
			int rcol = root%n;
			if(isConnectedToTop)
			{
				state[rrow][rcol][1]=true;
			}
			if(isConnectedToBottom)
			{
				state[rrow][rcol][2]=true;
			}
		}
		if(col != n)
		{
			int root = qf.find((row-1)*n+col);
			int rrow = root/n;
			int rcol = root%n;
			if(isConnectedToTop)
			{
				state[rrow][rcol][1]=true;
			}
			if(isConnectedToBottom)
			{
				state[rrow][rcol][2]=true;
			}
		}
		if(isConnectedToTop && isConnectedToBottom)
		{
			isPercolates = true;
		}

		state[row-1][col-1][0]= true;
		state[row-1][col-1][1]= isConnectedToTop;
		state[row-1][col-1][2]= isConnectedToBottom;
	}

	// is the site (row, col) open?
	public boolean isOpen(int row, int col)
	{
		if (row <= 0 || col <= 0 || row > n || col > n)
		{
			throw new IllegalArgumentException();
		}
		return state[row-1][col-1][0];
	}

	// is the site (row, col) full?
	public boolean isFull(int row, int col)
	{
		if (row <= 0 || col <= 0 || row > n || col > n)
		{
			throw new IllegalArgumentException();
		}
		if(!isOpen(row,col))
		{
			return false;
		}
		int root = qf.find((row-1)*n+col-1);
		int rrow = root/n;
		int rcol = root%n;
		return state[rrow][rcol][1];
	}

	// returns the number of open sites
	public int numberOfOpenSites()
	{
		return noOfOpenSites;
	}

	// does the system percolate?
	public boolean percolates()
	{
		return isPercolates;
	}
}
