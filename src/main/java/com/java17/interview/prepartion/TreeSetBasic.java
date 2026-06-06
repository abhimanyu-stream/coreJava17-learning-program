package com.java17.interview.prepartion;

import java.util.TreeSet;

public class TreeSetBasic {

    public static void main(String[] args) {
        // TreeSet stores elements in ascending (natural) order by default
        TreeSet<Integer> numbers = new TreeSet<>();

        numbers.add(50);
        numbers.add(10);
        numbers.add(30);
        numbers.add(20);
        numbers.add(10); // duplicate, ignored

        System.out.println("TreeSet: " + numbers); // [10, 20, 30, 50]
        System.out.println("First element: " + numbers.first());
        System.out.println("Last element: " + numbers.last());
    }
}
/**
 * Yes ✅ absolutely — there are several equivalent ways to write that compareTo() method depending on your style or what Java version you’re using.
 *
 * Let’s look at all the common variants 👇
 *
 * ✅ 1. Basic subtraction (old-school way)
 * @Override
 * public int compareTo(Employee other) {
 *     return this.id - other.id;
 * }
 *
 *
 * ⚠️ Be careful: This can cause integer overflow if id values are large (e.g., Integer.MAX_VALUE - (-1)), so it’s not recommended for production code.
 *
 * ✅ 2. Using Integer.compare() (modern & safe)
 * @Override
 * public int compareTo(Employee other) {
 *     return Integer.compare(this.id, other.id);
 * }
 *
 *
 * ✅ This is the recommended approach — it’s null-safe and avoids overflow.
 *
 * ✅ 3. Using ternary operator manually
 * @Override
 * public int compareTo(Employee other) {
 *     if (this.id == other.id)
 *         return 0;
 *     return this.id > other.id ? 1 : -1;
 * }
 *
 *
 * 🟢 Works the same, but more verbose (sometimes clearer for teaching).
 *
 * ✅ 4. Using Comparator utility (Java 8+)
 * @Override
 * public int compareTo(Employee other) {
 *     return Comparator.comparingInt(Employee::getId)
 *                      .compare(this, other);
 * }
 *
 *
 * 🟢 Very readable if you have getters and may extend to multi-field sorting easily:
 *
 * Comparator.comparingInt(Employee::getId)
 *           .thenComparing(Employee::getName);
 *
 * ✅ 5. Reverse order
 *
 * If you want descending order, you can invert the comparison:
 *
 * @Override
 * public int compareTo(Employee other) {
 *     return Integer.compare(other.id, this.id); // just swapped
 * }
 *
 * 💡 Summary
 * Style	Code	Safe?	Modern?	Comment
 * Subtraction	return this.id - other.id;	⚠️ No	❌	Can overflow
 * Integer.compare	return Integer.compare(this.id, other.id);	✅	✅	Recommended
 * Ternary	manual if-else	✅	❌	Clear but verbose
 * Comparator API	Comparator.comparingInt(...)	✅	✅✅	Great for complex sorting
 */