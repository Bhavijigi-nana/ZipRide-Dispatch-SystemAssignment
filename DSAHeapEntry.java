import java.util.*;
/* Author: Bhavisha Anil Gaikwad
 * Date: 20 May 2026
 * Desription: This class has the elements for heap entry
*/
public class DSAHeapEntry

{
	private int priority;
	private Object val;
	
	public DSAHeapEntry(int priority, Object val)
	{
		this.priority = priority;
		this.val = val;
	}
	
	public int getPriority()
	{
		return priority;
	}
	
	public Object getVal()
	{
		return val;
	}
	
	public void setPriority(int priority)
	{
		this.priority = priority;
	}
	
	public void setValue(Object val)
	{
		this.val = val;
	}
}

