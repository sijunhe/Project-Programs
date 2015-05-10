import java.util.*;

public class closestPair {

	private static double distance(Points a, Points b){
		//calculate the distance between two points
		double xDiff = a.x + b.x;
		double yDiff = a.y + b.y;
		return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
	}

	private static Points[] bruteForce(Points[] sortByX, Points[] sortByY){
		//brute force to find the closest pair when the size is small enough
		double min = distance(sortByX[0],sortByX[1]);
		Points[] pair = new Points[2];
		pair[0] = sortByX[0];
		pair[1] = sortByX[1];
		for (int i = 0;i < sortByX.length;i++){
			for (int j = i + 1;j < sortByX.length;j++){
				double dist1 = distance(sortByX[i],sortByX[j]);
				double dist2 = distance(sortByY[i],sortByY[j]);
				if (dist1 < min) {
					min = dist1;
					pair[0] = sortByX[i];
					pair[1] = sortByX[j];
				}
				
				if (dist2 < min) {
					min = dist1;
					pair[0] = sortByY[i];
					pair[1] = sortByY[j];
				}
			}
		}
		return pair;
	}

	public static Points[] closestPair(Points[] a){
		//create two copies of the original array 
		//sort each array according to the x coordinates and coordinates
		Points[] sortByX = new Points[a.length]; 
		Points[] sortByY = new Points[a.length];
		for (int i = 0;i < a.length;i++){
			sortByX[i] = a[i];
			sortByY[i] = a[i];
		}
		Arrays.sort(sortByX, Points.sortX);
		Arrays.sort(sortByY, Points.sortY);
		return closest(sortByX, sortByY);
	}
	
	private static Points[] closest(Points[] sortByX, Points[] sortByY){
		if (sortByX.length <=3) return bruteForce(sortByX, sortByY); //base case of recursion: brute force when size is small
		
		Points[] pair = new Points[2];
		double min;
		int center = sortByX.length /2;
		
		//separate the two arrays to left half and right half
		Points[] leftHalfX = new Points[center];
		Points[] rightHalfX = new Points[sortByX.length - center];

		Points[] leftHalfY = new Points[center];
		Points[] rightHalfY = new Points[sortByX.length - center];

		for (int i = 0;i < center;i++){
			leftHalfX[i] = sortByX[i];
			leftHalfY[i] = sortByY[i];
		}

		for (int i = center;i < sortByX.length;i++){
			rightHalfX[i-center] = sortByX[i];
			rightHalfY[i-center] = sortByY[i];
		}
		
		//independently find the closest pair of left half and right half
		Points[] pair1 = closest(leftHalfX, leftHalfY);
		double min1 = distance(pair1[0],pair1[1]);
		Points[] pair2 = closest(rightHalfX, rightHalfY);
		double min2 = distance(pair2[0],pair2[1]);
		
		//set the closest pair of the smaller of the previous two
		//calculate the closest distance
		if (min1 < min2) {
			pair = pair1;
			min = min1;
		}else{
			pair = pair2;
			min = min2;
		}
		
		//find closest "split" pairs
		//generate a strip of points whose x-coordinates are within closest distance of the center of sortByX
		//using ArrayList instead of Arrays for filtered data to allow for dynamic size
		ArrayList<Points> filtered = new ArrayList<Points>();
		double leftBoundary = sortByX[center].x - min; 
		double rightBoundary = sortByX[center].x + min;
		for (int i = 0; i<sortByY.length; i++){
			if (leftBoundary < sortByY[i].x && sortByY[i].x < rightBoundary){
				filtered.add(sortByY[i]);
			}
		}
		
		//if the closest pair p and q is a "split" pair, their positions in filtered data is at most 7 positions apart
		for (int i = 0; i < (filtered.size()-1);i++){
			for (int j = i + 1; j < Math.min(filtered.size(), i + 7);j++){
				double dist = distance(filtered.get(i),filtered.get(j));
				if (dist < min){
					min = dist;
					pair[0] = filtered.get(i);
					pair[1] = filtered.get(j);
				}
			}
		}
		return pair;
	}

	public static void main(String[] args){
		
		//generate 30 random points for testing
		Points[] a = new Points[30];
		Random ran = new Random();
		for (int i=0;i<30;i++){
			a[i] = new Points(ran.nextInt(101),ran.nextInt(101));
		}
		
		
		Points[] closestPair = closestPair(a);
		Points[] bruteforce = bruteForce(a,a);
		
		System.out.println("By the Brute Force method of O(n^2), the closest points are");
		System.out.println("Point:    x = " + bruteforce[0].x + " y= " + bruteforce[0].y);
		System.out.println("Point:    x = " + bruteforce[1].x + " y= " + bruteforce[1].y);
		System.out.println("The distances between them is " + distance(bruteforce[0],bruteforce[1]));
		System.out.println();
		System.out.println("By the divide and conquer method of O(nlog(n)), the closest points are:");
		System.out.println("Point:    x = " + closestPair[0].x + " y= " + closestPair[0].y);
		System.out.println("Point:    x = " + closestPair[1].x + " y= " + closestPair[1].y);
		System.out.println("The distances between them is " + distance(closestPair[0],closestPair[1]));
		
	}

}
