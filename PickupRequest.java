import java.util.*;
import java.io.*;

/* Author: Bhavisha Anil Gaikwad
 * Date: 20 May 2026
 * Desription: This class is for the pickup requests for heap
*/
public class PickupRequest
{
	public int pID;
	public int assignedDriverID;
	public double priority;
	public double timeNeeded;
	
	public PickupRequest(int pID, int assignedDriverID, double priority, double timeNeeded)
	{
		this.pID = pID;
		this.assignedDriverID = assignedDriverID;
		this.priority = priority;
		this.timeNeeded = timeNeeded;
	}
}
		
