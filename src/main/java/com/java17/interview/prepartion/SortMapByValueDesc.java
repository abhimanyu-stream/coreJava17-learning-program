package com.java17.interview.prepartion;

import java.util.*;
import java.util.stream.Collectors;

public class SortMapByValueDesc {
    public static void main(String[] args) {
        Map<String, Integer> employeeSalaryMap = new HashMap<>();
        employeeSalaryMap.put("Abhimanyu", 90000);
        employeeSalaryMap.put("Rahul", 120000);
        employeeSalaryMap.put("Sneha", 75000);
        employeeSalaryMap.put("Vikram", 150000);
        employeeSalaryMap.put("Anita", 110000);

        // Sort by value in descending order
        Map<String, Integer> sortedMap =
                employeeSalaryMap.entrySet()
                        .stream()
                        .sorted(
                                Map.Entry.comparingByValue(Comparator.reverseOrder())

                        )
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new
                        ));

        // Print result
        sortedMap.forEach((key, value) ->
                System.out.println(key + " -> " + value)
        );

        Map<String, Integer> SortByMultipleFields  =
                employeeSalaryMap.entrySet()
                        .stream()
                        .sorted(
                                Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder())
                                        .thenComparing(Map.Entry.comparingByKey())
                        )
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new
                        ));

        SortByMultipleFields .forEach((k, v) -> System.out.println(k + " -> " + v));

        TreeMap<String, Integer> TreeMapCustomComparator  = new TreeMap<>(
                (k1, k2) -> {
                    int cmp = employeeSalaryMap.get(k2)
                            .compareTo(employeeSalaryMap.get(k1)); // descending value
                    if (cmp == 0) {
                        return k1.compareTo(k2); // tie-break by key
                    }
                    return cmp;
                }
        );

        TreeMapCustomComparator .putAll(employeeSalaryMap);

        TreeMapCustomComparator .forEach((k, v) -> System.out.println(k + " -> " + v));

    }
}
