package com.java17.interview.prepartion;

public class ReverseGivenInt {

    public static void main(String[] args) {
        int number = 7843;
        int reversedNumber = reverseDigits(number);
        int reversedNumber2 = reverseDigits2(number);
        System.out.println("reversedNumber "+reversedNumber);
        System.out.println("reversedNumber2 "+reversedNumber2);

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
