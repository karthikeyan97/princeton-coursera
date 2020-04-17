package week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.Scanner;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private  int size = 0;
	private int capacity = 2;
	private Item[] items;
	public RandomizedQueue()
	{
		items = (Item[]) new Object[2];
	}

	@Override public Iterator<Item> iterator()
	{
		return new RandomizedQueueIterator(this);
	}

	private class RandomizedQueueIterator<Item> implements Iterator
	{
		Item[] arr;
		int size;
		RandomizedQueueIterator(RandomizedQueue queue)
		{

			this.arr = (Item[]) new Object[queue.size];
			this.size = queue.size;
			for(int i=0;i<queue.size;i++)
			{
				this.arr[i] = (Item)queue.items[i];
			}
		}
		public boolean hasNext()
		{
			return this.size != 0;
		}

		@Override public Item next()
		{
			if(this.size == 0)
			{
				throw new java.util.NoSuchElementException();
			}
			Integer pos = StdRandom.uniform(size);
			Item temp = arr[pos];
			arr[pos] = arr[size-1];
			arr[size-1] = null;
			this.size--;
			return temp;
		}

		@Override public void remove()
		{
			throw  new UnsupportedOperationException();
		}
	}

	// is the RandomizedQueue empty?
	public boolean isEmpty()
	{
		return size == 0;
	}

	// return the number of items on the RandomizedQueue
	public int size()
	{
		return size;
	}

	// add the item to the front
	public void enqueue(Item item)
	{
		if(item == null)
		{
			throw  new IllegalArgumentException();
		}
		if(capacity-1 == size)
		{
			capacity = capacity *2;
			Item[] newItem = (Item[]) new Object[capacity];
			for(int i=0;i<size;i++)
			{
				newItem[i] = items[i];
			}
			items = newItem;
		}
		items[size] = item;
		size++;
	}

	public Item dequeue()
	{
		if(size == 0)
		{
			throw  new java.util.NoSuchElementException();
		}
		else
		{
			Integer pos = StdRandom.uniform(size);
			if(capacity == 2*(size+1))
			{
				capacity = capacity /2;
				Item[] newItem = (Item[]) new Object[capacity];
				for(int i=0;i<size;i++)
				{
					newItem[i] = items[i];
				}
				items = newItem;
			}
			Item temp = items[pos];
			items[pos] = items[size-1];
			items[size-1] = null;
			size--;
			return temp;
		}
	}

	public Item sample()
	{
		if(size == 0)
		{
			throw  new java.util.NoSuchElementException();
		}
		else
		{
			Integer pos = StdRandom.uniform(size);
			return items[pos];
		}
	}

	/*public void printQueue()
	{
		Iterator<Item> items = this.iterator();
		while(items.hasNext())
		{
			System.out.println(items.next());
		}
	}*/

	// return an iterator over items in order from front to bac
	// unit testing (required)
	public static void main(String[] args)
	{
		RandomizedQueue<String> que = new RandomizedQueue<>();
		Integer userChoice = 1;
		while(userChoice!=5)
		{
			System.out.println("Enter the choice:");
			System.out.println("1.enque");
			System.out.println("2.deque");
			System.out.println("3.exit");
			userChoice = new Scanner(System.in).nextInt();

			String userInput = "";
			if(!userChoice.equals(2) && !userChoice.equals(3))
			{
				userInput = new Scanner(System.in).next();
			}
			switch(userChoice)
			{
				case 1:
					que.enqueue(userInput);
					break;
				case 2:
					System.out.println(que.dequeue());
					break;
			}


		}
	}

}
