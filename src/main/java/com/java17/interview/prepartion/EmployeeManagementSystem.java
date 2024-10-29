package com.java17.interview.prepartion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class EmployeeManagementSystem {

    public static void main(String[] args) {


        SpringApplication.run(EmployeeManagementSystem.class,args);

        Employeee emplyoee1 = new Employeee("Jack", "40000");

        Employeee emplyoee2 = new Employeee("Jhon", "10000");

        Employeee emplyoee3 = new Employeee("Kelly", "30000");


        List<Employeee> listOfEmployee = new ArrayList<>();

        listOfEmployee.add(emplyoee1);
        listOfEmployee.add(emplyoee2);
        listOfEmployee.add(emplyoee3);


       // listOfEmployee.stream().sorted(Comparator.comparing(Employeee::getSalary);
        Employeee emplyoeeo = listOfEmployee.stream().max(Comparator.comparing(Employeee::getSalary)).get();
        System.out.println(emplyoeeo);


        Employeee employeee = listOfEmployee.stream().sorted((o1, o2) -> Integer.parseInt(o2.getSalary()) - Integer.parseInt(o1.getSalary())).findFirst().get();
        System.out.println(employeee);


    }


}
class Employeee {

    private String name;
    private String salary;


    public Employeee() {
    }

    public Employeee(String name, String salary) {

        this.name = name;
        this.salary = salary;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employeee employeee = (Employeee) o;

        if (!name.equals(employeee.name)) return false;
        return salary.equals(employeee.salary);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + salary.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Employeee{" +
                "name='" + name + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}