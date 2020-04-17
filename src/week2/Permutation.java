package week2;

import edu.princeton.cs.algs4.StdIn;

public class Permutation
{
	public static void main(String args[])
	{
		RandomizedQueue<String> queue = new RandomizedQueue<>();
		Integer k = Integer.parseInt(args[0]);
		while(!StdIn.isEmpty())
		{
			queue.enqueue(StdIn.readString());
		}
		while(k>0)
		{
			System.out.println(queue.dequeue());
			k--;
		}

	}
}
