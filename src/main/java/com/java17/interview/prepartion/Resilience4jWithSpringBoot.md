# Resilience4j: Fault Tolerance Patterns in Spring Boot

## Overview

**Resilience4j** is a lightweight, zero-dependency fault tolerance library designed for functional programming.

It provides six core resilience patterns:

1. **Circuit Breaker** - Stop requests to failing services
2. **Retry** - Automatically retry failed operations
3. **Bulkhead** - Isolate resources to prevent cascading failures
4. **RateLimiter** - Throttle request rate
5. **TimeLimiter** - Set execution timeouts
6. **Cache** - Cache results to reduce load

All can be combined for comprehensive resilience.

---

## Setup: Maven Dependencies

```xml
<!-- Core Resilience4j -->
<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-spring-boot3</artifactId>
    <version>2.1.0</version>
</dependency>

<!-- All modules -->
<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-circuitbreaker</artifactId>
    <version>2.1.0</version>
</dependency>

<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-retry</artifactId>
    <version>2.1.0</version>
</dependency>

<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-bulkhead</artifactId>
    <version>2.1.0</version>
</dependency>

<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-ratelimiter</artifactId>
    <version>2.1.0</version>
</dependency>

<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-timelimiter</artifactId>
    <version>2.1.0</version>
</dependency>

<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-spring-boot3-actuator</artifactId>
    <version>2.1.0</version>
</dependency>

<!-- Spring Boot Actuator for monitoring -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

---

# 1. Circuit Breaker

## What is Circuit Breaker?

Prevents cascading failures by stopping requests to a failing service.

**States:**

```
CLOSED (normal) → requests pass through
    ↓
If failure rate exceeds threshold
    ↓
OPEN (circuit broken) → requests rejected immediately
    ↓
After waitDuration
    ↓
HALF_OPEN (testing) → allows one request to test recovery
    ↓
If success: back to CLOSED
If fail: back to OPEN
```

## Configuration

```yaml
resilience4j:
  circuitbreaker:
    instances:
      paymentService:
        registerHealthIndicator: true
        slidingWindowSize: 100
        failureRateThreshold: 50
        slowCallRateThreshold: 50
        slowCallDurationThreshold: 2000ms
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 30s
        recordExceptions:
          - java.io.IOException
          - java.net.ConnectException
        ignoreExceptions:
          - java.lang.IllegalArgumentException
```

## Usage Example

```java
@Service
public class PaymentService {
    
    @CircuitBreaker(
        name = "paymentService",
        fallbackMethod = "paymentFallback"
    )
    public PaymentResponse processPayment(Order order) {
        // If circuit opens, fallback method is called
        return paymentGateway.charge(order);
    }
    
    // Fallback method (same signature + Exception parameter)
    public PaymentResponse paymentFallback(Order order, Exception ex) {
        log.warn("Circuit breaker activated, returning pending status", ex);
        return PaymentResponse.pending(order.getId());
    }
}
```

## Key Configuration Properties

| Property | Purpose |
|----------|---------|
| `slidingWindowSize` | Number of calls to record |
| `failureRateThreshold` | Failure % to open circuit (0-100) |
| `slowCallRateThreshold` | Slow call % to open (0-100) |
| `slowCallDurationThreshold` | Call duration considered slow |
| `waitDurationInOpenState` | Time before trying half-open |
| `permittedNumberOfCallsInHalfOpenState` | Calls allowed in half-open |

---

# 2. Retry

## What is Retry?

Automatically retries failed operations with configurable backoff.

**Strategy:**

```
Request fails
    ↓
Wait (backoff)
    ↓
Retry
    ↓
Success → return result
Fail again → check retry count
    ↓
If attempts exhausted → throw exception
```

## Configuration

```yaml
resilience4j:
  retry:
    instances:
      paymentService:
        maxAttempts: 3
        waitDuration: 1000ms
        retryExceptions:
          - java.io.IOException
          - java.net.ConnectException
        ignoreExceptions:
          - java.lang.IllegalArgumentException
        intervalFunction: exponential
        exponentialBackoffMultiplier: 2
        randomizedWaitDuration: false
```

## Usage Example

```java
@Service
public class PaymentService {
    
    @Retry(
        name = "paymentService",
        fallbackMethod = "retryFallback"
    )
    public PaymentResponse processPayment(Order order) {
        // Will retry up to 3 times on IOException
        return paymentGateway.charge(order);
    }
    
