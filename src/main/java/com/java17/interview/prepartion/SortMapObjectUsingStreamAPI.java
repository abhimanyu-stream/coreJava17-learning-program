package com.java17.interview.prepartion;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SortMapObjectUsingStreamAPI {

    public static void main(String[] args) {

        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("eight", 8);
        hashMap.put("one", 1);
        hashMap.put("ten", 10);
        hashMap.put("five", 5);

        hashMap = hashMap.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(hashMap);
        hashMap = hashMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(hashMap);
        hashMap = hashMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(hashMap);
        hashMap = hashMap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(hashMap);

        System.out.println("*******TreeMap with Custom Sorting using Comparator*****");
        Map<EmployeeMS, Integer> employeeMSIntegerMap = new TreeMap<>((o1, o2)-> (int) (o2.getSalary() - o1.getSalary()));
        employeeMSIntegerMap.put(new EmployeeMS(1, 400000, "Sujay", "Civil"), 40);
        employeeMSIntegerMap.put(new EmployeeMS(2, 7000000, "Patrik", "IT"), 70);
        employeeMSIntegerMap.put(new EmployeeMS(3, 9000000, "Jolly", "Manager"), 90);
        employeeMSIntegerMap.put(new EmployeeMS(4, 200000, "Aam", "Government"), 20);

        System.out.println(employeeMSIntegerMap);
        System.out.println("*******TreeMap with Stream API*****");
        employeeMSIntegerMap.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.comparing(EmployeeMS::getDepartment))).forEach(System.out::println);
        System.out.println("*******TreeMap with Stream API reversed order*****");
        employeeMSIntegerMap.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.comparing(EmployeeMS::getDepartment).reversed())).forEach(System.out::println);



    }
}

class EmployeeMS{

    private int id;
    private double salary;
    private String name;
    private String department;

    public EmployeeMS(){}

    public EmployeeMS(int id, double salary, String name, String department) {
        this.id = id;
        this.salary = salary;
        this.name = name;
        this.department = department;
    }

    @Override
    public String toString() {
        return "EmployeeMS{" +
                "id=" + id +
                ", salary=" + salary +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
