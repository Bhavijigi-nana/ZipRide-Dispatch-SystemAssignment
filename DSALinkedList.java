import java.util.*;
/* Author: Bhavisha Anil Gaikwad
 * Date: 8 May 2026
 * Desription: This class is for the linked list implementation,=. it has all the functions a linked list has. Implements double ended LL.
*/
public class DSALinkedList
{
	private DSAListNode head;
	private DSAListNode tail;
	private int count;
	
	public DSALinkedList()
	{
		head = null;
		tail =null;
		count = 0;
	}
	
	public boolean isEmpty()
	{
		return head == null;
	}
	
	public void insertLast(Object val)
	{
		DSAListNode newNode = new DSAListNode(val);
		if(isEmpty())
		{
			head = newNode;
			tail = newNode;
		}
		else
		{
			tail.setNext(newNode);
			tail = newNode;
		}
		count++;
	}
	
	public Object removeFirst()
	{
		if(isEmpty())
		{
			throw new IllegalArgumentException("List is empty");
		}
		Object val = head.getValue();
		head = head.getNext();
		count--;
		
		if(isEmpty())
		{
			tail = null;
		}
		return val;
	}
	
	public Object removeLast()
	{
		if(isEmpty())
		{
			throw new IllegalArgumentException("List is empty");
		}
		Object val = tail.getValue();
		
		if(head==tail)
		{
			head = null;
			tail = null;
		}
		else
		{
			DSAListNode cur = head;
			while (cur.getNext() != tail)
			{
				cur = cur.getNext();
			}
			cur.setNext(null);
			tail=cur;
		}
		count--;
		return val;
	}
	
	public void remove(Object val)
	{
		if(isEmpty())
		{
			return;
		}
		if (head.getValue().equals(val))
		{
			removeFirst();
			return;
		}
		DSAListNode cur= head;
		DSAListNode prev = null;
		
		while(cur!=null && !cur.getValue().equals(val))
		{
			prev = cur;
			cur = cur.getNext();
		}
		if (cur!=null)
		{
			prev.setNext(cur.getNext());
			if (cur==tail)
			{
				tail = prev;
			}
			count--;
		}
	}
	
	public Object peekLast()
	{
		if(isEmpty())
		{
			throw new IllegalArgumentException("List is empty");
		}
		return tail.getValue();
	}
	public int getCount()
	{
		return count;
	}
	public DSAListNode getHead()
	{
		return head;
	}
	public boolean has(Object val)
	{
		DSAListNode cur = head;
		while (cur!=null)
		{
			if (cur.getValue().equals(val))
			{
				return true;
			}
			cur = cur.getNext();
		}
		return false;
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
			
	
