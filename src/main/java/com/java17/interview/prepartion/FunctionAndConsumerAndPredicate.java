package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Built-in Functional Interfaces — Complete Reference
 *
 * <pre>
 * Interface            SAM (abstract method)      Signature            Purpose
 * ─────────────────────────────────────────────────────────────────────────────
 * Predicate&lt;T&gt;         test(T t)                  T  → boolean         condition / filter
 * Function&lt;T,R&gt;        apply(T t)                 T  → R               transform
 * Consumer&lt;T&gt;          accept(T t)                T  → void            consume / print
 * Supplier&lt;T&gt;          get()                      () → T               supply / produce
 * UnaryOperator&lt;T&gt;     apply(T t)                 T  → T               same-type transform
 * BinaryOperator&lt;T&gt;    apply(T t1, T t2)          (T,T) → T            combine two → one
 * Runnable             run()                      () → void            task (no result, no throws)
 * Callable&lt;V&gt;          call() throws Exception    () → V               task (with result, throws)
 * Comparator&lt;T&gt;        compare(T o1, T o2)        (T,T) → int          ordering / sorting
 * </pre>
 */
public class FunctionAndConsumerAndPredicate {

    public static void main(String[] args) throws Exception {

        // ══════════════════════════════════════════════════════════════════════
        // 1. Predicate<T>
        //    boolean test(T t);
        //    Default methods: and(), or(), negate()
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("── Predicate ──────────────────────────────────────────");
        Predicate<Integer> isEven       = x -> x % 2 == 0;
        Predicate<Integer> isGreaterTen = x -> x > 10;

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 12, 15);
        for (Integer i : numbers) {
            System.out.println(i + " isEven=" + isEven.test(i));
        }

        Predicate<String> startsWithA = s -> s.toLowerCase().charAt(0) == 'a';
        System.out.println("startsWithA  'abhimanyu' : " + startsWithA.test("abhimanyu"));           // true
        System.out.println("negate()     'abhimanyu' : " + startsWithA.negate().test("abhimanyu"));  // false

        // and() — both must be true
        Predicate<Integer> evenAndGt10 = isEven.and(isGreaterTen);
        System.out.println("even AND >10 → 12       : " + evenAndGt10.test(12)); // true
        System.out.println("even AND >10 → 4        : " + evenAndGt10.test(4));  // false

        // or() — either must be true
        Predicate<Integer> evenOrGt10 = isEven.or(isGreaterTen);
        System.out.println("even OR  >10 → 3        : " + evenOrGt10.test(3));   // false
        System.out.println("even OR  >10 → 11       : " + evenOrGt10.test(11));  // true

        StudentKVS student = new StudentKVS("Animal");
        System.out.println("startsWithA  'Animal'   : " + startsWithA.test(student.getName()));      // true

        // ══════════════════════════════════════════════════════════════════════
        // 2. Consumer<T>
        //    void accept(T t);
        //    Default method: andThen(Consumer after)
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n── Consumer ────────────────────────────────────────────");
        Consumer<Integer> printNum    = x -> System.out.println("Value : " + x);
        Consumer<Integer> printSquare = x -> System.out.println("Square: " + (x * x));

        printNum.accept(5);    // Value : 5

        // andThen() — runs first Consumer then second
        Consumer<Integer> printBoth = printNum.andThen(printSquare);
        printBoth.accept(4);   // Value : 4  →  Square: 16

        // Method reference as Consumer
        Consumer<String> sysOut = System.out::println;
        sysOut.accept("Consumer via method reference");

        // ══════════════════════════════════════════════════════════════════════
        // 3. Supplier<T>
        //    T get();
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n── Supplier ────────────────────────────────────────────");
        Supplier<String>  greetSupplier  = () -> "Hello, World!";
        Supplier<Integer> randomSupplier = () -> (int)(Math.random() * 100);

        System.out.println("String supplier  : " + greetSupplier.get());
        System.out.println("Random supplier  : " + randomSupplier.get());

