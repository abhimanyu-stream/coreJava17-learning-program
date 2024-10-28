package com.java17.interview.prepartion;

import java.util.Scanner;

public class FindPrimeNumbersBetween1ToN {

    public static void main(String[] args) {

        int i, count;// there should be only two factors of a given number then it is a prime number
        System.out.print("Enter n value: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println("Prime numbers between 1 to " + n + " are:");
        for (int j = 2; j <= n; j++) {
            count = 0;
            for (i = 1; i <= j; i++) {
                if (j % i == 0) {
                    count++;
                }
            }
            if (count == 2)
                System.out.print(j + " "+"is prime ");
        }

        for(int j = 2; j <= n; j++){
            int counts = 0;
            for(int ii = 1; ii <= j ; ii++){
                if(j % ii == 0){
                    counts++;
                }
            }
            if(counts == 2){
                System.out.println(j +"is prime");
            }
        }

      int jj= 17;
            int counts = 0;
            // loop through 1 to input number, do % input number  from 1 to that number , if count == 2 then input number is prime.
            for(int ii = 1; ii <= jj ; ii++){
                if(jj % ii == 0){
                    counts++;
                }
            }
            if(counts == 2){
                System.out.println(jj +"is prime");
            }
        }

}

