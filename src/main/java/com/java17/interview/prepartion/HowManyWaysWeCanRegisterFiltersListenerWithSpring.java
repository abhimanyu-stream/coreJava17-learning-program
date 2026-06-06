package com.java17.interview.prepartion;

public class HowManyWaysWeCanRegisterFiltersListenerWithSpring {
}
/**
 * In Spring Boot / Spring MVC, filters and listeners can be registered in multiple ways. This is a common interview question because it tests Servlet + Spring Boot understanding.
 *
 * 1. Register Filter as @Component (Auto Registration)
 *
 * Simplest way.
 *
 * @Component
 * public class LoggingFilter extends OncePerRequestFilter {
 *
 *     @Override
 *     protected void doFilterInternal(
 *             HttpServletRequest request,
 *             HttpServletResponse response,
 *             FilterChain filterChain)
 *             throws ServletException, IOException {
 *
 *         System.out.println("Logging request");
 *
 *         filterChain.doFilter(request, response);
 *     }
 * }
 *
 * Spring Boot auto-detects it.
 *
 * Flow
 * Spring scans @Component
 *    |
 * creates filter bean
 *    |
 * auto registers in servlet container
 * When used
 * simple logging
 * request tracing
 * correlation id
 * 2. Register Filter using FilterRegistrationBean ⭐ (Most configurable)
 *
 * Best interview answer.
 *
 * @Configuration
 * public class FilterConfig {
 *
 *     @Bean
 *     public FilterRegistrationBean<LoggingFilter> loggingFilter() {
 *
 *         FilterRegistrationBean<LoggingFilter> registration =
 *                 new FilterRegistrationBean<>();
 *
 *         registration.setFilter(new LoggingFilter());
 *
 *         registration.addUrlPatterns("/api/*");
 *
 *         registration.setOrder(1);
 *
 *         return registration;
 *     }
 * }
 * Can configure
 * URL patterns
 * order
 * async support
 * init params
 * 3. Register Filter in Spring Security Chain
 *
 * Special case.
 *
 * @Bean
 * SecurityFilterChain securityFilterChain(HttpSecurity http)
 *         throws Exception {
 *
 *     http.addFilterBefore(
 *             new JwtAuthFilter(),
 *             UsernamePasswordAuthenticationFilter.class
 *     );
 *
 *     return http.build();
 * }
 * Used for
 * JWT auth
 * security filters
 * auth token validation
 *
 * This filter is not normal servlet filter registration; it becomes part of SecurityFilterChain
 *
 * 4. @WebFilter + @ServletComponentScan
 *
 * Servlet API way.
 *
 * @WebFilter(urlPatterns = "/*")
 * public class LoggingFilter implements Filter {
 *
 *     @Override
 *     public void doFilter(ServletRequest req,
 *                          ServletResponse res,
 *                          FilterChain chain)
 *             throws IOException, ServletException {
 *
 *         System.out.println("Request intercepted");
 *
 *         chain.doFilter(req, res);
 *     }
 * }
 *
 * Enable scan:
 *
 * @SpringBootApplication
 * @ServletComponentScan
 * public class Application {
 * }
 * This is pure servlet style
 *
 * Container registers it.
 *
 * Not typical in Spring Boot modern apps.
 *
 * FILTER REGISTRATION SUMMARY
 * Method	Used In	Configurable
 * @Component Simple Spring Boot	Low
 * FilterRegistrationBean	Production	High ⭐
 * Security addFilterBefore()	Spring Security	Security only
 * @WebFilter Servlet API	Medium
 * LISTENER registration ways
 *
 * Listeners also have multiple ways.
 *
 * 1. @WebListener + @ServletComponentScan
 *
 * Pure servlet style.
 *
 * @WebListener
 * public class MyContextListener
 *         implements ServletContextListener {
 *
 *     @Override
 *     public void contextInitialized(ServletContextEvent sce) {
 *         System.out.println("App started");
 *     }
 *
 *     @Override
 *     public void contextDestroyed(ServletContextEvent sce) {
 *         System.out.println("App stopped");
 *     }
 * }
 *
 * Enable:
 *
 * @ServletComponentScan
 * @SpringBootApplication
 * public class Application {
 * }
 * 2. ServletListenerRegistrationBean ⭐
 *
 * Spring Boot configurable way.
 *
 * @Configuration
 * public class ListenerConfig {
 *
 *     @Bean
 *     public ServletListenerRegistrationBean<MyListener> listener() {
 *
 *         return new ServletListenerRegistrationBean<>(
 *                 new MyListener()
 *         );
 *     }
 * }
 * 3. Spring Event Listener (Spring-native alternative)
 *
 * Instead of servlet listener:
 *
 * @Component
 * public class StartupListener {
 *
 *     @EventListener
 *     public void onStartup(ApplicationReadyEvent event) {
 *         System.out.println("Application started");
 *     }
 * }
 *
 * Common Spring lifecycle events:
 *
 * ApplicationStartedEvent
 * ApplicationReadyEvent
 * ContextClosedEvent
 * LISTENER SUMMARY
 * Method	Type	Best Use
 * @WebListener Servlet API	legacy
 * ServletListenerRegistrationBean	Spring Boot	configurable ⭐
 * @EventListener Spring-native	application lifecycle ⭐
 * Interview final answer
 * Filters: 4 ways
 * @Component
 * FilterRegistrationBean
 * @WebFilter
 * Spring Security addFilterBefore/addFilterAfter
 * Listeners: 3 ways
 * @WebListener
 * ServletListenerRegistrationBean
 * @EventListener
 * Interview one-liner
 *
 * “In Spring Boot, filters can be registered via @Component, FilterRegistrationBean, @WebFilter, or SecurityFilterChain. Listeners can be registered using @WebListener, ServletListenerRegistrationBean, or Spring lifecycle events via @EventListener.”
 *
 *
 */