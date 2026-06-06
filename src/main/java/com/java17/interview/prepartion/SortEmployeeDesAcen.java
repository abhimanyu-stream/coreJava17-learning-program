package com.java17.interview.prepartion;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class SortEmployeeDesAcen {

    public static void main(String[] args) {

        List<Employez> employees = Arrays.asList(
                new Employez(1, "Alice", 90000),
                new Employez(2, "Bob", 90000),
                new Employez(3, "Charlie", 120000)
        );

        List<Employez> sortedEmployees =
                employees.stream()
                        .sorted(
                                Comparator.comparing(Employez::getSalary).reversed()
                                        .thenComparing(Employez::getName)
                        )
                        .toList();

        sortedEmployees.forEach(e ->
                System.out.println(e.getName() + " " + e.getSalary())
        );
    }

}
class Employez{

    private int id;
    private String name;
    private double salary;

    public Employez(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employez employez = (Employez) o;
        return id == employez.id && Double.compare(employez.salary, salary) == 0 && Objects.equals(name, employez.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, salary);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employez{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}