import java.util.*;
/* Author: Bhavisha Anil Gaikwad
 * Date: 11 May 2026
 * Desription: This class sets the edge class with the elements of an edge
*/
public class DSAGraphEdge
{
	private String label; //name of the edge
	private DSAGraphNode from; //start node
	private DSAGraphNode to; //end Node
	private int weight; //driving time
	
	
	public DSAGraphEdge(DSAGraphNode fromVertex, DSAGraphNode toVertex, String inLabel, int inWeight)
	{
		from = fromVertex;
		to = toVertex;
		label = from.getLabel() + "-" + to.getLabel();
		weight = inWeight;
	}
	public String getLabel()
	{
		return label;
	}
	public DSAGraphNode getFrom()
	{
		return from;
	}
	public DSAGraphNode getTo()
	{
		return to;
	}
	public int getWeight()
	{
		return weight;
	}
	
	public String toString()
	{
		return label;
	}
}
