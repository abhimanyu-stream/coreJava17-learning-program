package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FindNthSalaryOfEmployee {
    public static void main(String[] args) {

        Woker w1 = new Woker("Oracle", 50000.0);
        Woker w2 = new Woker("Red", 890000.00);
        List<Woker> listOfWorker = new ArrayList<>();
        listOfWorker.add(w1);
        listOfWorker.add(w2);
        Woker wresult = listOfWorker.stream().sorted(Comparator.comparing(Woker::getSalary).reversed()).skip(1).findFirst().get();
        System.out.println(wresult);
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
