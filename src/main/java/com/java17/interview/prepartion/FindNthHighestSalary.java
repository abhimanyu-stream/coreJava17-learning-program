package com.java17.interview.prepartion;

import java.util.*;
import java.util.stream.Collectors;

public class FindNthHighestSalary {

    public static void main(String[] args) {
        // Create a HashMap to store employee names and their corresponding salaries
        Map<String, Integer> map = new HashMap<>();

        // Populate the HashMap with employee names and salaries
        map.put("Abrar", 20000);
        map.put("Chand", 35000);
        map.put("Kalam", 45000);
        map.put("Raheem", 35000);
        map.put("Kiran", 50000);
        map.put("Esa", 45000);


        Map.Entry<Integer, List<String>> integerListEntry = map.entrySet().stream().collect(Collectors.groupingBy(Map.Entry :: getValue, Collectors.mapping(Map.Entry :: getKey, Collectors.toList())))
                .entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByKey())).collect(Collectors.toList()).get(3 - 1);//3rd highest salary[ 3 - 1 ] or get(2)
        System.out.println("integerListEntry " + integerListEntry);





        // Specify the desired value of n
        int n = 3;// for 3rd highest salary
        // Get the nth highest salary along with the corresponding employee names
        Map.Entry<Integer, List<String>> res = getDynamicNthHighestSalary(map, n);
        // Print the result
        System.out.println("getDynamicNthHighestSalary "  +res);
    }

    // Method to find the nth highest salary dynamically
    public static Map.Entry<Integer, List<String>> getDynamicNthHighestSalary(Map<String, Integer> employeeSalaries, int nth) {
        // Using Java Streams to process the map and find the nth highest salary

        // 1. Group employee names by their corresponding salaries
        /*return employeeSalaries.entrySet()
                .stream()
                .collect(Collectors.groupingBy(
                        Map.Entry::getValue, // Group by salary
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList()) // Collect employee names as a list for each salary
                ))
                .entrySet()
                .stream()
                // 2. Sort the map entries by salary in descending order
                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
                // 3. Collect the sorted map entries into a List
                .collect(Collectors.toList())
                // 4. Get the nth element from the list (n - 1 as list index starts from 0)
                .get(n - 1);*/


        return employeeSalaries.entrySet()
                .stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toList())
                )).entrySet()
                .stream().sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
                .collect(
                        Collectors.toList()
                ).get(nth - 1);
    }

    
}