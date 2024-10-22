package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FindSecondLargestAndSmallestNumberFromArrayListUsingStreamAPI {

	public static void main(String[] args) {
		

		List<Integer> listOfInteger = Arrays.asList(4,5,6,77,1000);
		//List<Integer> numbers = Arrays.asList(5, 9, 11, 2, 8, 21, 1);
		//List<Integer> listOfInteger = new ArrayList<>();
		
		
		int scondLargest  = listOfInteger.stream().sorted(Comparator.reverseOrder()).limit(2).skip(1).findFirst().get();
		//int[] intArray = {4,55,66,33,2,18};

		//int scondLargest  = Arrays.stream(intArray).boxed().sorted(Comparator.reverseOrder()).limit(2).skip(1).findFirst().get();


		System.out.println(scondLargest);
		int scondSmallest  = listOfInteger.stream().sorted(Comparator.naturalOrder()).limit(2).skip(1).findFirst().get();
	
		System.out.println(scondSmallest);
		
	}
}
		
		
		
		
		