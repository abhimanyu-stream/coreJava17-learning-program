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
        Map<String, Integer> map = new HashMap<>();

        map.put("John", 20000);
        map.put("David", 35000);
        map.put("Michael", 45000);
        map.put("Robert", 35000);
        map.put("Kevin", 50000);
        map.put("Daniel", 45000);



        //Recommended Cleaner Version (Safe, Null-Proof & Readable)
        Optional<Map.Entry<Integer, List<String>>> thirdHighest = map.entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue,
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())))
                .entrySet().stream()
               // .sorted(Map.Entry.<Integer, List<String>>comparingByKey().reversed()) both line are equivalent
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))

                .skip(2)
                .findFirst();

        thirdHighest.ifPresent(System.out::println);
        //Why this is better?
        //
        //Uses comparingByKey().reversed() → more readable
        //
        //Uses Optional → avoids .get() crash
        //
        //No Collections.reverseOrder() needed

        Map<String, Double> employeeSalaryMap = new HashMap<>();
        employeeSalaryMap.put("Charlie", 5000.00);
        employeeSalaryMap.put("Jenifer", 6000.00);
        employeeSalaryMap.put("Cally", 6000.00);
        employeeSalaryMap.put("Newton", 5000.00);
        employeeSalaryMap.put("Madonna", 10000.00);

      /*  Map<String, Integer> sortedMap =
                employeeSalaryMap.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,//Non-static method cannot be referenced from a static context
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new
                        ));*/


        /**
         * employeeSalaryMap
         *
         */

        // Step 1: Find 2nd highest salary value
        double secondHighestSalary = employeeSalaryMap.values().stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No second highest salary found"));

        // Step 2: Collect all employees having that salary
        List<String> employeesWithSecondHighestSalary = employeeSalaryMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(secondHighestSalary))
                .map(Map.Entry::getKey)
                .toList();

        System.out.println("Second Highest Salary = " + secondHighestSalary);
        System.out.println("Employees with Second Highest Salary = " + employeesWithSecondHighestSalary);





        // ✅ One-liner to find all employees having 2nd highest salary
        List<String> secondhighestsalary =
                employeeSalaryMap.entrySet().stream()
                        .collect(Collectors.groupingBy(Map.Entry::getValue)) // Group by salary Map<Double, List<Map.Entry<String, Double>>>
                        .entrySet().stream()
                        .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder())) //Set<Map.Entry<Double, List<Map.Entry<String, Double>>>>
                        //.sorted(Comparator.comparingDouble(Employees::getSalary).reversed())
                        .skip(1) // skip highest salary group
                        .findFirst() // get 2nd highest salary group
                        .map(Map.Entry::getValue) // list of entries having that salary  List<Map.Entry<String, Double>
                        .orElse(Collections.emptyList()) // fallback
                        .stream()
                        .map(Map.Entry::getKey)
                        .toList();// List<String>

        System.out.println("Employees with Second Highest Salary = " + secondhighestsalary);


        List<String> list = employeeSalaryMap.entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .skip(1)
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse(Collections.emptyList())
                .stream()
                .map(Map.Entry::getKey)
                .toList();


        Optional<Map.Entry<Double, List<String>>> first = employeeSalaryMap.entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue,
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())))
                .entrySet().stream()
                // .sorted(Map.Entry.<Integer, List<String>>comparingByKey().reversed()) both line are equivalent
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))

                .skip(1)
                .findFirst();

        first.ifPresent(System.out::println);
        /**
         *   SortByMultipleFields .forEach((k, v) -> System.out.println(k + " -> " + v));
         */







        /*Map.Entry<Integer, List<String>> integerListEntry = map.entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry :: getValue, Collectors.mapping(Map.Entry :: getKey, Collectors.toList())))
                .entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByKey())).toList().get(3 - 1);//3rd highest salary[ 3 - 1 ] or get(2)
        System.out.println("integerListEntry " + integerListEntry);


        Map.Entry<Integer, List<String>> integerListEntry1 = map.entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toList())))
                .entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByKey())).toList().stream().skip(2).findFirst().get();
        System.out.println("integerListEntry1 " + integerListEntry1);*/



        //Comparator.comparingDouble(Woker :: getSalary)
        //Collections.reverseOrder(Map.Entry.comparingByKey())



    }



}