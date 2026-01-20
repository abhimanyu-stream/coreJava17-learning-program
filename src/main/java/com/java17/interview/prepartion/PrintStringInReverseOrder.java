package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrintStringInReverseOrder {

    public static void main(String[] args) {


        String str = "the java, developer jobs";


        //Approach-1

        // 1. String to String[]
        String[] splitted = str.split(" ");
        // 2. StringBuffer
        StringBuffer buffer = new StringBuffer(splitted.length);
        for(int last = splitted.length - 1; last >=0; last--){
            buffer.append(splitted[last]).append(" ");

        }
        //System.out.println(buffer);
        System.out.println(new String(buffer));




        //Approach-2
        String reversed = IntStream.range(0, str.length())
                .mapToObj(i -> String.valueOf(str.charAt(str.length() - 1 - i)))
                .collect(Collectors.joining());  // joining characters together

        System.out.println("Mirror image: " + reversed);





        //Approach-3
        String reversedWords = Arrays.stream(str.split(" "))  // split by spaces
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
                    Collections.reverse(list);               // reverse the list
                    return list.stream();
                }))
                .collect(Collectors.joining(" "));           // join back with spaces

        System.out.println(reversedWords);

    }
}
