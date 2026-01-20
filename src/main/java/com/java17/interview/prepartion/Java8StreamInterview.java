package com.java17.interview.prepartion;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Java8StreamInterview {

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

        System.out.println("=== Q1. Find even numbers ===");
        List<Integer> nums = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        List<Integer> evens = nums.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(evens);

        System.out.println("\n=== Q2. Convert strings to uppercase ===");
        List<String> words = Arrays.asList("java", "stream", "lambda", "interview");
        List<String> upper = words.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(upper);

        System.out.println("\n=== Q3. First element > 50 ===");
        List<Integer> numbers = Arrays.asList(10, 25, 30, 60, 70, 15);
        Integer firstGt50 = numbers.stream()
                .filter(n -> n > 50)
                .findFirst()
                .orElse(null);
        System.out.println(firstGt50);

        System.out.println("\n=== Q4. Find duplicates in a list ===");
        List<Integer> nums2 = Arrays.asList(1,2,3,4,5,1,2,6,7,8,3);
        Set<Integer> duplicates = nums2.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        System.out.println(duplicates);

        System.out.println("\n=== Q5. Word frequency in a sentence ===");
        String sentence = "Java Java Spring Boot Boot Boot Kafka";
        Map<String, Long> wordFreq = Arrays.stream(sentence.split(" "))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(wordFreq);

        System.out.println("\n=== Q6. 2nd highest salary employee ===");
        List<Employee> employees = Arrays.asList(
                new Employee(1,"Alice",5000,"IT"),
                new Employee(2,"Bob",7000,"HR"),
                new Employee(3,"Charlie",8000,"IT"),
                new Employee(4,"David",6000,"Finance")
        );
        Employee secondHighest = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .skip(1)
                .findFirst()
                .orElse(null);
        System.out.println(secondHighest);

        System.out.println("\n=== Q7. Group employees by department ===");
        Map<String, Long> groupByDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDept, Collectors.counting()));
        System.out.println(groupByDept);

        System.out.println("\n=== Q8. Flatten a list of lists ===");
        List<List<Integer>> nestedList = Arrays.asList(
                Arrays.asList(1,2), Arrays.asList(3,4), Arrays.asList(5,6)
        );
        List<Integer> flatList = nestedList.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println(flatList);

        System.out.println("\n=== Q9. Sort map by values descending ===");
        Map<String, Integer> map = new HashMap<>();
        map.put("A",10); map.put("B",5); map.put("C",20);
        LinkedHashMap<String, Integer> sortedMap = map.entrySet().stream()
                //.sorted(Map.Entry.<String,Integer>comparingByValue().reversed())//{C=20, A=10, B=5}
                .sorted(Map.Entry.comparingByValue())//{B=5, A=10, C=20}
                //  .sorted(Comparator.comparingDouble(Employee::getSalary).reversed()),
                // .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder())) //Set<Map.Entry<Double, List<Map.Entry<String, Double>>>>
                // .max(Comparator.comparingInt(String::length))
                //employees.stream().max(Comparator.comparingDouble(Employee::getSalary));
                //employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1,e2)->e1,
                        LinkedHashMap::new
                ));
        System.out.println(sortedMap);

        System.out.println("\n=== Q10. Find longest word in a list ===");
        List<String> words2 = Arrays.asList("java","microservices","springboot","cloud");
        String longest = words2.stream()
                .max(Comparator.comparingInt(String::length))
                .orElse("");
        System.out.println(longest);
    }
}
