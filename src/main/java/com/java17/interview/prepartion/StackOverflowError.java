package com.java17.interview.prepartion;


import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StackOverflowError {

    public static void main(String[] args) {
        recursiveFunction();
        //Exception in thread "main" java.lang.StackOverflowError
        //The java.lang.StackOverflowError is a runtime error .It extends the VirtualMachineError class.
    }
    static int recursiveFunction()
    {   int x = 10;

        recursiveFunction();
        return ++x;
    }
}
