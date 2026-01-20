package com.java17.interview.prepartion;

import java.util.*;
import java.util.stream.Collectors;

public class IfTwoOrMoreWordShareTheSameHigestFrequencyMap {

    public static void main(String[] args) {
        String str2 = "A fox jumped over the wall and over fence over the yard the";

        //Solution 1 — Find max count, then filter all entries with that value

        Map<String, Long> freq = Arrays.stream(str2.toLowerCase().split("\\s+"))
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));

        long maxCount = freq.values().stream()
                .max(Long::compare)
                .orElse(0L);

        List<String> mostFrequent = freq.entrySet().stream()
                .filter(e -> e.getValue() == maxCount)
                .map(Map.Entry::getKey)
                .toList();

        System.out.println("Highest frequency count: " + maxCount);
        System.out.println("Words with highest frequency: " + mostFrequent);


// Solution 2 — Do it in one chained stream (advanced)
        List<String> mostFrequent2 = Arrays.stream(str2.toLowerCase().split("\\s+"))
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                .entrySet().stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.groupingBy(Map.Entry::getValue,
                                Collectors.mapping(Map.Entry::getKey, Collectors.toList())),
                        m -> m.get(Collections.max(m.keySet()))
                ));

        System.out.println("mostFrequent2" + mostFrequent2);

        //Bonus – Tie-breakers
        //
        //If you instead want a single “winner” word in a tie (for example, lexicographically smallest), you can extend your comparator:
        String topWord = freq.entrySet().stream()
                .max(Comparator
                        .comparing(Map.Entry<String, Long>::getValue)
                        .thenComparing(Map.Entry::getKey)) // tie-break by key alphabetically
                //.stream().findFirst()
                .map(Map.Entry::getKey)
                .orElse(null);

        System.out.println("topWord"+ topWord);
    }
    /***
     * Summary
     * Case	Behavior	Example Code
     * Just highest one	Keeps first encountered	.max(Map.Entry.comparingByValue())
     * All tied highest	Filter by max count	Filter method
     * Group by count	.collect(groupingBy(Map.Entry::getValue))
     * Tie-break alphabetically	Use .thenComparing(Map.Entry::getKey)
     */


}
