package com.java17.interview.prepartion;

import java.util.Arrays;

public class CheckWhetherTwoWordsAreAnagrams {

    public static boolean isAnagramSortingApproach(String s, String t) {
        //Approach 1
        //sorting
        if (s.length() != t.length()) return false;

        char[] a = s.toCharArray();
        char[] b = t.toCharArray();

        Arrays.sort(a);
        Arrays.sort(b);

        return Arrays.equals(a, b);
    }

    public static boolean isAnagramFrequencyCount(String s, String t) {
        //Approach 2: Frequency Count (Optimized 🚀)
        //
        //👉 Better because no sorting → O(n)
        if (s.length() != t.length()) return false;

        int[] count = new int[26]; // assuming lowercase letters

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        for (int c : count) {
            if (c != 0) return false;
        }

        return true;
    }


    public static void main(String[] args) {
        String s = "carrace";//"eat";
        String t = "racecar";//"tea";

        System.out.println(isAnagramSortingApproach(s, t)); // true
        System.out.println(isAnagramFrequencyCount(s, t)); // true
    }
}
