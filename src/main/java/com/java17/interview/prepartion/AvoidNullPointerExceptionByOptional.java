package com.java17.interview.prepartion;

import java.util.Optional;

public class AvoidNullPointerExceptionByOptional {
    public static void main(String[] args) {
        

        Optional<String> a = Optional.empty();
        Optional<String> b = Optional.ofNullable(null);

        System.out.println(a.equals(b));   // true


        Optional<String> opt = Optional.empty();

        System.out.println(opt.isPresent());

        Optional<String> opt2 = Optional.empty();

        System.out.println(opt2.orElse("Guest"));


        Optional<String> opt3 = Optional.empty();

        opt3.orElseThrow(() -> new RuntimeException("Value missing"));

        Optional<String> opt4 = Optional.empty();

        opt4.ifPresent(System.out::println);

        
    }
}
