package com.java17.interview.prepartion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ReverseStringWithoutUsingStringFunction {

    public static <Char> void main(String[] args) {
        SpringApplication.run(ReverseStringWithoutUsingStringFunction.class, args);


        String  inputString = "JavaLearningCenter";
        char[] charArray = inputString.toCharArray();
        
        for(int i = charArray.length - 1; i >= 0; i--){
            System.out.print(charArray[i]);
        }
        System.out.println();
        System.out.println("Using StringBuilder");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputString);// As String is immutable and StringBuilder is mutable
        stringBuilder = stringBuilder.reverse();     // used string builder to reverse
        System.out.println(stringBuilder);

        System.out.println();
        System.out.println("Using Covert String into String[]");
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();






    }
}
