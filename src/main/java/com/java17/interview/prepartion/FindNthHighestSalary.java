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