package com.java17.interview.prepartion;

import java.util.*;
import java.util.stream.Collectors;

public class HighestFrequencyWordMap {

    public static void main(String[] args) {
        String str2 = "A fox jumped over the wall and over fence over the yard";

        String result = Arrays.stream(str2.toLowerCase().split("\\s+"))
                .collect(Collectors.groupingBy(w -> w, Collectors.counting())) // Map<String, Long>
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())   // find entry with the highest frequency


                .map(Map.Entry::getKey)              // get the word
                .orElse(null);

        System.out.println("Highest frequency word: " + result);

        /**
         * Let’s analyze it step-by-step.
         *
         * 🧩 Problem
         *
         * Your code:
         *
         * .max(Map.Entry.comparingByValue())
         *
         *
         * ➡️ returns only one entry — the first entry with the highest value encountered during the stream.
         *
         * So if two (or more) words share the same highest frequency, only one will be returned (which one depends on stream order — not deterministic if you use HashMap).
         *
         * ✅ Goal
         *
         * If multiple words have the same highest count, we want all of them.
         *
         */
        /**
         *
         */







        String text = "A fox jumped over the wall and over fence over the yard";
        Map<String, Long> wordCount = Arrays.stream(text.toLowerCase().split("\\s+"))
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));

        // 1. Sort by key ascending
        System.out.println("By Key Asc:");
        wordCount.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(System.out::println);

        //equivalent

        wordCount.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.naturalOrder()))
                .forEach(System.out::println);


        //Custom rules:

        wordCount.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparingInt(String::length)))
                .forEach(System.out::println);



        // 2. Sort by key descending
        System.out.println("\nBy Key Desc:");
        wordCount.entrySet().stream()
                //.sorted(Map.Entry.<String, Long>comparingByKey().reversed())
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .forEach(System.out::println);

        // 3. Sort by value descending
        System.out.println("\nBy Value Desc:");
        wordCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(System.out::println);

        // 4. Filter words that appear more than once
        System.out.println("\nWords appearing > 1:");
        wordCount.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .forEach(System.out::println);

        // 5. Top 3 words by frequency
        System.out.println("\nTop 3 frequent words:");
        wordCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                //.sorted(Map.Entry.comparingByValue()) --> ok
                .limit(3)
                .forEach(System.out::println);

 


        //----------------------------------------
        // ✅ Step 1 — use LinkedHashMap to preserve insertion (first appearance) order
        Map<String, Long> freq2 = Arrays.stream(str2.toLowerCase().split("\\s+"))
                .collect(Collectors.groupingBy(
                        w -> w,
                        LinkedHashMap::new, // keeps first occurrence order ---> over
                        // LinkedHashMap::new, // keeps first occurrence order  ---> the
                        Collectors.counting()
                ));

        // ✅ Step 2 — find the entry with highest count; tie -> first in insertion order
        String topWord = freq2.entrySet().stream()
               // .max(Comparator.comparing(Map.Entry<String, Long>::getValue))
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        System.out.println("Top word (first occurrence wins on tie): " + topWord);

    }
    /**
     * Summary — Intermediate Methods for Map Streams
     * Category	                      Method	                                         Purpose
     * Conversion	      entrySet().stream(), keySet().stream(), values().stream()	    Stream from Map
     * Filtering	      filter(predicate)	                                             Keep only matching entries
     * Sorting	          sorted(comparingByKey()), sorted(comparingByValue())	          Sort by key/value
     *
                        .sorted(Map.Entry.<String, Long>comparingByKey().reversed())
                        .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()))
     *
     * Transformation	  map(), mapToInt(), mapToObj()	                                 Convert entries to other types
     * Debug	        peek(System.out::println)	                                      View intermediate elements
     * Limiting	        limit(n), skip(n)	                                              Restrict stream size
     * Terminal	        forEach, collect(toMap), findFirst, max, min, Reduce	           Consume results
     *
     */

}
/***
 *
 *
 * Excellent — that’s a real-world use case of Java Streams + Map intermediate methods 👏
 *
 * Let’s go step by step to understand all important intermediate Map operations — including entrySet(), keySet(), values(), sorted(), filter(), map(), collect(), and how to sort by key/value and convert between maps, lists, and sets.
 *
 * 🌳 1️⃣ Start point — A word frequency Map
 *
 * We often start with a Map like this:
 *
 * Map<String, Long> wordCount = Arrays.stream(str2.toLowerCase().split("\\s+"))
 *         .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
 *
 *
 * Now wordCount is:
 *
 * {a=1, fox=1, jumped=1, over=3, the=2, wall=1, and=1, fence=1, yard=1}
 *
 * 🌿 2️⃣ Convert Map → Stream
 *
 * There are 3 main ways to stream over a Map:
 *
 * Stream Type	Code	Produces
 * Entry Stream	map.entrySet().stream()	Stream<Map.Entry<K,V>>
 * Key Stream	map.keySet().stream()	Stream<K>
 * Value Stream	map.values().stream()	Stream<V>
 *
 * 👉 Most powerful: entrySet().stream() because it lets you use both key + value.
 *
 * 🌾 3️⃣ Common intermediate operations on Map stream
 * (a) Filter entries
 * wordCount.entrySet().stream()
 *     .filter(e -> e.getValue() > 1)
 *
 *
 * ✅ Keeps only words appearing more than once.
 *
 * (b) Sort by key
 * .sorted(Map.Entry.comparingByKey())
 *
 *
 * ➡️ Descending order:
 *
 * .sorted(Map.Entry.<String, Long>comparingByKey().reversed())
 *
 * (c) Sort by value
 * .sorted(Map.Entry.comparingByValue())
 *
 *
 * ➡️ Descending order:
 *
 * .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()))
 *
 * (d) Map / Transform entries
 *
 * Example — extract just keys or values:
 *
 * .map(Map.Entry::getKey)     // Stream<String>
 * .map(Map.Entry::getValue)   // Stream<Long>
 *
 * (e) Limit / Skip
 * .limit(3)  // Top 3 entries
 * .skip(1)   // Skip the first one
 *
 * (f) Peek for debugging
 * .peek(System.out::println)
 *
 * 🌻 4️⃣ Terminal operations — Collect or Find
 * (a) Collect back to Map
 * Map<String, Long> sortedByValueDesc = wordCount.entrySet().stream()
 *         .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()))
 *         .collect(Collectors.toMap(
 *                 Map.Entry::getKey,
 *                 Map.Entry::getValue,
 *                 (e1, e2) -> e1, // merge function
 *                 LinkedHashMap::new // preserve order
 *         ));
 *
 * (b) Find max / min
 * Map.Entry<String, Long> maxEntry = wordCount.entrySet().stream()
 *         .max(Map.Entry.comparingByValue())
 *         .orElse(null);
 *
 *
 * Or directly the key (as in your example):
 *
 * String maxWord = wordCount.entrySet().stream()
 *         .max(Map.Entry.comparingByValue())
 *         .map(Map.Entry::getKey)
 *         .orElse(null);
 *
 * (c) Convert to list or set
 * List<String> keys = wordCount.keySet().stream().toList();
 * List<Long> values = wordCount.values().stream().toList();
 *
 * 🌸 5️⃣ Example — All common Map stream use cases in one place
 * public class MapStreamExamples {
 *     public static void main(String[] args) {
 *         String text = "A fox jumped over the wall and over fence over the yard";
 *         Map<String, Long> wordCount = Arrays.stream(text.toLowerCase().split("\\s+"))
 *                 .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
 *
 *         // 1. Sort by key ascending
 *         System.out.println("By Key Asc:");
 *         wordCount.entrySet().stream()
 *                 .sorted(Map.Entry.comparingByKey())
 *                 .forEach(System.out::println);
 *
 *         // 2. Sort by key descending
 *         System.out.println("\nBy Key Desc:");
 *         wordCount.entrySet().stream()
 *                 .sorted(Map.Entry.<String, Long>comparingByKey().reversed())
 *                 .forEach(System.out::println);
 *
 *         // 3. Sort by value descending
 *         System.out.println("\nBy Value Desc:");
 *         wordCount.entrySet().stream()
 *                 .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()))
 *                 .forEach(System.out::println);
 *
 *         // 4. Filter words that appear more than once
 *         System.out.println("\nWords appearing > 1:");
 *         wordCount.entrySet().stream()
 *                 .filter(e -> e.getValue() > 1)
 *                 .forEach(System.out::println);
 *
 *         // 5. Top 3 words by frequency
 *         System.out.println("\nTop 3 frequent words:");
 *         wordCount.entrySet().stream()
 *                 .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()))
 *                 .limit(3)
 *                 .forEach(System.out::println);
 *     }
 * }
 *
 * 🌼 Summary — Intermediate Methods for Map Streams
 * Category	Method	Purpose
 * Conversion	entrySet().stream(), keySet().stream(), values().stream()	Stream from Map
 * Filtering	filter(predicate)	Keep only matching entries
 * Sorting	sorted(comparingByKey()), sorted(comparingByValue())	Sort by key/value
 * Transformation	map(), mapToInt(), mapToObj()	Convert entries to other types
 * Debug	peek(System.out::println)	View intermediate elements
 * Limiting	limit(n), skip(n)	Restrict stream size
 * Terminal	forEach, collect(toMap), findFirst, max, min	Consume results
 *
 *
 */

