package com.java17.interview.prepartion;

import java.util.Scanner;
import java.util.Stack;
import java.util.function.Predicate;

public class Practice {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();


        findAllPrimes(input);

        //Predicate<T> uses and(), or(), and negate().

        Predicate<Integer> isEven = (n) -> {
            return n % 2 == 0;
        };
        Predicate<Integer> isPositive = (n) -> {
            return n > 0;
        };
        Predicate<Integer> evenAndPositive = isEven.and(isPositive);

        System.out.println(evenAndPositive.test(4));
        System.out.println(evenAndPositive.test(-2));

        String s1 = "()";
        System.out.println(isValidParentheses(s1));
        String s2 = "(}";
        System.out.println(isValidParentheses(s2));

    }

    private static boolean isValidParentheses(String str) {


        Stack<Character> stack = new Stack<>();
        for(char c:str.toCharArray()){

            if(c == '(' || c == '{' || c == '['){
                stack.push(c);
            }else if(c == ')' ||  c == '}' || c == ']'){
               if( stack.isEmpty()) {
                   return false;
               }

               char popped = stack.pop();
               if(c == ')' && popped != '('){
                   return false;
               }
                if(c == '}' && popped != '{'){
                    return false;
                }
                if(c == ']' && popped != '['){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static void findAllPrimes(int input) {

        for(int k = 2; k <= input ; k++){
            int count = 0;
            for(int i = 1; i <= k; i++){

                if(k % i == 0){
                    count++;
                }
            }
            if(count == 2)
                System.out.println(k +"is prime");
        }
    }

}