        // ══════════════════════════════════════════════════════════════════════
        // 4. Function<T, R>
        //    R apply(T t);
        //    Default methods: andThen(), compose()
        //    Static method : identity()
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n── Function ────────────────────────────────────────────");
        Function<String, Integer> strLength  = String::length;
        System.out.println("length 'sun'         : " + strLength.apply("sun"));       // 3

        Function<String, String> first3Chars = s -> s.substring(0, 3);
        System.out.println("first3 'suppliers'   : " + first3Chars.apply("suppliers")); // sup

        Function<String, String> identity = Function.identity();
        System.out.println("identity 'books'     : " + identity.apply("books"));       // books

        Function<Integer, Integer> times2 = x -> x * 2;
        Function<Integer, Integer> plus10 = x -> x + 10;

        // andThen: times2 THEN plus10  →  (5*2)+10 = 20
        System.out.println("andThen (5*2)+10     : " + times2.andThen(plus10).apply(5)); // 20
        // compose: plus10 FIRST then times2  →  (5+10)*2 = 30
        System.out.println("compose (5+10)*2     : " + times2.compose(plus10).apply(5)); // 30

        // ══════════════════════════════════════════════════════════════════════
        // 5. Chaining: Supplier → Predicate → Function → Consumer
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n── Supplier → Predicate → Function → Consumer ──────────");
        Supplier<Integer> hundredSupply = () -> 100;
        Predicate<Integer> isEvenPred   = x -> x % 2 == 0;
        Function<Integer, Integer> sq   = x -> x * x;
        Consumer<Integer> printer       = System.out::println;

        // 100 (Supplier) → even? (Predicate) → square (Function) → print (Consumer)
        if (isEvenPred.test(hundredSupply.get())) {
            printer.accept(sq.apply(hundredSupply.get())); // 10000
        }











        

        // ══════════════════════════════════════════════════════════════════════
        // 6. Function: List<Entity> → DTO
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n── Function: List<Entity> → DTO ────────────────────────");
        List<StudentKVS> students = Arrays.asList(
                new StudentKVS("Dog"), new StudentKVS("Tree"), new StudentKVS("Mountain"));

        Function<List<StudentKVS>, StudentResponse> toDto = list -> {
            StudentResponse r = new StudentResponse();
            r.setName(new ArrayList<>(list.stream().map(StudentKVS::getName).toList()));
            return r;
        };
        System.out.println("DTO names : " + toDto.apply(students).getName());

        // ══════════════════════════════════════════════════════════════════════
        // 7. Runnable
        //    void run();   — no input, no return, no checked exception
        //    Used with Thread / ExecutorService.execute()
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n── Runnable ────────────────────────────────────────────");

