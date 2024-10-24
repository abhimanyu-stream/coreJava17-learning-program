package com.java17.interview.prepartion;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CountIntegerFrequencyHashMap {

	public static void main(String[] args) {

		int[] arrInteger = { 10, 34, 5, 34, 34, 34, 10, 3, 5, 10 };

		Map<Integer, Integer> hashmap = new HashMap<>();
		for (int j : arrInteger) {
			if (hashmap.containsKey(j)) {
				Integer count = hashmap.get(j);
				hashmap.put(j, count + 1);
			} else {
				hashmap.put(j, 1);
			}
		}
		System.out.println(hashmap);
		for(Map.Entry<Integer, Integer> m:hashmap.entrySet()) {
			System.out.println(m.getKey()+ "  "+ m.getValue());

		}


		String s= "TankTan";

		String[] strArray = s.split("");
		Map<String, Integer> hashMapStr = new HashMap<>();
		for(int i = 0; i < s.length(); i++){

			if(hashMapStr.containsKey(strArray[i])){

				Integer count = hashMapStr.get(strArray[i]);
				hashMapStr.put(strArray[i], count+1);

			}else{
				hashMapStr.put(strArray[i], 1);
			}
		}
		System.out.println(hashMapStr);
		StringBuffer buffer = new StringBuffer();
		hashMapStr.forEach((key, value) -> buffer.append(key).append(value));
		System.out.println(buffer);




	}

}
