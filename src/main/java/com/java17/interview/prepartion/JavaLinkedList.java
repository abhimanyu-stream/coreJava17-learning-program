package com.java17.interview.prepartion;

import java.util.*;

/**
 * 🔗 LINKED LIST - Complete Implementation
 * 
 * A Linked List is a linear data structure where elements are stored in nodes,
 * and each node contains data and a reference to the next node.
 * 
 * Types: Singly, Doubly, Circular
 * Time Complexities:
 * - Access: O(n), Search: O(n), Insertion: O(1), Deletion: O(1)
 */
public class JavaLinkedList<T> {
    
    // Node class for singly linked list
    static class ListNode<T> {
        T data;
        ListNode<T> next;
        
        ListNode(T data) {
            this.data = data;
            this.next = null;
        }
    }
    
    // Node class for doubly linked list
    static class DoublyListNode<T> {
        T data;
        DoublyListNode<T> next;
        DoublyListNode<T> prev;
        
        DoublyListNode(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
    
    private ListNode<T> head;
    private int size;
    
    public JavaLinkedList() {
        this.head = null;
        this.size = 0;
    }
    
    /**
     * Add element at the beginning
     * Time: O(1)
     */
    public void addFirst(T data) {
        ListNode<T> newNode = new ListNode<>(data);
        newNode.next = head;
        head = newNode;
        size++;
    }
    
    /**
     * Add element at the end
     * Time: O(n)
     */
    public void addLast(T data) {
        ListNode<T> newNode = new ListNode<>(data);
        
        if (head == null) {
            head = newNode;
        } else {
            ListNode<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }
    
    /**
     * Add element at specific index
     * Time: O(n)
     */
    public void add(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        if (index == 0) {
            addFirst(data);
            return;
        }
        
        ListNode<T> newNode = new ListNode<>(data);
        ListNode<T> current = head;
        
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }
    
    /**
     * Remove first element
     * Time: O(1)
     */
    public T removeFirst() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        
        T data = head.data;
        head = head.next;
        size--;
        return data;
    }
    
    /**
     * Remove last element
     * Time: O(n)
     */
    public T removeLast() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        
        if (head.next == null) {
            T data = head.data;
            head = null;
            size--;
            return data;
        }
        
        ListNode<T> current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        
        T data = current.next.data;
        current.next = null;
        size--;
        return data;
    }
    
    /**
     * Remove element at specific index
     * Time: O(n)
     */
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        if (index == 0) {
            return removeFirst();
        }
        
        ListNode<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        
        T data = current.next.data;
        current.next = current.next.next;
        size--;
        return data;
    }
    
    /**
     * Get element at specific index
     * Time: O(n)
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        ListNode<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }
    
    /**
     * Find index of element
     * Time: O(n)
     */
    public int indexOf(T data) {
        ListNode<T> current = head;
        int index = 0;
        
        while (current != null) {
            if (Objects.equals(current.data, data)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }
    
    /**
     * Check if list contains element
     * Time: O(n)
     */
    public boolean contains(T data) {
        return indexOf(data) != -1;
    }
    
    /**
     * Get size of list
     * Time: O(1)
     */
    public int size() {
        return size;
    }
    
    /**
     * Check if list is empty
     * Time: O(1)
     */
    public boolean isEmpty() {
        return head == null;
    }
    
    /**
     * Clear the list
     * Time: O(1)
     */
    public void clear() {
        head = null;
        size = 0;
    }
    
    /**
     * Reverse the linked list
     * Time: O(n), Space: O(1)
     */
    public void reverse() {
        ListNode<T> prev = null;
        ListNode<T> current = head;
        ListNode<T> next;
        
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        
        head = prev;
    }
    
    /**
     * Find middle element (Floyd's Cycle Detection)
     * Time: O(n), Space: O(1)
     */
    public T findMiddle() {
        if (head == null) return null;
        
        ListNode<T> slow = head;
        ListNode<T> fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow.data;
    }
    
    /**
     * Detect cycle in linked list
     * Time: O(n), Space: O(1)
     */
    public boolean hasCycle() {
        if (head == null || head.next == null) return false;
        
        ListNode<T> slow = head;
        ListNode<T> fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) return true;
        }
        
        return false;
    }
    
    /**
     * Remove duplicates from sorted linked list
     * Time: O(n), Space: O(1)
     */
    public void removeDuplicatesFromSorted() {
        ListNode<T> current = head;
        
        while (current != null && current.next != null) {
            if (Objects.equals(current.data, current.next.data)) {
                current.next = current.next.next;
                size--;
            } else {
                current = current.next;
            }
        }
    }
    
