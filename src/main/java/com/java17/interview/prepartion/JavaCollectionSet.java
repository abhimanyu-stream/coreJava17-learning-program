package com.java17.interview.prepartion;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * 🔢 JAVA COLLECTION SET - Set Interface Implementations
 */
public class JavaCollectionSet {
    
    public static void demonstrateHashSet() {
        System.out.println("1. HashSet Demonstration:");
        
        HashSet<String> hashSet = new HashSet<>();
        
        // Adding elements
        hashSet.add("Apple");
        hashSet.add("Banana");
        hashSet.add("Cherry");
        hashSet.add("Apple"); // Duplicate - won't be added
        
        System.out.println("HashSet: " + hashSet);
        System.out.println("Size: " + hashSet.size());
        System.out.println("Contains 'Banana': " + hashSet.contains("Banana"));
        
        // No guaranteed order
        System.out.println("Iteration order (no guarantee):");
        for (String fruit : hashSet) {
            System.out.println("  " + fruit);
        }
        
        hashSet.remove("Banana");
        System.out.println("After removal: " + hashSet);
    }
    
    public static void demonstrateLinkedHashSet() {
        System.out.println("\n2. LinkedHashSet Demonstration:");
        
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
        
        linkedHashSet.add(30);
        linkedHashSet.add(10);
        linkedHashSet.add(20);
        linkedHashSet.add(10); // Duplicate
        
        System.out.println("LinkedHashSet: " + linkedHashSet);
        
        // Maintains insertion order
        System.out.println("Iteration order (insertion order maintained):");
        for (Integer num : linkedHashSet) {
            System.out.println("  " + num);
        }
    }
    
    public static void demonstrateTreeSet() {
        System.out.println("\n3. TreeSet Demonstration:");
        
        TreeSet<String> treeSet = new TreeSet<>();
        
        treeSet.add("Zebra");
        treeSet.add("Apple");
        treeSet.add("Mango");
        treeSet.add("Banana");
        
        System.out.println("TreeSet (sorted): " + treeSet);
        
        // NavigableSet methods
        System.out.println("First: " + treeSet.first());
        System.out.println("Last: " + treeSet.last());
        System.out.println("Higher than 'Banana': " + treeSet.higher("Banana"));
        System.out.println("Lower than 'Mango': " + treeSet.lower("Mango"));
        
        // Subset operations
        System.out.println("HeadSet (< 'Mango'): " + treeSet.headSet("Mango"));
        System.out.println("TailSet (>= 'Banana'): " + treeSet.tailSet("Banana"));
        System.out.println("SubSet ('Apple' to 'Mango'): " + treeSet.subSet("Apple", "Mango"));
    }
    
    public static void demonstrateEnumSet() {
        System.out.println("\n4. EnumSet Demonstration:");
        
        enum Day {
            MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
        }
        
        // Different ways to create EnumSet
        EnumSet<Day> weekdays = EnumSet.range(Day.MONDAY, Day.FRIDAY);
        EnumSet<Day> weekend = EnumSet.of(Day.SATURDAY, Day.SUNDAY);
        EnumSet<Day> allDays = EnumSet.allOf(Day.class);
        EnumSet<Day> noDays = EnumSet.noneOf(Day.class);
        
        System.out.println("Weekdays: " + weekdays);
        System.out.println("Weekend: " + weekend);
        System.out.println("All days: " + allDays);
        System.out.println("No days: " + noDays);
        
        // Set operations
        EnumSet<Day> workingDays = EnumSet.copyOf(weekdays);
        workingDays.removeAll(weekend);
        System.out.println("Working days: " + workingDays);
    }
    
    public static void demonstrateConcurrentSkipListSet() {
        System.out.println("\n5. ConcurrentSkipListSet Demonstration:");
        
        ConcurrentSkipListSet<Integer> concurrentSet = new ConcurrentSkipListSet<>();
        
        concurrentSet.add(50);
        concurrentSet.add(30);
        concurrentSet.add(70);
        concurrentSet.add(20);
        
        System.out.println("ConcurrentSkipListSet: " + concurrentSet);
        System.out.println("Thread-safe and sorted: " + concurrentSet.toString());
    }
    
    public static void demonstrateSetOperations() {
        System.out.println("\n6. Set Operations:");
        
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(4, 5, 6, 7, 8));
        
        System.out.println("Set1: " + set1);
        System.out.println("Set2: " + set2);
        
        // Union
        Set<Integer> union = new HashSet<>(set1);
        union.addAll(set2);
        System.out.println("Union: " + union);
        
        // Intersection
        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("Intersection: " + intersection);
        
        // Difference
        Set<Integer> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        System.out.println("Difference (Set1 - Set2): " + difference);
        
        // Symmetric Difference
        Set<Integer> symmetricDiff = new HashSet<>(union);
        symmetricDiff.removeAll(intersection);
        System.out.println("Symmetric Difference: " + symmetricDiff);
    }
    
    public static void comparePerformance() {
        System.out.println("\n7. Performance Comparison:");
        
        int size = 100000;
        
        // HashSet performance
        long start = System.nanoTime();
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < size; i++) {
            hashSet.add(i);
        }
        long hashSetTime = System.nanoTime() - start;
        
        // TreeSet performance
        start = System.nanoTime();
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < size; i++) {
            treeSet.add(i);
        }
        long treeSetTime = System.nanoTime() - start;
        
        System.out.println("HashSet add time: " + hashSetTime / 1000000 + " ms");
        System.out.println("TreeSet add time: " + treeSetTime / 1000000 + " ms");
        
        // Search performance
        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            hashSet.contains(size / 2);
        }
        long hashSetSearch = System.nanoTime() - start;
        
        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            treeSet.contains(size / 2);
        }
        long treeSetSearch = System.nanoTime() - start;
        
        System.out.println("HashSet search time: " + hashSetSearch / 1000000 + " ms");
        System.out.println("TreeSet search time: " + treeSetSearch / 1000000 + " ms");
    }
    
    public static void main(String[] args) {
        System.out.println("🔢 JAVA COLLECTION SET DEMONSTRATION");
        System.out.println("=".repeat(50));
        
        demonstrateHashSet();
        demonstrateLinkedHashSet();
        demonstrateTreeSet();
        demonstrateEnumSet();
        demonstrateConcurrentSkipListSet();
        demonstrateSetOperations();
        comparePerformance();
        
        // Interview Questions
        System.out.println("\n🎯 INTERVIEW QUESTIONS:");
        System.out.println("Q1: HashSet vs TreeSet vs LinkedHashSet?");
        System.out.println("A1: HashSet: O(1) ops, no order");
        System.out.println("    TreeSet: O(log n) ops, sorted");
        System.out.println("    LinkedHashSet: O(1) ops, insertion order");
        
        System.out.println("\nQ2: How does HashSet ensure uniqueness?");
        System.out.println("A2: Uses hashCode() and equals() methods");
        
        System.out.println("\nQ3: When to use TreeSet?");
        System.out.println("A3: When you need sorted unique elements");
        
        System.out.println("\nQ4: EnumSet advantages?");
        System.out.println("A4: Very efficient for enum types, uses bit vectors");
        
        System.out.println("\nQ5: Set interface methods?");
        System.out.println("A5: add(), remove(), contains(), size(), isEmpty()");
    }
}