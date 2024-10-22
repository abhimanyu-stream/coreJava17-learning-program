package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamAndMapAndReduce {

    public static void main(String[] args) {


        List<Integer> listOfInt = Arrays.asList(1,2,3,4,5);
        Integer sumValueWithInitialValueZero = listOfInt.stream().reduce(0, Integer::sum)+1;// 0+ 15 + 1 = 16, 0 is initial value
        System.out.println(sumValueWithInitialValueZero);
        Integer sumValueWithInitialValueTen = listOfInt.stream().reduce(10, Integer::sum)+1;// 10 + 15 + 1 = 26, 10 is initial value
        System.out.println(sumValueWithInitialValueTen);
        Integer minValue = listOfInt.stream().reduce(0, Integer::min);
        System.out.println(minValue);

    }

}
