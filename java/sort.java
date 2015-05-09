import java.util.*;
/*
 * created by sijunhe on 05/09/2015
 * Sorting - Implement two types of sorting algorithms: Merge sort and bubble sort
 */

public class sort {

	public static void main(String[] args) {
		int[] b = {15,8,7,3,9,2,1,6,8,12,5,8,6,22,31,1,0,3};
		int[] output = mergeSort(b);
		bubbleSort(b);
		System.out.println("Test merge sort " + Arrays.toString(output));
		System.out.println("Test bubble sort " + Arrays.toString(b));

	}

	public static int[] mergeSort(int[] original){
		if (original.length == 1) return original;

		int center = (original.length) / 2;
		int[] leftHalf = new int[center];
		int[] rightHalf = new int[original.length - center];

		for (int i = 0;i < center;i++){
			leftHalf[i] = original[i]; //copying left half
		}

		for (int i = center;i < original.length;i++){
			rightHalf[i-center] = original[i];//copying right half
		}
		return merge(mergeSort(leftHalf), mergeSort(rightHalf));
	}


	private static int[] merge(int[] leftArray, int[] rightArray){
		int[] output = new int[leftArray.length + rightArray.length];
		int i = 0; //initialize indexing of left array
		int j = 0; //initialize indexing of right array
		int k = 0; //initialize indexing of output array
		while (i < leftArray.length && j < rightArray.length){
			if (leftArray[i] < rightArray[j]) output[k++] = leftArray[i++];
			else output[k++] = rightArray[j++];
		}

		while (i < leftArray.length){
			output[k++] = leftArray[i++]; //right array runs out, copy the rest of left array
		}

		while (j < rightArray.length){
			output[k++] = rightArray[j++]; //left array runs out, copy the rest of right array
		}
		return output;
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
