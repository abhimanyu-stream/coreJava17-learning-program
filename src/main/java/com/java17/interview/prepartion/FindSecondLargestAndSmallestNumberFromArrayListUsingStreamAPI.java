package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FindSecondLargestAndSmallestNumberFromArrayListUsingStreamAPI {

	public static void main(String[] args) {
		

		List<Integer> listOfInteger = Arrays.asList(4,5,6,77,1000);

		
		int scondLargest  = listOfInteger.stream().sorted(Comparator.reverseOrder()).limit(2).skip(1).findFirst().get();


		System.out.println(scondLargest);
		int scondSmallest  = listOfInteger.stream().sorted(Comparator.naturalOrder()).limit(2).skip(1).findFirst().get();
	
		System.out.println(scondSmallest);
		
	}
}
		
		
		
		
		