/**
 * String text = "A fox jumped over the wall and over fence over the yard";
 *         Map<String, Long> wordCount = Arrays.stream(text.toLowerCase().split("\\s+"))
 *                 .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
 *
 *         // 1. Sort by key ascending
 *         System.out.println("By Key Asc:");
 *         wordCount.entrySet().stream()
 *                 .sorted(Map.Entry.comparingByKey())
 *                 .forEach(System.out::println);  is    both are same   wordCount.entrySet().stream()
 *                 .sorted(Map.Entry.comparingByKey(Comparator.naturalOrder))
 *                 .forEach(System.out::println);
 *
 *                 Yes — for String keys, those two lines are effectively the same.
 *
 * Here’s the small but important bit of Java lore behind it.
 *
 * Map.Entry.comparingByKey()
 * uses the key’s natural ordering. That means it relies on the key type implementing Comparable.
 *
 * String implements Comparable<String>, and its natural order is lexicographical (dictionary) order.
 *
 * So this:
 *
 * .sorted(Map.Entry.comparingByKey())
 *
 *
 * is internally equivalent to:
 *
 * .sorted(Map.Entry.comparingByKey(Comparator.naturalOrder()))
 *
 *
 * Because Comparator.naturalOrder() just says:
 * “Use the key’s own compareTo() method.”
 *
 * In your case:
 *
 * Key type → String
 *
 * Natural order → alphabetical
 *
 * Result → identical output
 *
 * Where the second form matters is when you want to be explicit or non-default, for example:
 *
 * Reverse order:
 *
 * .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
 *
 *
 * Custom rules:
 *
 * .sorted(Map.Entry.comparingByKey(
 *     Comparator.comparingInt(String::length)
 * ))
 *
 *
 * Interview-grade takeaway you can say out loud:
 *
 * comparingByKey() uses the natural ordering of the key. Supplying Comparator.naturalOrder() is redundant for Comparable keys like String, but useful when you want reverse or custom ordering.
 *
 */