package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FindSmallestMissingPositiveInteger {
    public static void main(String[] args) {

        int[] A = {1, 3, 6, 4, 1, 2};
        int missing = solution(A);
        System.out.println(missing);


    }

    public static int solution(int[] A) {
        //Approach (using boolean array — clean & safe)
        int N = A.length;
        boolean[] present = new boolean[N + 1];

        // Mark valid numbers
        for (int i = 0; i < N; i++) {
            if (A[i] > 0 && A[i] <= N) {
                present[A[i]] = true;
            }
        }

        // Find smallest missing positive
        for (int i = 1; i <= N; i++) {
            if (!present[i]) {
                return i;
            }
        }

        return N + 1;
    }


    public int solution2(int[] A) {
        //Optimal Variant (O(1) space — in-place)
        int N = A.length;

        // Step 1: Replace invalid numbers
        for (int i = 0; i < N; i++) {
            if (A[i] <= 0 || A[i] > N) {
                A[i] = N + 1;
            }
        }

        // Step 2: Mark presence
        for (int i = 0; i < N; i++) {
            int val = Math.abs(A[i]);
            if (val <= N) {
                if (A[val - 1] > 0) {
                    A[val - 1] = -A[val - 1];
                }
            }
        }

        // Step 3: Find missing
        for (int i = 0; i < N; i++) {
            if (A[i] > 0) {
                return i + 1;
            }
        }

        return N + 1;
    }


    public int solution3(int[] A) {
        //Stream-based Solution (using rangeClosed)
        Set<Integer> set = Arrays.stream(A)
                .filter(x -> x > 0)
                .boxed()
                .collect(Collectors.toSet());

        return IntStream.rangeClosed(1, A.length + 1)
                .filter(i -> !set.contains(i))
                .findFirst()
                .getAsInt();
    }

    public int solution4(int[] A) {
        //When can min/max be used safely?
        Set<Integer> set = Arrays.stream(A)
                .filter(x -> x > 0)
                .boxed()
                .collect(Collectors.toSet());

        int max = set.stream().mapToInt(Integer::intValue).max().orElse(0);

        return IntStream.rangeClosed(1, max + 1)
                .filter(i -> !set.contains(i))
                .findFirst()
                .orElse(1);
    }

    public int solution5(int[] A) {
        //Pure Stream (no extra data structures)
        return IntStream.rangeClosed(1, A.length + 1)
                .filter(i -> IntStream.of(A).noneMatch(x -> x == i))
                .findFirst()
                .getAsInt();
    }
}
/**
 * Write a function: class Solution { public int solution(int[] A); } content_copy that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A. For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5. Given A = [1, 2, 3], the function should return 4. Given A = [−1, −3], the function should return 1. Write an efficient algorithm for the following assumptions: N is an integer within the range [1..100,000]; each element of array A is an integer within the range [−1,000,000..1,000,000].
 *
 */