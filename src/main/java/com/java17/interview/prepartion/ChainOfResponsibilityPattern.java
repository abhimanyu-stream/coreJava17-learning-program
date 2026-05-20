package com.java17.interview.prepartion;

public class ChainOfResponsibilityPattern {
}
/**
 * Pass request through chain until handled.
 *
 * Example:
 *
 * Request
 *   → Auth Filter
 *   → Logging Filter
 *   → Validation Filter
 *
 * Spring:
 *
 * Servlet filters
 * Spring Security filter chain
 *
 * Interview line:
 *
 * “Request passes through handlers in sequence.”
 *
 */

/**
 * Spring Security real-world equivalent
 *
 * Spring does same thing with filter chain
 *
 * Request flow
 * HTTP Request
 *    |
 * SecurityContextHolderFilter
 *    |
 * CorsFilter
 *    |
 * CsrfFilter
 *    |
 * UsernamePasswordAuthenticationFilter
 *    |
 * AuthorizationFilter
 *    |
 * ExceptionTranslationFilter
 *    |
 * Controller
 *
 * Each filter:
 *
 * inspects request
 * maybe modifies it
 * maybe blocks it
 * otherwise passes next
 * 4. Spring Security configuration example
 * Security config
 * @Configuration
 * @EnableWebSecurity
 * public class SecurityConfig {
 *
 *     @Bean
 *     SecurityFilterChain securityFilterChain(HttpSecurity http)
 *             throws Exception {
 *
 *         http
 *             .csrf(csrf -> csrf.disable())
 *
 *             .authorizeHttpRequests(auth -> auth
 *                 .requestMatchers("/public").permitAll()
 *                 .anyRequest().authenticated()
 *             )
 *
 *             .httpBasic(Customizer.withDefaults());
 *
 *         return http.build();
 *     }
 * }
 * Internally what happens
 *
 * Spring builds a chain:
 *
 * Request
 *    |
 * SecurityContextHolderFilter
 *    |
 * BasicAuthenticationFilter
 *    |
 * AuthorizationFilter
 *    |
 * Controller
 * 5. Custom filter = custom handler in chain
 *
 * Example:
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
 *         System.out.println("Request URI: " + request.getRequestURI());
 *
 *         // pass to next handler
 *         filterChain.doFilter(request, response);
 *
 *         System.out.println("Response sent");
 *     }
 * }
 * Add to chain
 * @Bean
 * SecurityFilterChain securityFilterChain(HttpSecurity http)
 *         throws Exception {
 *
 *     http
 *         .addFilterBefore(
 *             new LoggingFilter(),
 *             UsernamePasswordAuthenticationFilter.class
 *         )
 *
 *         .authorizeHttpRequests(auth -> auth
 *             .anyRequest().authenticated()
 *         );
 *
 *     return http.build();
 * }
 * Flow now
 * Request
 *    |
 * LoggingFilter
 *    |
 * UsernamePasswordAuthenticationFilter
 *    |
 * AuthorizationFilter
 *    |
 * Controller
 * 6. How chain stops
 *
 * Suppose auth fails:
 *
 * if(invalidToken){
 *    response.sendError(401);
 *    return;   // chain stops
 * }
 *
 * Then:
 *
 * Request
 *    |
 * JWT Filter
 *    |
 * 401 Unauthorized
 *    X
 * Controller never called
 *
 * That is classic CoR behavior.
 *
 * JWT example (real interview favorite)
 * @Component
 * public class JwtAuthFilter extends OncePerRequestFilter {
 *
 *     @Override
 *     protected void doFilterInternal(
 *             HttpServletRequest request,
 *             HttpServletResponse response,
 *             FilterChain filterChain)
 *             throws ServletException, IOException {
 *
 *         String token = request.getHeader("Authorization");
 *
 *         if (token == null) {
 *             response.sendError(401, "Missing token");
 *             return; // stop chain
 *         }
 *
 *         System.out.println("Token validated");
 *
 *         filterChain.doFilter(request, response); // next filter
 *     }
 * }
 * 7. Why filterChain.doFilter() is important
 *
 * This line:
 *
 * filterChain.doFilter(request, response);
 *
 * means:
 *
 * “Pass request to next handler”
 *
 * Equivalent CoR:
 *
 * next.handle(request);
 * Mapping design pattern → Spring Security
 * Design Pattern	Spring Security Equivalent
 * Handler	Filter
 * next.handle()	filterChain.doFilter()
 * Chain setup	SecurityFilterChain
 * Stop chain	return / sendError
 * Final handler	Controller
 * Interview diagram
 * Client Request
 *     |
 * LoggingFilter
 *     |
 * JwtAuthFilter
 *     |
 * AuthorizationFilter
 *     |
 * Controller
 *
 * Each filter decides:
 *
 * Continue?
 *  YES → next filter
 *  NO → stop
 * Interview one-liner
 *
 * “Spring Security filter chain is a real-world Chain of Responsibility where each filter processes the request sequentially and either forwards it using doFilter() or stops the chain.”
 *
 *
 *
 */