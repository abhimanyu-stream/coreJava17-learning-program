package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortTwoGivenArrayAndMergeThemIntoANewArrayWithoutUsingBuiltInMethods {


    public static void main(String[] args) {



        int[] x = {4, 8, 2, 1};// 1 is min value
        int[] y = {9,1,33,87};
        // Sort both arrays
        bubbleSort(x);
        bubbleSort(y);

        // Merge the sorted arrays
        int[] mergedArray = mergeArrays(x, y);

        // Print the merged array
        System.out.println("Merged and sorted array: " + Arrays.toString(mergedArray));
        int[] result = mergeArrays(x,y);
        System.out.println(Arrays.toString(result));
        int sol = findMinimum(x);
        System.out.println(sol);





    }
    // Bubble sort implementation
    private static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap elements
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    private static int[] mergeArrays(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        int n2 = arr2.length;
        int[] result = new int[n1 + n2];

        int i = 0, j = 0, k = 0;
        while (i < n1 && j < n2) {
            if (arr1[i] <= arr2[j]) {
                result[k++] = arr1[i++];// smaller of arr1 and equal to arr2
            } else {
                result[k++] = arr2[j++];// rest of larger value of arr2
            }
        }

        while (i < n1) {
            // Rest of arr1
            result[k++] = arr1[i++];
        }

        while (j < n2) {
            // Rest of arr2
            result[k++] = arr2[j++];
        }

        return result;

    }

    private static int findMinimum(int[] x) {

        List<Integer> list = new ArrayList<>();
        for(int i =0;i <x.length; i++){
            list.add(x[i]);
        }
        int y = list.stream().min(Comparator.naturalOrder()).get();
        return y;
    }
}

	/*private static int[] mergeArrays(int[] arr, int[] y) {

		// Bubble sort implementation
		private static void bubbleSort(int[] arr) {
			int n = arr.length;
			for (int i = 0; i < n - 1; i++) {
				for (int j = 0; j < n - i - 1; j++) {
					if (arr[j] > arr[j + 1]) {
						// Swap elements
						int temp = arr[j];
						arr[j] = arr[j + 1];
						arr[j + 1] = temp;
					}
				}
			}
		}

		// Selection sort implementation
		private static void selectionSort(int[] arr) {
			int n = arr.length;
			for (int i = 0; i < n - 1; i++) {
				int minIndex = i;
				for (int j = i + 1; j < n; j++) {
					if (arr[j] < arr[minIndex]) {
						minIndex = j;
					}
				}
				// Swap elements
				int temp = arr[i];
				arr[i] = arr[minIndex];
				arr[minIndex] = temp;
			}
		}
*/






/***
 * Merge Two Sorted Arrays
 * Problem Statement
 * Implement the method int[] mergeArrays(int[] arr1, int[] arr2) that takes two chronologically
 * arrays and returns a new sorted array including all elements from the input arrays.
 *
 *
 * ----
 * Find Minimum and Maximun Value in Array
 * Problem Statement
 * Create a method int findMinimum(int[] arr) that takes an array and returns the smallest element within the array.
 */

