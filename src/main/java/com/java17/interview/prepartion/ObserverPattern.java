package com.java17.interview.prepartion;

public class ObserverPattern {
}
/**
 * One object changes → notify many listeners.
 *
 * Example
 * Student result published
 *    |
 * notify:
 *    parent
 *    email service
 *    SMS service
 *
 * Spring:
 *
 * Event listeners
 * Kafka consumers
 * ApplicationEventPublisher
 *
 * Interview line:
 *
 * “Publisher notifies subscribers.”
 *
 * Example code
 * interface Observer {
 *     void update(String msg);
 * }
 *
 * Spring equivalent:
 *
 * @EventListener
 */