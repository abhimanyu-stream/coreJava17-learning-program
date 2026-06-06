package com.java17.interview.prepartion;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Java8AdvancedStreams {

    static class Employee {
        int id;
        String name;
        double salary;
        String dept;

        public Employee(int id, String name, double salary, String dept) {
            this.id = id;
            this.name = name;
            this.salary = salary;
            this.dept = dept;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public double getSalary() { return salary; }
        public String getDept() { return dept; }

        @Override
        public String toString() {
            return name + "(" + salary + "," + dept + ")";
        }
    }

    public static void main(String[] args) {

        List<Integer> numbers = List.of(10, 5, 30, 20, 2);



        int max = numbers.stream()
                .max(Comparator.naturalOrder())
                .orElseThrow();
        int min = numbers.stream()
                .min(Comparator.naturalOrder())
                .orElseThrow();

        int sum = numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("sum "+sum);
        int sums = numbers.stream().reduce(0, Integer::sum).intValue();
        System.out.println("sums "+sums);
        List<Integer> ascSorted = numbers.stream()
                .sorted(Comparator.naturalOrder())
                .toList();
        //Equivalent, shorter:

        List<Integer> ascSortedd = numbers.stream()
                .sorted()
                .toList();
        List<Integer> descSorted = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .toList();


        numbers.stream().max(Integer::compareTo);
        numbers.stream().min(Integer::compareTo);
        numbers.stream().mapToInt(Integer::intValue).sum();
        numbers.stream().sorted(Comparator.reverseOrder());


        System.out.println("=== 1. Parallel Stream Sum of 1-1000 ===");
        int sum1 = IntStream.rangeClosed(1, 1000)
                .parallel()
                .sum();
        System.out.println("Sum1 = " + sum1);

        System.out.println("\n=== 2. Reduce to sum salaries ===");
        List<Employee> employees = Arrays.asList(
                new Employee(1,"Alice",5000,"IT"),
                new Employee(2,"Bob",7000,"HR"),
                new Employee(3,"Charlie",8000,"IT"),
                new Employee(4,"David",6000,"Finance")
        );

        double totalSalary = employees.stream()
                .map(Employee::getSalary)
                .reduce(0.0, Double::sum);
        System.out.println("Total Salary = " + totalSalary);

        System.out.println("\n=== 3. Use peek for debugging ===");
        List<String> namesUpper = employees.stream()
                .peek(e -> System.out.println("Before map: " + e.getName()))
                .map(Employee::getName)
                .map(String::toUpperCase)
                .peek(n -> System.out.println("After map: " + n))
                .collect(Collectors.toList());
        System.out.println(namesUpper);

        System.out.println("\n=== 4. Optional pitfalls ===");
        Optional<Employee> highest = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary));
        // correct way to handle Optional
        System.out.println(highest.orElseThrow(() -> new RuntimeException("No employee found")));

        System.out.println("\n=== 5. ConcurrentHashMap merge example ===");
        Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        List<String> items = Arrays.asList("A","B","A","C","B","A");
        items.forEach(key -> concurrentMap.merge(key, 1, Integer::sum));
        System.out.println(concurrentMap);

        System.out.println("\n=== 6. Flatten nested lists with parallel stream ===");
        List<List<Integer>> nestedList = Arrays.asList(
                Arrays.asList(1,2), Arrays.asList(3,4), Arrays.asList(5,6)
        );
        List<Integer> flatList = nestedList.parallelStream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println(flatList);

        System.out.println("\n=== 7. Group employees by dept with average salary ===");
        Map<String, Double> avgSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDept,
                        Collectors.averagingDouble(Employee::getSalary)
                ));
        System.out.println(avgSalaryByDept);

        System.out.println("\n=== 8. Find duplicate employees by name ===");
        List<Employee> dupEmployees = Arrays.asList(
                new Employee(1,"Alice",5000,"IT"),
                new Employee(2,"Alice",7000,"HR"),
                new Employee(3,"Charlie",8000,"IT")
        );
        Set<String> duplicateNames = dupEmployees.stream()
                .collect(Collectors.groupingBy(Employee::getName, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        System.out.println(duplicateNames);

        System.out.println("\n=== 9. Sort map by values descending ===");
        Map<String, Integer> map = new HashMap<>();
        map.put("X",10); map.put("Y",50); map.put("Z",20);
        LinkedHashMap<String, Integer> sortedMap = map.entrySet().stream()
                //.sorted(Map.Entry.<String,Integer>comparingByValue().reversed())
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))// .max(Comparator.comparingDouble(Employee::getSalary));
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1,e2)->e1,// merge()
                        LinkedHashMap::new
                ));
        System.out.println("sortedMap" + sortedMap);

        System.out.println("\n=== 10. Reduce to find max salary ===");
        double maxSalary = employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::max)
                .orElse(0.0);//employees.stream().max(Comparator.comparingDouble(Employee::getSalary));
        System.out.println("Max Salary = " + maxSalary);



        // tough question
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("ACC1001", 300);
        map2.put("ACC1002", 200);
        map2.put("ACC1003", 500);

        Map<String, Integer> result = map2.entrySet().stream()

                // Sorting by value descending
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))

                // Intentionally create duplicate keys (first 3 letters only)
                .collect(Collectors.toMap(
                        e -> e.getKey().substring(0, 3),   // <--- COLLISION: all become "ACC"
                        Map.Entry::getValue,

                        // merge function
                        (e1, e2) -> {
                            System.out.println("Collision! e1=" + e1 + ", e2=" + e2);
                            return e1;                       // keep first, discard second
                        },

                        LinkedHashMap::new
                ));

        System.out.println("result +"+result);


    }
}

