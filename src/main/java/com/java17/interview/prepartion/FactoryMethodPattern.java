package com.java17.interview.prepartion;

public class FactoryMethodPattern {
    public static void main(String[] args) {

        NotificationsFactory factory = new EmailFactory();
        factory.notifyUser();

        /**
         * Interview-safe rule
         * Factory Method
         *
         * Inheritance-based
         *
         * Subclass overrides creation
         *
         * One product.
         */
    }
}
// Product
interface Notifications {
    void send();
}

// Concrete Products
class EmailNotification implements Notifications {
    public void send() {
        System.out.println("Email sent");
    }
}

class SMSNotification implements Notifications {
    public void send() {
        System.out.println("SMS sent");
    }
}

// Creator
abstract class NotificationsFactory {

    // Factory Method
    abstract Notifications createNotification();

    public void notifyUser() {
        Notifications n = createNotification();// return EmailNotification
        n.send();

        /**
         * Visual flow
         * factory.notifyUser()
         *       |
         *       v
         * createNotification()   <-- overridden method
         *       |
         *       v
         * new EmailNotification()
         *       |
         *       v
         * send()
         *
         * So yes:
         *
         * notifyUser() indirectly creates another object.
         */
    }
}

// Concrete Creators
class EmailFactory extends NotificationsFactory {
    Notifications createNotification() {
        return new EmailNotification();
    }
}

class SMSFactory extends NotificationsFactory {
    Notifications createNotification() {
        return new SMSNotification();
    }
}
