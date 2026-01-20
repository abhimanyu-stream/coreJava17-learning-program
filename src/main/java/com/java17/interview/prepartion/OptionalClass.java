package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalClass {

    public static void main(String[] args) {

        Optional<List<String>> empty = Optional.empty();
        //empty.get().add("hum"); // it raises Exception java.util.NoSuchElementException : No value present

        if (empty.isPresent()) {
            empty.get().add("hum");
        } else {
            System.out.println("Optional is empty!");
        }


        empty.orElseGet(ArrayList::new).add("hum");

        empty.ifPresent(list -> list.add("hum"));



        //Note:-- after creating empty you have re-assign with valued Otional Object, then you can use/ call get() over empty

        Optional<String> stringOptional = Optional.of("hum");
        //Optional<Object> optionalofnull = Optional.of(null);// it raises null pointer exception[box[null] Exception in thread "main" java.lang.NullPointerException[ it mean we can not pass null as argument in of method of Optional]

        //System.out.println("optionalofnull "+ optionalofnull);
        Optional<String> stringOptional1 = Optional.ofNullable("");
        stringOptional1.ifPresent(value -> System.out.print("Optional.ofNullable(\"\");" + value));
        Optional<Object> optionalnull = Optional.ofNullable(null);// it do not raises null pointer exception[it prints  empty box/space on console]
        optionalnull.ifPresent(System.out :: println);
        Optional<String> hum = Optional.ofNullable("hum");
        hum.ifPresent(System.out :: println);

    }
}
