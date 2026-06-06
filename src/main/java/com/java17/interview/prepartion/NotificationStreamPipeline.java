package com.java17.interview.prepartion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class NotificationStreamPipeline {

    public static void main(String[] args) {

        List<NotificationStrategyPine> strategies = Arrays.asList(
                new EmailPineStrategy(),
                new SmsPineStrategy(),
                new PushPineStrategy()
        );

        // ==============================
        // STREAM PIPELINE
        // ==============================
        List<String> channelNames = strategies.stream()
                .filter(NotificationStrategyPine::isAvailable) // Step 1: filter
                .sorted(Comparator.comparingInt(NotificationStrategyPine::getPriority).reversed()) // Step 2: sort DESC
                .map(NotificationStrategyPine::getChannelName) // Step 3: transform
                .toList(); // Step 4: collect

        // Output
        System.out.println("Available Channels (sorted by priority):");
        channelNames.forEach(System.out::println);



        int size = 1_000_000; // Large dataset
        List<NotificationStrategyPine> strategiesz = generateData(size);

        // Warm-up (important for JVM optimization)
        runSequential(strategiesz);
        runParallel(strategiesz);

        System.out.println("----- BENCHMARK START -----");

        long seqTime = measure(() -> runSequential(strategies));
        long parTime = measure(() -> runParallel(strategies));

        System.out.println("Sequential Stream Time: " + seqTime + " ms");
        System.out.println("Parallel Stream Time:   " + parTime + " ms");

        double speedup = (double) seqTime / parTime;
        System.out.println("Speedup: " + String.format("%.2f", speedup) + "x");
    }


    // Sequential Stream
    private static List<String> runSequential(List<NotificationStrategyPine> strategies) {
        return strategies.stream()
                .filter(NotificationStrategyPine::isAvailable)
                .sorted(Comparator.comparingInt(NotificationStrategyPine::getPriority).reversed())
                .map(NotificationStrategyPine::getChannelName)
                .collect(Collectors.toList());
    }

    // Parallel Stream
    private static List<String> runParallel(List<NotificationStrategyPine> strategies) {
        return strategies.parallelStream()
                .filter(NotificationStrategyPine::isAvailable)
                .sorted(Comparator.comparingInt(NotificationStrategyPine::getPriority).reversed())
                .map(NotificationStrategyPine::getChannelName)
                .collect(Collectors.toList());
    }

    // Benchmark helper
    private static long measure(Runnable task) {
        long start = System.nanoTime();
        task.run();
        long end = System.nanoTime();
        return (end - start) / 1_000_000; // ms
    }

    // Generate random data
    private static List<NotificationStrategyPine> generateData(int size) {
        List<NotificationStrategyPine> list = new ArrayList<>(size);
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i = 0; i < size; i++) {
            int priority = random.nextInt(1, 10);
            boolean available = random.nextBoolean();
            String name = "Channel-" + i;

            list.add(new GenericStrategy(priority, available, name));
        }

        return list;
    }

}
// Strategy Interface
interface NotificationStrategyPine {
    int getPriority();
    boolean isAvailable();
    String getChannelName();
}



// Concrete Implementations
class EmailPineStrategy implements NotificationStrategyPine {
    public int getPriority() { return 2; }
    public boolean isAvailable() { return true; }
    public String getChannelName() { return "EMAIL"; }
}

class SmsPineStrategy implements NotificationStrategyPine {
    public int getPriority() { return 5; }
    public boolean isAvailable() { return false; } // not available
    public String getChannelName() { return "SMS"; }
}

class PushPineStrategy implements NotificationStrategyPine {
    public int getPriority() { return 3; }
    public boolean isAvailable() { return true; }
    public String getChannelName() { return "PUSH"; }
}

// Concrete Implementation
class GenericStrategy implements NotificationStrategyPine {
    private final int priority;
    private final boolean available;
    private final String name;

    public GenericStrategy(int priority, boolean available, String name) {
        this.priority = priority;
        this.available = available;
        this.name = name;
    }

    public int getPriority() { return priority; }
    public boolean isAvailable() { return available; }
    public String getChannelName() { return name; }
}

