import java.util.*;
/* Author: Bhavisha Anil Gaikwad
 * Date: 11 May 2026
 * Desription: This class has the element of each hash entry for the hash table
*/
public class DSAHashEntry
{
	private int key; 
	private Object val; 
	
	public DSAHashEntry()
	{
		this.key = -1;
		this.val = null;
	}
	
	public DSAHashEntry(int inKey, Object inVal)
	{
		this.key = inKey;
		this.val = inVal;
	}
	
	public int getKey()
	{
		return key;
	}
	public void setKey(int inKey)
	{	
		this.key = inKey;
	}
	public Object getValue()
	{
		return val;
	}
	public void setValue(Object inVal)
	{
		this.val = inVal;
	}
}
	
