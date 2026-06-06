package com.java17.interview.prepartion;

import java.util.Objects;
import java.util.TreeSet;

public class TreeSetWithCustomObjects {
    public static void main(String[] args) {

        TreeSet<EmployeeT> employees = new TreeSet<>();

        employees.add(new EmployeeT(3, "Alice"));
        employees.add(new EmployeeT(1, "Bob"));
        employees.add(new EmployeeT(2, "Charlie"));
        employees.add(new EmployeeT(1, "Duplicate Bob")); // ignored (same id)

        System.out.println("Sorted Employees by ID:");
        for (EmployeeT e : employees) {
            System.out.println(e);
        }
    }
}
class EmployeeT implements Comparable<EmployeeT> {
    int id;
    String name;

    EmployeeT(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // sort by id
    @Override
    public int compareTo(EmployeeT other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeT)) return false;
        EmployeeT e = (EmployeeT) o;
        return id == e.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}