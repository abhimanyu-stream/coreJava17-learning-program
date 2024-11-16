package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.lang.System.*;

public class FindSecondLargestAndSmallestNumberFromArrayListUsingStreamAPI {

	public static void main(String[] args) {
		

		List<Integer> listOfInteger = Arrays.asList(4,5,6,77,1000);

		
		int secondLargest  = listOfInteger.stream().sorted(Comparator.reverseOrder()).limit(2).skip(1).findFirst().get();


		out.println("secondLargest "+secondLargest);
		int secondSmallest  = listOfInteger.stream().sorted(Comparator.naturalOrder()).limit(2).skip(1).findFirst().get();
	
		out.println("secondSmallest "+secondSmallest);



		int[] ints = {4,5,6,77,1000};
		final int asInt = Arrays.stream(ints).sorted().limit(2).skip(1).findFirst().getAsInt();
		out.println("asInt "+asInt);


	}
}
		
		
		
		
		