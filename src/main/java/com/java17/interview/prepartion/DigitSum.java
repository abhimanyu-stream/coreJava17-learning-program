package com.java17.interview.prepartion;

import java.util.Scanner;

public class DigitSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a positive integer: ");
        int number = scanner.nextInt();
        scanner.close();

        //Enter a positive integer: 7982
        int digit = 7982;


        int sum = 0;

        while (digit != 0) {
            sum += digit % 10; // take last digit
            digit = digit / 10; // remove last digit
        }

        System.out.println(sum); // 26
        /***
         * Walk it once to see the gears turn:
         *
         * 7982 → 2 → sum = 2
         *
         * 798 → 8 → sum = 10
         *
         * 79 → 9 → sum = 19
         *
         * 7 → 7 → sum = 26
         *
         * 0 → stop
         *
         * Why digit != 0 matters:
         */

    }
}