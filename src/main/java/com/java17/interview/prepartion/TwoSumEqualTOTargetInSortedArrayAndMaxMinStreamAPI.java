package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class TwoSumEqualTOTargetInSortedArrayAndMaxMinStreamAPI {

    public static void main(String[] args) {

        int[] nums = {9, 9, 7, 8, 3, 9, 0};
        int target = 16;

        int[] result = twoSumUsingHashTable(nums, target);

        if (result.length == 2) {
            System.out.println("Indices found: " + result[0] + ", " + result[1]);
            System.out.println("Numbers found: " + nums[result[0]] + ", " + nums[result[1]]);
        } else {
            System.out.println("No two numbers add up to the target.");
        }


        //int[] ans= twoSum_sortedArray(nums, target);




        //int[] intanswer = twoSumUsingBruteForce(nums, target);




        //int[] answer = twoSumUsingBruteForce(nums, target);


        //int[] amswerd = twoSumUsingHashTable(nums, target);




    }
    private static int[] twoSum_sortedArray(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum < target) left++;
            else if (sum > target) right--;
            else return new int[]{nums[left], nums[right]};
        }
        return new int[2];//nums.length
    }
    public static int[] twoSumUsingBruteForce(int[] nums, int target) {
        // 1. Iterate over every possible number pair
        for (int i = 0; i < nums.length; i++) {
            // j is always ahead of i so that we don't re-evaluate already evaluated sums
            for (int j = i + 1; j < nums.length; j++) {
                // 2. Check if a given pair adds up to our target
                if (nums[i] + nums[j] == target) {
                    // Return the indices when a pair has been found
                    return new int[]{i, j};
                }
            }
        }
        // Return an empty array if no pair is found
        return new int[]{};
    }

    public static int[] twoSumUsingHashTable(int[] nums, int target) {
        // Our hash table that stores at which index the number is at
        HashMap<Integer, Integer> numToIndex = new HashMap<>();

        // 1. Iterate over every number in the array
        for (int i = 0; i < nums.length; i++) {
            // 2. Calculate the complement that would sum to our target
            int complement = target - nums[i];

            // 3. Check if that complement is in our hash table
            if (numToIndex.containsKey(complement)) {
                return new int[]{numToIndex.get(complement), i};
            }

            // 4. Add the current number to our hash table
            numToIndex.put(nums[i], i);
        }

        // If no solution found, return an empty array
        return new int[]{};
    }


    /**
     * a solution to the "two-sum" problem using a hash table (in Java, a HashMap). The goal is to find two numbers in the nums array that add up to a specified target and return their indices. Here’s a breakdown of what each part does:
     *

     * public static int[] twoSumUsingHashTable(int[] nums, int target) {
     * This is the function definition. It takes two inputs:
     * nums: an array of integers.
     * target: the integer sum we want to achieve by adding two elements from nums.
     * The function returns an array of two integers, representing the indices of the two numbers in nums that add up to target.

     * HashMap<Integer, Integer> numToIndex = new HashMap<>();
     * We create a hash table (HashMap) called numToIndex that will store pairs where:
     * The key is a number in the nums array.
     * The value is the index at which this number occurs in nums.

     * for (int i = 0; i < nums.length; i++) {
     * This for loop iterates over each element in the nums array.

     * int complement = target - nums[i];
     * For each element nums[i], we calculate its "complement" with respect to the target by subtracting nums[i] from target.
     * The "complement" is the number that, when added to nums[i], will equal target.

     * if (numToIndex.containsKey(complement)) {
     *     return new int[]{numToIndex.get(complement), i};
     * }
     * We check if the complement of nums[i] (the value that would complete the pair to sum to target) already exists in numToIndex.
     * If it does, this means we have found a pair of numbers (nums[i] and complement) that add up to target.
     * We then return an array with the indices of complement (from the hash table) and nums[i] (current index i).

     * numToIndex.put(nums[i], i);
     * If the complement is not found in the hash table, we add the current number nums[i] and its index i to numToIndex.
     *
     * return new int[]{};
     * If the loop finishes without finding a pair that adds up to target, we return an empty array, indicating no solution was found.
     * Summary: This code efficiently finds two indices of numbers in the array that add up to a target sum, using a hash table to look up complements in constant time. The algorithm has a time complexity of
     * 𝑂
     * (
     * 𝑛
     * )
     * O(n) due to the single pass through the array.
     * */




    // Get Min or Max String/Char
    String maxChar = Stream.of("H", "T", "D", "I", "J")
            .max(Comparator.comparing(String::valueOf))
            .get();

    String minChar = Stream.of("H", "T", "D", "I", "J")
            .min(Comparator.comparing(String::valueOf))
            .get();

    // Get Min or Max Number
    Integer maxNumber = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
            .max(Comparator.comparing(Integer::valueOf))
            .get();

    Integer minNumber = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
            .min(Comparator.comparing(Integer::valueOf))
            .get();



}
