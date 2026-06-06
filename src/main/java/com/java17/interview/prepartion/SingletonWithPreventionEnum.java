package com.java17.interview.prepartion;

// Enum Singleton — simplest and safest singleton form
enum SingletonEnum {

    INSTANCE; // only instance (thread-safe)

    // Example field
    private String connection = "Database Connection Established";

    // Example behavior
    public void showMessage() {
        System.out.println("Hello from Enum Singleton!");
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }
}

public class SingletonWithPreventionEnum {
    public static void main(String[] args) {
        SingletonEnum instance1 = SingletonEnum.INSTANCE;
        SingletonEnum instance2 = SingletonEnum.INSTANCE;

        instance1.showMessage();
        System.out.println("Connection: " + instance1.getConnection());

        // Modify field and verify both references are same
        instance1.setConnection("Updated Connection");
        System.out.println("Instance 2 connection: " + instance2.getConnection());

        System.out.println("Are both same? " + (instance1 == instance2));
    }
}
