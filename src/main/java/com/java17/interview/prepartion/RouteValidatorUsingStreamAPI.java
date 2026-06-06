package com.java17.interview.prepartion;



import java.util.List;
import java.util.function.Predicate;

public class RouteValidatorUsingStreamAPI {


    public static void main(String[] args) {


        List<String> openApiEndpoints = List.of(
                "/auth/register",
                "/auth/token",
                "/eureka"
        );
        boolean b = openApiEndpoints.stream().noneMatch((u) -> u.equals("/eureka"));
        System.out.println(b);

    }
}


