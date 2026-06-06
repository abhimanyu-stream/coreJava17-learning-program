package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.lang.System.out;

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

		int[] intArray = {4,55,66,33,2,18};

		int scondLargest  = Arrays.stream(intArray).boxed().sorted(Comparator.reverseOrder()).limit(2).skip(1).findFirst().get();

		Arrays.stream(intArray).boxed().max(Comparator.naturalOrder()).get();



		System.out.println(scondLargest);
		int scondSmallest  = Arrays.stream(intArray).boxed().sorted(Comparator.naturalOrder()).limit(2).skip(1).findFirst().get();
		System.out.println(scondSmallest);



		// check below two for correctness

		final Integer highestSal = Arrays.stream(intArray).boxed().sorted((o1, o2) -> (int) (o2 - o1)).findFirst().get();
		System.out.println(highestSal);

		final Integer SeconsHighestSal = Arrays.stream(intArray).boxed().sorted((o1, o2) -> (int) (o2 - o1)).limit(2).skip(1).findFirst().get();
		System.out.println(SeconsHighestSal);










		int largest = Arrays.stream(intArray).max().getAsInt();
		System.out.println("largest  " + largest);
		int minValue = Arrays.stream(intArray).sorted().findFirst().getAsInt();// ascending order
		System.out.println("minValue " + minValue);
		int min = Arrays.stream(intArray).min().getAsInt();
		System.out.println("minValue " + min);







		List<Integer> listOfInt = new ArrayList<>();
		for (int i : intArray) {
			listOfInt.add(i);
		}
		//Find Second Largest in the List
		Integer secondMax = listOfInt.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
		System.out.println("secondMax "+secondMax);
		Integer secondMin = listOfInt.stream().sorted().skip(1).findFirst().get();

		System.out.println("secondMin "+secondMin);


		// Convert int array to ArrayList<Integer>
		Integer[] inntegerArray = {5, 2, 10, 9, 8, 3};
		List<Integer> intList = Arrays.asList(inntegerArray);
		int secondMinInt = intList.stream().sorted(Comparator.naturalOrder()).skip(1).findFirst().get();
		System.out.println("secondMin "+secondMinInt);



	}
}
		
		
		
		
		