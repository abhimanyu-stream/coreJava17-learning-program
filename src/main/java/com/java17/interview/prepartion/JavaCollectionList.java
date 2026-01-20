package com.java17.interview.prepartion;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 📋 JAVA COLLECTION LIST - List Interface Implementations
 */
public class JavaCollectionList {
    
    public static void demonstrateArrayList() {
        System.out.println("1. ArrayList Demonstration:");
        
        ArrayList<String> arrayList = new ArrayList<>();
        
        // Adding elements
        arrayList.add("Apple");
        arrayList.add("Banana");
        arrayList.add("Cherry");
        arrayList.add(1, "Blueberry"); // Insert at index
        
        System.out.println("ArrayList: " + arrayList);
        System.out.println("Size: " + arrayList.size());
        System.out.println("Element at index 2: " + arrayList.get(2));
        
        // Searching
        System.out.println("Contains 'Apple': " + arrayList.contains("Apple"));
        System.out.println("Index of 'Cherry': " + arrayList.indexOf("Cherry"));
        
        // Removing elements
        arrayList.remove("Banana");
        arrayList.remove(0); // Remove by index
        System.out.println("After removal: " + arrayList);
    }
    
    public static void demonstrateLinkedList() {
        System.out.println("\n2. LinkedList Demonstration:");
        
        LinkedList<Integer> linkedList = new LinkedList<>();
        
        // Adding elements
        linkedList.add(10);
        linkedList.add(20);
        linkedList.add(30);
        linkedList.addFirst(5);  // Add at beginning
        linkedList.addLast(40);  // Add at end
        
        System.out.println("LinkedList: " + linkedList);
        
        // Queue operations
        System.out.println("First element: " + linkedList.peekFirst());
        System.out.println("Last element: " + linkedList.peekLast());
        
        // Stack operations
        linkedList.push(1); // Add to front
        System.out.println("After push: " + linkedList);
        System.out.println("Popped: " + linkedList.pop());
        System.out.println("After pop: " + linkedList);
    }
    
    public static void demonstrateVector() {
        System.out.println("\n3. Vector Demonstration:");
        
        Vector<String> vector = new Vector<>();
        
        vector.add("Vector1");
        vector.add("Vector2");
        vector.add("Vector3");
        
        System.out.println("Vector: " + vector);
        System.out.println("Capacity: " + vector.capacity());
        System.out.println("Size: " + vector.size());
        
        // Vector-specific methods
        vector.addElement("Vector4");
        System.out.println("After addElement: " + vector);
    }
    
    public static void demonstrateStack() {
        System.out.println("\n4. Stack Demonstration:");
        
        Stack<String> stack = new Stack<>();
        
        // Push elements
        stack.push("First");
        stack.push("Second");
        stack.push("Third");
        
        System.out.println("Stack: " + stack);
        System.out.println("Peek: " + stack.peek());
        System.out.println("Pop: " + stack.pop());
        System.out.println("After pop: " + stack);
        System.out.println("Search 'First': " + stack.search("First"));
    }
    
    public static void demonstrateCopyOnWriteArrayList() {
        System.out.println("\n5. CopyOnWriteArrayList Demonstration:");
        
        CopyOnWriteArrayList<String> cowList = new CopyOnWriteArrayList<>();
        
        cowList.add("Thread-Safe");
        cowList.add("Copy-On-Write");
        cowList.add("Concurrent");
        
        System.out.println("CopyOnWriteArrayList: " + cowList);
        
        // Safe for concurrent iteration
        for (String item : cowList) {
            System.out.println("Item: " + item);
            // Safe to modify during iteration
            if (item.equals("Copy-On-Write")) {
                cowList.add("New Item");
            }
        }
        
        System.out.println("After concurrent modification: " + cowList);
    }
    
    public static void comparePerformance() {
        System.out.println("\n6. Performance Comparison:");
        
        int size = 100000;
        
        // ArrayList performance
        long start = System.nanoTime();
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            arrayList.add(i);
        }
        long arrayListTime = System.nanoTime() - start;
        
        // LinkedList performance
        start = System.nanoTime();
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            linkedList.add(i);
        }
        long linkedListTime = System.nanoTime() - start;
        
        System.out.println("ArrayList add time: " + arrayListTime / 1000000 + " ms");
        System.out.println("LinkedList add time: " + linkedListTime / 1000000 + " ms");
        
        // Random access performance
        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            arrayList.get(size / 2);
        }
        long arrayListAccess = System.nanoTime() - start;
        
        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            linkedList.get(size / 2);
        }
        long linkedListAccess = System.nanoTime() - start;
        
        System.out.println("ArrayList random access: " + arrayListAccess / 1000000 + " ms");
        System.out.println("LinkedList random access: " + linkedListAccess / 1000000 + " ms");
    }
    
    public static void main(String[] args) {
        System.out.println("📋 JAVA COLLECTION LIST DEMONSTRATION");
        System.out.println("=".repeat(50));
        
        demonstrateArrayList();
        demonstrateLinkedList();
        demonstrateVector();
        demonstrateStack();
        demonstrateCopyOnWriteArrayList();
        comparePerformance();
        
        // Interview Questions
        System.out.println("\n🎯 INTERVIEW QUESTIONS:");
        System.out.println("Q1: ArrayList vs LinkedList?");
        System.out.println("A1: ArrayList: Random access O(1), insertion/deletion O(n)");
        System.out.println("    LinkedList: Sequential access O(n), insertion/deletion O(1)");
        
        System.out.println("\nQ2: When to use Vector?");
        System.out.println("A2: When thread safety is needed (but prefer Collections.synchronizedList)");
        
        System.out.println("\nQ3: ArrayList vs Array?");
        System.out.println("A3: ArrayList: Dynamic size, more methods, object overhead");
        System.out.println("    Array: Fixed size, primitive support, better performance");
        
        System.out.println("\nQ4: CopyOnWriteArrayList use case?");
        System.out.println("A4: When reads are frequent and writes are rare (thread-safe)");
        
        System.out.println("\nQ5: List interface methods?");
        System.out.println("A5: add(), get(), set(), remove(), size(), contains(), indexOf()");
    }
}