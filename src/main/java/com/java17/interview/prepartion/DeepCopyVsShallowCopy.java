package com.java17.interview.prepartion;

public class DeepCopyVsShallowCopy {

    public static void main(String[] args) {
        Address originalAddress = new Address("New York");
        PersonZone originalPerson = new PersonZone("John", originalAddress);

        // Shallow copy
        PersonZone shallowCopyPerson = new PersonZone(originalPerson.name, originalPerson.address);// storing only reference of has-a-relationship

        // Deep copy
        Address deepCopyAddress = new Address(originalPerson.address.city);// creates a new Object from actual value
        PersonZone deepCopyPerson = new PersonZone(originalPerson.name, deepCopyAddress);

        // Modify the city in the original address
        originalAddress.city = "Los Angeles";//it will make change
        System.out.println("originalAddress  Address: " + originalAddress.address.city);


        shallowCopyPerson.address.city="California";//it will also make change
        // Displaying values
        System.out.println("Shallow Copy - Address: " + shallowCopyPerson.address.city); // Output:  California
        
          //we can even modify deepClonedObject too.
        deepCopyPerson.name="debezium";
        deepCopyPerson.address.city="kafka";
        System.out.println("Deep Copy - Modified: " + deepCopyPerson);

        System.out.println("Deep Copy - Address: " + deepCopyPerson.address.city); // Output: New York


      
    }
}
class Address {
    String city;

    public Address(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                '}';
    }
}

class PersonZone {
    String name;
    Address address;

    public PersonZone(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "PersonZone{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
/**
 * Explain the difference between deep copy and shallow copy in Java, and provide examples of scenarios where each would be appropriate.
 * Deep copy and shallow copy are two techniques used to copy objects in Java, each with its own characteristics and use cases:
 *
 * Shallow Copy:
 *
 * Shallow copy creates a new object and then copies the contents of the original object to the new object. However, if the original object contains references to other objects, only the references are copied, not the objects themselves.
 * In a shallow copy, the copied object shares the same references to objects as the original object.
 * Therefore, Changes made to the copied object’s references will affect the original object, and vice versa.[ both are dependent still]
 * Shallow copying is typically faster and requires less memory compared to deep copying.
 * Example scenario: Shallow copying is appropriate when the objects being copied do not contain nested objects or when sharing data between objects is acceptable.
 *
 *
 * Deep Copy:
 *
 * Deep copy creates a new object and then recursively copies the contents of the original object and all its nested objects. This ensures that a complete, independent copy of the original object and its nested objects is created.
 * In a deep copy, the copied object and its nested objects are completely independent of the original object. Changes made to the copied object or its nested objects do not affect the original object, and vice versa.
 * Deep copying is typically slower and requires more memory compared to shallow copying, especially for objects with complex nested structures.
 * Example scenario: Deep copying is appropriate when you need to create independent copies of objects, especially if those objects contain nested objects that need to be copied recursively.
 *
 *
 * In this example, modifying the city in the original address affects the shallow copy (since it shares the same reference), but it doesn’t affect the deep copy (since it has its own independent copy of the address).
 * <p>
 * */


/***
 * 
 * 
 * 
 * Detailed Explanation of DeepCopyVsShallowCopy.java
This is an excellent practical example that demonstrates the difference between deep and shallow copying without using the Cloneable interface. Let me break it down step by step:

Initial Setup
Address originalAddress = new Address("New York");
PersonZone originalPerson = new PersonZone("John", originalAddress);
Memory state:

originalPerson → [name="John"] ──→ originalAddress [city="New York"]
1. Shallow Copy (Line 11)
PersonZone shallowCopyPerson = new PersonZone(originalPerson.name, originalPerson.address);
What happens:

Creates a new PersonZone object
Copies the name (String is immutable, so this is safe)
Copies the reference to the same Address object
Memory state:

originalPerson     → [name="John"] ──┐
                                      ├──→ SAME Address [city="New York"]
shallowCopyPerson  → [name="John"] ──┘
Key Point: Both originalPerson and shallowCopyPerson point to the same Address object.

2. Deep Copy (Lines 14-15)
Address deepCopyAddress = new Address(originalPerson.address.city); // Creates NEW Address
PersonZone deepCopyPerson = new PersonZone(originalPerson.name, deepCopyAddress);
What happens:

Creates a new Address object with the same city value
Creates a new PersonZone object with this new address
Memory state:

originalPerson     → [name="John"] ──→ originalAddress [city="New York"]

shallowCopyPerson  → [name="John"] ──→ (points to same originalAddress)

deepCopyPerson     → [name="John"] ──→ deepCopyAddress [city="New York"] (separate object)
3. Modifying Values (Lines 18-19)
originalAddress.city = "Los Angeles";          // Changes original
shallowCopyPerson.address.city = "California"; // Changes the SAME object again
After modifications:

originalAddress (and shallowCopyPerson.address) → [city="California"]
deepCopyAddress → [city="New York"] (unchanged!)
4. Output
System.out.println("Shallow Copy - Address: " + shallowCopyPerson.address.city);
// Output: California (affected by changes!)

System.out.println("Deep Copy - Address: " + deepCopyPerson.address.city);
// Output: New York (unchanged, independent!)
5. Modifying Deep Copy (Lines 25-27)
deepCopyPerson.name = "debezium";
deepCopyPerson.address.city = "kafka";
System.out.println("Deep Copy - Modified: " + deepCopyPerson);
// Output: PersonZone{name='debezium', address=Address{city='kafka'}}
This proves: Deep copy is completely independent. You can modify it without affecting the original.

Visual Timeline of Changes
Step	Original	Shallow Copy	Deep Copy
Initial	John, New York	John, New York (same ref)	John, New York (new ref)
After originalAddress.city = "LA"	John, Los Angeles	John, Los Angeles (affected!)	John, New York (safe!)
After shallowCopy.address.city = "California"	John, California	John, California (same!)	John, New York (safe!)
After deepCopy modifications	John, California	John, California	debezium, kafka (independent!)
Key Takeaways
Shallow Copy:
✅ Fast and memory-efficient
❌ Changes to nested objects affect both original and copy
🎯 Use when: Objects have only primitive fields or immutable objects
Deep Copy:
✅ Complete independence
✅ Safe for objects with mutable nested structures
❌ Slower and uses more memory
🎯 Use when: You need truly independent copies
Comparison with Previous Example
Aspect	DeepCopyVsShallowCopy.java	DeepCloningSchool...java
Method	Manual copy via constructors	Using Cloneable interface
Approach	Explicit new object creation	clone() method
Control	More control, clearer code	More standard Java approach
Flexibility	Can customize easily	Requires implementing interface
Both achieve the same result—the first is more explicit and easier to understand, while the second follows Java's cloning convention.

 */