    public PaymentResponse retryFallback(Order order, Exception ex) {
        log.error("All retry attempts exhausted", ex);
        return PaymentResponse.failed(order.getId());
    }
}
```

## Backoff Strategies

```yaml
# Fixed delay
intervalFunction: fixed
waitDuration: 1000ms

# Exponential backoff: 1s, 2s, 4s, 8s...
intervalFunction: exponential
waitDuration: 1000ms
exponentialBackoffMultiplier: 2

# Exponential with random: 1s ± 0.5s
intervalFunction: exponential
waitDuration: 1000ms
exponentialBackoffMultiplier: 2
randomizedWaitDuration: true
```

---

# 3. Bulkhead

## What is Bulkhead?

Isolates resources to prevent one service from exhausting all threads.

**Two types:**

1. **SEMAPHORE** - Limits concurrent calls on same thread pool
2. **THREADPOOL** - Separate thread pools per service

## Configuration

```yaml
resilience4j:
  bulkhead:
    instances:
      paymentService:
        maxConcurrentCalls: 20
        maxWaitDuration: 10s
        fairThreadPoolEnabled: false
      emailService:
        maxConcurrentCalls: 5
        maxWaitDuration: 2s
  
  thread-pool-bulkhead:
    instances:
      paymentPool:
        coreThreadPoolSize: 20
        maxThreadPoolSize: 20
        queueCapacity: 100
```

## Usage Example: Semaphore Bulkhead

```java
@Service
public class OrderService {
    
    @Bulkhead(
        name = "paymentService",
        type = Bulkhead.Type.SEMAPHORE
    )
    public PaymentResponse processPayment(Order order) {
        // Max 20 concurrent calls
        return paymentGateway.charge(order);
    }
}
```

## Usage Example: ThreadPool Bulkhead

```java
@Service
public class CriticalOrderService {
    
    @Bulkhead(
        name = "paymentPool",
        type = Bulkhead.Type.THREADPOOL
    )
    public CompletableFuture<PaymentResponse> processPaymentAsync(Order order) {
        // Runs in dedicated thread pool
        return CompletableFuture.supplyAsync(() -> 
            paymentGateway.charge(order)
        );
    }
}
```

---

# 4. RateLimiter

## What is RateLimiter?

Throttles request rate to prevent overload.

**Limit:** N requests per time period

## Configuration

```yaml
resilience4j:
  ratelimiter:
    instances:
      apiLimiter:
        registerHealthIndicator: false
        limitRefreshPeriod: 1m
        limitForPeriod: 100
        timeoutDuration: 5s
        allowHealthIndicatorToFail: true
      strictLimiter:
        limitRefreshPeriod: 1s
        limitForPeriod: 10
```

## Usage Example

```java
@Service
public class ApiService {
    
    @RateLimiter(name = "apiLimiter")
    public ApiResponse callExternalApi(String endpoint) {
        // Max 100 requests per minute
        return externalApi.call(endpoint);
    }
}
```

## Request Blocking Behavior

```java
@RateLimiter(
    name = "apiLimiter",
    fallbackMethod = "apiLimiterFallback"
)
public ApiResponse callApi(String endpoint) {
    return externalApi.call(endpoint);
}

public ApiResponse apiLimiterFallback(String endpoint, Exception ex) {
    log.warn("Rate limit exceeded");
    return ApiResponse.cached(endpoint);
}
```

---

# 5. TimeLimiter

## What is TimeLimiter?

Enforces execution timeouts to prevent hanging requests.

## Configuration

```yaml
resilience4j:
  timelimiter:
    instances:
      slowService:
        cancelRunningFuture: true
        timeoutDuration: 5s
      apiService:
        cancelRunningFuture: false
        timeoutDuration: 10s
```

## Usage Example

```java
@Service
public class SlowService {
    
    @TimeLimiter(
        name = "slowService",
        fallbackMethod = "slowServiceFallback"
    )
    public CompletableFuture<String> slowOperation() {
        // Must return CompletableFuture
        // Times out after 5 seconds
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000); // Simulated slow operation
                return "Result";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
    
    public CompletableFuture<String> slowServiceFallback(Exception ex) {
        return CompletableFuture.completedFuture("Timeout fallback");
    }
}
```

## Properties

| Property | Purpose |
|----------|---------|
| `timeoutDuration` | Max execution time |
| `cancelRunningFuture` | Cancel if timeout exceeded |

---

# 6. Cache

## What is Cache?

Caches successful results to reduce redundant calls.

**Note:** Requires Spring Cache or custom cache implementation.

## Setup with Spring Cache

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
</dependency>
```

