package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PrintElementsAccordingToTheFrequencyOfItsOccurenceInGivenString {

    public static void main(String[] args) {

      //java program input is  "aaaaaaammdddddkkkkx" output is "xmmkkkkdddddaaaaaaa"
        // Given text: "aaaaaaammdddddkkkkx"

        String  str = "aaaaaaammdddddkkkkx";// axaddaaakkaammdddkk
        // Sorted result: "xmmkkkkdddddaaaaaaa"

        char [] charArray = str.toCharArray();

        Map<Character, Integer> hashMap = new HashMap<>();
         int ifCount = 0;

        for(int i = 0; i < charArray.length; i++){
            if(hashMap.containsKey(charArray[i])){
                ifCount = hashMap.get(charArray[i]);

                hashMap.put(charArray[i], ifCount + 1);

            }else{
                hashMap.put(charArray[i], 1);
            }
        }
        System.out.println("hashMap " + hashMap);
        for(Map.Entry<Character, Integer> m: hashMap.entrySet()) {
            System.out.println(m.getKey()+ "  "+ m.getValue());
        }


        System.out.println("hashMap.keySet().size() " + hashMap.keySet().size());
        // Create a list to store the characters in sorted order
        List<Character> list = new ArrayList<>(hashMap.keySet());
        list.sort((c1, c2) -> hashMap.get(c1) - hashMap.get(c2));
        // Print the sorted string
        for (char c : list) {
            System.out.print(c);
        }


        System.out.println("\n--------------Using Stream API---------------------");

        // Count occurrences of each character
        Map<Character, Long> charCountMap = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        System.out.println(charCountMap);

        // Construct the output string
        StringBuilder output = new StringBuilder();
        charCountMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // Sort by character[ key] [ Higher value are placed at first place and so on]
                .forEach(entry -> {
                    char c = entry.getKey();// key
                    long counting = entry.getValue();// value
                    for (int i = 0; i < counting; i++) {
                        output.append(c);
                    }
                });

        System.out.println("output.reverse() "+output.reverse()); // Print the final output
    }



}
/**
 * Demonstrate cyclic dependency in java program
 *         @Component
 *         public Class A {
 *             @Autowired
 *             private B b;
 *         }
 *         @Component
 *         public Class B {
 *             @Autowired
 *             private A a;
 *         }
 * */

