package com.java17.interview.prepartion;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 🗺️ JAVA COLLECTION MAP - Map Interface Implementations
 */
public class JavaCollectionMap {
    
    public static void demonstrateHashMap() {
        System.out.println("1. HashMap Demonstration:");
        
        HashMap<String, Integer> hashMap = new HashMap<>();
        
        hashMap.put("Apple", 10);
        hashMap.put("Banana", 20);
        hashMap.put("Cherry", 15);
        hashMap.put("Apple", 25); // Updates existing key
        
        System.out.println("HashMap: " + hashMap);
        System.out.println("Value for 'Apple': " + hashMap.get("Apple"));
        System.out.println("Contains key 'Banana': " + hashMap.containsKey("Banana"));
        System.out.println("Contains value 20: " + hashMap.containsValue(20));
        
        // Java 8 methods
        hashMap.putIfAbsent("Date", 30);
        hashMap.computeIfAbsent("Elderberry", k -> k.length());
        hashMap.merge("Apple", 5, Integer::sum);
        
        System.out.println("After Java 8 operations: " + hashMap);
    }
    
    public static void demonstrateLinkedHashMap() {
        System.out.println("\n2. LinkedHashMap Demonstration:");
        
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        
        linkedHashMap.put("First", "1st");
        linkedHashMap.put("Third", "3rd");
        linkedHashMap.put("Second", "2nd");
        
        System.out.println("LinkedHashMap (insertion order): " + linkedHashMap);
        
        // Access order LinkedHashMap
        LinkedHashMap<String, String> accessOrder = new LinkedHashMap<>(16, 0.75f, true);
        accessOrder.put("A", "First");
        accessOrder.put("B", "Second");
        accessOrder.put("C", "Third");
        
        accessOrder.get("A"); // Access A
        System.out.println("Access order after getting 'A': " + accessOrder);
    }
    
    public static void demonstrateTreeMap() {
        System.out.println("\n3. TreeMap Demonstration:");
        
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        
        treeMap.put("Zebra", 26);
        treeMap.put("Apple", 1);
        treeMap.put("Mango", 13);
        treeMap.put("Banana", 2);
        
        System.out.println("TreeMap (sorted by keys): " + treeMap);
        
        // NavigableMap methods
        System.out.println("First key: " + treeMap.firstKey());
        System.out.println("Last key: " + treeMap.lastKey());
        System.out.println("Higher key than 'Banana': " + treeMap.higherKey("Banana"));
        System.out.println("Lower key than 'Mango': " + treeMap.lowerKey("Mango"));
        
        // Submap operations
        System.out.println("HeadMap (< 'Mango'): " + treeMap.headMap("Mango"));
        System.out.println("TailMap (>= 'Banana'): " + treeMap.tailMap("Banana"));
    }
    
    public static void demonstrateConcurrentHashMap() {
        System.out.println("\n4. ConcurrentHashMap Demonstration:");
        
        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        
        concurrentMap.put("Thread1", 100);
        concurrentMap.put("Thread2", 200);
        concurrentMap.put("Thread3", 300);
        
        System.out.println("ConcurrentHashMap: " + concurrentMap);
        
        // Thread-safe operations
        concurrentMap.computeIfAbsent("Thread4", k -> 400);
        concurrentMap.merge("Thread1", 50, Integer::sum);
        
        System.out.println("After concurrent operations: " + concurrentMap);
        
        // Parallel operations (Java 8+)
        int sum = concurrentMap.reduceValues(1, Integer::sum);
        System.out.println("Sum of all values: " + sum);
    }
    
    public static void demonstrateEnumMap() {
        System.out.println("\n5. EnumMap Demonstration:");
        
        enum Priority { LOW, MEDIUM, HIGH, CRITICAL }
        
        EnumMap<Priority, String> enumMap = new EnumMap<>(Priority.class);
        
        enumMap.put(Priority.HIGH, "Important task");
        enumMap.put(Priority.LOW, "Can wait");
        enumMap.put(Priority.CRITICAL, "Urgent!");
        enumMap.put(Priority.MEDIUM, "Normal priority");
        
        System.out.println("EnumMap: " + enumMap);
        
        // Iteration follows enum declaration order
        System.out.println("Iteration order (enum declaration order):");
        enumMap.forEach((priority, task) -> 
            System.out.println("  " + priority + ": " + task));
    }
    
    public static void demonstrateMapOperations() {
        System.out.println("\n6. Common Map Operations:");
        
        Map<String, List<String>> groupedData = new HashMap<>();
        
        // Grouping data
        String[] items = {"apple", "banana", "apricot", "blueberry", "cherry", "avocado"};
        
        for (String item : items) {
            String firstLetter = item.substring(0, 1).toUpperCase();
            groupedData.computeIfAbsent(firstLetter, k -> new ArrayList<>()).add(item);
        }
        
        System.out.println("Grouped by first letter: " + groupedData);
        
        // Frequency counting
        Map<Character, Integer> charFreq = new HashMap<>();
        String text = "hello world";
        
        for (char c : text.toCharArray()) {
            if (c != ' ') {
                charFreq.merge(c, 1, Integer::sum);
            }
        }
        
        System.out.println("Character frequency: " + charFreq);
    }
    
    public static void main(String[] args) {
        System.out.println("🗺️ JAVA COLLECTION MAP DEMONSTRATION");
        System.out.println("=".repeat(50));
        
        demonstrateHashMap();
        demonstrateLinkedHashMap();
        demonstrateTreeMap();
        demonstrateConcurrentHashMap();
        demonstrateEnumMap();
        demonstrateMapOperations();
        
        // Interview Questions
        System.out.println("\n🎯 INTERVIEW QUESTIONS:");
        System.out.println("Q1: HashMap vs TreeMap vs LinkedHashMap?");
        System.out.println("A1: HashMap: O(1) ops, no order");
        System.out.println("    TreeMap: O(log n) ops, sorted by keys");
        System.out.println("    LinkedHashMap: O(1) ops, maintains insertion/access order");
        
        System.out.println("\nQ2: How does HashMap work internally?");
        System.out.println("A2: Uses array of buckets, hashCode() for index, equals() for collision resolution");
        
        System.out.println("\nQ3: HashMap vs ConcurrentHashMap?");
        System.out.println("A3: HashMap: Not thread-safe, allows null");
        System.out.println("    ConcurrentHashMap: Thread-safe, no null keys/values");
        
        System.out.println("\nQ4: When to use EnumMap?");
        System.out.println("A4: When keys are enum constants - very efficient");
        
        System.out.println("\nQ5: Map interface key methods?");
        System.out.println("A5: put(), get(), remove(), containsKey(), keySet(), values(), entrySet()");
    }
}