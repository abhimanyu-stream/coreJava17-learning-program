package com.java17.interview.prepartion;

public class StepByStepWorkingOfCircuitBreaker {

    public static void main(String[] args) {

       //         resilience4j:
        //        circuitbreaker:
        //        instances:
        //        userService:
        //        registerHealthIndicator: true
        //        eventConsumerBufferSize: 10
        //        failureRateThreshold: 50
        //        minimumNumberOfCalls: 5
        //        automaticTransitionFromOpenToHalfOpenEnabled: true
        //        waitDurationInOpenState: 5s
        //        permittedNumberOfCallsInHalfOpenState: 3
        //        slidingWindowSize: 10
        //        slidingWindowType: COUNT_BASED


        //Step-by-Step Working of Circuit Breaker
        //Initialization and Monitoring Calls

        //The Circuit Breaker for userService starts in the Closed state, which means it allows calls to pass through normally.
        //As each request to userService is monitored, the Circuit Breaker records the success and failure rates based on a sliding window.
                //Sliding Window

        //This configuration sets a count-based sliding window (slidingWindowType: COUNT_BASED) with a size of 10 (slidingWindowSize: 10), meaning it will evaluate the last 10 calls.
                //It only begins calculating the failure rate once the minimum number of calls has reached 5 (minimumNumberOfCalls: 5).
        //Failure Rate Threshold

        //The Circuit Breaker has a failure rate threshold of 50% (failureRateThreshold: 50).
        //Once 5 calls are made, if 50% or more of these calls fail, the Circuit Breaker will open.
        //Transition to Open State

        //If the failure rate meets or exceeds 50%, the Circuit Breaker switches to the Open state.
                //In the Open state, further calls to userService will be immediately rejected to prevent additional load on the failing service.
                //The Circuit Breaker will remain in the Open state for 5 seconds (waitDurationInOpenState: 5s).
        //Automatic Transition to Half-Open State

        //After 5 seconds, the Circuit Breaker will automatically move from the Open state to the Half-Open state (automaticTransitionFromOpenToHalfOpenEnabled: true).
        //This transition allows the Circuit Breaker to test if the service has recovered.
        //Permitted Calls in Half-Open State

        //In the Half-Open state, the Circuit Breaker allows only 3 calls to userService (permittedNumberOfCallsInHalfOpenState: 3).
        //These calls act as test requests to check if the service is healthy. If the service responds successfully to these calls, the Circuit Breaker closes again, allowing normal traffic.
        //If the failure rate during these 3 calls is high, the Circuit Breaker will reopen and restart the 5-second wait period.
        //Health Indicator and Event Buffer

        //Health Indicator (registerHealthIndicator: true): This enables the Circuit Breaker’s health status to be monitored, making it visible in applications like Actuator.
        //Event Consumer Buffer (eventConsumerBufferSize: 10): This buffer size controls how many events (state changes or failure events) are kept in memory for processing or reporting.

        //Example Flow
        //Let’s consider the following example based on the above steps:

        //Initial 10 Calls:

        //Suppose we make 10 calls to userService, and 6 of them fail (failure rate = 60%).
                //Since this exceeds the 50% threshold after the minimum 5 calls, the Circuit Breaker moves to the Open state.
                //Open State:

        //For the next 5 seconds, any requests to userService are immediately rejected.
        //Half-Open State:

        //After 5 seconds, the Circuit Breaker transitions to the Half-Open state, allowing 3 test calls.
        //If these test calls succeed, the Circuit Breaker moves to the Closed state.
                //If they fail, the Circuit Breaker returns to the Open state, restarting the 5-second timer.
                //Summary of Configuration Impact
        //This configuration provides a robust fallback mechanism for handling failing services with a structured retry pattern:

        //Closed: Normal operation with monitoring.
        //Open: Traffic cut-off after a threshold is exceeded.
                //Half-Open: Limited testing to verify service health.
        //Using this setup with registerHealthIndicator also enables easy monitoring and visualization of the Circuit Breaker state, making it simpler to observe performance and health in real-time.

    }
}
