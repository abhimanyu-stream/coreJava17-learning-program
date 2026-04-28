package com.java17.interview.prepartion;

import java.util.*;
import java.util.stream.Collectors;

public class FindNthHighestSalary {

    public static void main(String[] args) {




        //sorted(Collections.reverseOrder()) — When using Collections API
        //Collections.sort(list, Collections.reverseOrder());


        //sorted(Comparator.reverseOrder()) — When using Streams
        //List<Integer> sortedList = list.stream()
        //        .sorted(Comparator.reverseOrder())
        //        .toList();




        //Sorting Objects in Reverse Order (Custom Comparator)
        //List<User> sortedUsers = users.stream()
        //        .sorted(Comparator.comparing(User::getAge).reversed())
        //        .toList();






        // Create a HashMap to store employee names and their corresponding salaries
        Map<String, Double> employeeSalaryMap = new HashMap<>();

        employeeSalaryMap.put("John", 20000.00);
        employeeSalaryMap.put("David", 35000.00);
        employeeSalaryMap.put("Michael", 45000.00);
        employeeSalaryMap.put("Robert", 35000.00);
        employeeSalaryMap.put("Kevin", 50000.00);
        employeeSalaryMap.put("Daniel", 45000.00);

        System.out.println("2nd Highest: " + getNthHighestSalaryEmployees(employeeSalaryMap, 2));
        System.out.println("3rd Highest: " + getNthHighestSalaryOptimized(employeeSalaryMap, 3));


    }

    public static List<String> getNthHighestSalaryEmployees(Map<String, Double> map, int n) {
        return map.entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue,
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())))
                .entrySet().stream()
                .sorted(Map.Entry.<Double, List<String>>comparingByKey().reversed())
                .skip(n - 1)
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse(Collections.emptyList());
    }
    public static List<String> getNthHighestSalaryOptimized(Map<String, Double> map, int n) {
        TreeMap<Double, List<String>> sorted =
                new TreeMap<>(Comparator.reverseOrder());

        map.forEach((name, salary) ->
                sorted.computeIfAbsent(salary, k -> new ArrayList<>()).add(name));

        return sorted.values().stream()
                .skip(n - 1)
                .findFirst()
                .orElse(Collections.emptyList());
    }



}