package com.java17.interview.prepartion;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@SpringBootApplication
public class FindGivenNumberIsPrimeOrNot {
    public static void main(String[] args) {
        SpringApplication.run(FindGivenNumberIsPrimeOrNot.class, args);


        int inputNumber = 15;
        int counts = 0;
        // loop through 1 to input number, do % input number  from 1 to that number, if count == 2 then input number is prime.
        for (int ii = 1; ii <= inputNumber; ii++) {
            if (inputNumber % ii == 0) {
                counts++;
            }
        }
        if (counts == 2) {
            System.out.println(inputNumber + " is prime");
        }else {
            System.out.println(inputNumber + " not prime");
        }
    }

}