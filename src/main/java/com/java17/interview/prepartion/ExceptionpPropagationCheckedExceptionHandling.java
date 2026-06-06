package com.java17.interview.prepartion;

import java.io.IOException;

public class ExceptionpPropagationCheckedExceptionHandling {


    public static void main(String[] args) throws IOException {

        String result = method1();

    }

    public static String method1() throws IOException {

        method2();
        return "ok";
    }

    private static void method2() throws IOException {
        method3();

    }

    private static void method3() throws IOException {
        throw new IOException();
    }
}
