import java.util.*;
/* Author: Bhavisha Anil Gaikwad
 * Date: 8 May 2026
 * Desription: This class has the elements for a node in the linked list
*/
public class DSAListNode
{
	private Object val;
	private DSAListNode next;
	
	public DSAListNode (Object inVal)
	{
		val = inVal;
		next=null;
	}
	public Object getValue()
	{
		return val;
	}
	public DSAListNode getNext()
	{
		return next;
	}
	public void setNext(DSAListNode newNext)
	{
		next=newNext;
	}
}

