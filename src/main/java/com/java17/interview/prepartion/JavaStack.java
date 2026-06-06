package com.java17.interview.prepartion;

import java.util.*;

/**
 * 📚 STACK - Complete Implementation (LIFO - Last In, First Out)
 * 
 * A Stack is a linear data structure that follows LIFO principle.
 * Elements are added and removed from the same end (top).
 * 
 * Time Complexities:
 * - Push: O(1), Pop: O(1), Peek: O(1), Search: O(n)
 * 
 * Applications: Function calls, expression evaluation, undo operations
 */
public class JavaStack<T> {
    
    // Array-based implementation
    private Object[] stackArray;
    private int top;
    private int capacity;
    
    // Default capacity
    private static final int DEFAULT_CAPACITY = 10;
    
    /**
     * Constructor with default capacity
     */
    public JavaStack() {
        this(DEFAULT_CAPACITY);
    }
    
    /**
     * Constructor with specified capacity
     */
    public JavaStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.capacity = capacity;
        this.stackArray = new Object[capacity];
        this.top = -1;
    }
    
    /**
     * Push element onto stack
     * Time: O(1)
     */
    public void push(T item) {
        if (isFull()) {
            resize();
        }
        stackArray[++top] = item;
    }
    
    /**
     * Pop element from stack
     * Time: O(1)
     */
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        T item = (T) stackArray[top];
        stackArray[top--] = null; // Help GC
        return item;
    }
    
    /**
     * Peek at top element without removing
     * Time: O(1)
     */
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return (T) stackArray[top];
    }
    
    /**
     * Check if stack is empty
     * Time: O(1)
     */
    public boolean isEmpty() {
        return top == -1;
    }
    
    /**
     * Check if stack is full
     * Time: O(1)
     */
    public boolean isFull() {
        return top == capacity - 1;
    }
    
    /**
     * Get current size of stack
     * Time: O(1)
     */
    public int size() {
        return top + 1;
    }
    
    /**
     * Search for element (returns distance from top)
     * Time: O(n)
     */
    public int search(T item) {
        for (int i = top; i >= 0; i--) {
            if (Objects.equals(stackArray[i], item)) {
                return top - i + 1; // Distance from top (1-based)
            }
        }
        return -1; // Not found
    }
    
    /**
     * Clear the stack
     * Time: O(n)
     */
    public void clear() {
        for (int i = 0; i <= top; i++) {
            stackArray[i] = null;
        }
        top = -1;
    }
    
    /**
     * Resize array when full
     */
    private void resize() {
        capacity *= 2;
        stackArray = Arrays.copyOf(stackArray, capacity);
    }
    
    /**
     * Convert stack to array (bottom to top)
     */
    public Object[] toArray() {
        Object[] result = new Object[size()];
        for (int i = 0; i <= top; i++) {
            result[i] = stackArray[i];
        }
        return result;
    }
    
    /**
     * Display stack contents
     */
    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        
        System.out.print("Stack (top to bottom): ");
        for (int i = top; i >= 0; i--) {
            System.out.print(stackArray[i]);
            if (i > 0) System.out.print(" -> ");
        }
        System.out.println();
    }
    
    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i <= top; i++) {
            sb.append(stackArray[i]);
            if (i < top) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
    
    // ==================== STACK APPLICATIONS ====================
    
    /**
     * Check if parentheses are balanced
     * Time: O(n), Space: O(n)
     */
    public static boolean isBalancedParentheses(String expression) {
        JavaStack<Character> stack = new JavaStack<>();
        
        for (char ch : expression.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) return false;
                
                char top = stack.pop();
                if (!isMatchingPair(top, ch)) {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
    }
    
    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '[' && close == ']') ||
               (open == '{' && close == '}');
    }
    
    /**
     * Evaluate postfix expression
     * Time: O(n), Space: O(n)
     */
    public static int evaluatePostfix(String expression) {
        JavaStack<Integer> stack = new JavaStack<>();
        String[] tokens = expression.split(" ");
        
        for (String token : tokens) {
            if (isOperator(token)) {
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                int result = performOperation(operand1, operand2, token);
                stack.push(result);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        
        return stack.pop();
    }
    
    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || 
               token.equals("*") || token.equals("/");
    }
    
    private static int performOperation(int a, int b, String operator) {
        switch (operator) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return a / b;
            default: throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
    
    /**
     * Convert infix to postfix
     * Time: O(n), Space: O(n)
     */
    public static String infixToPostfix(String infix) {
        JavaStack<Character> stack = new JavaStack<>();
        StringBuilder result = new StringBuilder();
        
        for (char ch : infix.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                result.append(ch).append(" ");
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop()).append(" ");
                }
                stack.pop(); // Remove '('
            } else if (isOperatorChar(ch)) {
                while (!stack.isEmpty() && precedence(ch) <= precedence(stack.peek())) {
                    result.append(stack.pop()).append(" ");
                }
                stack.push(ch);
            }
        }
        
        while (!stack.isEmpty()) {
            result.append(stack.pop()).append(" ");
        }
        
        return result.toString().trim();
    }
    
    private static boolean isOperatorChar(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }
    
    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }
    
    /**
     * Next Greater Element
     * Time: O(n), Space: O(n)
     */
    public static int[] nextGreaterElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        JavaStack<Integer> stack = new JavaStack<>();
        
        // Initialize result with -1
        Arrays.fill(result, -1);
        
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                result[stack.pop()] = nums[i];
            }
            stack.push(i);
        }
        
        return result;
    }
    
    /**
     * Largest Rectangle in Histogram
     * Time: O(n), Space: O(n)
     */
    public static int largestRectangleArea(int[] heights) {
        JavaStack<Integer> stack = new JavaStack<>();
        int maxArea = 0;
        int n = heights.length;
        
        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];
            
            while (!stack.isEmpty() && heights[stack.peek()] > currentHeight) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            
            stack.push(i);
        }
        
        return maxArea;
    }
    
    public static void main(String[] args) {
        System.out.println("📚 STACK DEMONSTRATION");
        System.out.println("=" .repeat(50));
        
        // Basic stack operations
        System.out.println("1. Basic Stack Operations:");
        JavaStack<Integer> stack = new JavaStack<>();
        
        // Push operations
        System.out.println("Pushing elements: 10, 20, 30, 40, 50");
        for (int i = 1; i <= 5; i++) {
            stack.push(i * 10);
        }
        
        System.out.println("Stack: " + stack);
        System.out.println("Size: " + stack.size());
        stack.display();
        
        // Peek and Pop operations
        System.out.println("\n2. Peek and Pop Operations:");
        System.out.println("Peek: " + stack.peek());
        System.out.println("Pop: " + stack.pop());
        System.out.println("Pop: " + stack.pop());
        System.out.println("After popping: " + stack);
        
        // Search operation
        System.out.println("\n3. Search Operations:");
        System.out.println("Search 30: " + stack.search(30));
        System.out.println("Search 60: " + stack.search(60));
        
        // Stack Applications
        System.out.println("\n4. Stack Applications:");
        
        // Balanced Parentheses
        System.out.println("\n4.1 Balanced Parentheses:");
        String[] expressions = {
            "((()))",
            "([{}])",
            "((())",
            "([)]"
        };
        
        for (String expr : expressions) {
            System.out.println(expr + " is balanced: " + isBalancedParentheses(expr));
        }
        
        // Postfix Evaluation
        System.out.println("\n4.2 Postfix Expression Evaluation:");
        String postfix = "2 3 1 * + 9 -";
        System.out.println("Postfix: " + postfix);
        System.out.println("Result: " + evaluatePostfix(postfix));
        
        // Infix to Postfix
        System.out.println("\n4.3 Infix to Postfix Conversion:");
        String infix = "a+b*(c^d-e)^(f+g*h)-i";
        System.out.println("Infix: " + infix);
        System.out.println("Postfix: " + infixToPostfix(infix));
        
        // Next Greater Element
        System.out.println("\n4.4 Next Greater Element:");
        int[] nums = {4, 5, 2, 25, 7, 8};
        int[] nge = nextGreaterElement(nums);
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("Next Greater: " + Arrays.toString(nge));
        
        // Largest Rectangle in Histogram
        System.out.println("\n4.5 Largest Rectangle in Histogram:");
        int[] heights = {2, 1, 5, 6, 2, 3};
        int maxArea = largestRectangleArea(heights);
        System.out.println("Heights: " + Arrays.toString(heights));
        System.out.println("Largest Rectangle Area: " + maxArea);
        
        // Interview Questions
        System.out.println("\n🎯 INTERVIEW QUESTIONS:");
        System.out.println("Q1: What is LIFO principle?");
        System.out.println("A1: Last In, First Out - last element added is first to be removed");
        
        System.out.println("\nQ2: Stack vs Queue difference?");
        System.out.println("A2: Stack: LIFO, Queue: FIFO (First In, First Out)");
        
        System.out.println("\nQ3: Applications of Stack?");
        System.out.println("A3: 1) Function call management");
        System.out.println("    2) Expression evaluation");
        System.out.println("    3) Undo operations");
        System.out.println("    4) Browser back button");
        System.out.println("    5) Balanced parentheses checking");
        
        System.out.println("\nQ4: Stack implementation options?");
        System.out.println("A4: 1) Array-based (fixed/dynamic size)");
        System.out.println("    2) Linked List-based (dynamic size)");
        
        System.out.println("\nQ5: Time complexities?");
        System.out.println("A5: Push/Pop/Peek: O(1), Search: O(n)");
    }
}
