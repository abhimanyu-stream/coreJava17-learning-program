package com.java17.interview.prepartion;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LongestCommonPrefixFromGivenStringArray {

    public static void main(String[] args) {

        String[] strArray = {"Amazon", "Amazone", "Amazonin"};// Amazon will be output
        String prefixResult = longestCommonPrefix(strArray);
        System.out.println("prefixResult   : " + prefixResult);




    }
    // String[] strArray = {"Amazon", "Amazone", "Amazonin"};// Amazon will be output
    public static String longestCommonPrefix(String[] strArray){

        if(strArray.length == 0)
            return "";

        String prefix = strArray[0];
        for(int i = 1; i < strArray.length; i++){// Array has length, String has length()

            while(strArray[i].indexOf(prefix) != 0 ){

                prefix = prefix.substring(0, prefix.length() - 1);
                if(prefix.isEmpty())
                    return "";

            }
        }


        return  prefix;
    }
    
    public static String longestCommonPrefixStream(String[] strArray) {

        if (strArray.length == 0) {
            return "";
        }

        String first = strArray[0];

        return java.util.stream.IntStream
                .range(0, first.length())
                .takeWhile(i ->
                        java.util.Arrays.stream(strArray)
                                .allMatch(s -> i < s.length() && s.charAt(i) == first.charAt(i))
                )
                .mapToObj(first::charAt)
                .map(String::valueOf)
                .collect(java.util.stream.Collectors.joining());
    }
    
    public static String longestCommonPrefixStream1(String[] strArray) {

        return java.util.Arrays.stream(strArray)
                .reduce((s1, s2) -> {
                    int i = 0;

                    while (i < s1.length()
                            && i < s2.length()
                            && s1.charAt(i) == s2.charAt(i)) {
                        i++;
                    }

                    return s1.substring(0, i);
                })
                .orElse("");
    }




}

