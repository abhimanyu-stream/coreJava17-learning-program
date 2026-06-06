package com.java17.interview.prepartion;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FindPatternAndPrint {

    public static void main(String[] args) {
        SpringApplication.run(FindPatternAndPrint.class,args);

        //8,6,9, 23,87,

       // 8* 1 - 2 = 6
       // 6*2 - 3 = 9

        int n  = 8;

        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n ; j++) {

                //System.out.println();
                n = (n*j) -(j+1);
                System.out.println(n);

            }

        }

    }

}
