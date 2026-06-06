import java.util.*;

/* Author: Bhavisha Anil Gaikwad
 * Date: 11 May 2026
 * Desription: This class has the major algorithms of Graph Module
*/
public class DSAGraph
{
	private DSALinkedList vertices; //linkedList for vertices
	private DSALinkedList edges; //linked list for the edges
	
	public DSAGraph()
	{
		vertices = new DSALinkedList();
		edges = new DSALinkedList();
	}
	
	public void addVertex(String label)
	{
		if(!hasVertex(label))
		{
			DSAGraphNode newNode= new DSAGraphNode(label);
			vertices.insertLast(newNode);
		}
	}
	
	public boolean hasVertex (String label)
	{	
		return getVertex(label) !=null;
	}
	
	public void addEdge(String v1, String v2, String label, int weight)
	{
		DSAGraphNode vA = getVertex(v1);
		DSAGraphNode vB = getVertex(v2);
		
		if(vA != null && vB != null)
		{	
			DSAGraphEdge edgeAB = new DSAGraphEdge(vA,vB,label,weight);
			DSAGraphEdge edgeBA = new DSAGraphEdge(vB,vA,label,weight);
			
			vA.addEdge(edgeAB); //add edge to LL of vA
			vB.addEdge(edgeBA); //add edge to LL of vb
			edges.insertLast(edgeAB); //add this in edges
		}
	}
	
	public DSAGraphNode getVertex(String label)
	{
		DSAListNode cur = vertices.getHead();
		while (cur!=null)
		{
			DSAGraphNode v = (DSAGraphNode)cur.getValue();
			if (v.getLabel().equalsIgnoreCase(label.trim()))
			{
				return v;
			}
			cur = cur.getNext();
		}
		return null;
	}
	
	public int getVertexCount()
	{
		return vertices.getCount();
	}
	
	public int getEdgeCount()
	{
		return edges.getCount();
	}
	
	public void clearVisited()
	{
		DSAListNode cur = vertices.getHead();
		while (cur!=null)
		{
			DSAGraphNode curNode = (DSAGraphNode) cur.getValue();
			curNode.clearVisited();
			cur = cur.getNext();
		}
	}

	public boolean isAdjacent (String label1, String label2)
	{
		DSAGraphNode v1 = getVertex(label1);
		DSAGraphNode v2 = getVertex(label2);
		return (v1 != null && v2 != null && v1.isAdjacent(v2));
	}
	
	public DSALinkedList getAdjacent(String label)
	{
		DSAGraphNode v = getVertex(label);
		return (v != null)? v.getAdjacent(): new DSALinkedList();
	}
	
	public void graphStructure()
	{
		System.out.println("===Graph Structure===");
		DSAListNode curNode = vertices.getHead();
		while (curNode != null)
		{
			DSAGraphNode v = (DSAGraphNode) curNode.getValue();
			System.out.print(v.getLabel() + ": ");
			
			DSALinkedList adj = v.getAdjacent(); //the edges that connect to v
			DSAListNode edgeNode = adj.getHead(); //the first in list
			
			while(edgeNode !=null)
			{
				DSAGraphEdge edgeDetails = (DSAGraphEdge)edgeNode.getValue();
				String dest = edgeDetails.getTo().getLabel();
				int time = edgeDetails. getWeight();
				
				System.out.print(dest + "(" + time + "), ");
				edgeNode = edgeNode.getNext();
			}
			System.out.println();
			curNode =curNode.getNext();
		}
	}
	public void bfs (String starting)
	{
		System.out.println("===BFS===");
		DSAGraphNode start = getVertex(starting);
		if(start == null)
		{
			return;
		}
		clearVisited();
		
		DSALinkedList queue = new DSALinkedList();
		start.setVisited(true);
		queue.insertLast(start);
		
		
		int level = 0;
		
		while (!queue.isEmpty())
		{
			int queueSize = queue.getCount();
			for (int i=0; i<queueSize;i++)
			{
				DSAGraphNode v = (DSAGraphNode) queue.removeFirst();
				
				DSAListNode cur = v.getAdjacent().getHead();
				while (cur !=null)
				{
					DSAGraphEdge edge = (DSAGraphEdge) cur.getValue();
					DSAGraphNode adj = edge.getTo();
					if(!adj.getVisited())
					{
						adj.setVisited(true);
						queue.insertLast(adj);
						System.out.println(v.getLabel() + ", " + adj.getLabel() + "(Level " + level + ")");
					}
					
					cur = cur.getNext();
				}
			}
			level++;
		}
	}
	
	public boolean hasCycle()
	{
		clearVisited();
		String[] cyclePath = {""};
		
		DSAListNode cur = vertices.getHead(); //LL of vertex
		while (cur!=null)
		{
			DSAGraphNode node = (DSAGraphNode) cur.getValue(); //get whatever inside that vertex
			if(!node.getVisited())
			{
				if(dfsCycle(node, null, cyclePath))
				{
					System.out.println("Cycle Detected: " + cyclePath[0]);
					return true;
				}
			}
			cur= cur.getNext();
		}
		return false;
	}
	
