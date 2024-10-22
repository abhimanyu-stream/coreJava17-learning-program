package com.java17.interview.prepartion;

import java.util.HashMap;
import java.util.Map;

public class CountCharacterFrequencyHashMap {
	
	public static void main(String[] args) {
		
		String str = "JavaSpringHibernateJPASAOPRESTAWS";
		
		char[] charArray = str.toCharArray();
		Map<Character, Integer> hashmap = new HashMap<>();
		for(int i = 0; i < charArray.length; i++) {
			
			if(hashmap.get(charArray[i]) != null) {
				int count = hashmap.get(charArray[i]);
				hashmap.put(charArray[i], count + 1);
			}else {
				hashmap.put(charArray[i], 1);
			}
		}
		System.out.println(hashmap);
	}

}