## Configuration

```yaml
resilience4j:
  cache:
    instances:
      userCache:
        baseConfig: default

spring:
  cache:
    type: simple
```

## Usage Example

```java
@Service
@EnableCaching
public class UserService {
    
    @Cacheable(
        value = "userCache",
        key = "#userId",
        cacheManager = "cacheManager"
    )
    public User getUserById(String userId) {
        // Cached for repeated calls with same userId
        return userRepository.findById(userId);
    }
}
```

---

# Combining All Patterns: Complete Example

```java
@Service
public class RobustPaymentService {
    
    @CircuitBreaker(
        name = "paymentService",
        fallbackMethod = "paymentCircuitBreakerFallback"
    )
    @Retry(name = "paymentService")
    @Bulkhead(
        name = "paymentService",
        type = Bulkhead.Type.SEMAPHORE
    )
    @RateLimiter(name = "paymentLimiter")
    @TimeLimiter(
        name = "paymentService",
        fallbackMethod = "paymentTimeoutFallback"
    )
    public CompletableFuture<PaymentResponse> processPayment(Order order) {
        return CompletableFuture.supplyAsync(() -> {
            log.info("Processing payment for order: {}", order.getId());
            
            // Simulated payment processing
            if (Math.random() < 0.3) {
                throw new PaymentGatewayException("Service unavailable");
            }
            
            return PaymentResponse.success(order.getId());
        });
    }
    
    // CircuitBreaker fallback
    public CompletableFuture<PaymentResponse> paymentCircuitBreakerFallback(
            Order order, Exception ex) {
        log.error("Circuit breaker opened for payment", ex);
        return CompletableFuture.completedFuture(
            PaymentResponse.pending(order.getId())
        );
    }
    
    // TimeLimiter fallback
    public CompletableFuture<PaymentResponse> paymentTimeoutFallback(
            Order order, Exception ex) {
        log.error("Payment timeout", ex);
        return CompletableFuture.completedFuture(
            PaymentResponse.timeout(order.getId())
        );
    }
}
```

## Execution Flow

```
Request arrives
    ↓
RateLimiter checks if rate exceeded
    → If yes: reject
    → If no: proceed
    ↓
Bulkhead checks if capacity available
    → If no: queue/reject
    → If yes: proceed
    ↓
TimeLimiter wraps in timeout
    ↓
Retry wrapper handles retries
    ↓
CircuitBreaker guards execution
    ↓
Actual method executes
    ↓
Result returned or fallback called
```

---

# Configuration: application.yml (Complete)

```yaml
resilience4j:
  # Circuit Breaker Configuration
  circuitbreaker:
    instances:
      paymentService:
        registerHealthIndicator: true
        slidingWindowSize: 100
        failureRateThreshold: 50
        slowCallRateThreshold: 50
        slowCallDurationThreshold: 2000ms
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 30s
  
  # Retry Configuration
  retry:
    instances:
      paymentService:
        maxAttempts: 3
        waitDuration: 1000ms
        retryExceptions:
          - java.io.IOException
        intervalFunction: exponential
        exponentialBackoffMultiplier: 2
  
  # Bulkhead Configuration
  bulkhead:
    instances:
      paymentService:
        maxConcurrentCalls: 20
        maxWaitDuration: 10s
  
  # RateLimiter Configuration
  ratelimiter:
    instances:
      paymentLimiter:
        limitRefreshPeriod: 1m
        limitForPeriod: 100
        timeoutDuration: 5s
  
  # TimeLimiter Configuration
  timelimiter:
    instances:
      paymentService:
        cancelRunningFuture: true
        timeoutDuration: 10s

# Management endpoints for monitoring
management:
  endpoints:
    web:
      exposure:
        include: health,metrics,circuitbreakers,retries,bulkheads,ratelimiters,timelimiters
  endpoint:
    health:
      show-details: always
  metrics:
    distribution:
      percentiles-histogram:
        resilience4j.circuitbreaker.calls: true
```

---

# Monitoring: Actuator Endpoints

## Health Check

```bash
GET http://localhost:8080/actuator/health
```

**Response:**

```json
{
  "status": "UP",
  "components": {
    "circuitbreakers": {
      "status": "UP",
      "details": {
        "paymentService": {
          "status": "UP",
          "details": {
            "state": "CLOSED",
            "failureRate": 0.0
          }
        }
      }
    }
  }
}
```

## Metrics Endpoints

