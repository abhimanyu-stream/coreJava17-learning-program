package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class QuestionAnswers {

    public static void main(String[] args) {

        Integer[] arr = {1, 2, 8, 2, 2, 2, 5, 1};

        // ─── Sum using reduce ──────────────────────────────────────────────────────
        //
        // reduce(identity, accumulator)
        //   identity    = starting value (0 for sum)
        //   accumulator = BinaryOperator applied to (runningTotal, nextElement)
        //
        // How it works step by step for {1, 2, 8, 2, 2, 2, 5, 1}:
        //   0 + 1 = 1
        //   1 + 2 = 3
        //   3 + 8 = 11
        //  11 + 2 = 13
        //  13 + 2 = 15
        //  15 + 2 = 17
        //  17 + 5 = 22
        //  22 + 1 = 23

        // Approach 1: reduce with lambda
        int sum1 = Arrays.stream(arr)
                .reduce(0, (total, num) -> total + num);
        System.out.println("Sum (lambda)            : " + sum1);  // 23

        // Approach 2: reduce with method reference — cleaner
        int sum2 = Arrays.stream(arr)
                .reduce(0, Integer::sum);
        System.out.println("Sum (method reference)  : " + sum2);  // 23

        // Approach 3: reduce without identity → returns Optional<Integer>
        // Use this when the array could be empty (no identity value assumed)
        Arrays.stream(arr)
                .reduce((total, num) -> total + num)
                .ifPresent(s -> System.out.println("Sum (Optional)          : " + s));  // 23

        // Approach 4: mapToInt(Integer::intValue) + sum
        // Integer::intValue is a method reference that unboxes Integer → int.
        // Arrays.stream(Integer[]) produces Stream<Integer> (boxed).
        // mapToInt converts Stream<Integer> → IntStream (primitive), enabling .sum().
        //
        // Integer::intValue  ≡  (Integer i) -> i.intValue()  ≡  (Integer i) -> (int) i
        //
        // Why not just .sum() directly on Stream<Integer>?
        //   Stream<Integer> has no .sum() — only IntStream/LongStream/DoubleStream do.
        //   mapToInt(Integer::intValue) is the bridge.
        int sum4 = Arrays.stream(arr)
                .mapToInt(Integer::intValue)   // Stream<Integer> → IntStream (unbox)
                .sum();                         // IntStream.sum() → int
        System.out.println("Sum (mapToInt+intValue) : " + sum4);  // 23

        // Approach 5: mapToInt with lambda (same result, explicit unboxing shown)
        int sum5 = Arrays.stream(arr)
                .mapToInt(i -> i.intValue())   // identical to Integer::intValue
                .sum();
        System.out.println("Sum (lambda intValue)   : " + sum5);  // 23



        // ─── Approach 1: containsKey + get ────────────────────────────────────────
        // Classic way — check if key exists, then read and increment
        Map<Integer, Integer> freq1 = new HashMap<>();
        for (int num : arr) {
            if (freq1.containsKey(num)) {
                freq1.put(num, freq1.get(num) + 1);
            } else {
                freq1.put(num, 1);
            }
        }
        System.out.println("Approach 1 (containsKey) : " + freq1);

        // ─── Approach 2: getOrDefault ──────────────────────────────────────────────
        // getOrDefault(key, defaultValue) returns the mapped value if key exists,
        // otherwise returns defaultValue — avoids the containsKey + get two-step.
        //
        // map.getOrDefault(key, 0)
        //   → if key present  : returns current count
        //   → if key absent   : returns 0  (safe default, no NullPointerException)
        // Then + 1 gives the new count to store back.
        Map<Integer, Integer> freq2 = new HashMap<>();
        for (int num : arr) {
            freq2.put(num, freq2.getOrDefault(num, 0) + 1);
        }
        System.out.println("Approach 2 (getOrDefault): " + freq2);

        // ─── Approach 3: merge ─────────────────────────────────────────────────────
        // merge(key, value, remappingFunction)
        //   → if key absent   : stores value as-is
        //   → if key present  : applies remappingFunction(existing, value)
        // Clean one-liner for frequency counting.
        Map<Integer, Integer> freq3 = new HashMap<>();
        for (int num : arr) {
            freq3.merge(num, 1, Integer::sum);
        }
        System.out.println("Approach 3 (merge)       : " + freq3);

        // ─── Approach 4: compute ───────────────────────────────────────────────────
        // compute(key, (k, v) -> newValue)
        //   → v is null if key is absent; v is current value if present.
        Map<Integer, Integer> freq4 = new HashMap<>();
        for (int num : arr) {
            freq4.compute(num, (k, v) -> v == null ? 1 : v + 1);
        }
        System.out.println("Approach 4 (compute)     : " + freq4);

        // ─── getOrDefault for safe lookup (no modification) ───────────────────────
        // Use getOrDefault when you just want to read a value safely
        // without modifying the map.
        int countOf2  = freq2.getOrDefault(2, 0);   // key exists  → returns 4
        int countOf99 = freq2.getOrDefault(99, 0);  // key absent  → returns 0
        System.out.println("Count of 2  : " + countOf2);   // 4
        System.out.println("Count of 99 : " + countOf99);  // 0
    }
}
