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


}

/***
 *
 * [14:30] Rama Krishna M
 * Find Longest common Prefix in an array
 * [“amazon”, “amazed”, “amaze”, “amazing”, “amazes”]
 * [14:30] Rama Krishna M
 * Given a String, find the first non-repeated character in it using Stream functions?  String s1= "Welcome to java world ";  output-c
 */
