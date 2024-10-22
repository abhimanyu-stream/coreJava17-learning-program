package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConvertListToMap {

    private final double cost;

    private final boolean isActive;

    private final Priorarity priorarity;

    public ConvertListToMap(double cost, boolean isActive, Priorarity priorarity) {
        this.cost = cost;
        this.isActive = isActive;
        this.priorarity = priorarity;
    }


    

    public double getCost() {
        return cost;
    }

    public boolean isActive() {
        return isActive;
    }

    public Priorarity getPriorarity() {
        return priorarity;
    }

    //using streams create a map , such that key that Tasks grouped by Priority.

    public static void main(String[] args) {

        List<ConvertListToMap> listOfTask = new ArrayList<>();
        ConvertListToMap t1 = new ConvertListToMap(40, true, Priorarity.HIGH);
        ConvertListToMap t2 = new ConvertListToMap(40, false, Priorarity.LOW);
        //ConvertListToMap t3 = new ConvertListToMap(40, true, Priorarity.HIGH);
        listOfTask.add(t1);
        listOfTask.add(t2);
        //listOfTask.add(t3);

       // Map<String, Integer> taskMap = listOfTask.stream().collect(Collectors.groupingBy(Task::getPriorarity, Collectors.counting()));

        System.out.println(listOfTask.stream().collect(Collectors.groupingBy(ConvertListToMap::getPriorarity, Collectors.counting())));
        listOfTask.stream().forEach(System.out::println);
       // Map<String, Task> userMap  = listOfTask.stream().collect(Collectors.toMap(task -> task.getPriorarity(),task -> task));
        System.out.println(listOfTask.stream().collect(Collectors.toMap(task -> task.getPriorarity(),task -> task)));


    }



}
enum Priorarity{

    HIGH, MIDIUM, LOW

}

