package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveDuplicatesStringUsingStreamAPI {

	public static void main(String[] args) {
		
		
		List<String> listOfString = new ArrayList<>();
		listOfString.add("java");
		listOfString.add("java");
		listOfString.add("java");
		listOfString.add("ejb");
		listOfString.add("ejb");
		listOfString.add("hibernate");
		listOfString.add("java");
		
		System.out.println(listOfString.stream().distinct().collect(Collectors.toList()));
		
		List<String> newListOfSubject = removeOneSubjectJava8(listOfString, "ejb");
		System.out.println(newListOfSubject);
		
		//Using Stream
		System.out.println(listOfString.stream().filter(f -> f !="ejb").collect(Collectors.toList()));

	}

	private static List<String> removeOneSubjectJava8(List<String> listOfString, String objectToRemove) {
		
		List<String>  newList = new ArrayList<>();
		for(String s:listOfString) {
			if(!s.equals(objectToRemove)) {
				newList.add(s);
				//listOfString.remove(objectToRemove);
			}
			
		}
		return newList;
	}

}
