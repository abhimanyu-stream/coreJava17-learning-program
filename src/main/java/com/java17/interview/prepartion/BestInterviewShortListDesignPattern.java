package com.java17.interview.prepartion;

/**
 * Best Interview Design Pattern Shortlist
 *
 * <p>If you have limited time, master these 8 patterns first — they cover 90% of interviews.</p>
 *
 * <h2>Must-Know Patterns (Priority)</h2>
 * <pre>
 * Pattern    Priority
 * ─────────────────────────
 * Singleton    ⭐
 * Factory      ⭐
 * Builder      ⭐
 * Strategy     ⭐
 * Observer     ⭐
 * Proxy        ⭐
 * Decorator    ⭐
 * Adapter      ⭐
 * </pre>
 *
 * <h2>Spring Boot Interview Favorites</h2>
 * <pre>
 * Pattern                Spring Boot Example
 * ────────────────────────────────────────────────────
 * Singleton              @Component / @Service beans (one instance per container)
 * Factory                BeanFactory / ApplicationContext
 * Proxy                  AOP, @Transactional, @Cacheable, @Async
 * Observer               ApplicationEvent / @EventListener
 * Strategy               Multiple @Service implementations of one interface
 * Template Method        JdbcTemplate, RestTemplate
 * Chain of Responsibility Filter chain / HandlerInterceptor chain
 * Builder                Lombok @Builder / ResponseEntity.ok().body(...)
 * </pre>
 *
 * <h2>One-Line Memory Tricks</h2>
 * <pre>
 * Singleton    = one object per JVM / container
 * Factory      = delegate object creation to a factory method
 * Builder      = build complex objects step by step
 * Strategy     = choose algorithm / behaviour at runtime
 * Observer     = notify all registered listeners on event
 * Proxy        = control access to another object
 * Decorator    = add responsibilities to object without changing it
 * Adapter      = make incompatible interfaces work together
 * Template     = define skeleton of algorithm; subclasses fill in steps
 * Chain        = pass request along a chain of handlers
 * </pre>
 *
 * <h2>Creational vs Structural vs Behavioural</h2>
 * <pre>
 * Creational  → Singleton, Factory, Abstract Factory, Builder, Prototype
 * Structural  → Proxy, Decorator, Adapter, Composite, Facade, Flyweight, Bridge
 * Behavioural → Strategy, Observer, Template Method, Chain of Responsibility,
 *               Command, Iterator, State, Visitor, Mediator, Memento
 * </pre>
 */
public class BestInterviewShortListDesignPattern {
    // This class is a reference/documentation holder.
    // See individual pattern files: SingletonClassLazyInitializationThreadSafe.java,
    // FactoryMethodPattern.java, BuilderPattern.java, NotificationStrategyPattern.java,
    // ObserverPattern.java, ProxyPattern.java, DecoratorPattern.java, AdapterPattern.java
}
