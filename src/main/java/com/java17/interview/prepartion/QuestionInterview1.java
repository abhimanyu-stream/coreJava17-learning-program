package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class QuestionInterview1 {

    public static void main(String[] args) {

        List<Integer> list1 = Arrays.asList(71, 21, 34, 89, 56, 28);
        List<Integer> list2 = Arrays.asList(12, 56, 17, 21, 94, 34);


        list1.forEach(s->{
            for(int i = 2; i <= list1.size(); i++){
                int count = 0;
                for(int j = 1; j <= s; j++){
                    if(s % j == 0)
                        count++;

                }

                if(count == 2)
                    System.out.println(s+"is prime");
            }
        });

        //find common elements between two lists

        BiFunction<List<Integer>, List<Integer>, List<Integer>> biFunction = (l1, l2)->{




            return null;

        };

        String str = "Hello World";


        //Reverse each word of a string using Java 8 streams

        for(int i = str.length(); i>= 0 ; i++){

        }

    }
}
