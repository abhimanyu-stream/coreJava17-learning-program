package com.java17.interview.prepartion;

/**
 * ⚡ JAVA 8 JVM OPTIMIZATIONS - Variable and Value Optimizations
 */
public class Java8JVM_Optimization_VariableAndValue {
    
    // String interning in Java 8
    public static void demonstrateStringInterning() {
        System.out.println("1. String Interning in Java 8:");
        
        String s1 = "Hello";
        String s2 = "Hello";
        String s3 = new String("Hello");
        String s4 = s3.intern();
        
        System.out.println("s1 == s2: " + (s1 == s2)); // true
        System.out.println("s1 == s3: " + (s1 == s3)); // false
        System.out.println("s1 == s4: " + (s1 == s4)); // true
        
        // Java 8: String deduplication (G1GC)
        System.out.println("String deduplication helps reduce memory usage");
    }
    
    // Integer caching
    public static void demonstrateIntegerCaching() {
        System.out.println("\n2. Integer Caching (-128 to 127):");
        
        Integer a1 = 100;
        Integer a2 = 100;
        Integer b1 = 200;
        Integer b2 = 200;
        
        System.out.println("Integer 100 == 100: " + (a1 == a2)); // true
        System.out.println("Integer 200 == 200: " + (b1 == b2)); // false
        
        // Autoboxing optimization
        System.out.println("Autoboxing uses cache for -128 to 127");
    }
    
    // Method inlining demonstration
    public static void demonstrateMethodInlining() {
        System.out.println("\n3. Method Inlining in Java 8:");
        
        long start = System.nanoTime();
        int sum = 0;
        for (int i = 0; i < 1000000; i++) {
            sum += add(i, 1); // Small method - candidate for inlining
        }
        long time = System.nanoTime() - start;
        
        System.out.println("Sum: " + sum);
        System.out.println("Time: " + time / 1000000 + " ms");
        System.out.println("JVM likely inlined the add() method");
    }
    
    private static int add(int a, int b) {
        return a + b;
    }
    
    // Escape analysis
    public static void demonstrateEscapeAnalysis() {
        System.out.println("\n4. Escape Analysis:");
        
        // Local object - may be stack allocated
        Point localPoint = new Point(10, 20);
        int sum = localPoint.x + localPoint.y;
        System.out.println("Local point sum: " + sum);
        
        // Object escapes - heap allocated
        Point escapedPoint = createPoint(30, 40);
        System.out.println("Escaped point: " + escapedPoint);
    }
    
    private static Point createPoint(int x, int y) {
        return new Point(x, y);
    }
    
