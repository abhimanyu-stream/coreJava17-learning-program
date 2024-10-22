package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Comparator;

public class FindSecondLargestAndSmallestNumberFromArrayUsingStreamAPI {

	public static void main(String[] args) {
		
		
		int[] intArray = {4,55,66,33,2,18};

		int scondLargest  = Arrays.stream(intArray).boxed().sorted(Comparator.reverseOrder()).limit(2).skip(1).findFirst().get();
		
		
		
		System.out.println(scondLargest);
		int scondSmallest  = Arrays.stream(intArray).boxed().sorted(Comparator.naturalOrder()).limit(2).skip(1).findFirst().get();
	
		System.out.println(scondSmallest);


	}

}
