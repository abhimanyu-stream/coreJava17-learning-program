package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindNthSalaryOfEmployee {
    public static void main(String[] args) {

        Woker w1 = new Woker("Oracle", 20.0);
        Woker w2 = new Woker("Red", 8000.00);
        Woker w3 = new Woker("Yahoo", 8000.00);
        Woker w4 = new Woker("Rediif", 8000.00);
        Woker w5 = new Woker("Gamil", 89400.00);
        Woker w6 = new Woker("AOL", 700.00);
        List<Woker> listOfWorker = new ArrayList<>();
        listOfWorker.add(w1);
        listOfWorker.add(w2);
        listOfWorker.add(w3);
        listOfWorker.add(w4);
        listOfWorker.add(w5);
        listOfWorker.add(w6);
        Map<Double, List<Woker>> collect = listOfWorker.stream().sorted(Comparator.comparingDouble(Woker :: getSalary).reversed())
                .collect(Collectors.groupingBy(m->m.getSalary(), Collectors.mapping(m -> m, Collectors.toList())));
        System.out.println(collect);

        Woker woker = listOfWorker.stream().sorted(Comparator.comparingDouble(Woker :: getSalary).reversed()).limit(2).skip(1).findFirst().get();

        System.out.println(woker);

    }
}

class Woker{



    private String name;
    private Double salary;

    public Woker(String name, Double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Woker{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
