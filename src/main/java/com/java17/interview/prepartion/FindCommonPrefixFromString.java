package com.java17.interview.prepartion;

public class FindCommonPrefixFromString {
    public static void main(String[] args) {

        String[] strArray = {"Docker","Double","DoublyLinkedList"};

        String output = findMostCommonPrefix(strArray);
        System.out.println(output);
    }

    private static String findMostCommonPrefix(String[] strArray) {

        if(strArray.length == 0){
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
}
