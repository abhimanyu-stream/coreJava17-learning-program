package com.java17.interview.prepartion;

import java.util.*;

/**
 * 🚶‍♂️ QUEUE - Complete Implementation (FIFO - First In, First Out)
 * 
 * A Queue is a linear data structure that follows FIFO principle.
 * Elements are added at rear and removed from front.
 * 
 * Time Complexities:
 * - Enqueue: O(1), Dequeue: O(1), Front: O(1), Search: O(n)
 * 
 * Applications: BFS, CPU scheduling, handling requests, print queue
 */
public class JavaQueue<T> {
    
    // Array-based circular queue implementation
    private Object[] queueArray;
    private int front;
    private int rear;
    private int size;
    private int capacity;
    
    // Default capacity
    private static final int DEFAULT_CAPACITY = 10;
    
    /**
     * Constructor with default capacity
     */
    public JavaQueue() {
        this(DEFAULT_CAPACITY);
    }
    
    /**
     * Constructor with specified capacity
     */
    public JavaQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.capacity = capacity;
        this.queueArray = new Object[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }
    
    /**
     * Add element to rear of queue
     * Time: O(1)
     */
    public void enqueue(T item) {
        if (isFull()) {
            resize();
        }
        rear = (rear + 1) % capacity;
        queueArray[rear] = item;
        size++;
    }
    
