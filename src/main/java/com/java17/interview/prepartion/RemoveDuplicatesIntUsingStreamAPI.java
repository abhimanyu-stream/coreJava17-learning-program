package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveDuplicatesIntUsingStreamAPI {

	public static void main(String[] args) {
     
		
		List<Integer> listOfInt = new ArrayList<>();
		
		listOfInt.add(1);
		listOfInt.add(1);
		listOfInt.add(2);
		listOfInt.add(2);
		listOfInt.add(2);
		listOfInt.add(1);
		
		
		System.out.println(listOfInt.stream().distinct().collect(Collectors.toList()));
		System.out.println(listOfInt);
	}

}
