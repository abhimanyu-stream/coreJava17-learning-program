package com.java17.interview.prepartion;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 12, 1, 6};

        System.out.println("Before sorting:");
        printArray(arr);

        bubbleSort(arr);

        System.out.println("After sorting:");
        printArray(arr);
    }

    /**
     *
     * Key Aspects of Your Code
     * Outer Loop (i)
     *
     * Runs from 0 to n-2 (n-1 iterations in total).
     * Ensures the largest unsorted element is placed at its correct position in each pass.
     * Inner Loop (j)
     *
     * Runs from 0 to n-i-2 in each pass.
     * Ensures adjacent elements are compared and swapped if necessary.
     * Comparison Logic
     *
     * if (arr[j] > arr[j + 1]) ensures sorting in ascending order.
     * Swapping
     *
     * Performed using a temporary variable (temp).
     * This is the standard way to swap values.
     * Efficiency
     *
     * The algorithm has a worst-case time complexity of
     * 𝑂
     * (
     * 𝑛
     * 2
     * )
     * O(n
     * 2
     *  ) (for unsorted input) and a best-case time complexity of
     * 𝑂
     * (
     * 𝑛
     * )
     * O(n) (if the array is already sorted, provided the code is optimized to detect this).
     */

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false; // Track if a swap occurred
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swapping adjacent elements
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true; // Swap happened
                }
            }
            if (!swapped) { // If no swaps, array is sorted
                break;
            }
        }
    }



    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
