package com.java17.interview.prepartion;

public class FactoryMethodAndAbstractFactory {

    public static void main(String[] args) {



    }
}
/**
 * The Factory Method and Abstract Factory are two creational design patterns that deal with object creation, but they are used in slightly different scenarios. Below is a comparison along with Java examples for better understanding.
 *
 * Factory Method Design Pattern
 * Purpose: Defines an interface for creating an object, but lets subclasses decide which class to instantiate.
 * When to Use: When a class cannot anticipate the type of objects it needs to create.
 * Key Components:
 * Product: The interface or abstract class defining the object to be created.
 * Concrete Product: The actual implementation of the product interface.
 * Creator: The abstract class or interface defining the factory method.
 * Concrete Creator: The class implementing the factory method to produce specific products.
 * Example:

 * // Product interface
 * interface Shape {
 *     void draw();
 * }
 *
 * // Concrete Products
 * class Circle implements Shape {
 *     public void draw() {
 *         System.out.println("Drawing a Circle");
 *     }
 * }
 *
 * class Square implements Shape {
 *     public void draw() {
 *         System.out.println("Drawing a Square");
 *     }
 * }
 *
 * // Creator
 * abstract class ShapeFactory {
 *     public abstract Shape createShape();
 * }
 *
 * // Concrete Creators
 * class CircleFactory extends ShapeFactory {
 *     public Shape createShape() {
 *         return new Circle();
 *     }
 * }
 *
 * class SquareFactory extends ShapeFactory {
 *     public Shape createShape() {
 *         return new Square();
 *     }
 * }
 *
 * // Client
 * public class FactoryMethodExample {
 *     public static void main(String[] args) {
 *         ShapeFactory circleFactory = new CircleFactory();
 *         Shape circle = circleFactory.createShape();
 *         circle.draw();
 *
 *         ShapeFactory squareFactory = new SquareFactory();
 *         Shape square = squareFactory.createShape();
 *         square.draw();
 *     }
 * }
 * Abstract Factory Design Pattern
 * Purpose: Provides an interface for creating families of related or dependent objects without specifying their concrete classes.
 * When to Use: When the system needs to create multiple families of related objects.
 * Key Components:
 * Abstract Factory: An interface defining methods to create abstract products.
 * Concrete Factory: A class implementing the abstract factory and producing family objects.
 * Abstract Product: Interface or abstract class for the family of objects.
 * Concrete Product: Specific implementations of the abstract product.
 * Example:

 * // Abstract Products
 * interface Button {
 *     void render();
 * }
 *
 * interface Checkbox {
 *     void render();
 * }
 *
 * // Concrete Products
 * class WindowsButton implements Button {
 *     public void render() {
 *         System.out.println("Rendering Windows Button");
 *     }
 * }
 *
 * class MacOSButton implements Button {
 *     public void render() {
 *         System.out.println("Rendering MacOS Button");
 *     }
 * }
 *
 * class WindowsCheckbox implements Checkbox {
 *     public void render() {
 *         System.out.println("Rendering Windows Checkbox");
 *     }
 * }
 *
 * class MacOSCheckbox implements Checkbox {
 *     public void render() {
 *         System.out.println("Rendering MacOS Checkbox");
 *     }
 * }
 *
 * // Abstract Factory
 * interface GUIFactory {
 *     Button createButton();
 *     Checkbox createCheckbox();
 * }
 *
 * // Concrete Factories
 * class WindowsFactory implements GUIFactory {
 *     public Button createButton() {
 *         return new WindowsButton();
 *     }
 *     public Checkbox createCheckbox() {
 *         return new WindowsCheckbox();
 *     }
 * }
 *
 * class MacOSFactory implements GUIFactory {
 *     public Button createButton() {
 *         return new MacOSButton();
 *     }
 *     public Checkbox createCheckbox() {
 *         return new MacOSCheckbox();
 *     }
 * }
 *
 * // Client
 * public class AbstractFactoryExample {
 *     public static void main(String[] args) {
 *         GUIFactory factory;
 *
 *         // Suppose we are on Windows
 *         factory = new WindowsFactory();
 *         Button button = factory.createButton();
 *         Checkbox checkbox = factory.createCheckbox();
 *         button.render();
 *         checkbox.render();
 *
 *         // Suppose we switch to MacOS
 *         factory = new MacOSFactory();
 *         button = factory.createButton();
 *         checkbox = factory.createCheckbox();
 *         button.render();
 *         checkbox.render();
 *     }
 * }
 * Comparison
 * Aspect	Factory Method	Abstract Factory
 * Intent	Define a method to create a single product.	Define a factory for creating related products.
 * Complexity	Simpler, deals with one product at a time.	More complex, deals with families of products.
 * Use Case	Single product creation.	Multiple related products (families).
 * Example Scenario	Creating shapes (e.g., Circle, Square).	Creating UI components (e.g., Button, Checkbox).
 * Flexibility	Can add new products by extending the creator.	Can add new families by creating new factories.
 * By choosing the right pattern, you can manage object creation in your application effectively!
 * */