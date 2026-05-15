package com.java17.interview.prepartion;

public class SwapTwoIntegerWithoutThirdVariable {
    public static void main(String[] args) {
        int a = 5, b = 3;


        //1. Using Addition and Subtraction
        a = a + b; // 8
        b = a - b; // 5
        a = a - b; // 3


        //2. Using Multiplication and Division
        a = a * b;
        b = a / b;
        a = a / b;
        //Avoid this if either value can be 0.


        //3. Using XOR Bitwise Operator (Most Popular in Interviews)


        a = a ^ b;
        b = a ^ b;
        a = a ^ b;


        //4.
        int temp;

        temp = a;
        a = b;
        b = temp;




    }
}
