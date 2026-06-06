package com.java17.interview.prepartion;

public class PairWithGivenSumInASortedArray {
    public static boolean hasPairWithSum(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) {
                return true;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 6};
        int target = 8;

        System.out.println("Pair exists: " + hasPairWithSum(arr, target));
    }
}
