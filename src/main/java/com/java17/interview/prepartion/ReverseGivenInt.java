package com.java17.interview.prepartion;

public class ReverseGivenInt {

    public static void main(String[] args) {
        int number = 7843;
        int reversedNumber = reverseDigits(number);
        int reversedNumber2 = reverseDigits2(number);
        System.out.println("reversedNumber "+reversedNumber);
        System.out.println("reversedNumber2 "+reversedNumber2);
        //Enter a positive integer: 7982
        //int number = 7982;
        int digit = number;


        int sum = 0;

        while (digit != 0) {
            sum += digit % 10; // take last digit
            digit = digit / 10; // remove last digit
        }

        System.out.println(sum); // 26

         sum = 1;//as Factorial of 0! is 1, 1! is 1 and no factorial for -ve number
        for(int i = 1; i <= 5; i++){ //in FibonacciSeries for(int i =0; i < count; i++) {/
            sum =sum * i;// 1 * 1 + 1 * 2 + 3 * 3 + 12 * 4 + () * 5 = 120                   //[ do not sum +=sum * i]
        }
        System.out.println("Factorial of "+5+ " is "+sum);
    }

    private static int reverseDigits2(int number) {

        int reversedNumber = 0;
        while(number != 0){

            int digit = number % 10;
            reversedNumber = reversedNumber * 10 + digit;
            number /= 10;
        }
        return  reversedNumber;
    }

    public static int reverseDigits(int number) {
        int reversedNumber = 0;

        while (number != 0) {
            int digit = number % 10;
            reversedNumber =  reversedNumber * 10 + digit;
            number /= 10;
        }

        return reversedNumber;
    }
}
/**
 * Code Execution Breakdown
 * Step 1: Initialization
 * number is initialized to 7843.
 * Method reverseDigits2 is called with number.
 * Step 2: Execution of reverseDigits2
 * Initial State:
 *
 * number = 7843
 * reversedNumber = 0
 * Iteration 1:
 *
 * digit = number % 10 = 7843 % 10 = 3
 * reversedNumber = reversedNumber * 10 + digit = 0 * 10 + 3 = 3
 * number = number / 10 = 7843 / 10 = 784
 * Iteration 2:
 *
 * digit = number % 10 = 784 % 10 = 4
 * reversedNumber = reversedNumber * 10 + digit = 3 * 10 + 4 = 34
 * number = number / 10 = 784 / 10 = 78
 * Iteration 3:
 *
 * digit = number % 10 = 78 % 10 = 8
 * reversedNumber = reversedNumber * 10 + digit = 34 * 10 + 8 = 348
 * number = number / 10 = 78 / 10 = 7
 * Iteration 4:
 *
 * digit = number % 10 = 7 % 10 = 7
 * reversedNumber = reversedNumber * 10 + digit = 348 * 10 + 7 = 3487
 * number = number / 10 = 7 / 10 = 0
 * Final State:
 *
 * reversedNumber = 3487
 * number = 0
 * The method reverseDigits2 returns 3487.
 *
 * Step 3: Output Results
 * The program prints:
 *

 * reversedNumber 7843
 * reversedNumber2 3487
 * Since the method reverseDigits isn't provided, it defaults to the number variable itself.
 *
 * Visualization Diagram
 * Here’s a simple step-by-step visualization:
 *
 * Iteration	 Number	Digit   Extracted	             Reversed Number
 * Start	     7843	            -	                  0
 * 1	         7843	            3	                  3
 * 2	         784	            4	                  34
 * 3	          78	            8	                  348
 * 4	          7	                7	                  3487
 * End	         0	               -	                  3487
 */