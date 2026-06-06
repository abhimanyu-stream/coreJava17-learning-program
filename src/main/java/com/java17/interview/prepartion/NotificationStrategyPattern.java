package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// Strategy Interface
interface NotificationStrategy {
    int getPriority();
    String getName();
}

// Concrete Implementation
class EmailStrategy implements NotificationStrategy {
    public int getPriority() { return 2; }
    public String getName() { return "Email"; }
}

class SmsStrategy implements NotificationStrategy {
    public int getPriority() { return 5; }
    public String getName() { return "SMS"; }
}

class PushStrategy implements NotificationStrategy {
    public int getPriority() { return 3; }
    public String getName() { return "Push Notification"; }
}


public class NotificationStrategyPattern {

    public static void main(String[] args) {
        List<NotificationStrategy> enabledStrategies = new ArrayList<>();

        enabledStrategies.add(new EmailStrategy());
        enabledStrategies.add(new SmsStrategy());
        enabledStrategies.add(new PushStrategy());

        // ---- BEFORE SORT ----
        System.out.println("Before Sorting:");
        printList(enabledStrategies);

        // ==============================
        // 1. USING STREAMS (IMMUTABLE STYLE)
        // ==============================
        List<NotificationStrategy> sortedByStream = enabledStrategies.stream()
                .sorted((s1, s2) -> Integer.compare(s2.getPriority(), s1.getPriority()))
                .collect(Collectors.toList());

        System.out.println("\nAfter Sorting using Streams:");
        printList(sortedByStream);

        // ==============================
        // 2. USING COMPARATOR (IN-PLACE SORT)
        // ==============================
        enabledStrategies.sort(
                Comparator.comparingInt(NotificationStrategy::getPriority).reversed()
        );

        System.out.println("\nAfter Sorting using Comparator:");
        printList(enabledStrategies);

        /**
         * Key Points
         * Streams version
         * Creates a new sorted list
         * Original list remains unchanged
         * Good for functional/immutable style
         * Comparator.sort() version
         * Sorts the existing list in place
         * More readable and efficient
         *
         */
    }

    private static void printList(List<NotificationStrategy> list) {
        for (NotificationStrategy strategy : list) {
            System.out.println(strategy.getName() + " -> Priority: " + strategy.getPriority());
        }
    }

}
