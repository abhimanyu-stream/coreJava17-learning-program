package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Comparator;

public class FindCommonPrefixFromString {
    public static void main(String[] args) {

        String[] strArray = {"Docker","Double","DoublyLinkedList"};

        Arrays.stream(strArray).sorted(Comparator.comparingInt(String::length)).toList();
        Arrays.stream(strArray).max(Comparator.comparingInt(String::length)).get();
       

        String output = findMostCommonPrefix(strArray);
        System.out.println(output);
    }

    private static String findMostCommonPrefix(String[] strArray) {

        if(strArray.length == 0){// empty check for Array
            return "";
        }

        String prefix = strArray[0];
        for(int i = 1; i < strArray.length; i++){
            while (strArray[i].indexOf(prefix) != 0){
                prefix = prefix.substring(0, prefix.length() - 1);
                if(prefix.isEmpty())
                    return "";
            }
        }
        return prefix;
    }

    private static String findMostCommonPrefix2(String[] strArray) {
        if (strArray.length == 0) return "";

        String prefix = strArray[0];
        for (int i = 1; i < strArray.length; i++) {
            while (!strArray[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }


}
