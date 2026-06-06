import java.util.*;
import java.io.*;

/* Author: Bhavisha Anil Gaikwad
 * Date: 16 May 2026
 * Desription: This class has the major features of Hash Table Module
*/
public class DSAHashTable
{
	private DSALinkedList[] hashArray; //Hash Table - the array of linkedList
	private int count; //number of elements in the array
	private int arraySize; //how big is the array
	private static final double upper = 0.7; //load factor >0.7 then grow
	private static final double lower = 0.4; //load factor <0.4 then gets smaller
	
	public DSAHashTable()
	{
		this(101); //creates hash table with size 101;
	}
	public DSAHashTable(int tableSize) //when size of table is given, use chaining so LL
	{	
		arraySize = tableSize;
		hashArray = new DSALinkedList[arraySize];
		
		for (int i=0; i<arraySize; i++)
		{
			hashArray[i]=new DSALinkedList();
		}
		count =0;
	}
	private int hash(int key) //hash function - converts key to hashIndex
	{
		return key % arraySize; //to stay in the range of the array
	}
	
	public int getCount()
	{
		return count;
	}
	public int getArraySize()
	{
		return arraySize;
	}
	public double getLoadFactor()
	{
		return (double) count/arraySize;
	}
	public DSALinkedList getAvailableDrivers()
	{
		DSALinkedList availableDrivers = new DSALinkedList();
		
		for (int i=0; i<arraySize;i++)
		{
			DSAListNode cur = hashArray[i].getHead();
			while(cur!=null)
			{
				DSAHashEntry entry = (DSAHashEntry) cur.getValue();
				Object record = entry.getValue();
				
				if(record instanceof Driver)
				{
					Driver d = (Driver) record;
					
					if(d.status.equals("Available"))
					{
						availableDrivers.insertLast(d);
					}
				}
				cur = cur.getNext();
			}
		}
		return availableDrivers;
	}	
	
	public boolean insert(int key, Object value)
	{
		int hashIdx = hash(key);
		DSALinkedList box = hashArray[hashIdx];
		
		DSAListNode cur = box.getHead();
		while(cur!=null)
		{
			DSAHashEntry entry = (DSAHashEntry)cur.getValue();
			if(entry.getKey() == key) //same id not allowed
			{
				System.out.println("Same ID found. Not allowed");
				System.out.println("---------------------------------------------");
				return false;
			}
			cur=cur.getNext();
		}
		if (!box.isEmpty())
		{	
			System.out.println("Index " + hashIdx + " in use (Colliion Detected)");// in use
			System.out.println("Added key in the Linked List of the index");
		}
		box.insertLast(new DSAHashEntry(key, value));
		System.out.println("Added key " + key);
		System.out.println("---------------------------------------------");
		count++;
		
		if(count % 10 == 0)
		{
			System.out.println("==================================");
			System.out.println("==LoadFactor Info==");
			System.out.println("Total items in hashArray: " + count);
			System.out.println("Load: " + getLoadFactor());
			if (getLoadFactor() > upper)
			{
				System.out.println("Load Factor has exceed 0.7");
			}
			System.out.println("==================================");
		}
		return true;
	}
	
	public Object search(int key)
	{
		int hashIdx = hash(key);
		DSALinkedList box = hashArray[hashIdx];
		
		DSAListNode cur = box.getHead();
		while(cur!=null)
		{
			DSAHashEntry entry = (DSAHashEntry)cur.getValue();
			if(entry.getKey()==key)
			{
				return entry.getValue();
			}
			cur=cur.getNext();
		}
		return "Not found";
	}
	
	public boolean delete (int key)
	{
		int hashIdx = hash(key);
		DSALinkedList box = hashArray[hashIdx];
		
		DSAListNode prevNode = null;
		DSAListNode cur = box.getHead();
		while(cur!=null)
		{
			DSAHashEntry entry = (DSAHashEntry) cur.getValue();
			if (entry.getKey() == key)
			{
				if (prevNode==null)
				{
					box.removeFirst();
				}
				else
				{
					prevNode.setNext(cur.getNext());
				}
				count--;
				return true;
			}
			prevNode = cur;
			cur=cur.getNext();
		}
		return false;
	}
	
