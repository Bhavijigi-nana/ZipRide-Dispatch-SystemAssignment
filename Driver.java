import java.util.*;

/* Author: Bhavisha Anil Gaikwad
 * Date: 15 May 2026
 * Desription: This class is to hold driver objects for the hash table implementation
*/
public class Driver
{
	public int id;
	public String name;
	public String curLocation;
	public String status;
	public boolean isAvailable = true;
	
	public Driver(int id, String name, String curLocation, String status)
	{
		if(id<400 || id>500)
		{
			throw new IllegalArgumentException("ID not within range");
		}
		if (name.trim().isEmpty())
		{
			throw new IllegalArgumentException("No name entered");
		}
		if (curLocation.isEmpty())
		{
			throw new IllegalArgumentException("No location entered");
		}
		if (!status.equals("Available")&&!status.equals("Busy")&&!status.equals("Offline"))
		{
			throw new IllegalArgumentException("Invalid Status");
		}
		this.id = id;
		this.name = name;
		this.curLocation = curLocation;
		this.status = status;
		this.isAvailable = status.equals("Available");
	}
}
