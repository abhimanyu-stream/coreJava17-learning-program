package com.java17.interview.prepartion;
import java.util.*;
import java.util.stream.Collectors;

public class SortMapVariousWay {
    
    public static void main(String[] args) {
        
    // =========================================================
        // Create Map
        // =========================================================

        Map<String, Integer> marks = new HashMap<>();

        marks.put("Rahul", 85);
        marks.put("Amit", 92);
        marks.put("Neha", 78);
        marks.put("Priya", 95);
        marks.put("Vikas", 88);

        System.out.println("Original Map:");
        System.out.println(marks);


        // =========================================================
        // 1. SORT BY KEY - ASCENDING
        // =========================================================

        Map<String, Integer> keyAscending = marks.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));

        System.out.println("\n1. Sort by Key - Ascending:");
        System.out.println(keyAscending);


        // ---------------------------------------------------------
        // Same sorting using Comparator
        // ---------------------------------------------------------

        Map<String, Integer> keyAscendingComparator = marks.entrySet()
                .stream()
                .sorted(
                        Comparator.comparing(
                                Map.Entry<String, Integer>::getKey
                        )
                )
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));

        System.out.println("\n1A. Sort by Key - Ascending using Comparator:");
        System.out.println(keyAscendingComparator);


        // =========================================================
        // 2. SORT BY KEY - DESCENDING
        // =========================================================

        Map<String, Integer> keyDescending = marks.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));

        System.out.println("\n2. Sort by Key - Descending:");
        System.out.println(keyDescending);


        // ---------------------------------------------------------
        // Same sorting using Comparator
        // ---------------------------------------------------------

        Map<String, Integer> keyDescendingComparator = marks.entrySet()
                .stream()
                .sorted(
                        Comparator.comparing(
                                Map.Entry<String, Integer>::getKey,
                                Comparator.reverseOrder()
                        )
                )
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));

        System.out.println("\n2A. Sort by Key - Descending using Comparator:");
        System.out.println(keyDescendingComparator);


        // =========================================================
        // 3. SORT BY VALUE - ASCENDING
        // =========================================================

        Map<String, Integer> valueAscending = marks.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));

        System.out.println("\n3. Sort by Value - Ascending:");
        System.out.println(valueAscending);


        // ---------------------------------------------------------
        // Same sorting using Comparator
        // ---------------------------------------------------------

        Map<String, Integer> valueAscendingComparator = marks.entrySet()
                .stream()
                .sorted(
                        Comparator.comparing(
                                Map.Entry<String, Integer>::getValue
                        )
                )
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));

        System.out.println("\n3A. Sort by Value - Ascending using Comparator:");
        System.out.println(valueAscendingComparator);


        // =========================================================
        // 4. SORT BY VALUE - DESCENDING
        // =========================================================

        Map<String, Integer> valueDescending = marks.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));

        System.out.println("\n4. Sort by Value - Descending:");
        System.out.println(valueDescending);


        // ---------------------------------------------------------
        // Same sorting using Comparator
        // ---------------------------------------------------------

        Map<String, Integer> valueDescendingComparator = marks.entrySet()
                .stream()
                .sorted(
                        Comparator.comparing(
                                Map.Entry<String, Integer>::getValue,
                                Comparator.reverseOrder()
                        )
                )
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));

        System.out.println("\n4A. Sort by Value - Descending using Comparator:");
        System.out.println(valueDescendingComparator);


        // =========================================================
        // 5. EXPLICIT Comparator.comparing()
        // =========================================================
        // Here we create a Comparator variable first.
        // This is useful when Comparator logic becomes complex.
        // =========================================================

        Comparator<Map.Entry<String, Integer>> byKey =
                Comparator.comparing(Map.Entry::getKey);

        Map<String, Integer> resultByKey = marks.entrySet()
                .stream()
                .sorted(byKey)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));

        System.out.println("\n5. Explicit Comparator - Key Ascending:");
        System.out.println(resultByKey);


        // =========================================================
        // 6. EXPLICIT Comparator - VALUE ASCENDING
        // =========================================================

        Comparator<Map.Entry<String, Integer>> byValue =
                Comparator.comparing(Map.Entry::getValue);

        Map<String, Integer> resultByValue = marks.entrySet()
                .stream()
                .sorted(byValue)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));

        System.out.println("\n6. Explicit Comparator - Value Ascending:");
        System.out.println(resultByValue);


        // =========================================================
        // 7. EXPLICIT Comparator - VALUE DESCENDING
        // =========================================================

        Comparator<Map.Entry<String, Integer>> byValueDescending =
                Comparator.comparing(
                        Map.Entry<String, Integer>::getValue,
                        Comparator.reverseOrder()
                );

        Map<String, Integer> resultByValueDescending = marks.entrySet()
                .stream()
                .sorted(byValueDescending)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));

        System.out.println("\n7. Explicit Comparator - Value Descending:");
        System.out.println(resultByValueDescending);


        // =========================================================
        // 8. SORT BY VALUE, THEN BY KEY
        // =========================================================
        // Primary sorting   -> Value
        // Secondary sorting -> Key
        // =========================================================

        Comparator<Map.Entry<String, Integer>> valueThenKey =
                Comparator.comparing(Map.Entry<String, Integer>::getValue)
                          .thenComparing(Map.Entry::getKey);

        Map<String, Integer> resultValueThenKey = marks.entrySet()
                .stream()
                .sorted(valueThenKey)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));

        System.out.println("\n8. Value Ascending, then Key Ascending:");
        System.out.println(resultValueThenKey);


        // =========================================================
        // 9. SORT BY VALUE DESCENDING, THEN KEY ASCENDING
        // =========================================================

        Comparator<Map.Entry<String, Integer>> valueDescThenKeyAsc =
                Comparator.comparing(
                                Map.Entry<String, Integer>::getValue,
                                Comparator.reverseOrder()
                        )
                        .thenComparing(Map.Entry::getKey);

        Map<String, Integer> resultValueDescThenKeyAsc = marks.entrySet()
                .stream()
                .sorted(valueDescThenKeyAsc)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));

        System.out.println("\n9. Value Descending, then Key Ascending:");
        System.out.println(resultValueDescThenKeyAsc);
    }
    
}
