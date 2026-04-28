package com.java17.interview.prepartion;

import java.util.HashMap;
import java.util.Map;

public class HashMapBasics {

    public static void main(String[] args) {


        Map<String, Integer> map = new HashMap<>();

        // 1. Adding normal key-value pairs
        map.put("A", 10);
        map.put("B", 20);

        // 2. NULL KEY (only ONE allowed)
        map.put(null, 100);
        map.put(null, 200); // overwrites previous null key value

        // 3. NULL VALUES (multiple allowed)
        map.put("C", null);
        map.put("D", null);

        // 4. DUPLICATE KEY (overwrites value)
        map.put("A", 999); // replaces 10

        // 5. DUPLICATE VALUES (allowed)
        map.put("E", 20); // same value as B
        map.put("F", 20); // same value as B


        map.put(null, null);
        map.put(null, null);
        map.put(null, null);
        map.put(null, null);

        // Printing map
        System.out.println("HashMap contents:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // Check behaviors
        System.out.println("\n--- Observations ---");

        System.out.println("Value for null key: " + map.get(null));

        System.out.println("Contains key 'A': " + map.containsKey("A"));
        System.out.println("Contains value 20: " + map.containsValue(20));

        System.out.println("Total size of map: " + map.size());



    }



}
