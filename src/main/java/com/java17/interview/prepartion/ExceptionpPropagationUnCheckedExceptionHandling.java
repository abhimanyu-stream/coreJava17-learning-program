package com.java17.interview.prepartion;

public class ExceptionpPropagationUnCheckedExceptionHandling {


    public static void main(String[] args) {

        String result = method1();

    }

    public static String method1(){

        method2();
        return "ok";
    }

    private static void method2() {
        method3();

    }

    private static void method3() {
        throw new NullPointerException();
    }
}
