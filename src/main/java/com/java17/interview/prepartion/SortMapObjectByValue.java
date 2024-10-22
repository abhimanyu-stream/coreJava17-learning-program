package com.java17.interview.prepartion;

import java.util.*;

public class SortMapObjectByValue {

    public static void main(String[] args) {
        Map<Integer, List<String>> map = new HashMap<>();
        map.put(1, Arrays.asList("Banana", "Apple"));
        map.put(2, Arrays.asList("Dog", "Cat", "Elephant"));
        map.put(3, Arrays.asList("Amplifier"));
        map.put(4, Arrays.asList("Fish"));
        map.put(4, Arrays.asList("FAmplifier"));// with same key , the value is overridden
        map.put(5, Arrays.asList("Abc"));

        // Create a list from the entries of the map
        // convert map into list
        List<Map.Entry<Integer, List<String>>> entries = new ArrayList<>(map.entrySet());

        // Sort the list using Map.Entry.comparingByValue()
        // Map.Entry.comparingByValue(Comparator.comparingInt(List::size)) it bring is ascending order
        entries.sort(Map.Entry.comparingByValue(Comparator.comparingInt(List::size)));

        // Print the sorted map
        for (Map.Entry<Integer, List<String>> entry : entries) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
}
