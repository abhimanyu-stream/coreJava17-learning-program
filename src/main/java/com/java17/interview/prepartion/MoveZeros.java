package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MoveZeros {
    public static void main(String[] args) {
        int[] arr = {2, 9, 5, 0, 0, 8, 11, 0, 14};

        int index = 0;

        // Move non-zero elements forward
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[index++] = arr[i];
            }
        }

        // Fill remaining positions with 0
        while (index < arr.length) {
            arr[index++] = 0;
        }

        // Print result
        for (int num : arr) {
            System.out.print(num + " ");
        }

        System.out.println();

        long zeroCount = Arrays.stream(arr).filter(x -> x == 0).count();

        int[] result = IntStream.concat(
                Arrays.stream(arr).filter(x -> x != 0),
                IntStream.generate(() -> 0).limit(zeroCount)
        ).toArray();

        // Print result
        Arrays.stream(result).forEach(x -> System.out.print(x + " "));

    }
}
