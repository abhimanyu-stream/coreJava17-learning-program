package com.java17.interview.prepartion;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@SpringBootApplication
public class FindGivenNumberIsPrimeOrNot {
    public static void main(String[] args) {
        SpringApplication.run(FindGivenNumberIsPrimeOrNot.class,args);

        int x = 31;
        int remainder;
        boolean prime = TRUE;
        for(int i = 2; i < x/2; i++){

            remainder = x%i;
            if(remainder == 0)
                prime = FALSE;
                break;


        }
        if(prime)
            System.out.println(prime);

    }
}
