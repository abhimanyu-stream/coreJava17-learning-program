/**
 * In Spring Boot, the bean lifecycle describes how objects (beans) are:
 *
 * Created
 * Initialized
 * Used inside Spring container
 * Destroyed
 *
 * Spring manages this automatically through the IoC Container (ApplicationContext).
 *
 * Full Spring Bean Lifecycle
 * 1. Container Starts
 *
 * When Spring Boot application starts:
 *
 * ApplicationContext is created
 * Component scanning begins
 * Beans are discovered from:
 * @Component
 * @Service
 * @Repository
 * @Controller
 * @Configuration
 * @Bean
 * 2. Bean Instantiation (Object Creation)
 *
 * Spring creates bean objects.
 *
 * Example:
 *
 * @Component
 * public class UserService {
 *
 *     public UserService() {
 *         System.out.println("Constructor called");
 *     }
 * }
 *
 * Constructor runs first.
 *
 * 3. Dependency Injection
 *
 * Spring injects dependencies using:
 *
 * Constructor Injection
 * Setter Injection
 * Field Injection
 *
 * Example:
 *
 * @Service
 * public class OrderService {
 *
 *     @Autowired
 *     private UserService userService;
 * }
 *
 * Spring injects UserService bean into OrderService.
 *
 * 4. Aware Interfaces (Optional)
 *
 * Spring can provide internal container details.
 *
 * Examples:
 *
 * BeanNameAware
 * ApplicationContextAware
 * BeanFactoryAware
 *
 * Example:
 *
 * @Component
 * public class DemoBean implements BeanNameAware {
 *
 *     @Override
 *     public void setBeanName(String name) {
 *         System.out.println(name);
 *     }
 * }
 * 5. BeanPostProcessor Before Initialization
 *
 * Spring executes:
 *
 * postProcessBeforeInitialization()
 *
 * Used internally for:
 *
 * proxies
 * AOP
 * transactions
 * custom processing
 * 6. Initialization Phase
 *
 * Bean initialization methods execute.
 *
 * Three common ways:
 *
 * a) @PostConstruct
 * @PostConstruct
 * public void init() {
 *     System.out.println("Bean initialized");
 * }
 * b) InitializingBean
 * public class DemoBean implements InitializingBean {
 *
 *     @Override
 *     public void afterPropertiesSet() {
 *         System.out.println("Initialized");
 *     }
 * }
 * c) Custom Init Method
 * @Bean(initMethod = "start")
 * public DemoBean demoBean() {
 *     return new DemoBean();
 * }
 * 7. Bean Ready for Use
 *
 * Now bean is fully initialized and available in Spring container.
 *
 * Used by:
 *
 * Controllers
 * Services
 * Kafka consumers
 * REST APIs
 * Schedulers
 * Security filters
 * etc.
 * 8. BeanPostProcessor After Initialization
 *
 * Spring runs:
 *
 * postProcessAfterInitialization()
 *
 * This is where:
 *
 * AOP proxy creation
 * transactional proxy
 * security proxy
 *
 * often happens.
 *
 * Example:
 *
 * @Transactional
 * @Async
 * @Cacheable
 *
 * Spring may wrap original bean with proxy object.
 *
 * Bean Destruction Lifecycle
 *
 * When application shuts down:
 *
 * JVM shutdown
 * ApplicationContext.close()
 * graceful shutdown
 *
 * Spring destroys beans.
 *
 * 9. Destruction Methods
 * a) @PreDestroy
 * @PreDestroy
 * public void cleanup() {
 *     System.out.println("Cleaning resources");
 * }
 * b) DisposableBean
 * public class DemoBean implements DisposableBean {
 *
 *     @Override
 *     public void destroy() {
 *         System.out.println("Destroyed");
 *     }
 * }
 * c) Custom Destroy Method
 * @Bean(destroyMethod = "stop")
 * public DemoBean demoBean() {
 *     return new DemoBean();
 * }
 * Complete Lifecycle Flow
 * Spring Container Starts
 *         ↓
 * Bean Object Created
 *         ↓
 * Dependencies Injected
 *         ↓
 * Aware Interfaces
 *         ↓
 * Before Initialization
 *         ↓
 * @PostConstruct / afterPropertiesSet()
 *         ↓
 * After Initialization
 *         ↓
 * Bean Ready to Use
 *         ↓
 * Application Running
 *         ↓
 * Shutdown Triggered
 *         ↓
 * @PreDestroy / destroy()
 *         ↓
 * Bean Removed
 * Important Bean Scopes
 * Singleton (Default)
 * @Component
 * @Scope("singleton")
 * One bean per container
 * Shared everywhere
 * Created once during startup
 * Prototype
 * @Scope("prototype")
 * New bean every request
 * Spring does NOT manage destroy phase fully
 * Request Scope
 * @RequestScope
 *
 * New bean per HTTP request.
 *
 * Session Scope
 * @SessionScope
 *
 * One bean per user session.
 *
 * Real Production Examples
 * Database Connection Pools
 *
 * Beans initialized:
 *
 * HikariCP
 * datasource
 * JPA entity manager
 *
 * Destroyed during shutdown.
 *
 * Kafka Beans
 *
 * Examples:
 *
 * KafkaProducer
 * KafkaConsumer
 * KafkaAdmin
 *
 * During startup:
 *
 * connections established
 * topics checked
 *
 * During shutdown:
 *
 * consumers closed
 * producers flushed
 * Scheduler Beans
 * @Scheduled
 *
 * Scheduler thread pools initialize during startup and stop during shutdown.
 *
 * Example Full Bean Lifecycle Code
 * @Component
 * public class LifeCycleBean {
 *
 *     public LifeCycleBean() {
 *         System.out.println("1 Constructor");
 *     }
 *
 *     @PostConstruct
 *     public void init() {
 *         System.out.println("2 Init");
 *     }
 *
 *     @PreDestroy
 *     public void destroy() {
 *         System.out.println("3 Destroy");
 *     }
 * }
 *
 * Output:
 *
 * 1 Constructor
 * 2 Init
 * Application Running...
 * 3 Destroy
 * Advanced Internal Concepts
 *
 * Spring internally uses:
 *
 * BeanFactory
 * ApplicationContext
 * BeanDefinition
 * BeanPostProcessor
 * CGLIB
 * JDK Dynamic Proxy
 *
 * for lifecycle management.
 *
 * Very Important Interview Questions
 * Q1. Are beans recreated after restart?
 *
 * Yes.
 * When Spring Boot restarts:
 *
 * old container destroyed
 * new container created
 * beans recreated
 *
 * Singleton beans do NOT survive restart.
 *
 * Q2. Does bean data persist after restart?
 *
 * Normally no.
 *
 * Bean memory is RAM-based.
 *
 * Persist data using:
 *
 * Database
 * Kafka
 * Redis
 * File system
 * Q3. Which beans are lazily initialized?
 * @Lazy
 *
 * Beans created only when first used.
 *
 * Lifecycle + Proxy Visualization
 * Original Bean
 *       ↓
 * Spring AOP Proxy
 *       ↓
 * @Transactional Proxy
 *       ↓
 * Final Bean Used by App
 *
 * This is why Spring lifecycle is powerful for enterprise systems.
 *
 * Advanced Internal Concepts
 * Spring internally uses:
 * BeanFactory
 * ApplicationContext
 * BeanDefinition
 * BeanPostProcessor
 * CGLIB
 * JDK Dynamic Proxy, combine Spring Container Starts
 *         ↓
 * Bean Object Created
 *         ↓
 * Dependencies Injected
 *         ↓
 * Aware Interfaces
 *         ↓
 * Before Initialization
 *         ↓
 * @PostConstruct / afterPropertiesSet()
 *         ↓
 * After Initialization
 *         ↓
 * Bean Ready to Use
 *         ↓
 * Application Running
 *         ↓
 * Shutdown Triggered
 *         ↓
 * @PreDestroy / destroy()
 *         ↓
 * Bean Removed
 * Advanced Spring Bean Lifecycle + Internal Working
 *
 * In Spring Boot and Spring Framework, the Spring container internally combines:
 *
 * BeanFactory
 * ApplicationContext
 * BeanDefinition
 * BeanPostProcessor
 * CGLIB
 * JDK Dynamic Proxy
 *
 * with the bean lifecycle pipeline.
 *
 * Full Internal Spring Flow
 * Spring Boot Application Starts
 *         ↓
 * ApplicationContext Created
 *         ↓
 * Bean Definitions Loaded
 *         ↓
 * BeanFactory Created
 *         ↓
 * Bean Metadata Registered
 *         ↓
 * Bean Object Instantiated
 *         ↓
 * Dependencies Injected
 *         ↓
 * Aware Interfaces Called
 *         ↓
 * BeanPostProcessor Before Init
 *         ↓
 * @PostConstruct / afterPropertiesSet()
 *         ↓
 * BeanPostProcessor After Init
 *         ↓
 * AOP Proxy Creation (CGLIB / JDK Proxy)
 *         ↓
 * Bean Ready in IOC Container
 *         ↓
 * Application Running
 *         ↓
 * Shutdown Hook Triggered
 *         ↓
 * @PreDestroy / destroy()
 *         ↓
 * Bean Removed from Container
 * 1. Spring Boot Starts
 *
 * Main class:
 *
 * @SpringBootApplication
 * public class App {
 *     public static void main(String[] args) {
 *         SpringApplication.run(App.class, args);
 *     }
 * }
 *
 * Internally:
 *
 * SpringApplication.run()
 *         ↓
 * Creates ApplicationContext
 * 2. ApplicationContext Created
 *
 * ApplicationContext is the advanced Spring container.
 *
 * It internally extends:
 *
 * ApplicationContext
 *         ↓
 * BeanFactory
 * Difference
 * Feature	BeanFactory	ApplicationContext
 * Basic IOC	Yes	Yes
 * Lazy loading	Default	No
 * Events	No	Yes
 * AOP support	Limited	Full
 * Internationalization	No	Yes
 * Enterprise features	Minimal	Full
 * 3. BeanDefinition Created
 *
 * Spring scans:
 *
 * @Component
 * @Service
 * @Repository
 * @Configuration
 * @Bean
 *
 * For every bean, Spring creates:
 *
 * BeanDefinition
 *
 * BeanDefinition contains metadata:
 *
 * Bean Class
 * Scope
 * Lazy
 * Dependencies
 * Init Method
 * Destroy Method
 * Proxy Info
 * Constructor Info
 *
 * Example:
 *
 * UserService BeanDefinition
 *
 * NOT actual object yet.
 *
 * 4. BeanFactory Stores Bean Definitions
 *
 * DefaultListableBeanFactory
 *
 * stores all BeanDefinitions.
 *
 * Internally:
 *
 * Map<String, BeanDefinition>
 *
 * Example:
 *
 * "userService" → BeanDefinition
 * "orderService" → BeanDefinition
 * 5. Bean Object Instantiation
 *
 * Spring creates actual object using:
 *
 * Reflection API
 * Constructor invocation
 *
 * Example:
 *
 * new UserService()
 *
 * Internally:
 *
 * ConstructorResolver
 * InstantiationStrategy
 * 6. Dependency Injection
 *
 * Spring resolves dependencies recursively.
 *
 * Example:
 *
 * @Service
 * class OrderService {
 *
 *     @Autowired
 *     UserService userService;
 * }
 *
 * Flow:
 *
 * Create OrderService
 *         ↓
 * Needs UserService
 *         ↓
 * Find UserService BeanDefinition
 *         ↓
 * Create UserService
 *         ↓
 * Inject into OrderService
 * 7. Aware Interfaces
 *
 * Spring injects internal container information.
 *
 * Examples:
 *
 * BeanNameAware
 * BeanFactoryAware
 * ApplicationContextAware
 * EnvironmentAware
 *
 * Example:
 *
 * @Component
 * public class DemoBean implements BeanNameAware {
 *
 *     @Override
 *     public void setBeanName(String name) {
 *         System.out.println(name);
 *     }
 * }
 * 8. BeanPostProcessor Before Initialization
 *
 * Spring executes:
 *
 * postProcessBeforeInitialization()
 *
 * Purpose:
 *
 * modify bean
 * validate bean
 * inject extra logic
 *
 * Example:
 *
 * @Component
 * public class MyProcessor implements BeanPostProcessor {
 *
 *     @Override
 *     public Object postProcessBeforeInitialization(
 *             Object bean,
 *             String beanName) {
 *
 *         return bean;
 *     }
 * }
 * 9. Initialization Phase
 *
 * Three major init mechanisms:
 *
 * a) @PostConstruct
 * @PostConstruct
 * public void init() {
 * }
 * b) InitializingBean
 * afterPropertiesSet()
 * c) Custom Init Method
 * @Bean(initMethod = "start")
 * 10. BeanPostProcessor After Initialization
 *
 * Spring executes:
 *
 * postProcessAfterInitialization()
 *
 * This is VERY important.
 *
 * Here Spring creates:
 *
 * AOP proxy
 * transactional proxy
 * async proxy
 * security proxy
 * 11. Proxy Creation
 *
 * If bean contains:
 *
 * @Transactional
 * @Async
 * @Cacheable
 *
 * Spring wraps bean with proxy.
 *
 * JDK Dynamic Proxy
 *
 * Used when bean implements interface.
 *
 * Example:
 *
 * interface PaymentService
 *
 * Spring creates runtime proxy implementing same interface.
 *
 * Flow:
 *
 * Client
 *    ↓
 * Proxy
 *    ↓
 * Real Bean
 * CGLIB Proxy
 *
 * Used when no interface exists.
 *
 * Spring creates subclass dynamically.
 *
 * UserService$$EnhancerBySpringCGLIB
 * Proxy Flow
 * Original Bean
 *         ↓
 * BeanPostProcessor
 *         ↓
 * Proxy Factory
 *         ↓
 * JDK Proxy OR CGLIB
 *         ↓
 * Proxy Bean Stored in Container
 * Example Transaction Proxy
 * @Transactional
 * public void transferMoney() {
 * }
 *
 * Internally:
 *
 * Client
 *   ↓
 * Transaction Proxy
 *   ↓
 * Begin Transaction
 *   ↓
 * Real Method
 *   ↓
 * Commit/Rollback
 * 12. Bean Ready in IOC Container
 *
 * Now bean stored inside container:
 *
 * SingletonObjects Cache
 *
 * Internally:
 *
 * Map<String,Object>
 * 13. Application Running
 *
 * Beans used by:
 *
 * REST Controllers
 * Kafka Consumers
 * Schedulers
 * Security
 * JPA
 * WebSockets
 *
 * etc.
 *
 * 14. Shutdown Triggered
 *
 * Triggered by:
 *
 * Ctrl + C
 * SIGTERM
 * ApplicationContext.close()
 * Kubernetes Shutdown
 * Docker Stop
 *
 * Spring executes graceful shutdown.
 *
 * 15. Destruction Phase
 *
 * Spring calls:
 *
 * @PreDestroy
 * destroy()
 * custom destroyMethod
 *
 * Example:
 *
 * @PreDestroy
 * public void cleanup() {
 * }
 * 16. Bean Removed
 *
 * Spring removes bean from caches:
 *
 * singletonObjects
 * earlySingletonObjects
 * factoryBeanObjects
 *
 * Resources closed:
 *
 * DB pools
 * Kafka consumers
 * thread pools
 * sockets
 * Complete Internal Architecture
 * SpringApplication.run()
 *         ↓
 * ApplicationContext
 *         ↓
 * DefaultListableBeanFactory
 *         ↓
 * BeanDefinitions Registered
 *         ↓
 * Create Bean via Reflection
 *         ↓
 * Dependency Injection
 *         ↓
 * Aware Interfaces
 *         ↓
 * BeanPostProcessor Before Init
 *         ↓
 * @PostConstruct
 *         ↓
 * BeanPostProcessor After Init
 *         ↓
 * Create Proxy (AOP)
 *    ↓             ↓
 * JDK Proxy     CGLIB
 *         ↓
 * Store Proxy Bean
 *         ↓
 * Application Running
 *         ↓
 * Shutdown Hook
 *         ↓
 * Destroy Methods
 *         ↓
 * Remove Bean
 * Important Internal Classes
 * Internal Class	Purpose
 * DefaultListableBeanFactory	Core bean storage
 * BeanDefinition	Bean metadata
 * AbstractAutowireCapableBeanFactory	Bean creation
 * AutowiredAnnotationBeanPostProcessor	Handles @Autowired
 * CommonAnnotationBeanPostProcessor	Handles @PostConstruct
 * AnnotationAwareAspectJAutoProxyCreator	Creates AOP proxies
 * CglibAopProxy	CGLIB proxy creation
 * JdkDynamicAopProxy	Interface proxy creation
 * Real Enterprise Example
 *
 * Suppose:
 *
 * @Service
 * @Transactional
 * public class PaymentService {
 * }
 *
 * Internal lifecycle:
 *
 * BeanDefinition Created
 *         ↓
 * PaymentService Object Created
 *         ↓
 * Dependencies Injected
 *         ↓
 * @PostConstruct
 *         ↓
 * Transaction Proxy Created
 *         ↓
 * Proxy Stored in Container
 *         ↓
 * Controller Uses Proxy
 *         ↓
 * Every Method Call Intercepted
 *         ↓
 * Transaction Started
 *         ↓
 * Method Executed
 *         ↓
 * Commit/Rollback
 * Key Concept
 *
 * Spring container usually stores:
 *
 * PROXY OBJECT
 *
 * NOT always original bean.
 *
 * That is the foundation of:
 *
 * AOP
 * Transactions
 * Security
 * Async
 * Caching
 * Logging
 * Metrics
 * Retry
 * Distributed tracing
 */