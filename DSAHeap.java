import java.util.*;

/* Author: Bhavisha Anil Gaikwad
 * Date: 20 May 2026
 * Desription: This class has the major features of Heap Module
*/
public class DSAHeap
{
	private DSAHeapEntry[] heapArray;
	private int count;
	private final int maxCap;
	
	public DSAHeap(int max)
	{
		this.maxCap = max;
		this.heapArray = new DSAHeapEntry[maxCap];
		count =0;
	}
	public int getCount()
	{
		return count;
	}
	
	public void insert(int priority, Object val)
	{
		if (count>=maxCap)
		{
			System.out.println("Heap Array is full");
			return;
		}
		
		DSAHeapEntry newEntry = new DSAHeapEntry(priority, val); //make new entry
		heapArray[count] = newEntry;
		trickleUp(count); //arrange the heap Array
		count++;
		
		System.out.println("Heap after insert");
		display();
	}
	
	private void trickleUp(int curIdx)
	{
		int parentIdx = (curIdx - 1) / 2;
		
		while (curIdx > 0 && heapArray[curIdx].getPriority() > heapArray[parentIdx].getPriority())
		{
			//Swap cur with parent
			DSAHeapEntry temp = heapArray[parentIdx];
			heapArray[parentIdx] = heapArray[curIdx];
			heapArray[curIdx] = temp;
			curIdx = parentIdx;
			parentIdx = (curIdx - 1) / 2;
		}
	}
	public DSAHeapEntry peek()
	{
		if (count==0)
		{
			return null;
		}
		else
		{
			return heapArray[0];
		}
	}
	
	public DSAHeapEntry remove()
	{
		if (count == 0)
		{
			System.out.println("Heap is empty");
			return null;
		}
		
		DSAHeapEntry root = heapArray[0];
		count--;
		heapArray[0] = heapArray[count];
		heapArray[count] = null;
		trickleDown(0);
		return root;
	}
	private void trickleDown(int curIdx)
	{
		int lChildIdx = curIdx * 2 + 1;
		int rChildIdx = lChildIdx + 1;
		boolean keepGoing = true;
		while (keepGoing && lChildIdx < count)
		{
			keepGoing = false;
			int largeIdx = lChildIdx;
			
			if (rChildIdx < count)
			{
				if (heapArray[lChildIdx].getPriority() < heapArray[rChildIdx].getPriority())
				{
					largeIdx = rChildIdx;
				}
			}
			
			if (heapArray[largeIdx].getPriority() > heapArray[curIdx].getPriority())
			{
				//Swap
				DSAHeapEntry temp = heapArray[largeIdx];
				heapArray[largeIdx] = heapArray[curIdx];
				heapArray[curIdx] = temp;
				keepGoing = true;
				curIdx = largeIdx;
				lChildIdx = curIdx * 2 + 1;
				rChildIdx = lChildIdx + 1;
			}
		}
	}
	
	public void display()
	{
		if (count == 0)
		{
			System.out.println("Heap is empty");
			return;
		}
	
		System.out.println("Heap Display");
		System.out.println("Total items: " + count);
		System.out.println("Tree Structure (level by level):");
		int itemsPerLevel = 1;
		int itemsPrinted = 0;
		int level = 1;
		
		while (itemsPrinted < count)
		{
			System.out.print("Level " + level + ": ");
			for (int i = 0; i < itemsPerLevel && itemsPrinted < count; i++)
			{
				System.out.print( heapArray[itemsPrinted].getPriority() + ",");
				itemsPrinted++;
			}
			
			System.out.println();
			itemsPerLevel *= 2;
			level++;
		}
		System.out.print("HeapArray representation: [");
		for (int i = 0; i < count; i++)
		{
			System.out.print(heapArray[i].getPriority() + ", ");
		}
		System.out.println("]");
		System.out.println();

	}
	
	private void swap(int idx1, int idx2)
	{
		DSAHeapEntry temp = heapArray[idx1];
		heapArray[idx1] = heapArray[idx2];
		heapArray[idx2] = temp;
	}
	
