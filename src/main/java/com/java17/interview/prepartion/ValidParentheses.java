package com.java17.interview.prepartion;

import java.util.Stack;

public class ValidParentheses {


    public static void main(String[] args) {
        String str = "()";
        boolean isValid = isValid(str);
        System.out.println(isValid); // true

        str = "()[]{}";
        isValid = isValid(str);
        System.out.println(isValid); // true

        str = "(]";
        isValid = isValid(str);
        System.out.println(isValid); // false

        str = "([)]";
        isValid = isValid(str);
        System.out.println(isValid); // false
    }
    public static boolean isValid(String str) {
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (c == ')' && top != '(') {
                    return false;
                }
                if (c == ']' && top != '[') {
                    return false;
                }
                if (c == '}' && top != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
        /**
         * Final Check:
         * After processing all characters, if the stack is empty, it means all opening characters had matching closing characters, and the string is valid. The method returns true.
         * If the stack is not empty, there are unmatched opening characters, and the string is invalid. The method returns false.
         * */
    }
}




