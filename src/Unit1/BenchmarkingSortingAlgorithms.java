package Unit1;

import java.util.*;

/** This class is used to estimate the run time between two identical arrays using 
 *  a sorting method from section 7.4 in the textbook and a built-in sorting method in Java */
public class BenchmarkingSortingAlgorithms {
	
	// Compute benchmarks of two different sorting techniques
	
	private int MAX_ARRAY_SIZE = 10000; // Array Size
	
	private int[] sortingArray1 = new int[MAX_ARRAY_SIZE]; // First Array		
	private int[] sortingArray2 = new int[MAX_ARRAY_SIZE]; // Second Array with the same size
	
	
	//a Constructor for initializing the arrays and computing the run time
	public BenchmarkingSortingAlgorithms(){  
		
		
		for (int i = 0; i < sortingArray1.length; i++) {
			
			// Filling two arrays with the same random numbers.
			
			sortingArray1[i]=(int)(Integer.MAX_VALUE * Math.random());
			sortingArray2[i] = sortingArray1[i];
		}
		
		/* -------------------- Array 1 -------------------- */
		
		long startTimeArray1 = System.currentTimeMillis(); // Start computing time
		
		selectionSort(sortingArray1); // Sorting Array1 with SelectionSort method 
		
		long runTimeArray1 = System.currentTimeMillis()-startTimeArray1; //run time for array 1
		
		
		/* -------------------- Array 2 -------------------- */
		
		long startTimeArray2 = System.currentTimeMillis();// Start computing time 
		
		Arrays.sort(sortingArray2); // Sorting Array2 with Arrays.sort
		
		long runTimeArray2 = System.currentTimeMillis()-startTimeArray2; // run time for array 2
		
		/* Printing the results of the two arrays */
		
		System.out.println("SelectionSort time(in seconds) : " + runTimeArray1/1000.0); 
		System.out.println("Arrays Sort time(in seconds) : " + runTimeArray2/1000.0); 
	
	}//end of the constructor
	
	
	//----------------------------------------------------------------------------
	
	/** The sorting method we will use on array 1 is copied from the textbook Section 7.4 
	 * (a selection sort)
	 * @param A the array that will be sorted */
	
	static void selectionSort(int[] A) {
		
		// Sort A in increasing order, using selection sort
		
		for (int lastPlace = A.length - 1; lastPlace > 0; lastPlace--) {
			
			// Find the largest item among A[0], A[1], ..., A[lastPlace], 
			//and move it into position lastPlace 
			//by swapping it with the number that is currently in position lastPlace.
			
			int maxLoc = 0; // Location of largest item seen so far
			
			for (int j = 1; j <= lastPlace; j++) {
				
				if (A[j] > A[maxLoc]) {
					
					// Since A[j] is bigger than the maximum we have seen so far,
					// j is the new location of the maximum value we have seen so far.
					
					maxLoc = j;
				
				} //end of if
				
			} // end of inner for loop
			
			int temp = A[maxLoc]; // Swap largest item with A[lastPlace].
			A[maxLoc] = A[lastPlace];
			A[lastPlace] = temp;
			
		} // end of outer for loop
		
	} //end of method
	
	
	public static void main(String[] args) {
		
		
		BenchmarkingSortingAlgorithms test = new BenchmarkingSortingAlgorithms();
		//we can clearly see that the Array.sort method is faster 
		//advice: try running the program more than once and notice the run time difference
		
	}
		
}//end of class


