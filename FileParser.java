import java.util.*;
import java.io.*;

/* Author: Bhavisha Anil Gaikwad
 * Date: 15 May 2026
 * Desription: This class is for file parsing of passenger.csv and drivers.csv
*/
public class FileParser
{
	public static Passenger[] parsePassengerFile(String filename)
	{
		Passenger[] passenger = new Passenger[50];
		int index = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(filename)))
		{
			String line;
			while ((line = br.readLine()) != null && index <50)
			{
				if (line.trim().isEmpty()) 
				{
					continue;
				}
				
				String[] parts = line.split(",");
				if (parts.length == 4)
				{
					try
					{
						int passengerID = Integer.parseInt(parts[0].trim());
						String name = parts[1].trim();
						String pickLocation = parts[2].trim();
						int tier = Integer.parseInt(parts[3].trim());
						if(name.isEmpty())
						{
							System.out.println("Name can't be empty");
							continue;
						}
						if (tier < 1 || tier >5)
						{
							System.out.println("MembershipTier must be from 1 to 5");
							continue;
						}
						passenger[index] = new Passenger(passengerID, name, pickLocation, tier);
						index++;
					}
					catch (NumberFormatException e)
					{
						System.out.println("Not a number");
					}
				}	
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found");
		}
		catch (IOException e)
		{
			System.out.println("Can't write to file");
		}
		return passenger;
	}
	
	public static Driver[] parseDriverFile(String filename)
	{
		Driver[] driver = new Driver[50];
		int index = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(filename)))
		{
			String line;
			while ((line = br.readLine()) != null && index <50)
			{
				if (line.trim().isEmpty()) 
				{
					continue;
				}
				
				String[] parts = line.split(",");
				if (parts.length == 4)
				{
					try
					{
						int driverID = Integer.parseInt(parts[0].trim());
						String name = parts[1].trim();
						String curLocation = parts[2].trim();
						String status = parts[3].trim();
						if(name.isEmpty())
						{
							System.out.println("Name can't be empty");
							continue;
						}
						if (!status.equals("Available")&&!status.equals("Busy")&&!status.equals("Offline"))
						{
							System.out.println("Invalid status");
							continue;
						}
						driver[index] = new Driver(driverID, name, curLocation, status);
						index++;
					}
					catch (NumberFormatException e)
					{
						System.out.println("Not a number");
					}
				}	
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found");
		}
		catch (IOException e)
		{
			System.out.println("Can't write to file");
		}
		return driver;
	}
}

					  
				
