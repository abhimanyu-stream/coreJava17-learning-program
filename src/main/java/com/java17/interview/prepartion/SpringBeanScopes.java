package com.java17.interview.prepartion;

public class SpringBeanScopes {
}
/***
 *
 * Spring Bean Scopes define the lifecycle and visibility of a bean within the Spring application context. They determine how many instances of a bean are created and how they are shared across the application.
 * Here's an explanation of the different Spring Bean Scopes:
 * 1. Singleton (default):
 * A single instance of the bean is created per Spring container.
 * This instance is shared across all requests for that bean.
 * It is the most commonly used scope and is ideal for stateless beans.
 * Java
 *
 * @Component
 * public class SingletonBean {
 *     // ...
 * }
 * 2. Prototype:
 * A new instance of the bean is created each time it is requested.
 * This is suitable for stateful beans where each request needs its own instance.
 * Java
 *
 * @Component
 * @Scope("prototype")
 * public class PrototypeBean {
 *     // ...
 * }
 * 3. Request:
 * A new instance of the bean is created for each HTTP request.
 * The bean lives as long as the request and is destroyed when the request is completed.
 * Only available in web-aware Spring contexts.
 * Java
 *
 * @Component
 * @Scope("request")
 * public class RequestBean {
 *     // ...
 * }
 * 4. Session:
 * A new instance of the bean is created for each HTTP session.
 * The bean lives as long as the session and is destroyed when the session expires.
 * Only available in web-aware Spring contexts.
 * Java
 *
 * @Component
 * @Scope("session")
 * public class SessionBean {
 *     // ...
 * }
 * 5. Application:
 * A single instance of the bean is created per ServletContext.
 * This instance is shared across all sessions and requests within a web application.
 * Only available in web-aware Spring contexts.
 * Java
 *
 * @Component
 * @Scope("application")
 * public class ApplicationBean {
 *     // ...
 * }
 * 6. WebSocket:
 * A new instance of the bean is created for each WebSocket session.
 * The bean lives as long as the WebSocket session and is destroyed when the session is closed.
 * Only available in Spring applications that use WebSocket support.
 * Java
 *
 * @Component
 * @Scope("websocket")
 * public class WebSocketBean {
 *     // ...
 * }
 * How to Specify Bean Scope:
 * You can specify the scope of a bean using the @Scope annotation:
 * Java
 *
 * @Scope("prototype")
 * Or, you can use XML configuration:
 * Code
 *
 * <bean id="myBean" class="com.example.MyBean" scope="prototype"/>
 * */