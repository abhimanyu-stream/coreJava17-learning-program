package com.java17.interview.prepartion;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectTheSortedStreamOfMapEntriesBackIntoALinkedHashMap {

    public static void main(String[] args) {
        Map<String, Double> employeeSalaryMap = Map.of(
                "Madonna", 10000.0,
                "Jenifer", 6000.0,
                "Charlie", 5000.0
        );
        Stream<Map.Entry<String, Double>> sorted = employeeSalaryMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()));


        Map<Double, String> salaryToEmployee = Map.of(
            10000.0, "Madonna",
            6000.0, "Jenifer",
            5000.0, "Charlie"
        );
        Stream<Map.Entry<Double, String>> sorted1 = salaryToEmployee.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()));

        /**
         *Summary Table
         * Expression	Sorts By	Example Input	Output Order
         * .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))	Key	Salary → Employee	10000 → 6000 → 5000
         * .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))	Value	Employee → Salary	10000 → 6000 → 5000
         */



        Map<String, Double> sortedByValueDesc = employeeSalaryMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) // sort by value desc
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, // merge function (not used here)
                        LinkedHashMap::new // maintain order of insertion = sorted order
                ));

        /**
         * Explanation:
         * Step	What it does
         * entrySet().stream()	Converts Map entries to stream
         * .sorted(...)	Sorts entries by value (descending here)
         * Collectors.toMap(...)	Collects back to a Map
         * LinkedHashMap::new	Keeps the same order as sorted stream
         */
        Map<String, Double> sortedByKeyDesc = employeeSalaryMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));



    }

}
