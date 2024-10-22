package com.java17.interview.prepartion;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MethodOverLoadingCallPreferenceSequence {


    public static void main(String[] args) {
        SpringApplication.run(MethodOverLoadingCallPreferenceSequence.class,args);
        callInternalMethod(null);//"Inside String"
        callInternalMethod(0);//"Inside Object"

    }
    public static void callInternalMethod(String A) {

        System.out.println("Inside String");// this one selected, before going to Object class , it goes other class

    }

    public static void callInternalMethod(Object A) {

        System.out.println("Inside Object");

    }

}
