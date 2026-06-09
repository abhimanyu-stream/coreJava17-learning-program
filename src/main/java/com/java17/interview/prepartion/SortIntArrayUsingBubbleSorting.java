package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Comprehensive Bubble Sort Implementation
 * 
 * Bubble Sort Algorithm:
 * - Compares adjacent elements and swaps them if they're in wrong order
 * - Largest element "bubbles up" to the end in each iteration
 * - Time Complexity: O(n²) - worst and average case
 * - Space Complexity: O(1) - in-place sorting
 * - Stable: Yes (maintains relative order of equal elements)
 * 
 * When to use:
 * - Small datasets (< 50 elements)
 * - Nearly sorted data (optimized version performs well)
 * - Educational purposes (simple to understand)
 */
public class SortIntArrayUsingBubbleSorting {

    public static void main(String[] args) {
        
        System.out.println("==================== BUBBLE SORT EXAMPLES ====================\n");
        
        // Test array
        int[] arr1 = {11, 2, 3, 0, 12, 15, 55, 66, 89, 99, 100, 101};
		
		int[] sorted = Arrays.stream(arr1)
                     .sorted()
                     .toArray();

System.out.println(Arrays.toString(sorted));

//Output:

//[0, 2, 3, 11, 12, 15, 55, 66, 89, 99, 100, 101]



Integer[] arr = {11, 2, 3, 0, 12};

Integer[] sorted = Arrays.stream(arr)
                         .sorted(Comparator.naturalOrder())
                         .toArray(Integer[]::new);

//or

Integer[] sortedDesc = Arrays.stream(arr)
                             .sorted(Comparator.reverseOrder())
                             .toArray(Integer[]::new);



	String[] names = {"Ram", "Shyam", "Mohan"};

String[] result = Arrays.stream(names)
                        .sorted()
                        .toArray(String[]::new);






/**
 * Object[] arr = Arrays.stream(names)
                     .toArray();

To get a specific array type:

String[] arr = Arrays.stream(names)
                     .toArray(String[]::new);
 *  */						
						
						
String[] names = {"Java", "Spring", "Kafka"};

String[] result = Arrays.stream(names)
                        .map(String::toUpperCase)
                        .toArray(String[]::new);


        System.out.println("Original Array: " + Arrays.toString(arr1));
        // Output: Original Array: [11, 2, 3, 0, 12, 15, 55, 66, 89, 99, 100, 101]


        // ==================== METHOD 1: Basic Bubble Sort ====================
        System.out.println("\n--- METHOD 1: Basic Bubble Sort (Ascending) ---");
        int[] arr2 = arr1.clone(); // Clone to preserve original
        basicBubbleSort(arr2);
        System.out.println("Sorted Array: " + Arrays.toString(arr2));
        // Output: Sorted Array: [0, 2, 3, 11, 12, 15, 55, 66, 89, 99, 100, 101]


        // ==================== METHOD 2: Optimized Bubble Sort ====================
        System.out.println("\n--- METHOD 2: Optimized Bubble Sort (with early termination) ---");
        int[] arr3 = arr1.clone();
        optimizedBubbleSort(arr3);
        System.out.println("Sorted Array: " + Arrays.toString(arr3));
        // Output: Sorted Array: [0, 2, 3, 11, 12, 15, 55, 66, 89, 99, 100, 101]


        // ==================== METHOD 3: Bubble Sort Descending ====================
        System.out.println("\n--- METHOD 3: Bubble Sort (Descending) ---");
        int[] arr4 = arr1.clone();
        bubbleSortDescending(arr4);
        System.out.println("Sorted Array (Desc): " + Arrays.toString(arr4));
        // Output: Sorted Array (Desc): [101, 100, 99, 89, 66, 55, 15, 12, 11, 3, 2, 0]


        // ==================== METHOD 4: Step-by-Step Visualization ====================
        System.out.println("\n--- METHOD 4: Bubble Sort with Step Visualization ---");
        int[] arr5 = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original: " + Arrays.toString(arr5));
        bubbleSortWithVisualization(arr5);
        System.out.println("Final Sorted: " + Arrays.toString(arr5));


        // ==================== METHOD 5: Using Stream API (for comparison) ====================
        System.out.println("\n--- METHOD 5: Using Stream API (not bubble sort) ---");
        int[] arr6 = arr1.clone();
        int[] sortedStream = Arrays.stream(arr6).sorted().toArray();
        System.out.println("Stream Sorted: " + Arrays.toString(sortedStream));
        // Output: Stream Sorted: [0, 2, 3, 11, 12, 15, 55, 66, 89, 99, 100, 101]


        // ==================== PERFORMANCE COMPARISON ====================
        System.out.println("\n--- PERFORMANCE COMPARISON ---");
        int[] largeArray = generateRandomArray(1000);
        
        long start1 = System.nanoTime();
        basicBubbleSort(largeArray.clone());
        long end1 = System.nanoTime();
        System.out.println("Basic Bubble Sort: " + (end1 - start1) / 1_000_000.0 + " ms");
        
        long start2 = System.nanoTime();
        optimizedBubbleSort(largeArray.clone());
        long end2 = System.nanoTime();
        System.out.println("Optimized Bubble Sort: " + (end2 - start2) / 1_000_000.0 + " ms");
        
        long start3 = System.nanoTime();
        Arrays.sort(largeArray.clone());
        long end3 = System.nanoTime();
        System.out.println("Arrays.sort (Built-in): " + (end3 - start3) / 1_000_000.0 + " ms");


        System.out.println("\n==================== END OF EXAMPLES ====================");
    }