    /**
     * Merge two sorted linked lists
     * Time: O(m + n), Space: O(1)
     */
    public static <T extends Comparable<T>> JavaLinkedList<T> mergeSorted(
            JavaLinkedList<T> list1, JavaLinkedList<T> list2) {
        
        JavaLinkedList<T> result = new JavaLinkedList<>();
        ListNode<T> current1 = list1.head;
        ListNode<T> current2 = list2.head;
        
        while (current1 != null && current2 != null) {
            if (current1.data.compareTo(current2.data) <= 0) {
                result.addLast(current1.data);
                current1 = current1.next;
            } else {
                result.addLast(current2.data);
                current2 = current2.next;
            }
        }
        
        // Add remaining elements
        while (current1 != null) {
            result.addLast(current1.data);
            current1 = current1.next;
        }
        
        while (current2 != null) {
            result.addLast(current2.data);
            current2 = current2.next;
        }
        
        return result;
    }
    
    /**
     * Convert to array
     */
    public Object[] toArray() {
        Object[] array = new Object[size];
        ListNode<T> current = head;
        int index = 0;
        
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        
        return array;
    }
    
    /**
     * Display the list
     */
    public void display() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        
        ListNode<T> current = head;
        System.out.print("[");
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println("]");
    }
    
    @Override
    public String toString() {
        if (head == null) return "[]";
        
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        ListNode<T> current = head;
        
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        
        sb.append("]");
        return sb.toString();
    }
    
    public static void main(String[] args) {
        System.out.println("🔗 LINKED LIST DEMONSTRATION");
        System.out.println("=" .repeat(50));
        
        JavaLinkedList<Integer> list = new JavaLinkedList<>();
        
        // Basic operations
        System.out.println("1. Basic Operations:");
        list.addFirst(10);
        list.addLast(20);
        list.addLast(30);
        list.add(1, 15);
        System.out.println("After insertions: " + list);
        System.out.println("Size: " + list.size());
        
        // Access operations
        System.out.println("\n2. Access Operations:");
        System.out.println("Element at index 2: " + list.get(2));
        System.out.println("Index of 20: " + list.indexOf(20));
        System.out.println("Contains 25: " + list.contains(25));
        System.out.println("Contains 30: " + list.contains(30));
        
        // Removal operations
        System.out.println("\n3. Removal Operations:");
        System.out.println("Removed first: " + list.removeFirst());
        System.out.println("After removing first: " + list);
        System.out.println("Removed last: " + list.removeLast());
        System.out.println("After removing last: " + list);
        System.out.println("Removed at index 0: " + list.remove(0));
        System.out.println("After removing at index 0: " + list);
        
        // Advanced operations
        System.out.println("\n4. Advanced Operations:");
        
        // Rebuild list for advanced operations
        list.clear();
        for (int i = 1; i <= 5; i++) {
            list.addLast(i);
        }
        System.out.println("Original list: " + list);
        
        // Find middle
        System.out.println("Middle element: " + list.findMiddle());
        
        // Reverse
        list.reverse();
        System.out.println("After reverse: " + list);
        
        // Cycle detection
        System.out.println("Has cycle: " + list.hasCycle());
        
        // Merge sorted lists
        System.out.println("\n5. Merge Sorted Lists:");
        JavaLinkedList<Integer> list1 = new JavaLinkedList<>();
        JavaLinkedList<Integer> list2 = new JavaLinkedList<>();
        
        list1.addLast(1);
        list1.addLast(3);
        list1.addLast(5);
        
        list2.addLast(2);
        list2.addLast(4);
        list2.addLast(6);
        
        System.out.println("List 1: " + list1);
        System.out.println("List 2: " + list2);
        
        JavaLinkedList<Integer> merged = mergeSorted(list1, list2);
        System.out.println("Merged: " + merged);
        
        // Remove duplicates
        System.out.println("\n6. Remove Duplicates:");
        JavaLinkedList<Integer> duplicateList = new JavaLinkedList<>();
        int[] duplicates = {1, 1, 2, 3, 3, 3, 4, 5, 5};
        for (int val : duplicates) {
            duplicateList.addLast(val);
        }
        System.out.println("Before removing duplicates: " + duplicateList);
        duplicateList.removeDuplicatesFromSorted();
        System.out.println("After removing duplicates: " + duplicateList);
        
        // Interview Questions
        System.out.println("\n🎯 INTERVIEW QUESTIONS:");
        System.out.println("Q1: Advantages of Linked List over Array?");
        System.out.println("A1: Dynamic size, efficient insertion/deletion at beginning");
        
        System.out.println("\nQ2: Disadvantages of Linked List?");
        System.out.println("A2: No random access, extra memory for pointers, not cache-friendly");
        
        System.out.println("\nQ3: How to detect cycle in linked list?");
        System.out.println("A3: Floyd's Cycle Detection (Tortoise and Hare algorithm)");
        
        System.out.println("\nQ4: How to find middle element in one pass?");
        System.out.println("A4: Use two pointers - slow (1 step) and fast (2 steps)");
        
        System.out.println("\nQ5: Time complexity comparison:");
        System.out.println("A5: Access: Array O(1), LinkedList O(n)");
        System.out.println("    Insert/Delete at beginning: Array O(n), LinkedList O(1)");
    }
}
