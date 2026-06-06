import java.util.*;
import java.io.*;

/* Author: Bhavisha Anil Gaikwad
 * Date: 11 May 2026
 * Desription: This class focuses on advanced sorting algorithms, merge and quick sorts as pe rthe module requirement
*/
public class Sorts
{
	private static Random rand;
	public static void mergeSort(PickupRequest[] A)
	{
		if (A.length >1)
		{
			mergeSortRecurse(A,0,A.length-1); 
		}
	}
	
	private static void mergeSortRecurse(PickupRequest[] A, int leftIdx, int rightIdx)
	{
		if (leftIdx < rightIdx)
		{
			int midIdx = (leftIdx + rightIdx)/2;
			mergeSortRecurse(A,leftIdx, midIdx); //split 1st half
			mergeSortRecurse(A, midIdx + 1, rightIdx); //split 2nd half
			merge(A, leftIdx, midIdx, rightIdx); //merge both halves
		}
	}
	private static void merge(PickupRequest[] A, int leftIdx, int midIdx, int rightIdx)
	{
		PickupRequest[] tempArr = new PickupRequest[rightIdx-leftIdx+1];
		
		int ii=leftIdx;
		int jj=midIdx +1;
		int kk=0;
		
		while(ii<= midIdx && jj<=rightIdx)
		{
			if (A[ii].timeNeeded <= A[jj].timeNeeded)
			{
				tempArr[kk] = A[ii];
				ii++;
			}
			else
			{
				tempArr[kk] = A[jj];
				jj++;
			}
			kk++;
		}
		for (; ii<= midIdx;ii++)
		{
			tempArr[kk] = A[ii];
			kk++;
		}
		for (; jj<=rightIdx; jj++)
		{
			tempArr[kk] = A[jj];
			kk++;
		}
		for (kk=leftIdx; kk<=rightIdx; kk++)
		{
			A[kk] = tempArr[kk-leftIdx];
		}
	}
		
	public static void quickSortMedian3(PickupRequest[] A)
	{
		if (A.length >1)
		{
			quickSortMedian3Recurse(A,0,A.length-1);
		}
	}
	
	private static void quickSortMedian3Recurse(PickupRequest[] A, int leftIdx, int rightIdx)
	{
		if(rightIdx > leftIdx)
		{
			int pivIdx = medianOf3(A, leftIdx, rightIdx);
			int newPivotIdx = doPartitioning(A, leftIdx, rightIdx, pivIdx);
			quickSortMedian3Recurse(A, leftIdx, newPivotIdx-1);
			quickSortMedian3Recurse(A, newPivotIdx+1, rightIdx);
		}
	}
	private static int medianOf3(PickupRequest[] A, int leftIdx, int rightIdx)
	{
		int midIdx = (leftIdx+rightIdx)/2;
		
		if (A[leftIdx].timeNeeded > A[midIdx].timeNeeded)
		{
			swap(A, leftIdx, midIdx);
		}
		if (A[leftIdx].timeNeeded > A[rightIdx].timeNeeded)
		{
			swap (A, leftIdx, rightIdx);
		}
		if(A[midIdx].timeNeeded > A[rightIdx].timeNeeded)
		{
			swap(A, midIdx, rightIdx);
		}
		return midIdx;
	}
	
	private static void swap(PickupRequest[] A, int idx1, int idx2)
	{
		PickupRequest temp = A[idx1];
		A[idx1] = A[idx2];
		A[idx2] = temp;
	}
	
	private static int doPartitioning(PickupRequest[] A, int leftIdx, int rightIdx, int pivIdx)
	{
		PickupRequest pivotVal = A[pivIdx];
		
		A[pivIdx] = A[rightIdx];
		A[rightIdx] = pivotVal;
		
		int curIdx = leftIdx;
		
		for (int ii=leftIdx; ii<=rightIdx-1; ii++)
		{
			if(A[ii].timeNeeded < pivotVal.timeNeeded)
			{
				swap(A, ii, curIdx);
				curIdx++;
			}
		}
		
		int newPivotIdx = curIdx;
		A[rightIdx] = A[newPivotIdx];
		A[newPivotIdx] = pivotVal;
		
		return newPivotIdx;
	}
	
