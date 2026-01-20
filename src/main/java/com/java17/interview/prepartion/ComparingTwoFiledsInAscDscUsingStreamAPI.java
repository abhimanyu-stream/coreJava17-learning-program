package com.java17.interview.prepartion;

import java.util.*;
import java.util.stream.Collectors;

//@SpringBootApplication
public class ComparingTwoFiledsInAscDscUsingStreamAPI {

    public static void main(String[] args) {
        //SpringApplication.run(ComparingTwoFiledsInAscDscUsingStreamAPI.class, args);


        List<GEmployee> listOfGEmployee = new ArrayList<>();//Arrays.asList("", "");

        GEmployee g1 = new GEmployee("Abhimanyu", 60000.00, Precedence.HIGH);
        GEmployee g2 = new GEmployee("Kelye", 9000.00,Precedence.MIDIUM);
        GEmployee g3 = new GEmployee("Kelye3", 9000.00,Precedence.MIDIUM);
        GEmployee g4 = new GEmployee("Kelye4", 9000.00,Precedence.MIDIUM);

        listOfGEmployee.add(g1);
        listOfGEmployee.add(g2);
        listOfGEmployee.add(g3);
        listOfGEmployee.add(g4);

        listOfGEmployee = listOfGEmployee.stream().sorted(Comparator.comparing(GEmployee::getName).thenComparingDouble(GEmployee::getSalary).reversed()).collect(Collectors.toList());
        System.out.println(listOfGEmployee);

        //create a map

       Map<Precedence, List<GEmployee>> mapBasedOnPrecedenceAndListOfGEmployee = listOfGEmployee.stream().collect(Collectors.groupingBy(GEmployee::getPrecedence, Collectors.toList()));
       System.out.println(mapBasedOnPrecedenceAndListOfGEmployee);

        Map<Double, List<GEmployee>> salaryWiseList = listOfGEmployee.stream().collect(Collectors.groupingBy(GEmployee::getSalary, Collectors.toList()));
        System.out.println("salaryWiseList+"+salaryWiseList);

        Map<Precedence, List<String>> collect = listOfGEmployee.stream().collect(Collectors.groupingBy(GEmployee::getPrecedence, Collectors.mapping(GEmployee::getName, Collectors.toList())));
        System.out.println("collect" +collect);
    }
}

class GEmployee{


    private final String name;
    private final Double salary;

    private final Precedence precedence;

    public GEmployee(String name, Double salary, Precedence precedence) {
        this.name = name;
        this.salary = salary;
        this.precedence = precedence;
    }

    public String getName() {
        return name;
    }

    public Double getSalary() {
        return salary;
    }

    public Precedence getPrecedence() {
        return precedence;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GEmployee.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("salary=" + salary)
                .add("precedence=" + precedence)
                .toString();
    }
}
enum Precedence{

    HIGH, MIDIUM, LOW,
}
