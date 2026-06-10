package com.java17.interview.prepartion;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Comprehensive Java Stream API examples covering:
 * - Two Sum Problem
 * - String Manipulation and Reversal
 * - Character Frequency Analysis
 * - Vowel Counting and Extraction
 * - Word Reversal Techniques
 */
public class TwoSumFindIndexesIntArray {
    
    public static void main(String[] args) {

        // ==================== SECTION 1: TWO SUM PROBLEM ====================
        System.out.println("==================== TWO SUM PROBLEM ====================");
        
        int[] nums = {9, 9, 7, 8, 3, 9, 0};
        int target = 16;
        
        System.out.println("Input Array: " + Arrays.toString(nums));
        System.out.println("Target Sum: " + target);

        // Find maximum value in array
        Integer maxi = Arrays.stream(nums).boxed().max(Comparator.naturalOrder()).get();
        System.out.println("Maximum value: " + maxi);
        // Output: Maximum value: 9

        // Get sorted stream
        IntStream sortedNums = Arrays.stream(nums).sorted();
        
        // Sort in natural order (ascending)
        List<Integer> list3 = Arrays.stream(nums).boxed().sorted(Comparator.naturalOrder()).toList();
        System.out.println("Sorted (Ascending): " + list3);
        // Output: Sorted (Ascending): [0, 3, 7, 8, 9, 9, 9]
        
        // Sort in reverse order (descending)
        List<Integer> list4 = Arrays.stream(nums).boxed().sorted(Comparator.reverseOrder()).toList();
        System.out.println("Sorted (Descending): " + list4);
        // Output: Sorted (Descending): [9, 9, 9, 8, 7, 3, 0]

        // Find two sum indexes using Stream API
        List<int[]> resultStream = twoSumUsingStream(nums, target);
        System.out.println("Two Sum Index Pairs:");
        resultStream.forEach(ints -> System.out.println("  [" + ints[0] + ", " + ints[1] + "] -> " + nums[ints[0]] + " + " + nums[ints[1]] + " = " + target));
        // Output: Two Sum Index Pairs:
        //   [0, 1] -> 9 + 9 = 16
        //   [1, 5] -> 9 + 9 = 16
        //   [5, 0] -> 9 + 9 = 16


        // ==================== SECTION 2: STRING REVERSAL ====================
        System.out.println("\n==================== STRING REVERSAL ====================");
        
        String inputString = "JavaLearningCenter";
        System.out.println("Original String: " + inputString);

        // Reverse entire string character by character using IntStream
        String reversed = IntStream.range(0, inputString.length())
                .mapToObj(i -> inputString.charAt(inputString.length() - 1 - i))
                .map(String::valueOf)
                .collect(Collectors.joining());
        System.out.println("Reversed String: " + reversed);
        // Output: Reversed String: retneCgninraeLavaJ


        // ==================== SECTION 3: VOWEL COUNTING AND EXTRACTION ====================
        System.out.println("\n==================== VOWEL COUNTING AND EXTRACTION ====================");
        
        List<String> l1 = new ArrayList<>();
        l1.add("apple");
        l1.add("crypt");
        System.out.println("Input Words: " + l1);

        StringBuffer buffer = new StringBuffer();// Thread Safe
        l1.forEach(word -> {
            long vowelCount = word.chars()
                    .mapToObj(c -> (char) c)
                    .filter(c -> "aeiouAEIOU".contains(String.valueOf(c)))
                    .count();
            System.out.println("Vowels in '" + word + "': " + vowelCount);

            // Append word 'vowelCount' times
            for (int i = 0; i < vowelCount; i++) {
                buffer.append(word);//s ->
            }
        });
        System.out.println("Buffer content: " + buffer);
        // Output: Buffer content: appleapple
        System.out.println("String from buffer: " + new String(buffer));
        // Output: String from buffer: appleapple






        String buffert = l1.stream()
                .flatMap(s -> s.chars().mapToObj(c -> (char) c))
                .filter(c -> "aeiouAEIOU".indexOf(c) != -1)
                .map(String::valueOf)
                .collect(Collectors.joining());
        System.out.println("All vowels concatenated: " + buffert);
        // Output: All vowels concatenated: ae

        System.out.println("buffert "+buffert);


        StringBuilder buffers = new StringBuilder();
        l1.forEach(s -> {
            String vowels = s.chars()
                    .mapToObj(c -> (char) c)
                    .filter(c -> "aeiouAEIOU".indexOf(c) != -1)
                    .map(String::valueOf)
                    .collect(Collectors.joining());
            System.out.println("Vowels in '" + s + "': " + vowels.length() + " (" + vowels + ")");
            // Output: Vowels in 'apple': 2 (ae)
            //         Vowels in 'crypt': 0 ()
            buffers.append(vowels);
        });


        // ==================== SECTION 4: CHARACTER FREQUENCY ANALYSIS ====================
        System.out.println("\n==================== CHARACTER FREQUENCY ANALYSIS ====================");
        
        String str = "axaddaaakkaammdddkk";
        System.out.println("Input String: " + str);

        // Finding all repeated characters (count > 1)
        List<String> repeatedChars = Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(f -> f.getValue() > 1L)
                .map(Map.Entry::getKey)
                .toList();
        System.out.println("Repeated characters: " + repeatedChars);
        // Output: Repeated characters: [a, d, k, m]

        // Find all non-repeated characters (count == 1)
        List<String> nonRepeatedChars = Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(f -> f.getValue() == 1L)
                .map(Map.Entry::getKey)
                .toList();
        System.out.println("Non-repeated characters: " + nonRepeatedChars);
        // Output: Non-repeated characters: [x]

        // Find first non-repeated character
        String firstNonRepeated = Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(f -> f.getValue() == 1L)
                .findFirst()
                .map(Map.Entry::getKey)
                .get();
        System.out.println("First non-repeated character: " + firstNonRepeated);
        // Output: First non-repeated character: x

        // Character frequency map (String keys)
        Map<String, Long> charMap2 = Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
        System.out.println("Character frequency (String keys): " + charMap2);
        // Output: Character frequency (String keys): {a=6, x=1, d=4, k=4, m=2}


        // ==================== SECTION 5: CHARACTER FREQUENCY WITH CHARACTER KEYS ====================
        System.out.println("\n==================== CHARACTER FREQUENCY WITH CHARACTER KEYS ====================");
        
        String str2 = "hello world";
        System.out.println("Input String: " + str2);

        // Find repeated characters using Character keys
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
        System.out.println("Repeated characters: " + list2);
        // Output: Repeated characters: [l, o]

        // Character frequency map (Character keys)
        Map<Character, Long> charMap3 = str2.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ));
        System.out.println("Character frequency map: " + charMap3);
        // Output: Character frequency map: {h=1, e=1, l=3, o=2,  =1, w=1, r=1, d=1}


        // ==================== SECTION 6: SORT STRING BY CHARACTER ====================
        System.out.println("\n==================== SORT STRING BY CHARACTER ====================");
        
        Map<Character, Long> charMap = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ));

        StringBuffer b = new StringBuffer(); // Thread Safe
        StringBuilder builder = new StringBuilder();

        // Sort characters alphabetically and reconstruct string
        charMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // Sort by character key
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
        System.out.println("Sorted string: " + new String(builder));
        // Output: Sorted string: aaaaaadddddkkkkmmx


        // Convert into Map after sorting
       /* charMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                    Map.Entry::getKey(),
                    Map.Entry::getValue
                ));
        */


        // ==================== SECTION 7: WORD ORDER REVERSAL ====================
        System.out.println("\n==================== WORD ORDER REVERSAL ====================");
        
        String str3 = "the java, developer jobs";
        System.out.println("Original: " + str3);

        // Approach 1: Using array and StringBuffer
        String[] splitted = str3.split(" ");
        StringBuffer buffer3 = new StringBuffer(splitted.length);
        for (int last = splitted.length - 1; last >= 0; last--) {
            buffer3.append(splitted[last]).append(" ");
        }
        System.out.println("Reversed word order (Approach 1): " + buffer3.toString().trim());
        // Output: Reversed word order (Approach 1): jobs developer java, the

        // Approach 2: Reverse entire string character by character
        String reversedd = IntStream.range(0, str3.length())
                .mapToObj(i -> (str3.charAt(str3.length() - 1 - i)))
                .map(String::valueOf)
                .collect(Collectors.joining());
        System.out.println("Mirror image (character reverse): " + reversedd);
        // Output: Mirror image (character reverse): sboj repoleved ,avaj eht


        // ==================== SECTION 8: REVERSE EACH WORD IN STRING ====================
        System.out.println("\n==================== REVERSE EACH WORD IN STRING ====================");
        
        String str3ForWordReversal = "Hello World Java";
        System.out.println("Original: " + str3ForWordReversal);

        // Method 1: Using IntStream to reverse each word
        String result1 = Arrays.stream(str3ForWordReversal.split(" "))
                .map(word -> IntStream.range(0, word.length())
                        .mapToObj(i -> String.valueOf(word.charAt(word.length() - 1 - i)))
                        .collect(Collectors.joining()))
                .collect(Collectors.joining(" "));

        // Method 2: Using StringBuilder (cleaner and more efficient)
        String result2 = Arrays.stream(str3ForWordReversal.split(" "))
                .map(word -> new StringBuilder(word).reverse().toString())
                .collect(Collectors.joining(" "));

        // Method 3: Using reduce (functional approach)
        String result3 = Arrays.stream(str3ForWordReversal.split(" "))
                .map(word -> {
                    return word.chars()
                            .mapToObj(c -> (char) c)
                            .reduce("", (acc, c) -> c + acc, String::concat);
                })
                .collect(Collectors.joining(" "));

        System.out.println("Method 1 (IntStream): " + result1);
        // Output: Method 1 (IntStream): olleH dlroW avaJ
        System.out.println("Method 2 (StringBuilder): " + result2);
        // Output: Method 2 (StringBuilder): olleH dlroW avaJ
        System.out.println("Method 3 (Reduce): " + result3);
        // Output: Method 3 (Reduce): olleH dlroW avaJ

        // Approach 3: Reverse word order using Collections.reverse
        String reversedWords = Arrays.stream(str3.split(" "))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list1 -> {
                            Collections.reverse(list1);
                            return list1.stream();
                        }
                ))
                .collect(Collectors.joining(" "));
        System.out.println("Reversed word order (Approach 3): " + reversedWords);
        // Output: Reversed word order (Approach 3): jobs developer java, the


        // ==================== SECTION 9: SENTENCE REVERSAL WITH PUNCTUATION ====================
        System.out.println("\n==================== SENTENCE REVERSAL WITH PUNCTUATION ====================");
        
        // Input: "Hello, world!  How are you?"
        // Output: "you? are How  world! Hello,"
        String str23 = "Hello, world!  How are you?";
        System.out.println("Original sentence: " + str23);

        // Method 1: Using collectingAndThen with ArrayList
        String mirrorString = Arrays.stream(str23.split(" "))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        listt -> {
                            Collections.reverse(listt);
                            return String.join(" ", listt);
                        }
                ));
        System.out.println("Reversed sentence (Method 1): " + mirrorString);
        // Output: Reversed sentence (Method 1): you? are How  world! Hello,

        // Method 2: Using Arrays.asList and Collections.reverse
        List<String> words = Arrays.asList(str23.split(" "));
        Collections.reverse(words);
        String WordOrderReverse = words.stream()
                .collect(Collectors.joining(" "));
        System.out.println("Reversed sentence (Method 2): " + WordOrderReverse);
        // Output: Reversed sentence (Method 2): you? are How  world! Hello,


        System.out.println("\n==================== END OF EXAMPLES ====================");
    }


    /**
     * Find pairs of indices where the sum of elements equals the target value.
     * Uses Stream API for functional approach.
     * 
     * @param A      Input array of integers
     * @param target Target sum to find
     * @return List of int arrays, each containing a pair of indices [i, j]
     *         where A[i] + A[j] == target
     * 
     * Example:
     *   Input: A = {9, 9, 7, 8, 3, 9, 0}, target = 16
     *   Output: [[0, 1], [1, 5], [5, 0]] (indices where nums[i] + nums[j] = 16)
     * 
     * Time Complexity: O(n²)
     * Space Complexity: O(k) where k is the number of valid pairs
     */
    private static List<int[]> twoSumUsingStream(int[] A, int target) {
        List<int[]> result = new ArrayList<>();

        // Outer loop: iterate through each element
        IntStream.range(0, A.length)
                .forEach(i ->
                        // Inner loop: iterate from i+1 to avoid duplicates
                        IntStream.range(i + 1, A.length)
                                // Filter pairs that sum to target
                                .filter(j -> A[i] + A[j] == target)
                                // Add valid pair to result
                                .forEach(j -> result.add(new int[]{i, j})
                                        )
                );

        return result;
        
        // Usage example:
        // List<int[]> resultStream = twoSumUsingStream(nums, target);
        // resultStream.forEach(ints -> System.out.println(ints[0] + "-" + ints[1]));
    }

}