        // Anonymous class (old way)
        Runnable runnableAnon = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable anon class : " + Thread.currentThread().getName());
            }
        };

        // Lambda — replaces anonymous class
        Runnable runnableLambda = () ->
                System.out.println("Runnable lambda     : " + Thread.currentThread().getName());

        new Thread(runnableLambda).start();                   // via Thread

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(runnableAnon);                        // fire and forget
        executor.execute(() ->
                System.out.println("Runnable inline     : " + Thread.currentThread().getName()));
        executor.shutdown();

        // ══════════════════════════════════════════════════════════════════════
        // 8. Callable<V>
        //    V call() throws Exception;   — no input, returns V, can throw checked
        //    Used with ExecutorService.submit() → returns Future<V>
        //
        //    Runnable vs Callable
        //    Runnable → run()  → void,  no checked exception
        //    Callable → call() → V,     checked exception allowed
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n── Callable ────────────────────────────────────────────");

        // Anonymous class (old way)
        Callable<String> callableAnon = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Callable anon class result";
            }
        };

        // Lambda — replaces anonymous class
        Callable<String> callableLambda = () -> "Callable lambda result";

        // Can throw checked exception without try-catch inside the lambda
        Callable<Integer> callableWithEx = () -> Integer.parseInt("42");

        ExecutorService exec = Executors.newSingleThreadExecutor();
        System.out.println(exec.submit(callableLambda).get());               // Callable lambda result
        System.out.println(exec.submit(callableAnon).get());                 // Callable anon class result
        System.out.println("Parsed int : " + exec.submit(callableWithEx).get()); // 42
        exec.shutdown();

        // ══════════════════════════════════════════════════════════════════════
        // 9. Comparator<T>
        //    int compare(T o1, T o2);
        //    Returns: negative → o1 before o2 | 0 → equal | positive → o1 after o2
        //
        //    Default methods : reversed(), thenComparing()
        //    Static  methods : comparing(), comparingInt(), naturalOrder(), reverseOrder()
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n── Comparator — on objects ─────────────────────────────");

        List<StudentKVS> nameList = new ArrayList<>(Arrays.asList(
                new StudentKVS("Zara"),
                new StudentKVS("Alice"),
                new StudentKVS("Mia"),
                new StudentKVS("Bob")
        ));

        // ── Anonymous class (old way)
        Comparator<StudentKVS> byNameAnon = new Comparator<StudentKVS>() {
            @Override
            public int compare(StudentKVS o1, StudentKVS o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };

        // ── Lambda
        Comparator<StudentKVS> byNameLambda = (o1, o2) -> o1.getName().compareTo(o2.getName());

        // ── Method reference via Comparator.comparing() — cleanest
        Comparator<StudentKVS> byNameRef = Comparator.comparing(StudentKVS::getName);

        // Sort ascending (mutates nameList)
        nameList.sort(byNameRef);
        System.out.println("sort() asc           : " + nameList.stream().map(StudentKVS::getName).toList());
        // [Alice, Bob, Mia, Zara]

        // Sort descending with reversed()
        nameList.sort(byNameRef.reversed());
        System.out.println("sort() desc          : " + nameList.stream().map(StudentKVS::getName).toList());
        // [Zara, Mia, Bob, Alice]

        // thenComparing() — secondary sort: by name-length first, then alphabetically
        Comparator<StudentKVS> byLengthThenName = Comparator
                .comparingInt((StudentKVS s) -> s.getName().length())
                .thenComparing(StudentKVS::getName);

        nameList.sort(byLengthThenName);
        System.out.println("by len then name     : " + nameList.stream().map(StudentKVS::getName).toList());
        // [Bob, Mia, Alice, Zara]  (len 3, 3, 5, 4 → sorted: Bob/Mia/Zara/Alice)

        // Inline lambda directly in sort()
        nameList.sort((o1, o2) -> o2.getName().compareTo(o1.getName())); // descending inline
        System.out.println("sort() inline desc   : " + nameList.stream().map(StudentKVS::getName).toList());

        // ── stream().sorted() — does NOT mutate the list
        System.out.println("\n── Comparator — stream().sorted() on objects ───────────");




        // sorted by name asc via method reference
        List<String> sortedAsc = nameList.stream()
                .sorted(Comparator.comparing(StudentKVS::getName))
                .map(StudentKVS::getName)
                .toList();
        System.out.println("stream sorted asc    : " + sortedAsc);

        // sorted by name desc
        List<String> sortedDesc = nameList.stream()
                .sorted(Comparator.comparing(StudentKVS::getName).reversed())
                .map(StudentKVS::getName)
                .toList();
        System.out.println("stream sorted desc   : " + sortedDesc);

        // sorted by name length (comparingInt)
        List<String> sortedByLen = nameList.stream()
                .sorted(Comparator.comparingInt(s -> s.getName().length()))
                .map(StudentKVS::getName)
                .toList();
        System.out.println("stream sorted by len : " + sortedByLen);

        System.out.println("original (unchanged) : " + nameList.stream().map(StudentKVS::getName).toList());

        // ── Comparator.thenComparing() on stream().sorted()
        // thenComparing() is Comparator's "andThen" for sorting:
        //   primary comparator fires first; if result == 0 (tie), secondary comparator breaks it.
        //
        // Example: sort by name-length ASC, then alphabetically ASC on ties
        List<StudentKVS> nameList2 = new ArrayList<>(Arrays.asList(
                new StudentKVS("Bob"),   // len 3
                new StudentKVS("Mia"),   // len 3 — tie with Bob
                new StudentKVS("Alice"), // len 5
                new StudentKVS("Zara"),  // len 4
                new StudentKVS("Ann"),   // len 3 — tie with Bob + Mia
                new StudentKVS("Eve")    // len 3 — tie
        ));

        // Step 1: comparingInt by length  (primary)
        // Step 2: .thenComparing by name  (secondary — breaks length ties alphabetically)
        Comparator<StudentKVS> byLenThenAlpha = Comparator
                .comparingInt((StudentKVS s) -> s.getName().length())  // primary
                .thenComparing(StudentKVS::getName);                    // secondary (tie-breaker)

        List<String> sortedLenThenAlpha = nameList2.stream()
                .sorted(byLenThenAlpha)
                .map(StudentKVS::getName)
                .toList();
        System.out.println("len then alpha       : " + sortedLenThenAlpha);
        // [Ann, Bob, Eve, Mia, Zara, Alice]  — all len-3 names sorted A→Z among themselves

        // reversed() on the combined comparator — flip the entire order
        List<String> sortedLenThenAlphaDesc = nameList2.stream()
                .sorted(byLenThenAlpha.reversed())
                .map(StudentKVS::getName)
                .toList();
        System.out.println("reversed()           : " + sortedLenThenAlphaDesc);
        // [Alice, Zara, Mia, Eve, Bob, Ann]

        // Three-level chain: length → name → (could add more with another .thenComparing)
        Comparator<StudentKVS> threeLevel = Comparator
                .comparingInt((StudentKVS s) -> s.getName().length())   // 1st: by length
                .thenComparing(StudentKVS::getName)                      // 2nd: alphabetically
                .thenComparing(Comparator.comparing(StudentKVS::getName).reversed()); // 3rd: rarely needed but shows chaining
        // (3rd has no effect here since names are unique, shown only to demonstrate the pattern)






        // ── stream().sorted() on Integer list
        System.out.println("\n── Comparator — stream().sorted() on integers ──────────");

        List<Integer> ints = Arrays.asList(5, 1, 8, 3, 9, 2);

        // naturalOrder() → ascending
        List<Integer> asc = ints.stream()
                .sorted(Comparator.naturalOrder())
                .toList();
        System.out.println("naturalOrder  asc    : " + asc);   // [1, 2, 3, 5, 8, 9]

        // reverseOrder() → descending
        List<Integer> desc = ints.stream()
                .sorted(Comparator.reverseOrder())
                .toList();
        System.out.println("reverseOrder  desc   : " + desc);  // [9, 8, 5, 3, 2, 1]

        // lambda  (a - b) → ascending
        List<Integer> ascLambda = ints.stream()
                .sorted((a, b) -> a - b)
                .toList();
        System.out.println("lambda (a-b)  asc    : " + ascLambda); // [1, 2, 3, 5, 8, 9]

        // lambda  (b - a) → descending
        List<Integer> descLambda = ints.stream()
                .sorted((a, b) -> b - a)
                .toList();
        System.out.println("lambda (b-a)  desc   : " + descLambda); // [9, 8, 5, 3, 2, 1]

        // Integer::compare method reference → ascending
        List<Integer> ascRef = ints.stream()
                .sorted(Integer::compare)
                .toList();
        System.out.println("Integer::compare asc : " + ascRef);    // [1, 2, 3, 5, 8, 9]

        System.out.println("original (unchanged) : " + ints);       // [5, 1, 8, 3, 9, 2]
    }
}

// ─── Supporting classes ───────────────────────────────────────────────────────
class StudentResponse {
    private List<String> name;

    public StudentResponse() {}
    public StudentResponse(List<String> name) { this.name = name; }

    public List<String> getName()            { return name; }
    public void setName(List<String> name)   { this.name = name; }

    @Override
    public String toString() { return "StudentResponse{name=" + name + "}"; }
}

class StudentKVS {
    private final String name;

    public StudentKVS(String name) { this.name = name; }
    public String getName()        { return name; }
}
