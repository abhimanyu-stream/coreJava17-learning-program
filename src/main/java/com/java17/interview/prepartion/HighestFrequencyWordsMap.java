package com.java17.interview.prepartion;

import java.util.*;
import java.util.stream.*;

public class HighestFrequencyWordsMap {
    /**
     *
     let’s now make it production-ready and interview-strong — showing:

     ✅ sorting by frequency descending,
     ✅ tie-break by lexicographic order,
     ✅ and optionally listing all top words clearly.
     */
    public static void main(String[] args) {
        String str2 = "A fox jumped over the wall and over fence over the yard the fence";

        Map<String, Long> freq = Arrays.stream(str2.toLowerCase().split("\\s+"))
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));

        // ✅ Sort: by frequency DESC, then word ASC
        //Sorted by frequency (desc), then lexicographically (asc):
        //Sort by Value ↓, then Key ↑
        List<Map.Entry<String, Long>> sorted = freq.entrySet().stream()
                .sorted(Comparator
                        .comparing(Map.Entry<String, Long>::getValue).reversed()
                        .thenComparing(Map.Entry::getKey))
                .toList();

        System.out.println("🔹 Sorted by frequency (desc), then lexicographically (asc):");
        sorted.forEach(System.out::println);

        // ✅ Find max frequency value
        long maxCount = sorted.get(0).getValue();

        // ✅ Extract all words with that max frequency
        List<String> topWords = sorted.stream()
                .filter(e -> e.getValue() == maxCount)
                .map(Map.Entry::getKey)
                .toList();

        System.out.println("\nHighest frequency count: " + maxCount);
        System.out.println("Words with highest frequency: " + topWords);
    }
}
/**
 * Explanation
 * Step	What it does	Key Method
 * 1️⃣	Count occurrences	groupingBy(w -> w, counting())
 * 2️⃣	Stream entries	.entrySet().stream()
 * 3️⃣	Sort by value descending	.comparingByValue().reversed()
 * 4️⃣	Tie-break by key ascending	.thenComparing(Map.Entry::getKey)
 * 5️⃣	Filter max value words	.filter(e -> e.getValue() == maxCount)
 * 🧾 Example Output
 * 🔹 Sorted by frequency (desc), then lexicographically (asc):
 * over=3
 * fence=2
 * the=2
 * a=1
 * and=1
 * fox=1
 * jumped=1
 * wall=1
 * yard=1
 *
 * Highest frequency count: 3
 * Words with highest frequency: [over]
 *
 *
 * If there’s a tie (say both over and the have 3 counts), output becomes:
 *
 * Highest frequency count: 3
 * Words with highest frequency: [over, the]
 *
 * 🔥 Shortcut variant (one-liner for top words)
 *
 * If you just want the top N words (e.g. top 3 by frequency):
 *
 * freq.entrySet().stream()
 *     .sorted(Comparator.<Map.Entry<String, Long>>comparingByValue().reversed()
 *             .thenComparing(Map.Entry::getKey))
 *     .limit(3)
 *     .forEach(System.out::println);
 */
