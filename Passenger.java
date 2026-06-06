import java.util.*;
/* Author: Bhavisha Anil Gaikwad
 * Date: 15 May 2026
 * Desription: This class is for the elements of passengers
*/
public class Passenger
{
	public int id;
	public String name;
	public String pickLocation;
	public int tier;
	
	public Passenger(int id, String name, String pickLocation, int tier)
	{	
		if(id<100 || id>200)
		{
			throw new IllegalArgumentException("ID not within range");
		}
		if (name.trim().isEmpty())
		{
			throw new IllegalArgumentException("No name entered");
		}
		if (pickLocation.isEmpty())
		{
			throw new IllegalArgumentException("No location entered");
		}
		if (tier <1 || tier >5)
		{
			throw new IllegalArgumentException("Tier not within range");
		}
		this.id = id;
		this.name = name;
		this.pickLocation = pickLocation;
		this.tier = tier;
	}
}
