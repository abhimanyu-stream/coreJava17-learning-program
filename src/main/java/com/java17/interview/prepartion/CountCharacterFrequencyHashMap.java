package com.java17.interview.prepartion;

import java.util.HashMap;
import java.util.Map;

public class CountCharacterFrequencyHashMap {
	
	public static void main(String[] args) {
		
		String str = "JavaSpringHibernateJPASAOPRESTAWS";
		
		//char[] charArray = str.toCharArray();
		String[] split = str.split("");
		Map<String, Integer> hashmap = new HashMap<>();
		for(int i = 0; i < str.length(); i++) {
			
			if(hashmap.containsKey(split[i])) {
				int count = hashmap.get(split[i]);
				hashmap.put(split[i], count + 1);
			}else {
				hashmap.put(split[i], 1);
			}
		}
		System.out.println(hashmap);
	}

}