    /**
     * Remove element from front of queue
     * Time: O(1)
     */
    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        T item = (T) queueArray[front];
        queueArray[front] = null; // Help GC
        front = (front + 1) % capacity;
        size--;
        return item;
    }
    
    /**
     * Peek at front element without removing
     * Time: O(1)
     */
    @SuppressWarnings("unchecked")
    public T front() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return (T) queueArray[front];
    }
    
    /**
     * Peek at rear element
     * Time: O(1)
     */
    @SuppressWarnings("unchecked")
    public T rear() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return (T) queueArray[rear];
    }
    
    /**
     * Check if queue is empty
     * Time: O(1)
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Check if queue is full
     * Time: O(1)
     */
    public boolean isFull() {
        return size == capacity;
    }
    
    /**
     * Get current size of queue
     * Time: O(1)
     */
    public int size() {
        return size;
    }
    
    /**
     * Get capacity of queue
     * Time: O(1)
     */
    public int capacity() {
        return capacity;
    }
    
    /**
     * Search for element
     * Time: O(n)
     */
    public boolean contains(T item) {
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            if (Objects.equals(queueArray[index], item)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Clear the queue
     * Time: O(n)
     */
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            queueArray[i] = null;
        }
        front = 0;
        rear = -1;
        size = 0;
    }
    
    /**
     * Resize array when full
     */
    private void resize() {
        Object[] newArray = new Object[capacity * 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = queueArray[(front + i) % capacity];
        }
        queueArray = newArray;
        front = 0;
        rear = size - 1;
        capacity *= 2;
    }
    
    /**
     * Convert queue to array (front to rear)
     */
    public Object[] toArray() {
        Object[] result = new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = queueArray[(front + i) % capacity];
        }
        return result;
    }
    
    /**
     * Display queue contents
     */
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        
        System.out.print("Queue (front to rear): ");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            System.out.print(queueArray[index]);
            if (i < size - 1) System.out.print(" -> ");
        }
        System.out.println();
    }
    
    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            sb.append(queueArray[index]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
    
    // ==================== PRIORITY QUEUE IMPLEMENTATION ====================
    
    /**
     * Priority Queue using Binary Heap
     */
    public static class PriorityQueue<T extends Comparable<T>> {
        private List<T> heap;
        private boolean isMaxHeap;
        
        public PriorityQueue() {
            this(false); // Min heap by default
        }
        
        public PriorityQueue(boolean isMaxHeap) {
            this.heap = new ArrayList<>();
            this.isMaxHeap = isMaxHeap;
        }
        
        public void offer(T item) {
            heap.add(item);
            heapifyUp(heap.size() - 1);
        }
        
        public T poll() {
            if (isEmpty()) throw new RuntimeException("Priority queue is empty");
            
            T root = heap.get(0);
            T lastElement = heap.remove(heap.size() - 1);
            
            if (!isEmpty()) {
                heap.set(0, lastElement);
                heapifyDown(0);
            }
            
            return root;
        }
        
        public T peek() {
            if (isEmpty()) throw new RuntimeException("Priority queue is empty");
            return heap.get(0);
        }
        
        public boolean isEmpty() {
            return heap.isEmpty();
        }
        
        public int size() {
            return heap.size();
        }
        
        private void heapifyUp(int index) {
            while (index > 0) {
                int parentIndex = (index - 1) / 2;
                if (!shouldSwap(index, parentIndex)) break;
                
                Collections.swap(heap, index, parentIndex);
                index = parentIndex;
            }
        }
        
        private void heapifyDown(int index) {
            while (true) {
                int leftChild = 2 * index + 1;
                int rightChild = 2 * index + 2;
                int targetIndex = index;
                
                if (leftChild < heap.size() && shouldSwap(leftChild, targetIndex)) {
                    targetIndex = leftChild;
                }
                
                if (rightChild < heap.size() && shouldSwap(rightChild, targetIndex)) {
                    targetIndex = rightChild;
                }
                
                if (targetIndex == index) break;
                
                Collections.swap(heap, index, targetIndex);
                index = targetIndex;
            }
        }
        
        private boolean shouldSwap(int childIndex, int parentIndex) {
            int comparison = heap.get(childIndex).compareTo(heap.get(parentIndex));
            return isMaxHeap ? comparison > 0 : comparison < 0;
        }
        
        @Override
        public String toString() {
            return heap.toString();
        }
    }
    
    // ==================== QUEUE APPLICATIONS ====================
    
    /**
     * BFS using Queue
     */
    public static void bfsDemo() {
        // Simple graph representation
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(0, 3, 4));
        graph.put(2, Arrays.asList(0, 5, 6));
        graph.put(3, Arrays.asList(1));
        graph.put(4, Arrays.asList(1));
        graph.put(5, Arrays.asList(2));
        graph.put(6, Arrays.asList(2));
        
        JavaQueue<Integer> queue = new JavaQueue<>();
        Set<Integer> visited = new HashSet<>();
        List<Integer> bfsResult = new ArrayList<>();
        
        queue.enqueue(0);
        visited.add(0);
        
        while (!queue.isEmpty()) {
            int current = queue.dequeue();
            bfsResult.add(current);
            
            for (int neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.enqueue(neighbor);
                }
            }
        }
        
        System.out.println("BFS traversal: " + bfsResult);
    }
    
    /**
     * Generate binary numbers from 1 to n using queue
     */
    public static List<String> generateBinaryNumbers(int n) {
        List<String> result = new ArrayList<>();
        JavaQueue<String> queue = new JavaQueue<>();
        
        queue.enqueue("1");
        
        for (int i = 0; i < n; i++) {
            String current = queue.dequeue();
            result.add(current);
            
            queue.enqueue(current + "0");
            queue.enqueue(current + "1");
        }
        
        return result;
    }
    
    /**
     * First non-repeating character in stream
     */
    public static class FirstNonRepeatingChar {
        private JavaQueue<Character> queue;
        private Map<Character, Integer> frequency;
        
        public FirstNonRepeatingChar() {
            queue = new JavaQueue<>();
            frequency = new HashMap<>();
        }
        
        public char addChar(char ch) {
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
            queue.enqueue(ch);
            
            while (!queue.isEmpty() && frequency.get(queue.front()) > 1) {
                queue.dequeue();
            }
            
            return queue.isEmpty() ? '#' : queue.front();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("🚶‍♂️ QUEUE DEMONSTRATION");
        System.out.println("=" .repeat(50));
        
        // Basic queue operations
        System.out.println("1. Basic Queue Operations:");
        JavaQueue<Integer> queue = new JavaQueue<>();
        
        // Enqueue operations
        System.out.println("Enqueuing elements: 10, 20, 30, 40, 50");
        for (int i = 1; i <= 5; i++) {
            queue.enqueue(i * 10);
        }
        
        System.out.println("Queue: " + queue);
        System.out.println("Size: " + queue.size());
        queue.display();
        
        // Front and Rear operations
        System.out.println("\n2. Front and Rear Operations:");
        System.out.println("Front: " + queue.front());
        System.out.println("Rear: " + queue.rear());
        
        // Dequeue operations
        System.out.println("\n3. Dequeue Operations:");
        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println("After dequeuing: " + queue);
        
        // Search operation
        System.out.println("\n4. Search Operations:");
        System.out.println("Contains 30: " + queue.contains(30));
        System.out.println("Contains 60: " + queue.contains(60));
        
        // Priority Queue
        System.out.println("\n5. Priority Queue (Min Heap):");
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        int[] priorities = {30, 10, 50, 20, 40};
        System.out.println("Adding elements: " + Arrays.toString(priorities));
        for (int val : priorities) {
            pq.offer(val);
        }
        
        System.out.println("Priority Queue: " + pq);
        System.out.println("Polling elements in priority order:");
        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
        System.out.println();
        
        // Queue Applications
        System.out.println("\n6. Queue Applications:");
        
        // BFS Demo
        System.out.println("\n6.1 Breadth-First Search:");
        bfsDemo();
        
        // Binary Numbers Generation
        System.out.println("\n6.2 Generate Binary Numbers:");
        List<String> binaryNumbers = generateBinaryNumbers(10);
        System.out.println("First 10 binary numbers: " + binaryNumbers);
        
        // First Non-Repeating Character
        System.out.println("\n6.3 First Non-Repeating Character in Stream:");
        FirstNonRepeatingChar fnrc = new FirstNonRepeatingChar();
        String stream = "aabccxb";
        System.out.print("Stream: ");
        for (char ch : stream.toCharArray()) {
            char result = fnrc.addChar(ch);
            System.out.print(ch + ":" + result + " ");
        }
        System.out.println();
        
        // Circular Queue Demo
        System.out.println("\n7. Circular Queue Behavior:");
        JavaQueue<String> circularQueue = new JavaQueue<>(3);
        
        circularQueue.enqueue("A");
        circularQueue.enqueue("B");
        circularQueue.enqueue("C");
        System.out.println("Full queue: " + circularQueue);
        
        System.out.println("Dequeue: " + circularQueue.dequeue());
        circularQueue.enqueue("D");
        System.out.println("After circular operation: " + circularQueue);
        
        // Interview Questions
        System.out.println("\n🎯 INTERVIEW QUESTIONS:");
        System.out.println("Q1: What is FIFO principle?");
        System.out.println("A1: First In, First Out - first element added is first to be removed");
        
        System.out.println("\nQ2: Queue vs Stack difference?");
        System.out.println("A2: Queue: FIFO, Stack: LIFO (Last In, First Out)");
        
        System.out.println("\nQ3: Types of Queues?");
        System.out.println("A3: 1) Simple Queue");
        System.out.println("    2) Circular Queue");
        System.out.println("    3) Priority Queue");
        System.out.println("    4) Double-ended Queue (Deque)");
        
        System.out.println("\nQ4: Applications of Queue?");
        System.out.println("A4: 1) BFS traversal");
        System.out.println("    2) CPU scheduling");
        System.out.println("    3) Handling requests in web servers");
        System.out.println("    4) Print queue management");
        System.out.println("    5) Buffer for data streams");
        
        System.out.println("\nQ5: Circular Queue advantages?");
        System.out.println("A5: Efficient memory utilization, no shifting required");
        
        System.out.println("\nQ6: Time complexities?");
        System.out.println("A6: Enqueue/Dequeue/Front: O(1), Search: O(n)");
    }
}
