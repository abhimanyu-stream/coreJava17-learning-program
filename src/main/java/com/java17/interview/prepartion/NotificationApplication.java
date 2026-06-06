package com.java17.interview.prepartion;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
        /**
         * Why This Design Works
         * Open/Closed Principle → Add new strategy without changing existing code
         * Spring auto-discovery → Just add @Component, done
         * Factory decouples selection logic
         * Priority sorting stays clean and centralized
         *
         */
    }
}
class NotificationRequest {

    private String type;
    private String message;

    // Getters & Setters
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}



@RestController
@RequestMapping("/notifications")
 class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @PostMapping("/send")
    public String send(@RequestBody NotificationRequest request) {
        service.send(request.getType(), request.getMessage());
        return "Notification sent!";
    }

    @PostMapping("/send-all")
    public String sendAll(@RequestBody NotificationRequest request) {
        service.sendAll(request.getMessage());
        return "All notifications sent!";
    }
}



@Service
 class NotificationService {

    private final NotificationFactory factory;

    public NotificationService(NotificationFactory factory) {
        this.factory = factory;
    }

    // Send specific type
    public void send(String type, String message) {
        Notification strategy = factory.getStrategy(type);
        strategy.send(message);
    }

    // Send ALL (sorted by priority DESC)
    public void sendAll(String message) {

        List<Notification> strategies = factory.getAllStrategies();

        strategies.sort(
                Comparator.comparingInt(Notification::getPriority).reversed()
        );

        for (Notification strategy : strategies) {
            strategy.send(message);
        }
    }
}


@Component
 class NotificationFactory {

    private final Map<String, Notification> strategyMap;

    public NotificationFactory(List<Notification> strategies) {
        // Map: TYPE -> Strategy
        this.strategyMap = strategies.stream()
                .collect(Collectors.toMap(
                        s -> s.getType().toUpperCase(),
                        s -> s
                ));
    }

    public Notification getStrategy(String type) {
        Notification strategy = strategyMap.get(type.toUpperCase());
        if (strategy == null) {
            throw new RuntimeException("Invalid notification type: " + type);
        }
        return strategy;
    }

    public List<Notification> getAllStrategies() {
        return strategyMap.values().stream().toList();
    }
}


@Component
 class PushhStrategy implements Notification {

    @Override
    public String getType() {
        return "PUSH";
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public void send(String message) {
        System.out.println("Sending PUSH: " + message);
    }
}


@Component
 class SmssStrategy implements Notification {

    @Override
    public String getType() {
        return "SMS";
    }

    @Override
    public int getPriority() {
        return 5;
    }

    @Override
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}


@Component
 class EmaillStrategy implements Notification {

    @Override
    public String getType() {
        return "EMAIL";
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public void send(String message) {
        System.out.println("Sending EMAIL: " + message);
    }
}


 interface Notification {
    String getType();       // EMAIL, SMS, PUSH
    int getPriority();      // For sorting
    void send(String message);
}