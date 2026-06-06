package com.java17.interview.prepartion;

/**
 * ⚡ JAVA 17 JVM OPTIMIZATIONS - Variable and Value Optimizations
 */
public class Java17JVM_Optimization_VariableAndValue {

    // String interning demonstration
    public static void demonstrateStringInterning() {
        System.out.println("1. String Interning:");

        String s1 = "Hello";
        String s2 = "Hello";
        String s3 = new String("Hello");
        String s4 = s3.intern();

        System.out.println("s1 == s2: " + (s1 == s2)); // true - same reference
        System.out.println("s1 == s3: " + (s1 == s3)); // false - different objects
        System.out.println("s1 == s4: " + (s1 == s4)); // true - interned

        System.out.println("s1.equals(s3): " + s1.equals(s3)); // true - same content
    }

    // Integer caching demonstration
    public static void demonstrateIntegerCaching() {
        System.out.println("\n2. Integer Caching (-128 to 127):");

        Integer a1 = 100;
        Integer a2 = 100;
        Integer b1 = 200;
        Integer b2 = 200;

        System.out.println("Integer 100 == 100: " + (a1 == a2)); // true - cached
        System.out.println("Integer 200 == 200: " + (b1 == b2)); // false - not cached

        // Forcing new objects
        Integer c1 = new Integer(100);
        Integer c2 = new Integer(100);
        System.out.println("new Integer(100) == new Integer(100): " + (c1 == c2)); // false
    }

    // Escape analysis demonstration
    public static void demonstrateEscapeAnalysis() {
        System.out.println("\n3. Escape Analysis Optimization:");

        // Local object - may be stack allocated
        Point localPoint = new Point(10, 20);
        int sum = localPoint.x + localPoint.y;
        System.out.println("Local point sum: " + sum);

        // Object escapes - heap allocated
        Point escapedPoint = createPoint(30, 40);
        System.out.println("Escaped point: " + escapedPoint);
    }

    private static Point createPoint(int x, int y) {
        return new Point(x, y); // Object escapes method
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    // Method inlining demonstration
    public static void demonstrateMethodInlining() {
        System.out.println("\n4. Method Inlining:");

        long start = System.nanoTime();
        int sum = 0;
        for (int i = 0; i < 1000000; i++) {
            sum += simpleAdd(i, 1); // Likely to be inlined
        }
        long time = System.nanoTime() - start;

        System.out.println("Sum with method calls: " + sum);
        System.out.println("Time taken: " + time / 1000000 + " ms");
    }

    private static int simpleAdd(int a, int b) {
        return a + b; // Simple method - candidate for inlining
    }

    // Dead code elimination
    public static void demonstrateDeadCodeElimination() {
        System.out.println("\n5. Dead Code Elimination:");

        int x = 10;
        int y = 20;
        int unused = x + y; // May be eliminated if not used

        // Only this will remain after optimization
        System.out.println("Active code result: " + (x * y));
    }

    // Loop optimization
    public static void demonstrateLoopOptimization() {
        System.out.println("\n6. Loop Optimizations:");

        int[] array = new int[1000];

        // Loop unrolling candidate
        long start = System.nanoTime();
        for (int i = 0; i < array.length; i++) {
            array[i] = i * 2;
        }
        long time = System.nanoTime() - start;

        System.out.println("Array filled in: " + time + " ns");
        System.out.println("First few elements: " + array[0] + ", " + array[1] + ", " + array[2]);
    }

    // Constant folding
    public static void demonstrateConstantFolding() {
        System.out.println("\n7. Constant Folding:");

        // These will be computed at compile time
        final int a = 10;
        final int b = 20;
        int result = a + b * 2; // Becomes: int result = 50;

        System.out.println("Constant folded result: " + result);

        // Runtime computation
        int x = (int) (Math.random() * 10);
        int dynamicResult = x + b * 2;
        System.out.println("Dynamic result: " + dynamicResult);
    }

    // Branch prediction
    public static void demonstrateBranchPrediction() {
        System.out.println("\n8. Branch Prediction Impact:");

        int[] data = new int[1000000];
        for (int i = 0; i < data.length; i++) {
            data[i] = (int) (Math.random() * 256);
        }

        // Predictable branches (sorted data)
        java.util.Arrays.sort(data);
        long start = System.nanoTime();
        long sum = 0;
        for (int value : data) {
            if (value >= 128) { // Predictable after sorting
                sum += value;
            }
        }
        long sortedTime = System.nanoTime() - start;

        System.out.println("Sorted data processing time: " + sortedTime / 1000000 + " ms");
        System.out.println("Sum of values >= 128: " + sum);
    }

    // JIT compilation tiers
    public static void demonstrateJITCompilation() {
        System.out.println("\n9. JIT Compilation Demonstration:");

        // Method will be compiled after multiple invocations
        long start = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            hotMethod(i);
        }
        long time = System.nanoTime() - start;

        System.out.println("Hot method execution time: " + time / 1000000 + " ms");
    }

    private static int hotMethod(int x) {
        // This method will become "hot" and get JIT compiled
        return x * x + x + 1;
    }

    public static void main(String[] args) {
        System.out.println("⚡ JAVA 17 JVM OPTIMIZATIONS DEMONSTRATION");
        System.out.println("=".repeat(60));

        demonstrateStringInterning();
        demonstrateIntegerCaching();
        demonstrateEscapeAnalysis();
        demonstrateMethodInlining();
        demonstrateDeadCodeElimination();
        demonstrateLoopOptimization();
        demonstrateConstantFolding();
        demonstrateBranchPrediction();
        demonstrateJITCompilation();

        // JVM Flags for optimization
        System.out.println("\n🔧 Important JVM Optimization Flags:");
        System.out.println("-XX:+UseG1GC - Use G1 garbage collector");
        System.out.println("-XX:+DoEscapeAnalysis - Enable escape analysis");
        System.out.println("-XX:+UseStringDeduplication - String deduplication");
        System.out.println("-XX:+OptimizeStringConcat - Optimize string concatenation");
        System.out.println("-XX:CompileThreshold=10000 - JIT compilation threshold");

        // Interview Questions
        System.out.println("\n🎯 INTERVIEW QUESTIONS:");
        System.out.println("Q1: What is escape analysis?");
        System.out.println("A1: JVM optimization that determines if object can be stack-allocated");

        System.out.println("\nQ2: Integer caching range?");
        System.out.println("A2: -128 to 127 by default (configurable with -XX:AutoBoxCacheMax)");

        System.out.println("\nQ3: What is method inlining?");
        System.out.println("A3: JVM replaces method calls with method body for performance");

        System.out.println("\nQ4: JIT compilation tiers?");
        System.out.println("A4: C1 (client), C2 (server), interpreter");

        System.out.println("\nQ5: What optimizations does JVM perform?");
        System.out.println("A5: Inlining, escape analysis, dead code elimination, loop unrolling");
    }
}