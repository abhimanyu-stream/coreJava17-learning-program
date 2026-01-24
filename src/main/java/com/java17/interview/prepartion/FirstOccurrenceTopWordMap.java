package com.java17.interview.prepartion;

import java.util.*;
import java.util.stream.Collectors;

public class FirstOccurrenceTopWordMap {
    public static void main(String[] args) {
        String str2 = "A fox jumped over the wall and over fence over the yard the";

        // ✅ Step 1 — use LinkedHashMap to preserve insertion (first appearance) order
        Map<String, Long> freq = Arrays.stream(str2.toLowerCase().split("\\s+"))
                .collect(Collectors.groupingBy(
                        w -> w,
                        LinkedHashMap::new, // keeps first occurrence order ---> over
                       // LinkedHashMap::new, // keeps first occurrence order  ---> the
                        Collectors.counting()
                ));

        // ✅ Step 2 — find the entry with highest count; tie -> first in insertion order
        String topWord = freq.entrySet().stream()
                //.max(Comparator.comparing(Map.Entry<String, Long>::getValue))
                .max(Map.Entry.comparingByValue())// ok use of Comparator
                .map(Map.Entry::getKey)
                .orElse(null);

        System.out.println("Top word (first occurrence wins on tie): " + topWord);




        String result = Arrays.stream(str2.toLowerCase().split("\\s+"))
                .collect(Collectors.groupingBy(w -> w, Collectors.counting())) // Map<String, Long>
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())   // find entry with the highest frequency


                .map(Map.Entry::getKey)              // get the word
                .orElse(null);

        System.out.println("Highest frequency word: " + result);


        /**
         * Output
         * Top word (first occurrence wins on tie): over
         *
         *
         * If another word later also had count 3, it would not replace over,
         * because LinkedHashMap keeps insertion order, and .max() picks the first encountered when values tie.
         *
         * 🧠 Why this works
         *
         * groupingBy(..., LinkedHashMap::new, ...)
         * → ensures iteration order follows first appearance in input string.
         *
         * max(comparingByValue())
         * → returns first entry with the highest value when multiple entries tie.
         *
         * Hence, the result = highest count + first appearance ✅
         *
         */



        //Optional tie-breaking logic (manual control)

        //If you didn’t use LinkedHashMap, you could still enforce this order by tracking index:

        String[] words = str2.toLowerCase().split("\\s+");
        Map<String, Long> freq2 = Arrays.stream(words)
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));

        String topWord2 = Arrays.stream(words)
                .filter(w -> Objects.equals(freq2.get(w), Collections.max(freq2.values())))
                .findFirst()
                .orElse(null);


    }
}
