package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Top5NamesByDepartment {

    public static void main(String[] args) {

        List<Employees> employees = Arrays.asList(
                new Employees("Alice",   "IT",        120_000),
                new Employees("Bob",     "IT",        95_000),
                new Employees("Charlie", "IT",        110_000),
                new Employees("David",   "IT",        130_000),
                new Employees("Eve",     "IT",        105_000),
                new Employees("Frank",   "IT",        90_000),

                new Employees("Grace",   "HR",        80_000),
                new Employees("Hannah",  "HR",        85_000),
                new Employees("Ian",     "HR",        75_000),
                new Employees("Jane",    "HR",        95_000),
                new Employees("Kevin",   "HR",        70_000),

                new Employees("Leo",     "Finance",   150_000),
                new Employees("Mona",    "Finance",   140_000),
                new Employees("Nina",    "Finance",   145_000),
                new Employees("Oscar",   "Finance",   135_000),
                new Employees("Paul",    "Finance",   160_000),
                new Employees("Quinn",   "Finance",   130_000)
        );

        Map<String, List<String>> top5NamesByDept =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employees::getDepartment,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> list.stream()
                                                //.sorted(Comparator.comparing(Employees::getSalary).reversed()) its also ok
                                                .sorted(Comparator.comparingDouble(Employees::getSalary).reversed())

                                                .limit(5)
                                                .map(Employees::getName)
                                                .collect(Collectors.toList())
                                )
                        ));

        // Optional: print result
        top5NamesByDept.forEach((dept, names) ->
                System.out.println(dept + " -> " + names)
        );
    }
}

class Employees {
    private final String name;
    private final String department;
    private final double salary;

    Employees(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
}
