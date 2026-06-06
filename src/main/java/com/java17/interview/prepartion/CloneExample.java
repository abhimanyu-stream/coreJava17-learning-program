package com.java17.interview.prepartion;

import java.io.*;

// A class with nested objects
class AddressZone implements Cloneable {
    String city;
    String country;

    public AddressZone(String city, String country) {
        this.city = city;
        this.country = country;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // Shallow clone of Address
    }
}

class Person implements Cloneable {
    String name;
    int age;
    AddressZone address;

    public Person(String name, int age, AddressZone address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person cloned = (Person) super.clone();  // Shallow clone of Person
        // Deep clone of the Address object inside Person
        cloned.address = (AddressZone) address.clone();
        return cloned;
    }
}

public class CloneExample {
    public static void main(String[] args) throws CloneNotSupportedException {
        AddressZone address1 = new AddressZone("New York", "USA");
        Person person1 = new Person("John", 30, address1);

        // Shallow Clone
        Person shallowClonedPerson = (Person) person1.clone();
        shallowClonedPerson.name = "Alice"; // Modify name in shallow clone
        shallowClonedPerson.address.city = "Los Angeles"; // Modify address in shallow clone

        // Deep Clone
        Person deepClonedPerson = (Person) person1.clone();
        deepClonedPerson.name = "Bob"; // Modify name in deep clone
        deepClonedPerson.address.city = "San Francisco"; // Modify address in deep clone

        // Print original and cloned objects to see the difference
        System.out.println("Original person: " + person1.name + " from " + person1.address.city);
        System.out.println("Shallow cloned person: " + shallowClonedPerson.name + " from " + shallowClonedPerson.address.city);
        System.out.println("Deep cloned person: " + deepClonedPerson.name + " from " + deepClonedPerson.address.city);
    }
}
