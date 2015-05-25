import java.io.FileNotFoundException;
import java.util.*;

/*
 *This program was wrote to finish the Programming Problem # 1 of "Algorithms: Design and Analysis, Part 1" on Coursera
 *The objectives of the program is to count the inversion of a file containing all of the 100,000 unsorted integers between 1 and 100,000 with an O(nlog(n)) algorithm.
 */

public class countingInversion {
	private static long inversionCount = 0; 
	
	
	public static long bruteForce(ArrayList<Integer> list){
		long count = 0;
		for (int i =0;i<(list.size()-1);i++){
			for (int j =i+1;j<(list.size());j++){
				if (list.get(i) > list.get(j)) count++;
			}
		}
		return count;
	}
	
	public static ArrayList<Integer> mergeSort(ArrayList<Integer> original){
		if (original.size() == 1) return original;

		int center = (original.size()) / 2;
		ArrayList<Integer> leftHalf = new ArrayList<Integer>();
		ArrayList<Integer> rightHalf = new ArrayList<Integer>();

		for (int i = 0;i < center;i++){
			leftHalf.add(original.get(i)); //copying left half
		}

		for (int i = center;i < original.size();i++){
			rightHalf.add(original.get(i));//copying right half
		}
		return merge(mergeSort(leftHalf), mergeSort(rightHalf));
	}


	private static ArrayList<Integer> merge(ArrayList<Integer> leftArray, ArrayList<Integer> rightArray){
		ArrayList<Integer> output = new ArrayList<Integer>();
		int i = 0; //initialize indexing of left array
		int j = 0; //initialize indexing of right array
		int k = 0; //initialize indexing of output array
		while (i < leftArray.size() && j < rightArray.size()){
			if (leftArray.get(i) < rightArray.get(j)) {
				output.add(leftArray.get(i));
				i++;
			}
			else {
				inversionCount+=(leftArray.size()) - i; 
				//the number of inversion involving an element Y in the right Array is the number of elements left in the leftArray 
				//when element Y is merged
				output.add(rightArray.get(j));
				j++;
			}
		}

		while (i < leftArray.size()){
			output.add(leftArray.get(i));
			i++; //right array runs out, copy the rest of left array
		}

		while (j < rightArray.size()){
			output.add(rightArray.get(j));
			j++; //left array runs out, copy the rest of right array
		}
		return output;
	}
	
	
	

	public static void main(String[] args) throws FileNotFoundException {
		loadData txtFile = new loadData("IntegerArray.txt");
		ArrayList<Integer> data = txtFile.readFile(); 
		final long bruteStartTime = System.currentTimeMillis();
		long bruteCount = bruteForce(data);
		final long bruteEndTime = System.currentTimeMillis();
		System.out.println("Using Brute Force method, the count is " + bruteCount + ", runtime is " + (bruteEndTime - bruteStartTime) + " ms");
		
		
		final long mergeStartTime = System.currentTimeMillis();
		ArrayList<Integer> sortedArrayList  = mergeSort(data);
		final long mergeEndTime = System.currentTimeMillis();
		
		System.out.println("Using Divide and Conquer method, the count is " + inversionCount + ", runtime is " + (mergeEndTime - mergeStartTime) + " ms");
		
		
		
	}

}
