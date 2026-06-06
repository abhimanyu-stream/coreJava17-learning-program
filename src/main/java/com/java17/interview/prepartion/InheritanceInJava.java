package com.java17.interview.prepartion;

public class InheritanceInJava {
    public static void main(String[] args) {

        //1. Single Inheritance
        //Single inheritance occurs when a class inherits from one superclass.
        Dogx dog = new Dogx();
        dog.eat();    // Inherited from Animal
        dog.bark();   // Defined in Dog

        //Explanation:
        //Here, Dog inherits from Animal, establishing a single-level inheritance chain.



        //2. Multilevel Inheritance
        //Multilevel inheritance occurs when a class is derived from a class that is also derived from another class, forming a chain.
        Bulldog bulldog = new Bulldog();
        bulldog.eat();    // Inherited from Animal
        bulldog.bark();   // Inherited from Dog
        bulldog.growl();  // Defined in Bulldog

        //Explanation:
        //Here, Bulldog inherits from Dog, which in turn inherits from Animal, creating a multilevel inheritance chain.



       // 3. Hierarchical Inheritance
       // Hierarchical inheritance occurs when multiple classes inherit from a single superclass.

        Dogc dogc = new Dogc();
        dogc.eat();   // Inherited from Animal
        dogc.bark();  // Defined in Dog

        Catc catc = new Catc();
        catc.eat();   // Inherited from Animal
        catc.meow();  // Defined in Cat

        //Explanation:
        //In this example, Dog and Cat both inherit from the Animal class, sharing a common superclass. This structure is called hierarchical inheritance.




        //Example of Multiple Inheritance with Interfaces
        Document doc = new Document();
        doc.print();
        doc.show();

    }


}
class Animalx {
    void eat() {
        System.out.println("Eating...");
    }
}

class Dogx extends Animalx {
    void bark() {
        System.out.println("Barking...");
    }
}
/**
 * In Java, inheritance allows one class to inherit the fields and methods of another class. However, Java doesn’t support all types of inheritance. Here’s an overview:
 *
 * Types of Inheritance in Java
 * Single Inheritance
 * Multilevel Inheritance
 * Hierarchical Inheritance
 * Types of Inheritance Java Does Not Support
 * Multiple Inheritance (Using Classes)
 * Let’s go into details with examples for each type and understand why Java restricts certain forms of inheritance.
 *
 *
 * 1. Single Inheritance
 * Single inheritance occurs when a class inherits from one superclass.
 * */



//2. Multilevel Inheritance
//Multilevel inheritance occurs when a class is derived from a class that is also derived from another class, forming a chain.

class Animaly {
    void eat() {
        System.out.println("Eating...");
    }
}

class Dogy extends Animaly {
    void bark() {
        System.out.println("Barking...");
    }
}

class Bulldog extends Dogy {
    void growl() {
        System.out.println("Growling...");
    }
}



//3. Hierarchical Inheritance
 //Hierarchical inheritance occurs when multiple classes inherit from a single superclass.
class Animalc {
    void eat() {
        System.out.println("Eating...");
    }
}

class Dogc extends Animalc {
    void bark() {
        System.out.println("Barking...");
    }
}

class Catc extends Animalc {
    void meow() {
        System.out.println("Meowing...");
    }
}




//The Diamond Problem
//The Diamond Problem arises when two superclasses have a method with the same signature, and a subclass inherits from both. This can create ambiguity as to which superclass method the subclass should inherit.

//Example (If Java Supported Multiple Inheritance)

class Aa {
    void show() {
        System.out.println("Class A");
    }
}

class Bb {
    void show() {
        System.out.println("Class B");
    }
}

// This would cause ambiguity if C inherits from both A and B


//class Cc extends Aa, Bb {
// Which show() method would be inherited here?

//In the example above, if Java allowed multiple inheritance, C would inherit from both A and B. If both A and B have a show() method, Java would face ambiguity as to which show() method should be called from C.

//}


//Solution in Java: Using Interfaces
//Java provides interfaces as a way to implement multiple inheritance. Since interfaces only contain method declarations (and optionally default or static methods), there is no ambiguity. A class can implement multiple interfaces, thus achieving multiple inheritance of behavior.
//Example of Multiple Inheritance with Interfaces

interface Printable {
    void print();
}

interface Showable {
    void show();
}

class Document implements Printable, Showable {
    public void print() {
        System.out.println("Printing document...");
    }

    public void show() {
        System.out.println("Showing document...");
    }
}


    /**
     *
     * Why Java Doesn’t Support Multiple Inheritance Using Classes
     * Multiple inheritance is a type of inheritance where a class can inherit from more than one superclass. Java does not support multiple inheritance using classes to avoid the Diamond Problem.
     *
     * The Diamond Problem
     * The Diamond Problem arises when two superclasses have a method with the same signature, and a subclass inherits from both. This can create ambiguity as to which superclass method the subclass should inherit.
     *
     * Example (If Java Supported Multiple Inheritance)
     * java
     * Copy code
     * class A {
     *     void show() {
     *         System.out.println("Class A");
     *     }
     * }
     *
     * class B {
     *     void show() {
     *         System.out.println("Class B");
     *     }
     * }
     *
     * // This would cause ambiguity if C inherits from both A and B
     * class C extends A, B {
     *     // Which show() method would be inherited here?
     * }
     * In the example above, if Java allowed multiple inheritance, C would inherit from both A and B. If both A and B have a show() method, Java would face ambiguity as to which show() method should be called from C.
     *
     * Solution in Java: Using Interfaces
     * Java provides interfaces as a way to implement multiple inheritance. Since interfaces only contain method declarations (and optionally default or static methods), there is no ambiguity. A class can implement multiple interfaces, thus achieving multiple inheritance of behavior.
     *
     * Example of Multiple Inheritance with Interfaces
     * java
     * Copy code
     * interface Printable {
     *     void print();
     * }
     *
     * interface Showable {
     *     void show();
     * }
     *
     * class Document implements Printable, Showable {
     *     public void print() {
     *         System.out.println("Printing document...");
     *     }
     *
     *     public void show() {
     *         System.out.println("Showing document...");
     *     }
     * }
     *
     * public class Main {
     *     public static void main(String[] args) {
     *         Document doc = new Document();
     *         doc.print();
     *         doc.show();
     *     }
     * }
     * Explanation:
     * Here, Document implements both Printable and Showable interfaces without any ambiguity, as it provides specific implementations for both print() and show() methods.
     *
     * Summary
     * Supported Types of Inheritance in Java: Single, Multilevel, and Hierarchical.
     * Unsupported Type of Inheritance in Java: Multiple inheritance with classes (to avoid the Diamond Problem).
     * Workaround: Use interfaces to achieve multiple inheritance of behavior without ambiguity.
     *
     *
     * */
