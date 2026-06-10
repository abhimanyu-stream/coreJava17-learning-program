package com.java17.interview.prepartion;

import java.util.Arrays;

public class AnagramCheck {

     public static boolean isAnagram(String s1, String s2) {

        s1 = s1.replaceAll("\\s", "").toLowerCase();
        s2 = s2.replaceAll("\\s", "").toLowerCase();

        if (s1.length() != s2.length()) {
            return false;
        }

        char[] arr1 = s1.toLowerCase().toCharArray();
        char[] arr2 = s2.toLowerCase().toCharArray();

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        String s3 = new String(arr1);
         String s4 = new String(arr2);
         System.out.print(s3.equals(s4));


        return Arrays.equals(arr1, arr2);
    }

    public static void main(String[] args) {

        String s1 = "listen";
        String s2 = "silent";

        System.out.println(isAnagram(s1, s2));
    }
    
}
