package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Stream;

public class TwoSumEqualTOTagetInSortedArrayAndMaxMinStreamAPI {

    public static void main(String[] args) {

        int[] nums = {2, 6, 7, 8, 3, 9, 0};
        Arrays.sort(nums);
        int target = 8;

        int[] ans= twoSum_sortedArray(nums, target);


        for(int i = 0; i < ans.length; i++){
            System.out.println(i);
        }
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