    static class Point {
        int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }
        @Override
        public String toString() { return "(" + x + "," + y + ")"; }
    }
    
    // Dead code elimination
    public static void demonstrateDeadCodeElimination() {
        System.out.println("\n5. Dead Code Elimination:");
        
        int x = 10;
        int y = 20;
        @SuppressWarnings("unused")
        int unused = x + y; // May be eliminated
        
        System.out.println("Result: " + (x * y));
        System.out.println("Unused variable may be eliminated by JIT");
    }
    
    // Loop optimization
    public static void demonstrateLoopOptimization() {
        System.out.println("\n6. Loop Optimizations:");
        
        int[] array = new int[1000];
        
        // Loop unrolling and vectorization
        long start = System.nanoTime();
        for (int i = 0; i < array.length; i++) {
            array[i] = i * 2;
        }
        long time = System.nanoTime() - start;
        
        System.out.println("Array filled in: " + time + " ns");
        System.out.println("JVM may unroll this loop for better performance");
    }
    
    // Constant folding
    public static void demonstrateConstantFolding() {
        System.out.println("\n7. Constant Folding:");
        
        final int a = 10;
        final int b = 20;
        int result = a + b * 2; // Computed at compile time
        
        System.out.println("Constant folded result: " + result);
        
        // Runtime computation
        int x = (int) (Math.random() * 10);
        int dynamicResult = x + b * 2;
        System.out.println("Dynamic result: " + dynamicResult);
    }
    
    // JIT compilation demonstration
    public static void demonstrateJITCompilation() {
        System.out.println("\n8. JIT Compilation:");
        
        // Warm up the method
        for (int i = 0; i < 10000; i++) {
            hotMethod(i);
        }
        
        // Measure performance after JIT compilation
        long start = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            hotMethod(i);
        }
        long time = System.nanoTime() - start;
        
        System.out.println("Hot method execution time: " + time / 1000000 + " ms");
        System.out.println("Method likely JIT compiled after warm-up");
    }
    
    private static int hotMethod(int x) {
        return x * x + x + 1;
    }
    
    // Lambda optimization in Java 8
    public static void demonstrateLambdaOptimization() {
        System.out.println("\n9. Lambda Optimization in Java 8:");
        
        // Stateless lambda - may be cached
        @SuppressWarnings("unused")
        Runnable r1 = () -> System.out.println("Stateless lambda");
        @SuppressWarnings("unused")
        Runnable r2 = () -> System.out.println("Stateless lambda");
        
        System.out.println("Stateless lambdas may be cached and reused");
        
        // Capturing lambda - new instance each time
        int value = 42;
        @SuppressWarnings("unused")
        Runnable r3 = () -> System.out.println("Capturing: " + value);
        
        System.out.println("Capturing lambdas create new instances");
    }
    
    // Compressed OOPs (Ordinary Object Pointers)
    public static void demonstrateCompressedOOPs() {
        System.out.println("\n10. Compressed OOPs:");
        
        Object[] objects = new Object[1000];
        for (int i = 0; i < objects.length; i++) {
            objects[i] = new Object();
        }
        
        System.out.println("Created " + objects.length + " objects");
        System.out.println("With CompressedOOPs, object references use 32-bit instead of 64-bit");
        System.out.println("Enabled by default on 64-bit JVMs with heap < 32GB");
    }
    
    public static void main(String[] args) {
        System.out.println("⚡ JAVA 8 JVM OPTIMIZATIONS DEMONSTRATION");
        System.out.println("=" .repeat(60));
        
        demonstrateStringInterning();
        demonstrateIntegerCaching();
        demonstrateMethodInlining();
        demonstrateEscapeAnalysis();
        demonstrateDeadCodeElimination();
        demonstrateLoopOptimization();
        demonstrateConstantFolding();
        demonstrateJITCompilation();
        demonstrateLambdaOptimization();
        demonstrateCompressedOOPs();
        
        // Java 8 specific JVM improvements
        System.out.println("\n🚀 Java 8 JVM Improvements:");
        System.out.println("- Metaspace replaces PermGen");
        System.out.println("- G1GC improvements and string deduplication");
        System.out.println("- Lambda and method handle optimizations");
        System.out.println("- Improved escape analysis");
        System.out.println("- Better tiered compilation");
        
        // JVM Flags
        System.out.println("\n🔧 Important Java 8 JVM Flags:");
        System.out.println("-XX:+UseG1GC - Use G1 garbage collector");
        System.out.println("-XX:+UseStringDeduplication - String deduplication (G1)");
        System.out.println("-XX:+DoEscapeAnalysis - Enable escape analysis");
        System.out.println("-XX:+UseCompressedOops - Use compressed object pointers");
        System.out.println("-XX:CompileThreshold=10000 - JIT compilation threshold");
        
        // Interview Questions
        System.out.println("\n🎯 INTERVIEW QUESTIONS:");
        System.out.println("Q1: What replaced PermGen in Java 8?");
        System.out.println("A1: Metaspace - native memory area for class metadata");
        
        System.out.println("\nQ2: Integer caching range in Java 8?");
        System.out.println("A2: -128 to 127 by default");
        
        System.out.println("\nQ3: What is escape analysis?");
        System.out.println("A3: JVM optimization to determine object allocation location");
        
        System.out.println("\nQ4: Lambda optimization in Java 8?");
        System.out.println("A4: Stateless lambdas cached, method handles used for invocation");
        
        System.out.println("\nQ5: G1GC string deduplication?");
        System.out.println("A5: Removes duplicate String objects to save memory");
    }
}