	public static void reverse(PickupRequest[] A)
	{
		for (int i=0; i< (A.length)/2; i++)
		{
			swap(A, i, A.length-i-1);
		}
	}
	
	public static void random(PickupRequest[] A)
	{
		int RANDOM_TIMES = 5;
		for (int i=0; i<RANDOM_TIMES * A.length; i++)
		{
			int x = rand.nextInt(A.length);
			int y = rand.nextInt(A.length);
			swap(A, x, y);
		}
	}
	
	public static void nearlySorted(PickupRequest[] A)
	{
		int n = A.length;
		double NEARLY_PERCENT = 0.10;
		for(int i=0; i<n*NEARLY_PERCENT/2.0; i++)
		{
			int x= rand.nextInt(n);
			int y = rand.nextInt(n);
			swap(A,x,y);
		}
	}
	
	private static double timeCalc(char type, PickupRequest[] A)
	{
		long start = System.nanoTime();
		
		if(type =='m')
		{
			mergeSort(A);
		}
		else if(type == 'q')
		{
			quickSortMedian3(A);
		}
		
		long end = System.nanoTime();
		double runTime = (end-start)/1000.0;
		return   runTime;
	}
	
	public static void print5(PickupRequest[] A)
	{
		for (int i=0; i<5; i++)
		{
			System.out.printf("-%d PassengerID: %d | Time Needed: %.2f min%n", i, A[i].pID, A[i].timeNeeded);
		}
	}
			
	public static void main(String[] args)
	{
		int[] testSize = {100, 500, 1000};
		String[] order = {"reversed", "random", "nearlySorted"};
		
		System.out.println("===Sorted Data for 100 records===");
		rand = new Random(42);
		PickupRequest[] mergeArr = new PickupRequest[100];
		PickupRequest[] quickArr = new PickupRequest[100];
		for (int i=0; i<100; i++)
		{
			double testT = 1.0 + 59.0 * rand.nextDouble();
			mergeArr[i] = new PickupRequest(100+i, 400+i, 50, testT);
			quickArr[i] = new PickupRequest(100+i, 400+i, 50, testT);
		}
		mergeSort(mergeArr);
		quickSortMedian3(quickArr);
		
		System.out.println("Merge Sort: ");
		print5(mergeArr);
		System.out.println();
		System.out.println("Quick Sort (Median 3): ");
		print5(quickArr);
		System.out.println();
		System.out.println("------------------------------------------------------------------");
		
		System.out.printf("%-15s | %-15s | %-18s | %-18s%n","Test Size", "Order", "MergeRunTime", "QuickSortRunTime");
		System.out.println("------------------------------------------------------------------");
		for (int size=0; size<testSize.length; size++)
		{
			int n = testSize[size];
			
			for (int type=0; type<order.length;type++)
			{
				String o = order[type];
				
				rand = new Random(42);
				mergeArr = new PickupRequest[n];
				quickArr = new PickupRequest[n];
				for (int i=0; i<n; i++)
				{
					double testT = 1.0 + 59.0 *rand.nextDouble();
					mergeArr[i] = new PickupRequest(100+i, 400+i, 50, testT);
					quickArr[i] = new PickupRequest(100+i, 400+i, 50, testT);
				}
				
				switch (o)
				{
					case "reversed":
						reverse(mergeArr);
						reverse(quickArr);
						break;
					case "random":
						random(mergeArr);
						random(quickArr);
						break;
					case "nearlySorted":
						mergeSort(mergeArr);
						nearlySorted(mergeArr);
						mergeSort(quickArr);
						nearlySorted(quickArr);
						break;
				}
				double mergeRunTime = timeCalc('m', mergeArr);
				double quickRunTime = timeCalc('q', quickArr);
				
				System.out.printf("%-15d | %-15s | %-18.4f | %-18.4f%n", n, o, mergeRunTime, quickRunTime);
			}
			System.out.println("------------------------------------------------------------------");
		}
		System.out.println("===Performance Analysis===");
		System.out.println("-> Quick Sort performed sorting faster in most of the cases, especially for the random case");
		System.out.println("-> Merge Sort performed better for nearly sorted data for all sizes");
		System.out.println("------------------------------------------------------------------");
	}
}

