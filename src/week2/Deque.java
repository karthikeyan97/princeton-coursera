package week2;

import java.util.Iterator;
import java.util.Scanner;

public class Deque<Item> implements Iterable<Item>
{
	private Node head;
	private Node tail;
	private  int size;
	public Deque()
	{
		head = null;
		tail = null;
	}

	@Override public Iterator<Item> iterator()
	{
		return new DequeIterator(head);
	}

	private class DequeIterator<Item> implements Iterator
	{
		Node currentNode;
		DequeIterator(Node node)
		{
			currentNode = node;
		}
		public boolean hasNext()
		{
			return currentNode != null ;
		}

		@Override public Item next()
		{
			if(currentNode == null)
			{
				throw new java.util.NoSuchElementException();
			}
			Item value = (Item) currentNode.value;
			currentNode=currentNode.next;
			return value;
		}

		@Override public void remove()
		{
			throw  new UnsupportedOperationException();
		}
	}

	private class Node{
		Item value;
		Node prev;
		Node next;
	}

	// is the dequeue empty?
	public boolean isEmpty()
	{
		return head == null;
	}

	// return the number of items on the dequeue
	public int size()
	{
		return size;
	}

	// add the item to the front
	public void addFirst(Item item)
	{
		if(item == null)
		{
			throw  new IllegalArgumentException();
		}
		Node currentNode = new Node();
		currentNode.value = item;
		currentNode.next = head;
		currentNode.prev = null;
		if(head == null)
		{
			tail = currentNode;
		}
		else
		{
			head.prev = currentNode;
		}
		head=currentNode;
		size++;
	}

	// add the item to the back
	public void addLast(Item item)
	{
		if(item == null)
		{
			throw  new IllegalArgumentException();
		}
		Node currentNode = new Node();
		currentNode.value = item;
		currentNode.prev = tail;
		if(tail != null)
		{
			tail.next = currentNode;
		}
		else
		{
			head = currentNode;
		}
		currentNode.next=null;
		tail=currentNode;
		size++;
	}

	// remove and return the item from the front
	public Item removeFirst()
	{
		Item value = null;
		if(head!=null)
		{
			if(head.next == null)
			{
				tail = null;
			}
			else
			{
				head.next.prev=null;
			}
			value = head.value;
			head = head.next;
			size--;
		}
		else
		{
			throw  new java.util.NoSuchElementException();
		}
		return value;
	}

	// remove and return the item from the back
	public Item removeLast()
	{
		Item value = null;
		if(tail!=null)
		{
			value = tail.value;
			tail = tail.prev;
			if(tail == null)
			{
				head = null;
			}
			else
			{
				tail.next = null;
			}
			size--;
		}
		else
		{
			throw  new java.util.NoSuchElementException();
		}
		return value;
	}

	// return an iterator over items in order from front to bac
	// unit testing (required)
	public static void main(String[] args)
	{
		Deque<Integer> que = new Deque<>();
		Integer userChoice = 1;
		while(userChoice!=5)
		{
			System.out.println("Enter the choice:");
			System.out.println("1.AddFirst");
			System.out.println("2.AddLast");
			System.out.println("3.removeFirst");
			System.out.println("4.removeLast");
			System.out.println("5.exit");
			userChoice = new Scanner(System.in).nextInt();

			Integer userInput = 0;
			if(!userChoice.equals(3) && !userChoice.equals(4) && !userChoice.equals(5))
			{
				userInput = new Scanner(System.in).nextInt();
			}
			switch(userChoice)
			{
				case 1:
					que.addFirst(userInput);
					break;
				case 2:
					que.addLast(userInput);
					break;
				case 3:
					que.removeFirst();
					break;
				case 4:
					que.removeLast();
					break;
			}
		}
	}
}
