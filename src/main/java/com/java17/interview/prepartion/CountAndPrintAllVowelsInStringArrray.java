package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CountAndPrintAllVowelsInStringArrray {

    public static void main(String[] args) {

        String[] str = {"apple","ieapple", "out"};

        Arrays.stream(str).forEach(


                s -> {
                    List<Character> collect = s.chars().mapToObj(i -> (char) i).filter(f -> "aeiouAEIOU".contains(String.valueOf(f))).toList();
                    System.out.println(s +"  contains "+collect);
                }
        );
    }
}
