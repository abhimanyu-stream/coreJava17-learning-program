package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
        int[] arrInteger2 = {1, 2, 1, 3, 2, 1};

        Map<Integer, Integer> hashmap2 = new HashMap<>();

        for (int j : arrInteger2) {

            hashmap2.put(j, hashmap2.getOrDefault(j, 0) + 1);//map.getOrDefault(key, defaultValue)
        }

        System.out.println(hashmap);



        Map<Integer, Long> collect = Arrays.stream(arrInteger).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("collect"+collect);



        String s= "TankTan";


		 Map<String, Long> collect2 = Arrays.stream(s.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		  System.out.println("collect2 "+collect2);

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
