package com.java17.interview.prepartion;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class OptionalClassUnderstandingWorking {

    public static void main(String[] args) {
        // Tree ways to create Optional Object
        // ofNullable, of, empty

        //Optional<String> name = Optional.ofNullable(getName());
        Optional<Integer> valueInteger = Optional.ofNullable(-1);
        System.out.println(valueInteger);//Optional[-1]
        valueInteger.ifPresent(System.out::println);//-1
        Optional<String> valueString = Optional.ofNullable("abc");
        System.out.println(valueString);//Optional[abc]
        valueString.ifPresent(System.out::println);//abc
        Optional<String> valuePassedNULL = Optional.ofNullable(null);
        System.out.println(valuePassedNULL);//Optional.empty
        valuePassedNULL.ifPresent(System.out::println);// nothing gets printed here as bucket Optional is empty



        Optional.empty();
        Optional<Object> o = Optional.of(null);

        System.out.println(o);// its raises NullPointerException
        if(o.isPresent()){
            // safe mode of working to avoid NullPointerException
        }

        Optional.ofNullable(null);

    }
    
}
