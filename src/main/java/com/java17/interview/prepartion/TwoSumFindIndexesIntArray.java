package com.java17.interview.prepartion;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TwoSumFindIndexesIntArray {
    
    public static void main(String[] args) {

        // ========== Two Sum Problem ==========
        int[] nums = {9, 9, 7, 8, 3, 9, 0};
        int target = 16;

        // Find maximum value
        Integer maxi = Arrays.stream(nums).boxed().max(Comparator.naturalOrder()).get();
        // Output: 9
        System.out.println("Maximum value: " + maxi);

        // Sorted stream
        IntStream sortedNums = Arrays.stream(nums).sorted();

        // Sort in natural order (ascending)
        List<Integer> list3 = Arrays.stream(nums).boxed().sorted(Comparator.naturalOrder()).toList();
        // Output: [0, 3, 7, 8, 9, 9, 9]
        System.out.println("Natural order: " + list3);

        // Sort in reverse order (descending)
        List<Integer> list4 = Arrays.stream(nums).boxed().sorted(Comparator.reverseOrder()).toList();
        // Output: [9, 9, 9, 8, 7, 3, 0]
        System.out.println("Reverse order: " + list4);

        // Find two sum indexes
        List<int[]> resultStream = twoSumUsingStream(nums, target);
        // Output: 0-1, 1-5
        System.out.println("Two Sum indexes:");
        resultStream.forEach(ints -> System.out.println(ints[0] + "-" + ints[1]));


        // ========== String Reverse ==========
        String inputString = "JavaLearningCenter";

        // Reverse entire string - here input is String type
        String reversed = IntStream.range(0, inputString.length())
                .mapToObj(i -> inputString.charAt(inputString.length() - 1 - i))
                .map(String::valueOf)
                .collect(Collectors.joining());
        // Output: retneCgninraeLavaJ
        System.out.println("\nReversed string: " + reversed);


        // ========== Count Vowels in Each String ==========
        List<String> l1 = new ArrayList<>();
        l1.add("apple");
        l1.add("crypt");

        StringBuffer buffer = new StringBuffer();
        l1.forEach(s -> {
            long vowelCount = s.chars()
                    .mapToObj(c -> (char) c)
                    .filter(c -> "aeiouAEIOU".contains(String.valueOf(c)))
                    .count();
            // Output: Vowels in 'apple': 2
            //         Vowels in 'crypt': 0
            System.out.println("Vowels in '" + s + "': " + vowelCount);

            for (int i = 0; i < vowelCount; i++) {
                buffer.append(s);
            }
        });
        // Output: appleapple
        System.out.println("Buffer: " + buffer);
        System.out.println("String from buffer: " + new String(buffer));


        // Extract only vowels from all strings
        String buffert = l1.stream()
                .flatMap(s -> s.chars().mapToObj(c -> (char) c))
                .filter(c -> "aeiouAEIOU".indexOf(c) != -1)
                .map(String::valueOf)
                .collect(Collectors.joining());
        // Output: ae
        System.out.println("All vowels: " + buffert);


        // Extract vowels from each string separately
        StringBuilder buffers = new StringBuilder();
        l1.forEach(s -> {
            String vowels = s.chars()
                    .mapToObj(c -> (char) c)
                    .filter(c -> "aeiouAEIOU".indexOf(c) != -1)
                    .map(String::valueOf)
                    .collect(Collectors.joining());
            // Output: Vowels in 'apple': 2
            //         Vowels in 'crypt': 0
            System.out.println("Vowels in '" + s + "': " + vowels.length());
            buffers.append(vowels);
        });


        // ========== Character Frequency and Sorting ==========
        String str = "axaddaaakkaammdddkk";

        // Finding all repeated chars
        List<String> list = Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(f -> f.getValue() > 1L)
                .map(Map.Entry::getKey)
                .toList();
        // Output: [a, d, k, m]
        System.out.println("\nRepeated characters: " + list);

        // Find all non-repeated chars
        List<String> list5 = Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(f -> f.getValue() == 1L)
                .map(Map.Entry::getKey)
                .toList();
        // Output: [x]
        System.out.println("Non-repeated characters: " + list5);

        // Find first non-repeated char
        String s = Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(f -> f.getValue() == 1L)
                .findFirst()
                .map(Map.Entry::getKey)
                .get();
        // Output: x
        System.out.println("First non-repeated character: " + s);

        // Character frequency map
        Map<String, Long> charMap2 = Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
        // Output: {a=6, x=1, d=4, k=4, m=2}
        System.out.println("Character frequency (String keys): " + charMap2);


        // ========== Character Frequency with Character Keys ==========
        String str2 = "hello world";

        List<Character> list2 = str2.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(f -> f.getValue() > 1L)
                .map(Map.Entry::getKey)
                .toList();
        // Output: [l, o]
        System.out.println("Repeated characters in 'hello world': " + list2);

        Map<Character, Long> charMap3 = str2.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ));
        // Output: {h=1, e=1, l=3, o=2,  =1, w=1, r=1, d=1}
        System.out.println("Character frequency map: " + charMap3);


        // ========== Sort String by Character ==========
        Map<Character, Long> charMap = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ));

        StringBuffer b = new StringBuffer(); // Thread Safe
        StringBuilder builder = new StringBuilder();

        charMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // Sorting by key
                // Alternative sorting options:
                // .sorted((e1, e2) -> e1.getKey().compareTo(e2.getKey()))
                // .sorted(Map.Entry.<Character, Long>comparingByKey().reversed())
                // .sorted(Map.Entry.comparingByKey(Comparator.comparingInt(Character::toLowerCase)))
                .forEach(characterLongEntry -> {
                    Character character = characterLongEntry.getKey();
                    Long value = characterLongEntry.getValue();
                    for (int i = 0; i < value.intValue(); i++) {
                        builder.append(character);
                    }
                });
        // Output: aaaaaadddddkkkkmmx
        System.out.println("\nSorted string: " + new String(builder));


        // ========== Reverse Words in String ==========
        String str3 = "the java, developer jobs";

        // Approach-1: Using array and StringBuffer
        String[] splitted = str3.split(" ");
        StringBuffer buffer3 = new StringBuffer(splitted.length);
        for (int last = splitted.length - 1; last >= 0; last--) {
            buffer3.append(splitted[last]).append(" ");
        }
        // Output: jobs developer java, the
        System.out.println("\nReverse word order (Approach 1):");
        System.out.println(new String(buffer3));


        // Approach-2: Reverse entire string character by character
        String reversedd = IntStream.range(0, str3.length())
                .mapToObj(i -> (str3.charAt(str3.length() - 1 - i)))
                .map(String::valueOf)
                .collect(Collectors.joining());
        // Output: sboj repoleved ,avaj eht
        System.out.println("Mirror image (character reverse): " + reversedd);


        // ========== Reverse Each Word in String ==========
        // Method 1: Using IntStream (reverse each word)
        String result1 = Arrays.stream(str3.split(" "))
                .map(word -> IntStream.range(0, word.length())
                        .mapToObj(i -> String.valueOf(word.charAt(word.length() - 1 - i)))
                        .collect(Collectors.joining()))
                .collect(Collectors.joining(" "));

        // Method 2: Using StringBuilder (cleaner)
        String result2 = Arrays.stream(str3.split(" "))
                .map(word -> new StringBuilder(word).reverse().toString())
                .collect(Collectors.joining(" "));

        // Method 3: Using reduce (functional approach)
        String result3 = Arrays.stream(str3.split(" "))
                .map(word -> {
                    return word.chars()
                            .mapToObj(c -> (char) c)
                            .reduce("", (acc, c) -> c + acc, String::concat);
                })
                .collect(Collectors.joining(" "));

        // Output: eht ,avaj repoleved sboj
        System.out.println("\nReverse each word:");
        System.out.println("Original:  " + str3);
        System.out.println("Method 1:  " + result1);
        System.out.println("Method 2:  " + result2);
        System.out.println("Method 3:  " + result3);


        // Approach-3: Reverse word order using Collections.reverse
        String reversedWords = Arrays.stream(str3.split(" "))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list1 -> {
                            Collections.reverse(list1);
                            return list1.stream();
                        }
                ))
                .collect(Collectors.joining(" "));
        // Output: jobs developer java, the
        System.out.println("Reverse word order (Approach 3): " + reversedWords);


        // ========== Reverse Sentence with Punctuation ==========
        // Input: "Hello, world!  How are you?"
        // Output: "you? are How  world! Hello,"
        String str23 = "Hello, world!  How are you?";

        String mirrorString = Arrays.stream(str23.split(" "))
                .collect(Collectors.collectingAndThen(
                        Collectors.toCollection(ArrayList::new),
                        listt -> {
                            Collections.reverse(listt);
                            return String.join(" ", listt);
                        }
                ));
        // Output: you? are How  world! Hello,
        System.out.println("\nReversed sentence: " + mirrorString);


        // Alternative: Word order reverse using Collections
        List<String> words = Arrays.asList(str23.split(" "));
        Collections.reverse(words);
        String wordOrderReverse = words.stream()
                .collect(Collectors.joining(" "));
        // Output: you? are How  world! Hello,
        System.out.println("Word order reverse: " + wordOrderReverse);

    }


    /**
     * Stream-based implementation to find two numbers that sum to target
     * Returns list of index pairs [i, j] where nums[i] + nums[j] == target
     *
     * @param A      input array
     * @param target target sum
     * @return List of int[] containing index pairs
     */
    private static List<int[]> twoSumUsingStream(int[] A, int target) {
        List<int[]> result = new ArrayList<>();

        IntStream.range(0, A.length) // Outer loop
                .forEach(i -> IntStream.range(i + 1, A.length) // Inner loop
                        .filter(j -> A[i] + A[j] == target)
                        .forEach(j -> result.add(new int[]{i, j})));

        return result;

        // Usage:
        // List<int[]> resultStream = twoSumUsingStream(nums, target);
        // resultStream.forEach(ints -> System.out.println(ints[0] + "-" + ints[1]));
    }

}
