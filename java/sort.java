import java.util.*;
/*
 * created by sijunhe on 05/09/2015
 * Sorting - Implement two types of sorting algorithms: Merge sort and bubble sort
 */

public class sort {

	public static void main(String[] args) {
		int[] a = {15,8,7,3,9,2,1,6,8,12,5,8,6,22,31,1,0,3};
		int[] b = {15,8,7,3,9,2,1,6,8,12,5,8,6,22,31,1,0,3};
		mergeSort(a);
		bubbleSort(b);
		System.out.println("Test merge sort " + Arrays.toString(a));
		System.out.println("Test bubble sort " + Arrays.toString(b));

	}

	public static void mergeSort(int[] input){
		int[] buff = new int[input.length];
		mergeSort(input,buff,0,input.length-1);
	}

	private static void mergeSort(int[] input, int[] buff, int low, int high){
		if (low<high){
			int mid = (low+high)/2;
			mergeSort(input,buff,low,mid);
			mergeSort(input,buff,mid+1,high);
			merge(input,buff,low,mid+1,high);
		}

	}

	private static void merge(int[ ] input, int[ ] buff, int left, int right, int rightEnd){
		int leftEnd = right - 1;
		int k = left;
		int num = rightEnd - left + 1;

		while(left <= leftEnd && right <= rightEnd)
			if(input[left] < input[right])
				buff[k++] = input[left++];
			else
				buff[k++] = input[right++];

		while(left <= leftEnd)    //right array runs out, copy the rest of left array
			buff[k++] = input[left++];

		while(right <= rightEnd)  //left array runs out, copy the rest of right array
			buff[k++] = input[right++];

		// Copy the sorted array from buffer back to the input array
		for(int i = 0; i < num; i++, rightEnd--)
			input[rightEnd] = buff[rightEnd];
	}

	public static void bubbleSort(int[] original){
		int temp; //used for swapping elements
		int n = original.length;
		for (int i = 0; i < n - 1; i++){
			for (int j = 0; j < n -1; j++){
				if (original[j] > original[j + 1]){ //if earlier element is greater, swap
					temp = original[j];
					original[j]  = original[j + 1];
					original[j + 1] = temp; 
				}
			}
		}
	}
}