```bash
# All metrics
GET http://localhost:8080/actuator/metrics

# Circuit breaker metrics
GET http://localhost:8080/actuator/metrics/resilience4j.circuitbreaker.calls

# Retry metrics
GET http://localhost:8080/actuator/metrics/resilience4j.retry.calls

# Bulkhead metrics
GET http://localhost:8080/actuator/metrics/resilience4j.bulkhead.calls

# RateLimiter metrics
GET http://localhost:8080/actuator/metrics/resilience4j.ratelimiter.calls

# TimeLimiter metrics
GET http://localhost:8080/actuator/metrics/resilience4j.timelimiter.calls
```

---

# Pattern Comparison Table

| Pattern | Purpose | Mechanism | When Used |
|---------|---------|-----------|-----------|
| **Circuit Breaker** | Stop cascade failures | Check failure rate, open/close | Failing external service |
| **Retry** | Auto-recovery | Retry with backoff | Transient failures |
| **Bulkhead** | Prevent exhaustion | Limit concurrency | Resource isolation |
| **RateLimiter** | Throttle traffic | Limit requests/time | Overload prevention |
| **TimeLimiter** | Enforce timeout | Cancel after timeout | Hanging requests |
| **Cache** | Reduce load | Store results | Repeated queries |

---

# Real-World Example: Complete Microservice

```java
@RestController
@RequestMapping("/api/orders")
@Service
public class OrderController {
    
    private final PaymentService paymentService;
    private final InventoryService inventoryService;
    private final NotificationService notificationService;
    
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest req) {
        try {
            Order order = new Order(req);
            
            // Step 1: Reserve inventory (protected by rate limiter)
            inventoryService.reserve(order.getItems());
            
            // Step 2: Process payment (all patterns applied)
            PaymentResponse payment = paymentService
                .processPayment(order)
                .get();
            
            if (payment.isSuccess()) {
                order.setStatus(OrderStatus.CONFIRMED);
                
                // Step 3: Send notification (non-critical, isolated)
                notificationService.sendConfirmation(order);
                
                return ResponseEntity.ok(
                    new OrderResponse(order.getId(), "CREATED")
                );
            }
        } catch (CallNotPermittedException e) {
            return ResponseEntity.status(503)
                .body(new OrderResponse(null, "SERVICE_UNAVAILABLE"));
        } catch (RequestNotPermitted e) {
            return ResponseEntity.status(429)
                .body(new OrderResponse(null, "RATE_LIMITED"));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                .body(new OrderResponse(null, "ERROR"));
        }
        
        return ResponseEntity.status(400)
            .body(new OrderResponse(null, "PAYMENT_FAILED"));
    }
}
```

---

# Best Practices

| Practice | Benefit |
|----------|---------|
| **Combine patterns** | Comprehensive resilience |
| **Monitor metrics** | Detect issues early |
| **Set appropriate thresholds** | Balance performance & safety |
| **Use fallback methods** | Graceful degradation |
| **Log all failures** | Debugging and analytics |
| **Test circuit breaker state** | Understand failure modes |
| **Configure per service** | Different services = different needs |

---

# Exception Handling

```java
// Import all exception types
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.timelimiter.TimeLimiterException;

@ExceptionHandler(CallNotPermittedException.class)
public ResponseEntity<ErrorResponse> handleCircuitBreaker(CallNotPermittedException ex) {
    return ResponseEntity.status(503).body(new ErrorResponse("Service temporarily unavailable"));
}

@ExceptionHandler(RequestNotPermitted.class)
public ResponseEntity<ErrorResponse> handleRateLimit(RequestNotPermitted ex) {
    return ResponseEntity.status(429).body(new ErrorResponse("Too many requests"));
}

@ExceptionHandler(BulkheadFullException.class)
public ResponseEntity<ErrorResponse> handleBulkheadFull(BulkheadFullException ex) {
    return ResponseEntity.status(503).body(new ErrorResponse("System overloaded"));
}

@ExceptionHandler(TimeLimiterException.class)
public ResponseEntity<ErrorResponse> handleTimeout(TimeLimiterException ex) {
    return ResponseEntity.status(504).body(new ErrorResponse("Request timeout"));
}
```

---

# Key Takeaway

**Resilience4j provides production-ready fault tolerance patterns:**

- ✅ Simple annotations
- ✅ YAML configuration
- ✅ Built-in monitoring
- ✅ Easy composition
- ✅ Zero dependencies (core)

**Use all six patterns together for bulletproof microservices.**
