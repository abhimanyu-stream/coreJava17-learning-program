package com.java17.interview.prepartion;

import java.util.*;
import java.util.stream.Collectors;

public class FindSecondHighestSalaryStreamApi {

    public static void main(String[] args) {
        //Find top 2 highest salaried employees using Stream API.

        Map<String, Double> employeeSalaryMap = new HashMap<>();
        employeeSalaryMap.put("Charlie", 5000.00);
        employeeSalaryMap.put("Jenifer", 6000.00);
        employeeSalaryMap.put("Cally", 6000.00);
        employeeSalaryMap.put("Newton", 5000.00);
        employeeSalaryMap.put("Madonna", 10000.00);

        // Step 1: Find 2nd highest salary value
        double secondHighestSalary = employeeSalaryMap.values().stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No second highest salary found"));

        // Step 2: Collect all employees having that salary
        List<String> employeesWithSecondHighestSalary = employeeSalaryMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(secondHighestSalary))
                .map(Map.Entry::getKey)
                .toList();

        System.out.println("Second Highest Salary = " + secondHighestSalary);
        System.out.println("Employees with Second Highest Salary = " + employeesWithSecondHighestSalary);





        // ✅ One-liner to find all employees having 2nd highest salary
        List<String> secondhighestsalary =
                employeeSalaryMap.entrySet().stream()
                        .collect(Collectors.groupingBy(Map.Entry::getValue)) // Group by salary Map<Double, List<Map.Entry<String, Double>>>
                        .entrySet().stream()
                        .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder())) //Set<Map.Entry<Double, List<Map.Entry<String, Double>>>>
                        .skip(1) // skip highest salary group
                        .findFirst() // get 2nd highest salary group
                        .map(Map.Entry::getValue) // list of entries having that salary  List<Map.Entry<String, Double>
                        .orElse(Collections.emptyList()) // fallback
                        .stream()
                        .map(Map.Entry::getKey)
                        .toList();// List<String>

        System.out.println("Employees with Second Highest Salary = " + secondhighestsalary);

        /***
         *
         * The line
         * .collect(Collectors.groupingBy(Map.Entry::getValue))
         *
         * 💡 Step-by-step Explanation
         *
         * We start from:
         *
         * employeeSalaryMap.entrySet().stream()
         *
         *
         * That gives a stream of elements like:
         *
         * [
         *   Charlie=5000.0,
         *   Jenifer=6000.0,
         *   Cally=6000.0,
         *   Newton=5000.0,
         *   Madonna=10000.0
         * ]
         *
         *
         * Now we apply:
         *
         * .collect(Collectors.groupingBy(Map.Entry::getValue))
         *
         *
         * This means:
         *
         * “Group all map entries by their value (salary).”
         *
         * 📦 Result of groupingBy
         *
         * Collectors.groupingBy(Map.Entry::getValue) produces a Map<Double, List<Map.Entry<String, Double>>>
         *
         * So the result looks like this:
         *
         * {
         *   10000.0 = [Madonna=10000.0],
         *   6000.0 = [Jenifer=6000.0, Cally=6000.0],
         *   5000.0 = [Charlie=5000.0, Newton=5000.0]
         * }
         *
         *
         * Here’s what’s happening:
         *
         * Salary (Key)	Value (List of entries with that salary)
         * 10000.0	[Madonna=10000.0]
         * 6000.0	[Jenifer=6000.0, Cally=6000.0]
         * 5000.0	[Charlie=5000.0, Newton=5000.0]
         * 🧠 Why we need this
         *
         * Grouping allows us to treat each unique salary as a group.
         * Then we can sort these groups by salary, skip the highest one, and take the next one.
         *
         * So after grouping, we do:
         *
         * .entrySet().stream()
         * .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
         * .skip(1)
         * .findFirst()
         *
         *
         * Now we’re iterating over:
         *
         * Set<Map.Entry<Double, List<Map.Entry<String, Double>>>>
         *
         *
         * Each entry represents:
         * salary → list of employees having that salary
         *
         * 🧩 Final Summary
         * Step	Expression	Description
         * 1️⃣	.entrySet().stream()	Stream of entries (employee=salary)
         * 2️⃣	.collect(Collectors.groupingBy(Map.Entry::getValue))	Group entries by salary
         * 3️⃣	.entrySet().stream()	Stream over grouped salaries
         * 4️⃣	.sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))	Sort by salary descending
         * 5️⃣	.skip(1)	Skip the highest salary group
         * 6️⃣	.findFirst()	Get 2nd highest salary group
         * 7️⃣	.map(Map.Entry::getValue)	Extract employees in that group
         * 8️⃣	.map(Map.Entry::getKey)	Extract employee names
         *
         *
         *
         */



    }
}


