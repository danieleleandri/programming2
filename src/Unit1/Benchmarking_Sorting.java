package Unit1;

import java.util.Arrays;

import java.util.Scanner;

public class Benchmarking_Sorting {

	private static Scanner scan;

	public static void selectionSort(int arr[])

	{

		int n = arr.length;

		for (int i = 0; i < n - 1; i++)

		{

//We should find the min element of the array 

			int min_idx = i;

			for (int j = i + 1; j < n; j++)

				if (arr[j] < arr[min_idx])

					min_idx = j;

// Swapping the min element with the first one 

			int temp = arr[min_idx];

			arr[min_idx] = arr[i];

			arr[i] = temp;

		}

	}

	public static void printArrayBySelectionSort(int[] arr) {

		for (int i = 0; i < arr.length; i++) {

			System.out.print(arr[i] + " ");

		}

	}

	public static void sortByInBuilt(int arr[]) {

		Arrays.sort(arr);

	}

	public static void main(String[] args) {

		scan = new Scanner(System.in);
		int lim = 10001;
		while (lim > 10000) {
			System.out.println("Enter the limit of the array:");

			lim = scan.nextInt();
		}

		int[] array = new int[lim];

		for (int i = 0; i < array.length; i++) {

			array[i] = (int) (Math.random() * 100);

		}

		long startTime = System.currentTimeMillis();

		selectionSort(array);

		long runTime = System.currentTimeMillis() - startTime;

		System.out.println("\nRun time of the Selection sort is:" + runTime / 1000.0 + " Sec");

		long startTime1 = System.currentTimeMillis();

		sortByInBuilt(array);

		long runTime1 = System.currentTimeMillis() - startTime1;

		System.out.println("\nRun time of the In Built Sort method: " + runTime1 / 1000.0 + " Sec");

		printArrayBySelectionSort(array);

	}

}