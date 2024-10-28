package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Collections;

public class SumOfEvenFromGivenArray {


    public static void main(String[] args) {

        //int[] a= {1,2,3,4,5,6,7,8} write a code to sum of all even numbers using Java8 only

        int[] a= {1,2,3,4,5,6,7,8};

        int sum = Arrays.stream(a).filter(f -> f % 2 == 0).sum();
        System.out.println(sum);

    }
}
