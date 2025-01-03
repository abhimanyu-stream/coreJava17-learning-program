package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class TwoSumUsingJavaStream {

    public static void main(String[] args) {

        int[] A = {2, 3, 5, 1, 4, 9};


        int target = 5;
        int index1, index2;
        int j = 0;
        int i;
        for (i = 0; i < A.length; i++) {

            //int j;
            for (j = i+1 ; j < A.length; j++) {

                if (target == A[i] + A[j] &&  A[i]<=A[j]) {// &&  A[i]<=A[j]it removes duplicates selection
                    System.out.println("two indexes pairs where we can get sum  of 5 {" + i+ " "+j +"}");

                } else {
// no match match found
                }
            }
        }

        //Optional<int[]> result = findTwoSum(A, target);


        //result.ifPresent(pair -> System.out.println("Indices: " + pair[0] + ", " + pair[1]));

        // Print all pairs whose sum equals the target value
        //printAllPairs(A, target);

        // Get all pairs whose sum equals the target
        printAllPairs(A, target);

        // Print each pair
        /*pairs.forEach(pair -> System.out.println("Pair: (" + pair[0] + ", " + pair[1] + ")"));*/
    }
    public static Optional<int[]> findTwoSum(int[] A, int target) {


        //System.out.println("list"+list.stream().forEach(System.out::println););


        // Use a stream to find two indices that sum up to the target
        return Arrays.stream(A)
                .boxed()  // Convert int to Integer for proper use with streams
                .flatMap(a ->
                        Arrays.stream(A)  // For each element, create a stream of all elements
                                .filter(b -> a + b == target)  // Check if a+b equals the target
                                .mapToObj(b -> new int[]{a, b})  // Convert the pair to int[]
                )
                .findFirst();  // Find the first pair (optional)

    }
    public static void printAllPairs(int[] A, int target) {
        // Loop through the array with streams and print all pairs that sum to the target
        Arrays.stream(A)
                .forEach(a -> Arrays.stream(A)  // For each element in the array
                        .filter(b -> a + b == target && a <= b)  // Check if the pair sums to target and avoid duplicates
                        .forEach(b -> System.out.println("Pair Elements are whose sum is 5: (" + a + ", " + b + ")")));
    }


    public static List<int[]> printAllPairs2(int[] A, int target) {
        // Use streams to find all pairs that sum to the target and collect them into a list
        return Arrays.stream(A)
                .boxed()  // Convert int to Integer for proper use with streams
                .flatMap(a -> Arrays.stream(A)  // For each element 'a', create a new stream
                        .filter(b -> a + b == target && a <= b)  // Check if sum of a and b equals target, and avoid duplicates (a <= b)
                        .mapToObj(b -> new int[]{a, b})) // Convert the valid pair to int[]
                .collect(Collectors.toList());  // Collect the pairs into a List
    }
}
