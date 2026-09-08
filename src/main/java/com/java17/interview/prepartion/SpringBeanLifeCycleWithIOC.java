/**
# Spring Bean Lifecycle in Spring Boot

The **Spring Bean Lifecycle** describes how Spring manages a bean from the moment the Spring container discovers its definition until the bean is destroyed.

At a high level:

```text
Spring Boot Starts
       ↓
ApplicationContext Created
       ↓
Bean Definitions Registered
       ↓
Bean Instantiated
       ↓
Dependencies Injected
       ↓
Aware Interfaces
       ↓
BeanPostProcessor - Before Initialization
       ↓
@PostConstruct
       ↓
InitializingBean
       ↓
Custom Init Method
       ↓
BeanPostProcessor - After Initialization
       ↓
AOP / Transaction / Async / Cache Proxy
       ↓
Bean Ready for Use
       ↓
Application Running
       ↓
Application Shutdown
       ↓
@PreDestroy
       ↓
DisposableBean.destroy()
       ↓
Custom Destroy Method
       ↓
Bean Destroyed
```

---

# 1. Spring Boot Application Starts

The application normally starts with:

```java
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
```

The important call is:

```java
SpringApplication.run(App.class, args);
```

Spring Boot creates and prepares the Spring `ApplicationContext`.

```text
SpringApplication.run()
        ↓
ApplicationContext
        ↓
BeanFactory
        ↓
Bean Definitions
        ↓
Bean Creation
```

---

# 2. ApplicationContext Is Created

`ApplicationContext` is Spring's advanced IoC container.

It provides:

* Bean creation and management
* Dependency Injection
* Application events
* Resource loading
* Internationalization
* Environment and property management
* Integration with AOP
* Integration with many enterprise features

Conceptually:

```text
ApplicationContext
        ↓
BeanFactory capabilities
```

More precisely, `ApplicationContext` extends interfaces that ultimately include `BeanFactory`; it is **not simply a subclass of `BeanFactory`**.

## BeanFactory vs ApplicationContext

| Feature                  | BeanFactory      | ApplicationContext                                      |
| ------------------------ | ---------------- | ------------------------------------------------------- |
| IoC / DI                 | Yes              | Yes                                                     |
| Bean management          | Yes              | Yes                                                     |
| Lazy bean creation       | Default behavior | Non-lazy singleton beans are eagerly created by default |
| Application events       | Basic/No         | Yes                                                     |
| Internationalization     | No               | Yes                                                     |
| Enterprise integration   | Basic            | Extensive                                               |
| Common Spring Boot usage | Rare directly    | Yes                                                     |

In Spring Boot applications, you normally work with:

```java
ApplicationContext
```

rather than directly using `BeanFactory`.

---

# 3. Component Scanning Starts

Spring Boot uses component scanning to discover beans.

For example:

```java
@Component
public class UserService {
}
```

Other common bean definitions are:

```java
@Component
@Service
@Repository
@Controller
@RestController
@Configuration
@Bean
```

Example:

```java
@Service
public class UserService {
}
```

Spring discovers this class during component scanning.

---

# 4. BeanDefinition Is Created

An important concept:

> `BeanDefinition` is metadata about a bean. It is NOT the actual bean object.

For example:

```java
@Service
public class UserService {
}
```

Spring creates metadata describing this bean.

Conceptually:

```text
UserService
     ↓
BeanDefinition
```

A `BeanDefinition` can contain information such as:

* Bean class
* Scope
* Lazy initialization
* Constructor information
* Dependency information
* Initialization method
* Destruction method
* Autowiring information

For example:

```text
"userService"
      ↓
BeanDefinition
      ↓
Class = UserService
Scope = singleton
Lazy = false
```

---

# 5. BeanDefinitions Are Registered in BeanFactory

Spring's core implementation commonly involved here is:

```text
DefaultListableBeanFactory
```

It maintains bean definitions and bean instances/caches.

Conceptually:

```text
BeanFactory
     ↓
BeanDefinition Registry
     ↓
"userService"  → BeanDefinition
"orderService" → BeanDefinition
"paymentService" → BeanDefinition
```

Important:

```text
BeanDefinition ≠ Bean Object
```

At this point Spring may only have metadata describing the bean.

---

# 6. Bean Instantiation — Object Is Created

When Spring decides that a bean needs to be created, it instantiates the object.

For example:

```java
@Service
public class UserService {

    public UserService() {
        System.out.println("Constructor called");
    }
}
```

Spring effectively creates an object through its bean creation infrastructure.

Conceptually:

```text
BeanDefinition
      ↓
Constructor Resolution
      ↓
Object Instantiation
      ↓
UserService object
```

Internally, Spring uses infrastructure such as:

```text
AbstractAutowireCapableBeanFactory
ConstructorResolver
InstantiationStrategy
```

You can think of the result as:

```java
new UserService();
```

although Spring performs much more work around the actual construction.

---

# 7. Constructor Executes

The constructor executes during instantiation.

Example:

```java
@Service
public class UserService {

    public UserService() {
        System.out.println("Constructor called");
    }
}
```

Output:

```text
Constructor called
```

Important:

> The constructor executes before dependency injection through fields/setters and before initialization callbacks.

---

# 8. Dependency Injection

After instantiation, Spring populates the bean's dependencies.

Spring supports:

### Constructor Injection

```java
@Service
public class OrderService {

    private final UserService userService;

    public OrderService(UserService userService) {
        this.userService = userService;
    }
}
```

### Setter Injection

```java
@Service
public class OrderService {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
```

### Field Injection

```java
@Service
public class OrderService {

    @Autowired
    private UserService userService;
}
```

For modern Spring applications, **constructor injection is generally preferred**.

---

# 9. Dependency Resolution Happens

Suppose:

```java
@Service
public class OrderService {

    private final UserService userService;

    public OrderService(UserService userService) {
        this.userService = userService;
    }
}
```

Spring must resolve:

```text
Create OrderService
        ↓
OrderService requires UserService
        ↓
Find UserService BeanDefinition
        ↓
Create UserService if necessary
        ↓
Inject UserService
        ↓
OrderService becomes populated
```

Spring can recursively resolve dependencies.

---

# 10. Aware Interfaces

After dependency population, Spring can provide internal container information through various `Aware` interfaces.

Common examples:

```java
BeanNameAware
BeanFactoryAware
ApplicationContextAware
ApplicationEventPublisherAware
EnvironmentAware
```

Example:

```java
@Component
public class DemoBean implements BeanNameAware {

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean name: " + name);
    }
}
```

Spring calls:

```java
setBeanName(...)
```

and provides the bean's name.

Example:

```text
userService
```

---

# 11. BeanPostProcessor — Before Initialization

Spring now invokes:

```java
postProcessBeforeInitialization()
```

through registered `BeanPostProcessor` implementations.

A `BeanPostProcessor` allows Spring or application developers to process beans before initialization.

Example:

```java
@Component
public class MyBeanPostProcessor
        implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(
            Object bean,
            String beanName) {

        return bean;
    }
}
```

This is an important Spring extension mechanism.

---

# 12. @PostConstruct Executes

One of the most commonly used initialization mechanisms is:

```java
@PostConstruct
```

Example:

```java
@Component
public class DemoBean {

    @PostConstruct
    public void init() {
        System.out.println("Bean initialized");
    }
}
```

The method executes after dependency injection has completed.

Conceptually:

```text
Constructor
      ↓
Dependency Injection
      ↓
@PostConstruct
```

`@PostConstruct` is handled by Spring's bean post-processing infrastructure.

---

# 13. InitializingBean Executes

A bean can implement:

```java
InitializingBean
```

Example:

```java
@Component
public class DemoBean implements InitializingBean {

    @Override
    public void afterPropertiesSet() {
        System.out.println("Initialized");
    }
}
```

Spring calls:

```java
afterPropertiesSet()
```

during initialization.

---

# 14. Custom Initialization Method

A custom initialization method can also be configured.

Example:

```java
public class DemoBean {

    public void start() {
        System.out.println("Custom initialization");
    }
}
```

Configuration:

```java
@Bean(initMethod = "start")
public DemoBean demoBean() {
    return new DemoBean();
}
```

Spring invokes:

```java
start()
```

during bean initialization.

---

# 15. Initialization Order

A simplified and useful interview-level ordering is:

```text
Bean Instantiation
        ↓
Dependency Injection
        ↓
Aware callbacks
        ↓
BeanPostProcessor.beforeInitialization()
        ↓
@PostConstruct
        ↓
InitializingBean.afterPropertiesSet()
        ↓
Custom init-method
        ↓
BeanPostProcessor.afterInitialization()
```

The exact ordering between multiple post-processors can depend on their ordering configuration, but this is the standard lifecycle sequence to remember.

---

# 16. BeanPostProcessor — After Initialization

After initialization, Spring invokes:

```java
postProcessAfterInitialization()
```

This phase is particularly important for Spring AOP.

For example:

```java
@Transactional
@Async
@Cacheable
```

may cause Spring to expose a proxy around the bean.

Conceptually:

```text
Original Bean
      ↓
BeanPostProcessor
      ↓
Possible Proxy
      ↓
Bean stored/exposed by container
```

---

# 17. Spring AOP Proxy Creation

Suppose we have:

```java
@Service
@Transactional
public class PaymentService {

    public void transferMoney() {
        System.out.println("Transfer money");
    }
}
```

Spring may create a proxy around `PaymentService`.

The application generally interacts with the proxy rather than directly with the original target object.

Conceptually:

```text
Client
   ↓
Spring Proxy
   ↓
Transaction Interceptor
   ↓
PaymentService
   ↓
transferMoney()
```

---

# 18. JDK Dynamic Proxy

Spring can use a **JDK dynamic proxy** when appropriate, particularly when the target exposes interfaces.

Example:

```java
public interface PaymentService {
    void transferMoney();
}
```

Implementation:

```java
@Service
@Transactional
public class PaymentServiceImpl
        implements PaymentService {

    @Override
    public void transferMoney() {
    }
}
```

Conceptually:

```text
Client
   ↓
JDK Dynamic Proxy
   ↓
PaymentServiceImpl
```

---

# 19. CGLIB-Based Proxy

Spring can also use a subclass-based proxy mechanism.

Conceptually:

```text
PaymentService
      ↓
Generated subclass
      ↓
Proxy intercepts method calls
```

In modern Spring, this is generally implemented through **Spring's CGLIB-based proxy support**.

The important interview point is:

```text
JDK Proxy       → interface-based proxy
CGLIB-based     → subclass-based proxy
```

Spring can also be configured to use class-based proxies even when interfaces exist.

---

# 20. @Transactional Example

Consider:

```java
@Service
@Transactional
public class PaymentService {

    public void transferMoney() {
        System.out.println("Transfer money");
    }
}
```

The runtime flow can be:

```text
Controller
     ↓
PaymentService Proxy
     ↓
Begin Transaction
     ↓
PaymentService.transferMoney()
     ↓
Success?
   ↙     ↘
 Yes      No
  ↓        ↓
Commit   Rollback
```

Therefore, when a controller calls:

```java
paymentService.transferMoney();
```

the object referenced by `paymentService` may actually be a proxy.

---

# 21. Bean Is Ready for Use

After the initialization and post-processing phases are complete, the bean is ready for normal application use.

It can be used by:

```text
REST Controllers
Services
Repositories
Kafka Components
Schedulers
Security Components
JPA Components
WebSocket Components
Message Listeners
```

For singleton beans, the container normally keeps the managed instance in its singleton cache.

---

# 22. Application Is Running

At this point:

```text
Spring Container
       ↓
Beans Ready
       ↓
Application Running
```

Example:

```text
HTTP Request
      ↓
Controller
      ↓
Service Proxy
      ↓
Service
      ↓
Repository
      ↓
Database
```

---

# 23. Bean Scopes

Spring supports different bean scopes.

## Singleton

Default scope:

```java
@Component
@Scope("singleton")
public class UserService {
}
```

There is normally:

```text
ONE INSTANCE
per Spring IoC container
```

The same singleton instance is shared wherever it is injected.

---

## Prototype

```java
@Component
@Scope("prototype")
public class ReportGenerator {
}
```

Spring creates a new instance each time the bean is requested from the container.

Important interview point:

> Spring does not fully manage the destruction lifecycle of prototype beans after handing them to the caller.

---

## Request Scope

```java
@RequestScope
public class RequestContext {
}
```

A new instance is created for an HTTP request.

Conceptually:

```text
HTTP Request 1 → RequestContext A
HTTP Request 2 → RequestContext B
HTTP Request 3 → RequestContext C
```

---

## Session Scope

```java
@SessionScope
public class UserSession {
}
```

One instance is associated with an HTTP session.

---

# 24. @Lazy

By default, Spring Boot eagerly creates non-lazy singleton beans during application context startup.

To delay creation:

```java
@Component
@Lazy
public class ExpensiveService {
}
```

Now the bean can be created when it is first needed.

Conceptually:

```text
Application Starts
       ↓
@Lazy Bean NOT Created
       ↓
First Request / First Lookup
       ↓
Bean Created
```

---

# 25. Application Shutdown

When the Spring application shuts down, the `ApplicationContext` is closed.

Shutdown can happen because of:

```text
Ctrl + C
SIGTERM
ApplicationContext.close()
Kubernetes termination
Docker stop
Application shutdown
```

Spring begins destroying managed beans.

Conceptually:

```text
Application Running
       ↓
Shutdown Triggered
       ↓
Bean Destruction
```

---

# 26. @PreDestroy

One of the most common destruction callbacks is:

```java
@PreDestroy
```

Example:

```java
@Component
public class DemoBean {

    @PreDestroy
    public void cleanup() {
        System.out.println("Cleaning resources");
    }
}
```

Spring invokes this method during bean destruction.

Typical use cases:

```text
Close resources
Stop background tasks
Flush buffers
Release resources
Cleanup connections
```

---

# 27. DisposableBean

A bean can implement:

```java
DisposableBean
```

Example:

```java
@Component
public class DemoBean implements DisposableBean {

    @Override
    public void destroy() {
        System.out.println("Bean destroyed");
    }
}
```

Spring invokes:

```java
destroy()
```

during destruction.

---

# 28. Custom Destroy Method

A custom destruction method can be specified:

```java
public class DemoBean {

    public void stop() {
        System.out.println("Custom destroy method");
    }
}
```

Configuration:

```java
@Bean(destroyMethod = "stop")
public DemoBean demoBean() {
    return new DemoBean();
}
```

Spring invokes:

```java
stop()
```

during destruction.

---

# 29. Destruction Order

A useful simplified sequence is:

```text
Application Shutdown
        ↓
@PreDestroy
        ↓
DisposableBean.destroy()
        ↓
Custom destroy method
        ↓
Bean destroyed
```

The exact ordering can be affected by bean dependencies and lifecycle infrastructure, but these are the major destruction mechanisms to remember.

---

# 30. Complete Spring Bean Lifecycle

The complete simplified lifecycle is:

```text
SpringApplication.run()
        ↓
ApplicationContext Created
        ↓
Bean Definitions Discovered
        ↓
BeanDefinitions Registered
        ↓
Bean Instantiated
        ↓
Constructor Executes
        ↓
Dependencies Injected
        ↓
Aware Interfaces
        ↓
BeanPostProcessor.beforeInitialization()
        ↓
@PostConstruct
        ↓
InitializingBean.afterPropertiesSet()
        ↓
Custom init-method
        ↓
BeanPostProcessor.afterInitialization()
        ↓
AOP / Transaction / Async / Cache Proxy
        ↓
Bean Ready
        ↓
Application Running
        ↓
Shutdown Triggered
        ↓
@PreDestroy
        ↓
DisposableBean.destroy()
        ↓
Custom destroy-method
        ↓
Bean Destroyed
```

---

# 31. Important Internal Spring Classes

These classes are useful for interviews.

| Class / Interface                        | Responsibility                                                       |
| ---------------------------------------- | -------------------------------------------------------------------- |
| `ApplicationContext`                     | High-level Spring container                                          |
| `BeanFactory`                            | Core IoC bean management                                             |
| `DefaultListableBeanFactory`             | Major bean factory implementation and registry                       |
| `BeanDefinition`                         | Bean metadata                                                        |
| `AbstractAutowireCapableBeanFactory`     | Core bean creation/autowiring infrastructure                         |
| `ConstructorResolver`                    | Resolves constructors/factory methods                                |
| `BeanPostProcessor`                      | Processes beans before/after initialization                          |
| `AutowiredAnnotationBeanPostProcessor`   | Processes annotations such as `@Autowired`                           |
| `CommonAnnotationBeanPostProcessor`      | Supports common annotations such as `@PostConstruct` / `@PreDestroy` |
| `AnnotationAwareAspectJAutoProxyCreator` | Infrastructure for creating AOP proxies                              |
| `JdkDynamicAopProxy`                     | JDK dynamic proxy support                                            |
| `CglibAopProxy`                          | Class-based proxy support                                            |

---

# 32. Important Internal Bean Creation Flow

A simplified internal flow is:

```text
ApplicationContext
        ↓
DefaultListableBeanFactory
        ↓
BeanDefinition
        ↓
getBean()
        ↓
Create Bean
        ↓
Instantiate
        ↓
Populate Properties
        ↓
Aware Callbacks
        ↓
BeanPostProcessor.beforeInitialization()
        ↓
Initialization
        ↓
BeanPostProcessor.afterInitialization()
        ↓
Possible AOP Proxy
        ↓
Singleton Cache
        ↓
Bean Available
```

---

# 33. Singleton Bean Cache

For singleton beans, Spring maintains internal singleton caches.

One important cache is:

```text
singletonObjects
```

Conceptually:

```text
singletonObjects

"userService"    → UserService object
"orderService"   → OrderService object
"paymentService" → PaymentService proxy/object
```

Do not think of Spring as simply maintaining one ordinary `Map<String,Object>` for all bean lifecycle behavior. Internally, Spring uses multiple caches and registries for singleton creation, early references, factories, and related lifecycle handling.

---

# 34. Why Spring Has Multiple Singleton Caches

During complex situations such as circular dependencies, Spring may need early references and singleton factories.

Conceptually, Spring has mechanisms involving:

```text
singletonObjects
earlySingletonObjects
singletonFactories
```

These are particularly relevant to understanding circular dependency resolution.

For a normal interview answer, remember:

```text
singletonObjects
       ↓
Fully initialized singleton instances
```

---

# 35. Real Enterprise Example — PaymentService

Consider:

```java
@Service
@Transactional
public class PaymentService {

    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        System.out.println("PaymentService initialized");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("PaymentService destroyed");
    }

    public void transferMoney() {
        // business logic
    }
}
```

The simplified lifecycle is:

```text
Application Starts
        ↓
PaymentService BeanDefinition
        ↓
PaymentService Object Created
        ↓
PaymentRepository Injected
        ↓
Aware callbacks if applicable
        ↓
@PostConstruct
        ↓
InitializingBean / custom init if applicable
        ↓
@Transactional processing
        ↓
Proxy Created/Applied
        ↓
Proxy Exposed as Bean
        ↓
Controller Uses PaymentService
        ↓
Transaction Interceptor
        ↓
transferMoney()
        ↓
Commit / Rollback
        ↓
Application Shutdown
        ↓
@PreDestroy
        ↓
Bean Destroyed
```

---

# 36. Database Connection Pool Example

Spring Boot applications commonly use a connection pool such as HikariCP.

Conceptually:

```text
Application Startup
        ↓
DataSource Created
        ↓
Connection Pool Initialized
        ↓
Application Uses Database
        ↓
Application Shutdown
        ↓
Connection Pool Closed
```

Spring manages lifecycle integration for the relevant infrastructure beans.

---

# 37. Kafka Example

A Spring Boot Kafka application may contain:

```text
Kafka Producer
Kafka Consumer
KafkaAdmin
Kafka Listener Container
```

During application startup:

```text
Spring Starts
      ↓
Kafka infrastructure initialized
      ↓
Producer / consumer infrastructure starts
      ↓
Application Running
```

During shutdown:

```text
Shutdown
    ↓
Kafka listener containers stop
    ↓
Consumers close
    ↓
Producer resources are released
```

The exact behavior depends on the Spring Kafka configuration and infrastructure involved.

---

# 38. Scheduler Example

For:

```java
@Scheduled(fixedRate = 5000)
public void processOrders() {
}
```

Spring creates and manages scheduling infrastructure.

Conceptually:

```text
Application Startup
        ↓
Scheduler Initialized
        ↓
Scheduled Task Executes
        ↓
Application Shutdown
        ↓
Scheduler Stops
```

---

# 39. Does a Singleton Bean Survive Application Restart?

No.

Suppose:

```java
@Component
public class UserService {
}
```

The singleton exists inside the current Spring container.

When the application is restarted:

```text
Old JVM / Application
        ↓
Old ApplicationContext
        ↓
Old Singleton Destroyed
        ↓
Application Restarted
        ↓
New ApplicationContext
        ↓
New Singleton Created
```

The Java object does not survive the process restart.

---

# 40. Does Bean Data Persist After Restart?

Normally, data stored only inside the bean's memory does not survive a restart.

Example:

```java
@Component
public class CounterService {

    private int counter = 100;
}
```

After application restart:

```text
counter = 100
```

The previous in-memory state is lost.

Persistent state should be stored in appropriate external systems such as:

```text
Database
Redis
Kafka
Object Storage
File System
External Services
```

---

# 41. Very Important: Bean Object vs Proxy Object

One of the most important Spring interview concepts is:

> The object retrieved from the Spring container may be a proxy rather than the original target object.

Example:

```java
@Service
@Transactional
public class PaymentService {
}
```

Conceptually:

```text
Original PaymentService
        ↓
Spring AOP Infrastructure
        ↓
Proxy
        ↓
Container exposes managed object
```

Then:

```java
PaymentService service =
        applicationContext.getBean(PaymentService.class);
```

`service` may refer to the proxy.

---

# 42. Why Does Spring Use Proxies?

Proxies enable cross-cutting concerns without putting infrastructure code directly into business logic.

Examples:

```text
@Transactional
@Async
@Cacheable
@Retryable
Security
Logging
Metrics
Tracing
```

Conceptually:

```text
Business Method
      ↑
      |
Proxy
      ↓
Transaction
Security
Caching
Metrics
Tracing
      ↓
Business Method
```

---

# 43. Important Correction: AOP Proxy Is Not a Separate Lifecycle Step

For interview purposes, you can visualize:

```text
BeanPostProcessor
       ↓
AOP Proxy
```

But internally, proxy creation is generally performed by an appropriate `BeanPostProcessor`, especially during the post-initialization processing.

Therefore, avoid saying:

```text
BeanPostProcessor After Initialization
        ↓
Some completely separate proxy lifecycle
```

Instead say:

```text
BeanPostProcessor
        ↓
AOP infrastructure may wrap the bean with a proxy
```

---

# 44. Important Correction: @Bean Does Not Mean "Concrete Class Only"

This statement is incorrect:

```text
Spring creates objects of those who have @Bean annotation.
```

More accurately:

> `@Bean` marks a method whose return value is registered as a Spring bean.

Example:

```java
@Configuration
public class AppConfig {

    @Bean
    public UserService userService() {
        return new UserService();
    }
}
```

Here:

```text
@Bean method
     ↓
userService()
     ↓
UserService object
     ↓
Spring manages returned object
```

The declared return type does not have to be a concrete class.

For example:

```java
@Bean
public PaymentService paymentService() {
    return new PaymentServiceImpl();
}
```

The important thing is that the method produces the object that Spring should manage.

---

# 45. Prototype Bean Destruction — Important Interview Question

Consider:

```java
@Component
@Scope("prototype")
public class ReportGenerator {
}
```

Spring creates the prototype instance:

```text
getBean()
   ↓
New ReportGenerator
```

But after giving the prototype bean to the caller, Spring generally does **not** manage its complete destruction lifecycle.

Therefore:

```text
Singleton
    → Creation + destruction managed by Spring

Prototype
    → Creation managed by Spring
    → Destruction generally NOT managed automatically
```

This is a very common interview question.

---

# 46. Simplified Lifecycle to Memorize for Interviews

If the interviewer asks:

> "Explain the Spring Bean lifecycle."

Give this answer:

```text
1. Spring Boot starts
        ↓
2. ApplicationContext is created
        ↓
3. Component scanning / @Bean definitions are processed
        ↓
4. BeanDefinition is created/registered
        ↓
5. Bean is instantiated
        ↓
6. Dependencies are injected
        ↓
7. Aware callbacks execute
        ↓
8. BeanPostProcessor.beforeInitialization()
        ↓
9. @PostConstruct
        ↓
10. InitializingBean.afterPropertiesSet()
        ↓
11. Custom init method
        ↓
12. BeanPostProcessor.afterInitialization()
        ↓
13. AOP proxy may be applied
        ↓
14. Bean is ready for use
        ↓
15. Application runs
        ↓
16. Shutdown triggered
        ↓
17. @PreDestroy
        ↓
18. DisposableBean.destroy()
        ↓
19. Custom destroy method
        ↓
20. Bean destroyed
```

---

# 47. One-Line Memory Trick

Remember:

```text
CREATE
  ↓
INJECT
  ↓
AWARE
  ↓
BEFORE INIT
  ↓
INIT
  ↓
AFTER INIT
  ↓
USE
  ↓
DESTROY
```

Or:

```text
C → I → A → B → I → A → U → D
```

Where:

```text
C = Create
I = Inject
A = Aware
B = Before Initialization
I = Initialization
A = After Initialization
U = Use
D = Destroy
```

---

# 48. Final Architecture View

```text
                    SPRING BOOT
                         │
                         ▼
              SpringApplication.run()
                         │
                         ▼
                 ApplicationContext
                         │
                         ▼
              DefaultListableBeanFactory
                         │
                         ▼
                  BeanDefinitions
                         │
                         ▼
                  Bean Instantiation
                         │
                         ▼
                Dependency Injection
                         │
                         ▼
                   Aware Callbacks
                         │
                         ▼
          BeanPostProcessor.beforeInit()
                         │
                         ▼
                  @PostConstruct
                         │
                         ▼
              afterPropertiesSet()
                         │
                         ▼
                  Custom init-method
                         │
                         ▼
         BeanPostProcessor.afterInit()
                         │
                         ▼
                AOP / Proxy Support
                  ┌──────┴──────┐
                  ▼             ▼
              JDK Proxy    CGLIB-based
                            Proxy
                  └──────┬──────┘
                         ▼
                 Bean Ready for Use
                         │
                         ▼
                 Application Running
                         │
                         ▼
                  Shutdown Triggered
                         │
                         ▼
                    @PreDestroy
                         │
                         ▼
                DisposableBean
                    destroy()
                         │
                         ▼
                 Custom destroy-method
                         │
                         ▼
                  Bean Destroyed
```

---

# 49. Most Important Interview Questions

### Q1. What is a Spring Bean?

An object whose lifecycle and dependencies are managed by the Spring IoC container.

---

### Q2. What is BeanDefinition?

Metadata describing how Spring should create and manage a bean.

```text
BeanDefinition ≠ actual object
```

---

### Q3. What happens first — constructor or dependency injection?

The constructor executes first.

```text
Constructor
    ↓
Dependency Injection
```

For constructor injection, dependencies are resolved before the constructor is invoked, but the conceptual bean lifecycle still starts with instantiation through the resolved constructor.

---

### Q4. When does @PostConstruct execute?

After bean instantiation and dependency population, during the initialization phase.

---

### Q5. What is BeanPostProcessor?

An extension mechanism that allows Spring to process beans before and after initialization.

```java
postProcessBeforeInitialization()
postProcessAfterInitialization()
```

---

### Q6. Where does Spring AOP proxying happen?

AOP proxying is commonly performed by Spring's `BeanPostProcessor` infrastructure, especially during post-initialization processing.

---

### Q7. What happens with @Transactional?

Spring typically exposes the bean through an AOP proxy.

```text
Client
  ↓
Transaction Proxy
  ↓
Real Bean
  ↓
Method
```

---

### Q8. Does Spring create singleton beans at startup?

For normal non-lazy singleton beans in an `ApplicationContext`, Spring generally eagerly instantiates them during context startup.

With:

```java
@Lazy
```

creation can be deferred.

---

### Q9. Does Spring manage prototype destruction?

No, not fully. Spring creates prototype instances but generally does not manage their complete destruction lifecycle.

---

### Q10. What happens when Spring Boot shuts down?

Spring closes the application context and invokes destruction callbacks for beans whose destruction lifecycle it manages.

Common callbacks:

```text
@PreDestroy
DisposableBean.destroy()
custom destroy method
```

---

# 50. The Core Concept

The most important idea is:

```text
Spring does NOT simply do:

new Object()
```

Instead, Spring manages the object through a complete lifecycle:

```text
BeanDefinition
      ↓
Instantiation
      ↓
Dependency Injection
      ↓
Aware callbacks
      ↓
BeanPostProcessors
      ↓
Initialization
      ↓
Possible Proxy
      ↓
Container Management
      ↓
Application Usage
      ↓
Destruction
```

This lifecycle is the foundation for many Spring features:

```text
Dependency Injection
AOP
Transactions
Security
Caching
Async Processing
Logging
Metrics
Tracing
Retry
Event Handling
Resource Management
```

That is why understanding the **Spring Bean Lifecycle** is fundamental for a Senior Java / Spring Boot interview.
**/
