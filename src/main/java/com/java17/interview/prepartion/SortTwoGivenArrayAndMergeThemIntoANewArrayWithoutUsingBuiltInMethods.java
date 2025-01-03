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
        bubbleSort(x);//Note: - after complete execution of bubbleSort(x) the Array x will be sorted.
        bubbleSort(y);

        // Merge the sorted arrays
        int[] mergedArray = mergeArrays(x, y);

        // Print the merged array
        //System.out.println("Merged and sorted array: " + Arrays.toString(mergedArray));//Returns a string representation of the contents of the specified array. The string representation consists of a list of the array's elements, enclosed in square brackets ("[]"). Adjacent elements are separated by the characters ", " (a comma followed by a space). Elements are converted to strings as by String.valueOf(int). Returns "null" if a is null.

        //int[] result = mergeArrays(x,y);
        System.out.println(Arrays.toString(mergedArray));
        int sol = findMinimum(x);
        System.out.println(sol);





    }
    // Bubble sort implementation
    private static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false; // Reset swapped flag for each pass

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap elements
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true; // Indicate that a swap occurred
                }
            }

            // If no two elements were swapped, the array is already sorted
            if (!swapped) break;//!! equalsto true then break will be executed.    !true equalsto false then break will not be executed
        }
    }
    /***
     * The logic if (!swapped) in the Bubble Sort method is an optimization to improve efficiency by exiting the algorithm early if the array becomes sorted before completing all passes. Here's a detailed explanation:
     *
     * Purpose of if (!swapped)
     * Swapping Indicates Unsorted State:
     *
     * During each pass, the inner loop (for (int j = 0; j < n - i - 1; j++)) compares adjacent elements and swaps them if they are out of order.
     * If no swaps occur during a pass, it implies that all the elements are already in the correct order, and further passes are unnecessary.
     * Break Condition:
     *
     *
     * The statement if (!swapped) break; checks whether any swaps occurred during the current pass.
     * If swapped remains false, the loop exits early, avoiding redundant iterations.
     *
     *
     *
     * How It Works:
     * At the beginning of each pass (for loop with i), the swapped flag is reset to false.
     * As the inner loop progresses (for loop with j), elements are compared and swapped if necessary.
     * If a swap happens, swapped is set to true.
     * After completing the inner loop:
     * If swapped is false, no swaps were made during the pass, meaning the array is already sorted.
     * The break statement exits the outer loop early, saving computation time.
     * Example:
     * Array: {1, 2, 3, 4, 5} (Already Sorted)
     * Pass 1:
     * Inner loop completes without any swaps (swapped = false).
     * if (!swapped) break; is triggered, exiting the loop.
     * The algorithm finishes after one pass.
     * Array: {5, 4, 3, 2, 1} (Reverse Sorted)
     * Pass 1:
     * Multiple swaps occur to move the largest element to the correct position.
     * swapped = true.
     * Pass 2, 3, 4:
     * The process repeats until the array is fully sorted.
     * swapped is true for each pass until the last one.
     * Benefits:
     * Improved Best-Case Time Complexity:
     *
     * For an already sorted array, the algorithm exits after a single pass, reducing time complexity to
     * 𝑂
     * (
     * 𝑛
     * )
     * O(n).
     * Avoids Redundant Comparisons:
     *
     * Prevents unnecessary iterations when the array is already sorted.
     * Efficiency in Real-World Data:
     *
     * Often, data might be nearly sorted. This optimization significantly improves performance in such scenarios.
     * Summary:
     * The if (!swapped) check is a crucial improvement to the traditional Bubble Sort, enhancing its efficiency in best-case scenarios by detecting early termination when no swaps are needed.
     * */


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

