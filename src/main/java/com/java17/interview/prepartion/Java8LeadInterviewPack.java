package com.java17.interview.prepartion;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.*;
import java.util.stream.*;

public class Java8LeadInterviewPack {

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

                public int getId() {
                        return id;
                }

                public String getName() {
                        return name;
                }

                public double getSalary() {
                        return salary;
                }

                public String getDept() {
                        return dept;
                }

                @Override
                public String toString() {
                        return name + "(" + salary + "," + dept + ")";
                }
        }

        static class Transaction {
                String txId;
                double amount;
                String type;

                public Transaction(String txId, double amount, String type) {
                        this.txId = txId;
                        this.amount = amount;
                        this.type = type;
                }

                public String getTxId() {
                        return txId;
                }

                public double getAmount() {
                        return amount;
                }

                public String getType() {
                        return type;
                }

                @Override
                public String toString() {
                        return txId + "(" + type + "," + amount + ")";
                }
        }

        public static void main(String[] args) {

                System.out.println("=== 1. Filter, Map, Collect ===");
                List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
                List<Integer> evens = nums.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
                System.out.println(evens);

                System.out.println("\n=== 2. Uppercase conversion using map ===");
                List<String> words = Arrays.asList("java", "lambda", "stream", "functional");
                List<String> upper = words.stream().map(String::toUpperCase).collect(Collectors.toList());
                System.out.println(upper);

                System.out.println("\n=== 3. First element > 50 ===");
                int firstGt50 = Arrays.asList(10, 20, 55, 60).stream().filter(n -> n > 50).findFirst().orElse(-1);
                System.out.println(firstGt50);

                System.out.println("\n=== 4. Find duplicates in list ===");
                List<Integer> nums2 = Arrays.asList(1, 2, 3, 2, 4, 5, 3, 6, 1);
                Set<Integer> duplicates = nums2.stream()
                                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                                .entrySet().stream()
                                .filter(e -> e.getValue() > 1)
                                .map(Map.Entry::getKey)
                                .collect(Collectors.toSet());
                System.out.println(duplicates);

                System.out.println("\n=== 5. Word frequency in sentence ===");
                String sentence = "Java Java Stream Lambda Stream Lambda Stream";
                Map<String, Long> freq = Arrays.stream(sentence.split(" "))
                                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
                System.out.println(freq);

                System.out.println("\n=== 6. 2nd highest salary employee ===");
                List<Employee> employees = Arrays.asList(
                                new Employee(1, "Alice", 5000, "IT"),
                                new Employee(2, "Bob", 7000, "HR"),
                                new Employee(3, "Charlie", 8000, "IT"),
                                new Employee(4, "David", 6000, "Finance"));
                Employee secondHighest = employees.stream()
                                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                                .skip(1)
                                .findFirst()
                                .orElse(null);
                System.out.println(secondHighest);

                System.out.println("\n=== 7. Group employees by dept & count ===");
                Map<String, Long> groupByDept = employees.stream()
                                .collect(Collectors.groupingBy(Employee::getDept, Collectors.counting()));
                System.out.println(groupByDept);

                System.out.println("\n=== 8. Average salary per dept ===");
                Map<String, Double> avgSalaryByDept = employees.stream()
                                .collect(Collectors.groupingBy(Employee::getDept,
                                                Collectors.averagingDouble(Employee::getSalary)));
                System.out.println(avgSalaryByDept);

                System.out.println("\n=== 9. Flatten nested lists ===");
                List<List<Integer>> nestedList = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4),
                                Arrays.asList(5, 6));
                List<Integer> flatList = nestedList.stream().flatMap(List::stream).collect(Collectors.toList());
                System.out.println(flatList);

                System.out.println("\n=== 10. Sort map by values descending - ALL INTERVIEW APPROACHES ===");
                Map<String, Integer> map = new HashMap<>();
                map.put("A", 10);
                map.put("B", 50);
                map.put("C", 20);

                /*
                 * 🎯 INTERVIEW APPROACH ANALYSIS:
                 * Method 1 & 2: Most commonly expected in interviews - shows standard Java 8
                 * knowledge
                 * Method 3: Shows lambda expertise - demonstrates functional programming skills
                 * Method 5: Demonstrates performance awareness - memory efficient for large
                 * datasets
                 * Method 6: Shows knowledge of alternative data structures - advanced Java
                 * collections
                 */

                // ✅ METHOD 1: Most Common Interview Answer
                // Uses method reference with explicit type parameters and reversed()
                // 🔥 INTERVIEW TIP: This is what 90% of interviewers expect to see
                LinkedHashMap<String, Integer> sortedMap1 = map.entrySet().stream()
                                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1,
                                                LinkedHashMap::new));
                System.out.println("✅ Method 1 (Standard): " + sortedMap1);

                // ✅ METHOD 2: Alternative Standard Approach
                // Uses Collections.reverseOrder() - equally acceptable in interviews
                // 🔥 INTERVIEW TIP: Shows knowledge of Collections utility class
                LinkedHashMap<String, Integer> sortedMap2 = map.entrySet().stream()
                                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1,
                                                LinkedHashMap::new));
                System.out.println("✅ Method 2 (Collections.reverseOrder): " + sortedMap2);

                // 🚀 METHOD 3: Lambda Expertise Demonstration
                // Direct lambda expression - shows deep understanding of comparators
                // 🔥 INTERVIEW TIP: Use this to show functional programming mastery
                LinkedHashMap<String, Integer> sortedMap3 = map.entrySet().stream()
                                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1,
                                                LinkedHashMap::new));
                System.out.println("🚀 Method 3 (Lambda Expert): " + sortedMap3);

                // 📊 METHOD 4: Comparator.comparing Approach
                // More verbose but very explicit about intention
                // 🔥 INTERVIEW TIP: Good for explaining step-by-step logic
                LinkedHashMap<String, Integer> sortedMap4 = map.entrySet().stream()
                                .sorted(Comparator.comparing(Map.Entry<String, Integer>::getValue,
                                                Comparator.reverseOrder()))
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1,
                                                LinkedHashMap::new));
                System.out.println("📊 Method 4 (Explicit Comparing): " + sortedMap4);

                // ⚡ METHOD 5: Performance Awareness (Memory Efficient)
                // No intermediate collection creation - best for display-only scenarios
                // 🔥 INTERVIEW TIP: Shows performance consciousness for large datasets
                System.out.print("⚡ Method 5 (Performance Aware - No Collection): {");
                map.entrySet().stream()
                                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                                .forEach(entry -> System.out.print(entry.getKey() + "=" + entry.getValue() + ", "));
                System.out.println("}");

                // 🏗️ METHOD 6: Alternative Data Structure Knowledge
                // TreeMap with custom comparator - shows advanced collections understanding
                // 🔥 INTERVIEW TIP: Demonstrates knowledge beyond basic Stream API
                Map<String, Integer> sortedTreeMap = new TreeMap<>((k1, k2) -> {
                        int valueCompare = map.get(k2).compareTo(map.get(k1)); // Reverse order by value
                        return valueCompare != 0 ? valueCompare : k1.compareTo(k2); // Handle equal values by key
                });
                sortedTreeMap.putAll(map);
                System.out.println("🏗️ Method 6 (TreeMap Alternative): " + sortedTreeMap);

                // 💡 BONUS METHOD 7: Using Stream.of for inline creation and sorting
                // Shows advanced stream manipulation
                // 🔥 INTERVIEW TIP: Demonstrates creative problem-solving
                LinkedHashMap<String, Integer> sortedMap7 = Stream.of(
                                Map.entry("A", 10),
                                Map.entry("B", 50),
                                Map.entry("C", 20))
                                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1,
                                                LinkedHashMap::new));
                System.out.println("💡 Method 7 (Stream.of Bonus): " + sortedMap7);

                /*
                 * 🎯 INTERVIEW STRATEGY:
                 * - Start with Method 1 (shows you know the standard approach)
                 * - If asked for alternatives, show Method 2 (Collections.reverseOrder)
                 * - For senior roles, demonstrate Method 3 (lambda expertise)
                 * - For performance discussions, mention Method 5 (memory efficiency)
                 * - For architecture roles, discuss Method 6 (data structure alternatives)
                 */

                System.out.println("\n=== 11. Max salary using reduce ===");
                double maxSalary = employees.stream().map(Employee::getSalary).reduce(Double::max).orElse(0.0);
                System.out.println(maxSalary);

                System.out.println("\n=== 12. Total salary using reduce ===");
                double totalSalary = employees.stream().map(Employee::getSalary).reduce(0.0, Double::sum);
                System.out.println(totalSalary);

                System.out.println("\n=== 13. Peek example for debugging ===");
                List<String> namesUpper = employees.stream()
                                .peek(e -> System.out.println("Before map: " + e.getName()))
                                .map(Employee::getName)
                                .map(String::toUpperCase)
                                .peek(n -> System.out.println("After map: " + n))
                                .collect(Collectors.toList());
                System.out.println(namesUpper);

                System.out.println("\n=== 14. Optional pitfalls ===");
                Optional<Employee> highest = employees.stream().max(Comparator.comparingDouble(Employee::getSalary));
                System.out.println(highest.orElseThrow(() -> new RuntimeException("No employee found")));

                System.out.println("\n=== 15. ConcurrentHashMap merge example ===");
                Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
                List<String> items = Arrays.asList("X", "Y", "X", "Z", "Y", "X");
                items.forEach(k -> concurrentMap.merge(k, 1, Integer::sum));
                System.out.println(concurrentMap);

                System.out.println("\n=== 16. Parallel Stream sum 1-1000 ===");
                int sumParallel = IntStream.rangeClosed(1, 1000).parallel().sum();
                System.out.println(sumParallel);

                System.out.println("\n=== 17. Custom collector: join employee names ===");
                String empNames = employees.stream().map(Employee::getName).collect(Collectors.joining(", "));
                System.out.println(empNames);

                System.out.println("\n=== 18. Filter + map + reduce real scenario ===");
                double itTotal = employees.stream()
                                .filter(e -> e.getDept().equals("IT"))
                                .map(Employee::getSalary)
                                .reduce(0.0, Double::sum);
                System.out.println("IT Dept Total Salary = " + itTotal);

                System.out.println("\n=== 19. Find duplicate employees by name ===");
                List<Employee> dupEmployees = Arrays.asList(
                                new Employee(1, "Alice", 5000, "IT"),
                                new Employee(2, "Alice", 7000, "HR"),
                                new Employee(3, "Charlie", 8000, "IT"));
                Set<String> duplicateNames = dupEmployees.stream()
                                .collect(Collectors.groupingBy(Employee::getName, Collectors.counting()))
                                .entrySet().stream()
                                .filter(e -> e.getValue() > 1)
                                .map(Map.Entry::getKey)
                                .collect(Collectors.toSet());
                System.out.println(duplicateNames);

                System.out.println("\n=== 20. Transactions: group by type & sum ===");
                List<Transaction> transactions = Arrays.asList(
                                new Transaction("TX1", 1000, "CREDIT"),
                                new Transaction("TX2", 2000, "DEBIT"),
                                new Transaction("TX3", 1500, "CREDIT"),
                                new Transaction("TX4", 500, "DEBIT"));
                Map<String, Double> sumByType = transactions.stream()
                                .collect(Collectors.groupingBy(Transaction::getType,
                                                Collectors.summingDouble(Transaction::getAmount)));
                System.out.println(sumByType);
        }
}
