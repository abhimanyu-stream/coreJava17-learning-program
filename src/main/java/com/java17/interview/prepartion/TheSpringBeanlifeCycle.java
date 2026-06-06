package com.java17.interview.prepartion;

public class TheSpringBeanlifeCycle {
}
/***
 * The Spring Bean lifecycle encompasses the series of steps a bean undergoes from its creation to its destruction within the Spring container. Here's a breakdown of the key stages:
 * 1. Instantiation:
 * The Spring container creates an instance of the bean using its constructor or factory method.
 * 2. Population of Properties:
 * The container injects dependencies and sets property values defined in the bean definition.
 * 3. Bean Aware Interfaces:
 * If the bean implements certain interfaces, such as BeanNameAware, BeanFactoryAware, or ApplicationContextAware, the container invokes the corresponding methods to provide the bean with information about its name, factory, or application context.
 * 4. BeanPostProcessor (Pre-Initialization):
 * BeanPostProcessor implementations can modify the bean before initialization. This is where AOP (Aspect Oriented Programming) logic is often applied.
 * 5. InitializingBean:
 * If the bean implements the InitializingBean interface, its afterPropertiesSet() method is called.
 * 6. Custom Init Method:
 * If a custom initialization method is specified using @PostConstruct annotation or the init-method attribute in XML configuration, that method is executed.
 * 7. Bean Ready for Use:
 * The bean is now fully initialized and ready to be used by the application.
 * 8. BeanPostProcessor (Post-Initialization):
 * BeanPostProcessor implementations can modify the bean after initialization.
 * 9. DisposableBean:
 * If the bean implements the DisposableBean interface, its destroy() method is called when the container is closing.
 * 10. Custom Destroy Method:
 * If a custom destroy method is specified using @PreDestroy annotation or the destroy-method attribute in XML configuration, that method is executed.
 *
 *
 *
 * In summary:
 * Instantiation: The bean is created.
 * Population: Dependencies are injected, and properties are set.
 * Initialization: The bean is initialized, and any custom initialization logic is executed.
 * Ready: The bean is ready for use.
 * Destruction: The bean is destroyed, and any cleanup logic is performed.
 * */