	public boolean dfsCycle(DSAGraphNode cur, DSAGraphNode parent, String[] path)
	{
		cur.setVisited(true);
		DSAListNode curEdge = cur.getAdjacent().getHead(); //node A LL head
		while(curEdge!=null)
		{
			DSAGraphEdge edge = (DSAGraphEdge) curEdge.getValue();
			DSAGraphNode adj = edge.getTo();
			
			if (!adj.getVisited())
			{
				if(dfsCycle(adj,cur,path)) //go deeper
				{
					path[0] = cur.getLabel() + " -> " + path[0];
					return true;
				}
			}
			else if (adj != parent) //to check where does adj come from
			{
				path[0] = cur.getLabel() + " -> " + adj.getLabel();
				return true;
			}
			curEdge = curEdge.getNext();
		}
		return false;
	}
	
	public int dijkstra(String start, String end)
	{
		clearVisited();
		int time;
		
		DSAListNode cur = vertices.getHead();
		while(cur != null) //set all nodes distance to infinity
		{
			DSAGraphNode node = (DSAGraphNode)cur.getValue();
			node.distance = Integer.MAX_VALUE;
			node.prev = null;
			cur = cur.getNext();
		}
		
		DSAGraphNode startNode = getVertex(start);
		if (startNode!=null) //set distance of starting node to 0
		{
			startNode.distance = 0;
		}
		else
		{
			return Integer.MAX_VALUE;
		}
		
		for (int i=0; i<vertices.getCount();i++)
		{
			DSAGraphNode u = findSmallestDistance();
			if (u==null|| u.distance == Integer.MAX_VALUE)
			{
				break;
			}
			
			u.setVisited(true);
			
			DSAListNode currentEdge = u.getAdjacent().getHead();
			while(currentEdge != null)
			{
				DSAGraphEdge edge = (DSAGraphEdge) currentEdge.getValue();
				DSAGraphNode v = edge.getTo();
				
				if(!v.getVisited() && (u.distance +edge.getWeight() < v.distance))
				{
					v.distance = u.distance + edge.getWeight();
					v.prev=u;
				}
				currentEdge = currentEdge.getNext();
			}
		}
		
		DSAGraphNode dest = getVertex(end);
		
		if(dest ==null)
		{
			time = Integer.MAX_VALUE;
		}
		else
		{
			time = dest.distance;
		}
		return time;
	}
	
	private DSAGraphNode findSmallestDistance()
	{
		DSAGraphNode smallest = null;
		DSAListNode cur = vertices.getHead();
		while (cur!= null)
		{
			DSAGraphNode n = (DSAGraphNode)cur.getValue();
			if (!n.getVisited() && (smallest == null || n.distance < smallest.distance))
			{
				smallest = n;
			}
			cur = cur.getNext();
		}
		return smallest;
	}
	
	private void printPath (String start, String end)
	{
		DSAGraphNode dest = getVertex(end);
		
		if (dest==null || dest.distance == Integer.MAX_VALUE)
		{
			System.out.println("No path found");
			return;
		}
		
		String path = "";
		DSAGraphNode cur = dest;
		while (cur!=null)
		{
			if (path.equals(""))
			{
				path = cur.getLabel();
			}
			else
			{
				path = cur.getLabel() + " -> "+path;
			}
			cur = cur.prev;
		}
		
		System.out.println("Start: " + start);
		System.out.println("End: " + end);
		System.out.println("Path: " + path);
		System.out.println("Shortest distance to destination: " + dest.distance + " min");
	}
	
	public static void main(String[] args)
	{
		DSAGraph obj = new DSAGraph();
		
		obj.addVertex("CBD");
		obj.addVertex("Airport");
		obj.addVertex("Uni");
		obj.addVertex("SuburbSouth");
		obj.addVertex("SuburbNorth");
		obj.addVertex("Mall");
		obj.addVertex("Cafe");
		obj.addVertex("Hospital");
		obj.addVertex("Park");
		
		obj.addEdge("CBD", "Airport", "Road1", 20);
		obj.addEdge("CBD", "Uni", "Road2", 13);
		obj.addEdge("Airport", "Uni", "Road3", 35);
		obj.addEdge("Airport", "SuburbSouth", "Road4", 17);
		obj.addEdge("Uni", "SuburbNorth", "Road5", 10);
		obj.addEdge("Uni", "Cafe", "Road6", 10);
		obj.addEdge("Cafe", "SuburbNorth", "Road7", 25);
		obj.addEdge("Cafe", "Hospital", "Road8", 30);
		obj.addEdge("Hospital", "SuburbNorth", "Road9", 23);
		obj.addEdge("SuburbSouth", "Mall", "Road10", 13);
		obj.addEdge("Mall", "SuburbNorth", "Road11", 12);
		
		obj.graphStructure();
		System.out.println();
		obj.bfs("CBD");
		System.out.println();
		System.out.println("===DFS and Cycle Detection===");
		obj.hasCycle();
		System.out.println();
		System.out.println("===Shortest Distance===");
		obj.dijkstra("CBD", "Hospital");
		obj.printPath("CBD", "Hospital");
		System.out.println();
	}
		
		
}
			 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
			
				
	
	
