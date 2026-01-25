package com.java17.interview.prepartion;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Top2HighestPaidEmployeesPerDepartmentTIgnoreLast6M {

    public static void main(String[] args) {




        List<Empl> employees = new ArrayList<>();

        employees.add(new Empl(101, "Alice", "IT", 120000,
                LocalDate.of(2020, 3, 10)));

        employees.add(new Empl(102, "Bob", "IT", 110000,
                LocalDate.of(2021, 6, 15)));

        employees.add(new Empl(103, "Charlie", "HR", 90000,
                LocalDate.of(2019, 1, 20)));

        employees.add(new Empl(104, "Diana", "HR", 95000,
                LocalDate.of(2023, 8, 5)));   // recent joiner

        employees.add(new Empl(105, "Eve", "Finance", 130000,
                LocalDate.of(2018, 11, 25)));

        employees.add(new Empl(106, "Frank", "Finance", 125000,
                LocalDate.of(2022, 2, 14)));

        employees.add(new Empl(107, "Grace", "IT", 115000,
                LocalDate.of(2017, 7, 30)));


        Map<String, List<String>> result =
                employees.stream()
                        // 1️⃣ ignore employees joined in last 6 months
                        .filter(e -> e.getJoiningDate()
                                .isBefore(LocalDate.now().minusMonths(6)))

                        // 2️⃣ group by department
                        .collect(Collectors.groupingBy(
                                Empl::getDept,

                                // 3️⃣ within each dept → sort desc salary → take top 2 → map to names
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> list.stream()
                                                .sorted(Comparator.comparing(Empl::getSalary).reversed())
                                                .limit(2)
                                                .map(Empl::getName)
                                                .toList()
                                )
                        ));


        System.out.println(result);
        Map<String, Optional<Empl>> secondHighestSalaryPerDept =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Empl::getDept,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> list.stream()
                                                .sorted(Comparator.comparing(Empl::getSalary).reversed())
                                                .skip(1)
                                                .findFirst()
                                )
                        ));


        System.out.println(secondHighestSalaryPerDept);
        Map<String, Optional<Empl>> secondHighestSalaryPerDept2 =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Empl::getDept,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> list.stream()
                                                .collect(Collectors.collectingAndThen(
                                                        Collectors.toList(),
                                                        l -> l.size() < 2
                                                                ? Optional.empty()
                                                                : l.stream()
                                                                .sorted(Comparator.comparing(Empl::getSalary).reversed())
                                                                .skip(1)
                                                                .findFirst()
                                                ))
                                )
                        ));

        secondHighestSalaryPerDept.forEach((dept, empOpt) -> {
            System.out.println(
                    dept + " -> " +
                            empOpt.map(e -> e.getName() + " : " + e.getSalary())
                                    .orElse("No second highest salary")
            );
        });

        secondHighestSalaryPerDept.entrySet().stream()
                .map(e -> e.getKey() + " -> " +
                        e.getValue()
                                .map(emp -> emp.getName() + " : " + emp.getSalary())
                                .orElse("N/A"))
                .forEach(System.out::println);






    }
}
class Empl {
    int id;
    String name;
    String dept;
    double salary;
    LocalDate joiningDate;
    public Empl(int id, String name, String dept, double salary, LocalDate joiningDate) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.salary = salary;
        this.joiningDate = joiningDate;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDept() { return dept; }
    public double getSalary() { return salary; }
    public LocalDate getJoiningDate() { return joiningDate; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empl empl = (Empl) o;
        return id == empl.id && Double.compare(empl.salary, salary) == 0 && Objects.equals(name, empl.name) && Objects.equals(dept, empl.dept) && Objects.equals(joiningDate, empl.joiningDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dept, salary, joiningDate);
    }

    @Override
    public String toString() {
        return "Empl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                ", salary=" + salary +
                ", joiningDate=" + joiningDate +
                '}';
    }
}