	public void loadFile(String filename1, String filename2)
	{
		Passenger[] passengers = FileParser.parsePassengerFile(filename1);
		Driver[] drivers = FileParser.parseDriverFile(filename2);
					
		System.out.println("Inserting passengers into hash table");
		for (Passenger passenger : passengers)
		{
			if (passenger!=null)
			{
				insert(passenger.id, passenger);
			}
		}
		System.out.println("Inserting drivers into hash table");
		for (Driver driver : drivers)
		{
			if (driver!=null)
			{
				insert(driver.id,driver);
			}
		}
	}
	public static void saveInsert(Object newInsert)
	{
		FileOutputStream fileStrm = null;
		PrintWriter pw;
		if(newInsert instanceof Passenger)
		{
			Passenger newP = (Passenger) newInsert;
			try 
			{
				fileStrm = new FileOutputStream("passenger.csv",true);
				pw = new PrintWriter(fileStrm);
				pw.println(newP.id+","+newP.name+","+newP.pickLocation+","+newP.tier);
				pw.close();
			}
			catch (IOException e)
			{
				System.out.println("Can't write to file");
			}
		}
		else if (newInsert instanceof Driver)
		{
			Driver newD = (Driver) newInsert;
			try
			{
				fileStrm = new FileOutputStream("driver.csv",true);
				pw = new PrintWriter(fileStrm);
				pw.println(newD.id+","+newD.name+","+newD.curLocation+","+newD.status);
				pw.close();
			}
			catch(IOException e)
			{
				System.out.println("Can't write to file");
			}
		}
	}
				
				
			
							
	public static void main(String[] args)
	{
		DSAHashTable obj = new DSAHashTable(53);
		Scanner sc = new Scanner(System.in);
		int choice;
		int inputChoice;
		do
		{
			System.out.println("Main Menu");
			System.out.println(">1 Load passengers and drivers from .csv");
			System.out.println(">2 Insert Passenger or Driver");
			System.out.println(">3 Search by ID");
			System.out.println(">4 Delete ID");
			System.out.println(">5 Exit");
			System.out.print("Choice: ");
			choice = sc.nextInt();
			
			switch(choice)
			{
				case 1: 
					
					obj.loadFile("passenger.csv","driver.csv");
					break;
				case 2:
					System.out.println("Do you want to insert 1) passenger or 2) driver?");
					System.out.print("Choice: ");
					inputChoice = sc.nextInt();
					if (inputChoice==1)
					{
						System.out.print("Enter ID(100-200): ");
						int pID = sc.nextInt();
						if (pID<100||pID>200)
						{
							throw new IllegalArgumentException("PassengerID not in range");
						}

						sc.nextLine();
						System.out.print("Enter name: ");
						String name = sc.nextLine();
						System.out.print("Enter pick up location: ");
						String location = sc.nextLine();
						System.out.print("Enter tier (1-5): ");
						int tier = sc.nextInt();
						if (tier<1||tier>5)
						{
							throw new IllegalArgumentException("Tier not in range");
						}

						Passenger addPassenger = new Passenger(pID, name, location, tier);
						if (obj.insert(pID, addPassenger))
						{
							saveInsert(addPassenger);
						}
					}
					else if(inputChoice==2)
					{
						System.out.print("Enter ID(400-500): ");
						int dID = sc.nextInt();
						if (dID<400||dID>500)
						{
							throw new IllegalArgumentException("DriverID not in range");
						}

						sc.nextLine();
						System.out.print("Enter name: ");
						String name = sc.nextLine();
						System.out.print("Enter current location: ");
						String location = sc.nextLine();
						System.out.print("Enter status (Available, Busy, Offline): ");
						String status = sc.nextLine();
						
						Driver addDriver = new Driver(dID, name, location, status);
						if(obj.insert(dID, addDriver))
						{
							saveInsert(addDriver);	
						}
					}
					else
					{
						System.out.println("Invalid Choice");
						return;
					}
					break;
				case 3:
					System.out.print("Enter ID to search: ");
					int searchID = sc.nextInt();
					long searchStartTime = System.nanoTime();
					Object result = obj.search(searchID);
					
					if(result instanceof Passenger)
					{
						Passenger p = (Passenger) result;
						System.out.println("Passenger ID: " + p.id);
						System.out.println("Passenger Name: " + p.name);
						System.out.println("Passenger PickUp Location: " + p.pickLocation);		
						System.out.println("Passenger Tier: " + p.tier);
					}
					else if(result instanceof Driver)
					{
						Driver d = (Driver) result;
						System.out.println("Driver ID: " + d.id);
						System.out.println("Driver Name: " + d.name);
						System.out.println("Driver Current Location: " + d.curLocation);		
						System.out.println("Driver Status: " + d.status);
					}
					else if (result.equals("Not found"))
					{
						System.out.println("This ID is not in the records");
					}
					long searchEndTime = System.nanoTime();
					long searchRunningTime = (int)((double)(searchEndTime - searchStartTime)/1000.0);
					System.out.println("RunTime: " + searchRunningTime + "ms" );
					System.out.println();
					break;
				case 4:
					System.out.print("Enter ID to be deleted: ");
					int deleteKey = sc.nextInt();
					long deleteStartTime = System.nanoTime();
					if(obj.delete(deleteKey))
					{
						System.out.println("The Data has been deleted");
					}
					else
					{
						System.out.println("The key was not found");
					}
					long deleteEndTime = System.nanoTime();
					long deleteRunningTime = (int)((double)(deleteEndTime - deleteStartTime)/1000.0);
					System.out.println("RunTime: " + deleteRunningTime + "ms" );
					System.out.println();
					break;
				case 5:
					System.out.println("Exiting...");
					break;
				default:
					System.out.println("Invalid choice");
					break;
			}
		}while(choice!=5);
		
		sc.close();
	}	
}

				
	
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
				
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
