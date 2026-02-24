package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Comparator;

public class FindLongestCommonPrefix {
    public static void main(String[] args) {
        String[] s = {"flower", "flow", "flight", "javaspringboothibernate"};
        String resuly = longestCommonPrefix(s);
        System.out.println(resuly);


        String longestString = Arrays.stream(s).max(Comparator.comparingInt(String::length)).get();
        System.out.println(longestString);
    }

    public static String longestCommonPrefix(String[] S) {
        if (S.length == 0) return "";
        String prefix = S[0];
        for (int i = 1; i < S.length ; i++) {// as S is String[]
            //while (S[i].indexOf(prefix) != 0) {
                while (!S[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1); // as prefix is String
                if (prefix.isEmpty()) return "";
            }

        }
        return prefix;
    }



}

