package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FindCommonPrefixFromString {
    public static void main(String[] args) {

        String[] strArray = {"Docker","Double","DoublyLinkedList"};

        List<String> list = Arrays.asList("Docker", "Double", "DoublyLinkedList");

        String[] arr = list.toArray(new String[0]);

        System.out.println(Arrays.toString(arr));

        /**
         * Why new String[0]?
         * Java automatically creates the correct-sized array internally.
         * This is the most widely used and interview-safe approach.
         */

        String[] arr2 = list.toArray(String[]::new);




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

    private static String findMostCommonPrefix3(String[] str){

        if(str.length == 0){
            return "";
        }
        String prefix = str[0];

        for(int i = 1; i < str.length; i++){

            while(!str[i].startsWith(prefix)){ // while(str[i].indexOf(prefix) != 0)
                prefix = prefix.substring(0,prefix.length() - 1);
                if(prefix.isEmpty()) return "";

            }
        }



        return  prefix;
    }


}
