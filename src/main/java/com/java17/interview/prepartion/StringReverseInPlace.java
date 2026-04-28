package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringReverseInPlace {

    public static void main(String[] args) {
        //Reverse
        //Input : Java Coding Series
        //Output: avaJ gnidoC series



        String str = "Java Coding Series";


        String fullReverse = IntStream.range(0, str.length())
                .mapToObj(i -> str.charAt(str.length() - 1 - i))
                .map(String::valueOf)
                .collect(Collectors.joining());

        System.out.println(fullReverse);//seireS gnidoC avaJ


        /**
         * Interview Tip
         *
         * If interviewer says:
         *
         * "Reverse string" → full reverse
         * "Reverse words in place" → reverse each word, keep positions same
         * "Reverse word order" → words order reversed
         *
         * Example:
         *
         * Input:  Java Coding Series
         *
         * 1. Full reverse → seireS gnidoC avaJ
         * 2. In-place word reverse → avaJ gnidoC seireS
         * 3. Word order reverse → Series Coding Java
         */


        //Correct Approach (Reverse each word in-place)
        //reverse each word, keep positions same
        String reverseEachWordKeepPositionsSame = Arrays.stream(str.split(" "))
                .map(word -> new StringBuilder(word).reverse().toString())
                .collect(Collectors.joining(" "));

        System.out.println(reverseEachWordKeepPositionsSame);//avaJ gnidoC seireS

        String result = Arrays.stream(str.split(" "))
                .map(word -> IntStream.range(0, word.length())
                        .mapToObj(i -> word.charAt(word.length() - 1 - i))
                        .map(String::valueOf)
                        .collect(Collectors.joining()))//close map
                .collect(Collectors.joining(" "));



        //3WordOrderReverse
        List<String> words = Arrays.asList(str.split(" "));
        Collections.reverse(words);

        String WordOrderReverse = words.stream()
                .collect(Collectors.joining(" "));

        System.out.println(WordOrderReverse);//Series Coding Java



    }
}
