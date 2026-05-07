package com.java17.interview.prepartion;

public class RecordAndSealed {
    public static void main(String[] args) {
        Employeey e = new Employeey(1, "Alice", 75_000);

        System.out.println(e.name());     // Alice
        System.out.println(e.salary());   // 75000.0

    }


}
 record Employeey(int id, String name, double salary) {
}

/**
 * In Java, when you declare a record like:
 *
 * record Employeey(int id, String name, double salary) {
 * }
 *
 * the compiler automatically generates several things for you behind the scenes.
 *
 * ✅ 1. Private Final Fields
 * private final int id;
 * private final String name;
 * private final double salary;
 * ✅ 2. Canonical Constructor
 *
 * A constructor with all fields:
 *
 * public Employeey(int id, String name, double salary) {
 *     this.id = id;
 *     this.name = name;
 *     this.salary = salary;
 * }
 * ✅ 3. Getter Methods (NOT getId(), but field names)
 * public int id() { return id; }
 * public String name() { return name; }
 * public double salary() { return salary; }
 *
 * 👉 Important: No getId() style — method name is exactly the field name.
 *
 * ✅ 4. toString() Implementation
 * @Override
 * public String toString() {
 *     return "Employeey[id=" + id + ", name=" + name + ", salary=" + salary + "]";
 * }
 * ✅ 5. equals() Method
 *
 * Compares all fields:
 *
 * @Override
 * public boolean equals(Object o) {
 *     // compares id, name, salary
 * }
 * ✅ 6. hashCode() Method
 *
 * Based on all fields:
 *
 * @Override
 * public int hashCode() {
 *     // generated using id, name, salary
 * }
 * 🚀 Summary (Interview One-Liner)
 *
 * 👉 A Java record automatically generates:
 *
 * Private final fields
 * Canonical constructor
 * Accessor methods (same name as fields)
 * toString(), equals(), hashCode()
 * ⚡ Bonus (Important Interview Insight)
 * Records are immutable by default
 * Cannot extend other classes (implicitly extends java.lang.Record)
 * Good for DTOs / data carriers
 *
 *
 *
 *
 *
 * No — setters are NOT generated for a record.
 *
 * ❌ Why no setters?
 *
 * Your record:
 *
 * record Employeey(int id, String name, double salary) {}
 *
 * is designed to be immutable.
 *
 * That means:
 *
 * Fields are final
 * Values are set only once (via constructor)
 * Cannot be changed later → so no setters
 * ✅ What you get instead
 *
 * You only get accessor methods (read-only):
 *
 * e.id();      // getter
 * e.name();
 * e.salary();
 * ⚡ Interview Answer
 *
 * 👉 Java records do NOT have setters because they are immutable data carriers. All fields are final and can only be initialized via the constructor.
 *
 * 🔥 If you need to "modify" data
 *
 * You create a new object, not update existing one:
 *
 * Employeey e1 = new Employeey(1, "Abhi", 50000);
 *
 * // update salary → create new object
 * Employeey e2 = new Employeey(e1.id(), e1.name(), 60000);
 * 💡 Pro Insight
 *
 * If you really need setters, then:
 * 👉 Don’t use record — use a normal class
 *
 *
 *
 *
 *
 * Here’s a clean, interview-ready breakdown of Record vs Class and the tricky “when NOT to use records” scenarios.
 *
 * 🚀 Record vs Class (Java)
 * Feature	            Record	                                        Class
 * Purpose	           Data carrier (DTO)	                     General-purpose
 * Mutability	      ❌ Immutable (fields are final)	       ✅ Mutable or immutable
 * Boilerplate	      ✅ Minimal (auto-generated)	           ❌ Manual (getters/setters/etc.)
 * Fields	          Implicit private final	               Any modifier
 * Constructor	        Auto canonical constructor	           Custom constructors
 * Getters	            id() style	                           getId() style
 * Setters	         ❌ Not allowed	                          ✅ Allowed
 * Inheritance	      ❌ Cannot extend class	                  ✅ Can extend
 * Extensibility	   Limited	                             Fully flexible
 * equals/hashCode	   Auto	                                Must implement
 * toString	           Auto	                               Must implement
 *
 *
 *
 * Interview Trick Question
 *
 * Q: Can we add methods in records?
 * 👉 YES
 *
 * Q: Should we add heavy business logic?
 * 👉 NO (bad design)
 *
 *
 */

 sealed interface Payment permits CashPayment, CardPayment, UpiPayment {


}

/**
 * Error
 *  class CashPayment(double amount) implements Payment {
 * }
 */
 record CashPayment(double amount) implements Payment {
}
 record CardPayment(double amount, String cardNumber)implements Payment {
}

 record UpiPayment(double amount, String upiId)implements Payment {
}
class PaymentProcessor {

    public static String process(Payment payment) {
        return switch (payment) {
            case CashPayment c ->
                    "Cash payment of " + c.amount();
            case CardPayment c ->
                    "Card payment of " + c.amount() +
                            " using card " + c.cardNumber();
            case UpiPayment u ->
                    "UPI payment of " + u.amount() +
                            " via " + u.upiId();
        };
    }
}

