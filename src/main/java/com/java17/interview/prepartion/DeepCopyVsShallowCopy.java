package com.java17.interview.prepartion;

public class DeepCopyVsShallowCopy {

    public static void main(String[] args) {
        Address originalAddress = new Address("New York");
        Person originalPerson = new Person("John", originalAddress);

        // Shallow copy
        Person shallowCopyPerson = new Person(originalPerson.name, originalPerson.address);// storing only reference of has-a-relationship

        // Deep copy
        Address deepCopyAddress = new Address(originalPerson.address.city);// creates a new Object from actual value
        Person deepCopyPerson = new Person(originalPerson.name, deepCopyAddress);

        // Modify the city in the original address
        originalAddress.city = "Los Angeles";//it will make change
        shallowCopyPerson.address.city="California";//it will also make change

        // Displaying values
        System.out.println("Shallow Copy - Address: " + shallowCopyPerson.address.city); // Output: Los Angeles
        System.out.println("Deep Copy - Address: " + deepCopyPerson.address.city); // Output: New York


        //we can even modify deepClonedObject too.
        deepCopyPerson.name="debezium";
        deepCopyPerson.address.city="kafka";
        System.out.println("Deep Copy - Modified: " + deepCopyPerson);
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

class Person {
    String name;
    Address address;

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
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