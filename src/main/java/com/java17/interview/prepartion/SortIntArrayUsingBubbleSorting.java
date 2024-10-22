package com.java17.interview.prepartion;

public class SortIntArrayUsingBubbleSorting {

	public static void main(String[] args) {
		
		
		int[] arr1 = {11, 2, 3, 0, 12, 15,55, 66, 89,99,100,101};
		//int[] arr2 = new int[arr1.length];
		int temp ;
		for(int i = 0; i <= arr1.length; i++) {
			for(int j = i + 1; j < arr1.length; j++) {
				
				if(arr1[i] > arr1[j]) {
					temp = arr1[i];
					arr1[i] = arr1[j];
					arr1[j] = temp;
				}
			}
		}
		
		for(int i = 0; i < arr1.length; i++) {
			//arr2[i] = arr1[i];
			System.out.println(arr1[i]);
		}

	}

}
/**
 * 11
getClass()
clone()
hashCode()
equals()
toString()
wait()
notify()
notifyAll()
wait()
wait(long timeout)
wait(long timeout, int nanos)
finalize()
*/

