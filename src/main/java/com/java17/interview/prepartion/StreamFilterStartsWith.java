package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamFilterStartsWith {


    public static void main(String[] args) {



        //Example using Product Array and filtering it for products starting with character "S".


        List<String> products = Arrays.asList("Laptop", "Smartphone", "Tablet", "Smartwatch", "Camera");





        


        products = products.stream().filter(f-> f.startsWith("S")).collect(Collectors.toList());
        System.out.println(products);
    }
}
