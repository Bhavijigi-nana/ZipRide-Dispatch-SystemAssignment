import java.util.*;
/* Author: Bhavisha Anil Gaikwad
 * Date: 11 May 2026
 * Desription: This class holds the elements of graph nodes
*/
public class DSAGraphNode
{
	private String label;
	private DSALinkedList adjacentLinks;
	private boolean visited;
	public int distance;
	public DSAGraphNode prev;
	
	public DSAGraphNode (String inLabel)
	{
		label = inLabel;
		adjacentLinks = new DSALinkedList();
		visited = false;
		distance = Integer.MAX_VALUE;
		prev = null;
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public DSALinkedList getAdjacent()
	{
		return adjacentLinks;
	}
	
	public void addEdge( DSAGraphEdge edge)
	{
		
		adjacentLinks.insertLast(edge);
	}

	public void setVisited(boolean inVisit)
	{
		visited = inVisit;
	}
	
	public void clearVisited()
	{
		visited = false;
	}
	
	public boolean getVisited()
	{
		return visited;
	}
	
	public int getDegree()
	{
		return adjacentLinks.getCount();
	}
	
	public boolean isAdjacent(DSAGraphNode vertex)
	{
		boolean found = false;
		DSAListNode cur = adjacentLinks.getHead();
		while(cur!=null && !found)
		{
			DSAGraphEdge edge = (DSAGraphEdge)cur.getValue();
			
			if (edge.getTo().equals(vertex)) //we check if that edgeTO is the same as the node we want to check if is adjacent
			{
				found = true;
			}
			cur = cur.getNext();
		}
		return found;
	}
	public String toString()
	{
		return label;
	}
}
	
