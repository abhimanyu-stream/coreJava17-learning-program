package com.java17.interview.prepartion;

public class isPalindromeCheck {

    public static boolean isPalindromeTwoPointerApproch(String str) {
        str = str.toLowerCase(); // Convert to lowercase for case-insensitive comparison
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    public static boolean isPalindromeReversingByStringBuilderApproch(String str) {
        String reversed = new StringBuilder(str).reverse().toString();
        return str.equals(reversed);
    }

    public static void main(String[] args) {
        //String input = "abccbaj"; false
        String input = "abccba";// true
        boolean result = isPalindromeTwoPointerApproch(input);

        System.out.println("Is '" + input + "' a palindrome? " + result);

        String input2 = "racecar";// true
        boolean result2 = isPalindromeReversingByStringBuilderApproch(input2);
        System.out.println("Is '" + input2 + "' a palindrome? " + result2);


        System.out.println(4 % 2 == 0 ? Boolean.TRUE : Boolean.FALSE);
    }

}
