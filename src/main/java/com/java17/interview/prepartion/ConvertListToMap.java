package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConvertListToMap {

    private final double cost;
    private final boolean active;
    private final Priority priority;

    public ConvertListToMap(double cost, boolean active, Priority priority) {
        this.cost = cost;
        this.active = active;
        this.priority = priority;
    }

    public double getCost() {
        return cost;
    }

    public boolean isActive() {
        return active;
    }

    public Priority getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Task{" +
                "cost=" + cost +
                ", active=" + active +
                ", priority=" + priority +
                '}';
    }

    public static void main(String[] args) {

        List<ConvertListToMap> tasks = new ArrayList<>();

        tasks.add(new ConvertListToMap(400, true, Priority.HIGH));
        tasks.add(new ConvertListToMap(50, true, Priority.MEDIUM));
        tasks.add(new ConvertListToMap(30, false, Priority.LOW));

        Map<Priority, ConvertListToMap> taskMap =
                tasks.stream()
                        .collect(Collectors.toMap(
                                ConvertListToMap::getPriority,
                                task -> task
                        ));

        taskMap.forEach((priority, task) ->
                System.out.println(priority + " -> " + task)
        );
    }
}

enum Priority {
    HIGH,
    MEDIUM,
    LOW
}