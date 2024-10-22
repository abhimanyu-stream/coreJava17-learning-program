package com.java17.interview.prepartion;

import java.util.HashMap;
import java.util.Map;

public class CountIntegerFrequencyHashMap {

	public static void main(String[] args) {

		int[] arrInteger = { 10, 34, 5, 34, 34, 34, 10, 3, 5, 10 };
		
		

		Map<Integer, Integer> hashmap = new HashMap<>();
		
		
		for(int i = 0; i < arrInteger.length; i++) {
			
			if(hashmap.get(arrInteger[i]) != null) {
				Integer count = hashmap.get(arrInteger[i]);
				hashmap.put(arrInteger[i], count + 1);
			
			}else {
				hashmap.put(arrInteger[i], 1);
			}
		}
		System.out.println(hashmap);
		
		for(Map.Entry<Integer, Integer> m: hashmap.entrySet()) {
			System.out.println(m.getKey()+ "  "+ m.getValue());
		}
	}

}
