package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.List;

public class FindSumOfAllElementsByReduce {

    public static void main(String[] args) {
        

        List<Integer> list = Arrays.asList(3,6,7,3,2);
        int sum = list.stream()
        .reduce(0, Integer::sum);

        System.out.println(sum);
        
    }
    
}
