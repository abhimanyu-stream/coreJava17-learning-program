package com.java17.interview.prepartion;

public class WhatIsOutPut {

    public static void main(String[] args) {

        System.out.println(10 + 20 +"Interview");
        System.out.println("Interview" + 10 + 20);
        // finally block will be skipped if System.exit(0); is executed in try{}
        try{
            System.exit(0);

        }catch (Exception e){

        }finally {
            System.out.println("finally");
        }
    }

}
