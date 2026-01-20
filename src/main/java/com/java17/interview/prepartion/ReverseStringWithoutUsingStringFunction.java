package com.java17.interview.prepartion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class ReverseStringWithoutUsingStringFunction {

    public static <Char> void main(String[] args) {
        //SpringApplication.run(ReverseStringWithoutUsingStringFunction.class, args);


        String  inputString = "JavaLearningCenter";


       String s = IntStream.range(0, inputString.length())
                .mapToObj(i -> inputString.charAt(inputString.length() - 1 - i))
                .map(String::valueOf)
                .collect(Collectors.joining());
        System.out.println("collect rev    " + s);


    }
    // ✅ Stream-based implementation to return index pairs
    private static List<int[]> twoSumUsingStream(int[] A, int target) {

        List<int[]> result = new ArrayList<>();

        IntStream.range(0, A.length)
                .forEach(i -> IntStream.range(i + 1, A.length)
                        .filter(j -> A[i] + A[j] == target)
                        .forEach(j -> result.add(new int[]{i, j})));

        return result;

    }
}