	public static void main(String[] args)
	{
		DSAGraph map = new DSAGraph();
		map.addVertex("CBD");
		map.addVertex("Airport");
		map.addVertex("Uni");
		map.addVertex("SuburbSouth");
		map.addVertex("SuburbNorth");
		map.addVertex("Mall");
		map.addVertex("Cafe");
		map.addVertex("Hospital");
		map.addVertex("Park");
		
		map.addEdge("CBD", "Airport", "Road1", 20);
		map.addEdge("CBD", "Uni", "Road2", 13);
		map.addEdge("Airport", "Uni", "Road3", 35);
		map.addEdge("Airport", "SuburbSouth", "Road4", 17);
		map.addEdge("Uni", "SuburbNorth", "Road5", 10);
		map.addEdge("Uni", "Cafe", "Road6", 10);
		map.addEdge("Cafe", "SuburbNorth", "Road7", 25);
		map.addEdge("Cafe", "Hospital", "Road8", 30);
		map.addEdge("Hospital", "SuburbNorth", "Road9", 23);
		map.addEdge("SuburbSouth", "Mall", "Road10", 13);
		map.addEdge("Mall", "SuburbNorth", "Road11", 12);
		
		DSAHashTable table = new DSAHashTable(53);
		table.loadFile("passenger.csv","driver.csv");
		
		System.out.println("Done inserting into Hash Table");
		System.out.println();
		
		DSAHeap requestHeap = new DSAHeap(30);
		int[] newRequests = {120,156,160,164, 179, 105, 153, 170, 142, 114};
		
		for (int i=0; i<newRequests.length; i++)
		{
			Object result = table.search(newRequests[i]); //search for the ID
			if (result instanceof Passenger)
			{
				Passenger passenger = (Passenger) result;
				
				DSALinkedList availableDrivers = table.getAvailableDrivers();
				if (availableDrivers.isEmpty())
				{
					System.out.println("There are currently no drivers available right now");
				}
				
				else
				{
					int minT = Integer.MAX_VALUE;
					Driver closest = null;
					DSAListNode cur = availableDrivers.getHead();
					while (cur!=null) //loop in the LL of drivers
					{
						Driver driver = (Driver) cur.getValue();
						if(!driver.isAvailable)//if that driver is not available anymore the continue with next
						{
							cur=cur.getNext();
							continue;
						}
						int travel = map.dijkstra(driver.curLocation, passenger.pickLocation);
						
						if (travel != Integer.MAX_VALUE && travel < minT)
						{
							minT = travel;
							closest = driver;
						}
						cur = cur.getNext();
					}
					
					if(closest == null || minT == Double.MAX_VALUE)
					{
						if(closest == null)
						{
							System.out.println("No Driver available");
						}
						else if (minT == Integer.MAX_VALUE)
						{
							System.out.println("This location is out of reach.");
						}
					}
					else
					{
						double T;
						int priority;
						if (minT == 0.0)
						{
							T = 0.5;
						}
						else
						{
							T = minT;
						}
						
						priority = (int) ((6.0-passenger.tier) + (1000.0/T));
						System.out.println("Passenger: " + passenger.name + " (Tier: " + passenger.tier + ")");
						System.out.println("Driver: " + closest.name);
						System.out.println("Time to reach: " + minT);
						System.out.println("Priority: " + priority + " ((6.0 - " + passenger.tier + ") + (1000.0/" + T + "))");
						System.out.println();
						
						
						PickupRequest newRequest = new PickupRequest(passenger.id, closest.id, priority, minT);
						requestHeap.insert(priority, newRequest);
						closest.isAvailable = false; //changes the flag to not available anymore
						
						System.out.println("The request was added in the heapArray");
						System.out.println("---------------------------------------------");
						System.out.println();
					}
				}
			}
			else
			{
				System.out.println("PassengetId not found");
			}
		}
		
		int dispatchCount = 1;
		for (int i=0; i<5; i++)
		{
			if (requestHeap.getCount()==0)
			{
				break;
			}
			DSAHeapEntry job = requestHeap.remove();
			PickupRequest request = (PickupRequest) job.getVal();
			
			System.out.println("Dispatch " + dispatchCount);
			System.out.println("Priority: " + job.getPriority());
			System.out.println("Passenger ID: " + request.pID);
			System.out.println("Driver ID: " + request.assignedDriverID);
			System.out.println("Travel Time:  " + request.timeNeeded);
			dispatchCount++;
			System.out.println();
			requestHeap.display();
			System.out.println("---------------------------------------------");
		}
		
		System.out.println("===Peek the current highest priority===");
		DSAHeapEntry peekEntry = requestHeap.peek();
		PickupRequest peekRequest = (PickupRequest) peekEntry.getVal();
		System.out.println("Priority: " + peekEntry.getPriority());
		System.out.println("PassengerID: " + peekRequest.pID);
		System.out.println("Driver ID: " + peekRequest.assignedDriverID);
		System.out.println("Time Needed: " + peekRequest.timeNeeded);
		System.out.println("---------------------------------------------");
	}
}
	
	
	

