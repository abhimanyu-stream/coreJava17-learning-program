package com.java17.interview.prepartion;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CountWordFrequencyHashMap {

	public static void main(String[] args) {
		SpringApplication.run(CountWordFrequencyHashMap.class, args);
		
		
		
		String str = "Abhimanyu Java spring Abhimanyu JPA Java Core Java JPA";
		
		
		//Step 1
		String[] splitted = str.split(" ");
		
		
		//Step 2
		Map<String, Integer> mapObj = new HashMap<>();
		// Approach 1 containsKey
		//Step 4
		for(int i = 0; i < splitted.length; i++) {
			//Step 5.1
			if(mapObj.containsKey(splitted[i])) {
				
				int count = mapObj.get(splitted[i]);
				mapObj.put(splitted[i], count + 1);
				
			}else {
				//Step 5.2
				mapObj.put(splitted[i], 1);//Very first insert of Key
			}
		}
		//Step 6
		System.out.println(mapObj);
		
		
		// Approach 2 null
		//Step 4 
				for(int i = 0; i < splitted.length; i++) {
					int count = mapObj.get(splitted[i]);
					if(mapObj.get(splitted[i]) == null) {
						//very first entry
						mapObj.put(splitted[i], 1);
					}else {
						mapObj.put(splitted[i], count + 1);
					}
				}
	}
	
	
	

}