    /**
     * METHOD 1: Basic Bubble Sort (Ascending Order)
     * 
     * Algorithm:
     * 1. Compare adjacent elements
     * 2. Swap if left > right
     * 3. Repeat for all elements
     * 4. After each pass, largest element reaches correct position
     * 
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     * 
     * @param arr Array to be sorted
     */
    public static void basicBubbleSort(int[] arr) {
        int n = arr.length;
        
        // Outer loop: number of passes
        // Runs (n - 1) times, where n is the length of array
        // Example: if n = 12, loop runs 11 times (i = 0 to 10)
        for (int i = 0; i < n - 1; i++) {
            
            // Inner loop: comparisons in each pass
            // Pass 1: runs (n-1) times, Pass 2: runs (n-2) times, etc.
            // Total comparisons across all passes: n(n-1)/2
            for (int j = 0; j < n - i - 1; j++) {
                
                // Compare adjacent elements
                if (arr[j] > arr[j + 1]) {
                    // Swap if they're in wrong order
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }


    /**
     * METHOD 2: Optimized Bubble Sort
     * 
     * Optimization: If no swaps occur in a pass, array is already sorted
     * This reduces time complexity for nearly sorted arrays to O(n)
     * 
     * Best Case: O(n) - when array is already sorted
     * Average Case: O(n²)
     * Worst Case: O(n²)
     * 
     * @param arr Array to be sorted
     */
    public static void optimizedBubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        
        // Outer loop: runs maximum (n - 1) times
        // Can terminate early if array becomes sorted before all passes
        // Example: if n = 12, loop can run anywhere from 1 to 11 times
        for (int i = 0; i < n - 1; i++) {
            swapped = false; // Flag to track if any swap happened
            
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            
            // If no swaps occurred, array is sorted
            if (!swapped) {
                System.out.println("Array sorted after " + (i + 1) + " passes (early termination)");
                break;
            }
        }
    }


    /**
     * METHOD 3: Bubble Sort in Descending Order
     * 
     * Similar to ascending, but swap condition is reversed
     * Swaps when left < right (instead of left > right)
     * 
     * @param arr Array to be sorted in descending order
     */
    public static void bubbleSortDescending(int[] arr) {
        int n = arr.length;
        
        // Outer loop: runs (n - 1) times
        // Example: for array of 12 elements, runs 11 times (i = 0 to 10)
        // Outer loop: runs (n - 1) times
        // Example: for array of 12 elements, runs 11 times (i = 0 to 10)
        for (int i = 0; i < n - 1; i++) {
            // Inner loop: comparisons in each pass
            // Pass 1: (n-1) comparisons, Pass 2: (n-2) comparisons, etc.
            for (int j = 0; j < n - i - 1; j++) {
                // Swap if current element is smaller than next
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }


    /**
     * METHOD 4: Bubble Sort with Step-by-Step Visualization
     * 
     * Shows the array state after each pass
     * Useful for understanding how bubble sort works
     * 
     * @param arr Array to be sorted
     */
    public static void bubbleSortWithVisualization(int[] arr) {
        int n = arr.length;
        
        System.out.println("Step-by-step visualization:");
        
        // Outer loop: runs maximum (n - 1) times
        // Example: for 7 elements, runs max 6 times but may terminate early
        for (int i = 0; i < n - 1; i++) {
            System.out.println("\nPass " + (i + 1) + ":");
            boolean swapped = false;
            
            // Inner loop: comparisons in current pass
            // Pass i: runs (n - i - 1) times
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Show swap
                    System.out.println("  Swapping: " + arr[j] + " <-> " + arr[j + 1]);
                    
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            
            System.out.println("  After Pass " + (i + 1) + ": " + Arrays.toString(arr));
            
            if (!swapped) {
                System.out.println("  Array is sorted! (No swaps in this pass)");
                break;
            }
        }
    }


    /**
     * METHOD 5: Recursive Bubble Sort
     * 
     * Recursive implementation of bubble sort
     * Each recursion performs one pass
     * 
     * @param arr Array to be sorted
     * @param n   Current size to consider
     */
    public static void recursiveBubbleSort(int[] arr, int n) {
        // Base case: if size is 1, array is sorted
        if (n == 1) {
            return;
        }
        
        // One pass: move the largest element to end
        // Inner loop: runs (n - 1) times in this single pass
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        
        // Recursively sort the remaining n-1 elements
        recursiveBubbleSort(arr, n - 1);
    }


    /**
     * Helper method to generate random array for testing
     * 
     * @param size Size of array to generate
     * @return Array of random integers
     */
    private static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 1000);
        }
        return arr;
    }

}


/**
 * ==================== BUBBLE SORT EXPLANATION ====================
 * 
 * How Bubble Sort Works:
 * 
 * Example: [64, 34, 25, 12, 22]
 * 
 * Pass 1:
 *   [64, 34, 25, 12, 22] -> Compare 64 & 34, swap -> [34, 64, 25, 12, 22]
 *   [34, 64, 25, 12, 22] -> Compare 64 & 25, swap -> [34, 25, 64, 12, 22]
 *   [34, 25, 64, 12, 22] -> Compare 64 & 12, swap -> [34, 25, 12, 64, 22]
 *   [34, 25, 12, 64, 22] -> Compare 64 & 22, swap -> [34, 25, 12, 22, 64]
 *   Largest element (64) is now in correct position!
 * 
 * Pass 2:
 *   [34, 25, 12, 22, 64] -> Compare 34 & 25, swap -> [25, 34, 12, 22, 64]
 *   [25, 34, 12, 22, 64] -> Compare 34 & 12, swap -> [25, 12, 34, 22, 64]
 *   [25, 12, 34, 22, 64] -> Compare 34 & 22, swap -> [25, 12, 22, 34, 64]
 *   Second largest (34) is now in correct position!
 * 
 * ...and so on until fully sorted: [12, 22, 25, 34, 64]
 * 
 * ==================== COMPLEXITY ANALYSIS ====================
 * 
 * Time Complexity:
 *   Best Case:    O(n)   - Already sorted (with optimization)
 *   Average Case: O(n²)  - Random order
 *   Worst Case:   O(n²)  - Reverse sorted
 * 
 * Space Complexity: O(1) - In-place sorting
 * 
 * Number of Comparisons: n(n-1)/2
 * Number of Swaps (worst): n(n-1)/2
 * 
 * ==================== PROS & CONS ====================
 * 
 * Pros:
 *   ✅ Simple to understand and implement
 *   ✅ In-place sorting (no extra space needed)
 *   ✅ Stable (maintains order of equal elements)
 *   ✅ Good for small datasets
 *   ✅ Adaptive (performs well on nearly sorted data with optimization)
 * 
 * Cons:
 *   ❌ Poor performance on large datasets
 *   ❌ O(n²) time complexity
 *   ❌ Not suitable for production use (use QuickSort, MergeSort, or HeapSort instead)
 * 
 * ==================== WHEN TO USE ====================
 * 
 * Use Bubble Sort when:
 *   - Dataset is very small (< 50 elements)
 *   - Data is nearly sorted
 *   - Teaching/learning sorting algorithms
 *   - Memory is extremely limited
 * 
 * Don't use Bubble Sort when:
 *   - Dataset is large (> 100 elements)
 *   - Performance is critical
 *   - Better alternatives are available (Arrays.sort, Collections.sort)
 */
