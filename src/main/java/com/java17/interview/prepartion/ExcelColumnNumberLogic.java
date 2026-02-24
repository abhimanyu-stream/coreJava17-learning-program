package com.java17.interview.prepartion;

public class ExcelColumnNumberLogic {
    public static void main(String[] args) {
        /**
         * Excel Column Name to Number
         * Question
         *
         * Convert column names like H, AB, ALH to numbers.
         *
         *
         * H   → 8
         * AB  → 28
         * ALH → 996
         */



    }
    public static int columnNumber(String s) {
        int result = 0;
        for (char c : s.toUpperCase().toCharArray()) {
            result = result * 26 + (c - 'A' + 1);
        }
        return result;
    }
    /**Generic Column Position Function
    Question

    Write a method that returns Excel column position for any string.

    Solution*/
    public static int userInput(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            result = result * 26 + (s.charAt(i) - 'A' + 1);
        }
        return result;
    }

}
