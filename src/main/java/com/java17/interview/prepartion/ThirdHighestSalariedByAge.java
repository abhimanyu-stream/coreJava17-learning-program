package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ThirdHighestSalariedByAge {

    public static void main(String[] args) {

        List<EmployeeHcl> emplist = new ArrayList<>();

        emplist.add(new EmployeeHcl(101, "vinod", 25, 10000));
        emplist.add(new EmployeeHcl(107, "kumar", 35, 20000));
        emplist.add(new EmployeeHcl(107, "kumarii", 35, 20000));
        emplist.add(new EmployeeHcl(103, "ravi", 15, 5000));
        emplist.add(new EmployeeHcl(100, "charan", 35, 10000));
        emplist.add(new EmployeeHcl(100, "rama", 45, 10000));
        emplist.add(new EmployeeHcl(110, "navi", 55, 30000));
        emplist.add(new EmployeeHcl(110, "abc", 65, 30000));
        emplist.add(new EmployeeHcl(110, "dummy", 75, 30000));



        //all employees who have the second highest salary (not just one)


        // Step 1: Get second highest salary
        Optional<Integer> secondHighestSalary = emplist.stream()
                .map(EmployeeHcl::getSalary)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst();

        // Step 2: Filter employees with that salary
        if (secondHighestSalary.isPresent()) {
            double salary = secondHighestSalary.get();

            List<EmployeeHcl> result = emplist.stream()
                    .filter(e -> e.getSalary() == salary)
                    .collect(Collectors.toList());

           result.stream().forEach(System.out::println);
           result.stream().forEach(e->System.out.println(e.name));

        }

    }
}
class EmployeeHcl {
    int id;
    String name;
    int age;
    int salary;

    public EmployeeHcl(int id, String name, int age, int salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public int getSalary() { return salary; }
    public int getAge() { return age; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "EmployeeHcl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}