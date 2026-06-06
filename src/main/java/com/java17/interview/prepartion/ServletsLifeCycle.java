/**
 * Servlet Life Cycle in Java (Step-by-Step)
 *
 * A Servlet in Java follows a well-defined lifecycle managed by the Servlet Container (like Apache Tomcat or Jetty).
 *
 * The lifecycle has mainly 3 major methods:
 *
 * init()
 * service()
 * destroy()
 * Complete Servlet Life Cycle Flow
 * Client Request
 *       ↓
 * Servlet Container loads servlet
 *       ↓
 * Servlet object created
 *       ↓
 * init() called ONLY ONCE
 *       ↓
 * service() called for every request
 *       ↓
 * destroy() called before servlet removal
 *       ↓
 * Garbage Collection
 * Step 1: Loading and Instantiation
 *
 * When the first request comes:
 *
 * Servlet container loads servlet class
 * Creates servlet object
 * public class MyServlet extends HttpServlet {
 * }
 *
 * Equivalent internally:
 *
 * MyServlet obj = new MyServlet();
 *
 * This happens only once.
 *
 * Step 2: init() Method
 *
 * After object creation, container calls:
 *
 * public void init(ServletConfig config)
 *
 * OR commonly:
 *
 * public void init()
 *
 * Purpose:
 *
 * Initialize resources
 * Open DB connections
 * Read config
 * Load cache
 * Create expensive objects
 *
 * Called:
 *
 * Only once in servlet lifetime
 *
 * Example:
 *
 * @Override
 * public void init() throws ServletException {
 *     System.out.println("Servlet Initialized");
 * }
 * Step 3: service() Method
 *
 * After initialization:
 *
 * For EVERY client request:
 *
 * service(HttpServletRequest req,
 *         HttpServletResponse res)
 *
 * is called.
 *
 * This is the MOST IMPORTANT method.
 *
 * How service() Works Internally
 *
 * For HttpServlet:
 *
 * Container checks HTTP method:
 *
 * HTTP Request	Method Called
 * GET	doGet()
 * POST	doPost()
 * PUT	doPut()
 * DELETE	doDelete()
 *
 * Internally:
 *
 * service()
 *    ↓
 * Checks HTTP type
 *    ↓
 * Calls corresponding method
 * Example
 * @WebServlet("/hello")
 * public class HelloServlet extends HttpServlet {
 *
 *     @Override
 *     public void init() {
 *         System.out.println("Init called");
 *     }
 *
 *     @Override
 *     protected void doGet(HttpServletRequest req,
 *                          HttpServletResponse res)
 *                          throws IOException {
 *
 *         System.out.println("doGet called");
 *
 *         res.getWriter().println("Hello");
 *     }
 *
 *     @Override
 *     public void destroy() {
 *         System.out.println("Destroy called");
 *     }
 * }
 * Execution Flow
 *
 * Suppose 3 users hit endpoint:
 *
 * GET /hello
 *
 * Flow:
 *
 * Servlet class loaded
 * ↓
 * Object created
 * ↓
 * init() called ONCE
 *
 * User1 request
 * ↓
 * service()
 * ↓
 * doGet()
 *
 * User2 request
 * ↓
 * service()
 * ↓
 * doGet()
 *
 * User3 request
 * ↓
 * service()
 * ↓
 * doGet()
 *
 * Server shutdown
 * ↓
 * destroy() called ONCE
 * Step 4: destroy() Method
 *
 * Before servlet removal:
 *
 * public void destroy()
 *
 * Purpose:
 *
 * Close DB connections
 * Release resources
 * Stop threads
 * Cleanup memory
 *
 * Called:
 *
 * Only once
 *
 * Example:
 *
 * @Override
 * public void destroy() {
 *     System.out.println("Servlet Destroyed");
 * }
 * Important Interview Point:
 * Servlet Object Count
 *
 * Servlet container creates:
 *
 * ONLY ONE servlet object
 *
 * But:
 *
 * Multiple threads handle multiple requests
 *
 * Example:
 *
 * One Servlet Object
 *        ↓
 * Thread-1 → Request-1
 * Thread-2 → Request-2
 * Thread-3 → Request-3
 *
 * Therefore:
 *
 * Servlets are MULTITHREADED by default
 * Major Concurrency Problem
 *
 * Avoid instance variables:
 *
 * ❌ Bad:
 *
 * public class TestServlet extends HttpServlet {
 *
 *     int count = 0;
 *
 *     protected void doGet(...) {
 *         count++;
 *     }
 * }
 *
 * Because multiple threads modify same variable.
 *
 * Leads to:
 *
 * Race condition
 * Data inconsistency
 * Safe Approach
 *
 * Use:
 *
 * Local variables
 * Immutable objects
 * Synchronization carefully
 *
 * ✔ Good:
 *
 * protected void doGet(...) {
 *
 *     int localCount = 0;
 * }
 * Servlet Life Cycle Diagram
 *                 ┌──────────────┐
 *                 │ Class Loaded │
 *                 └──────┬───────┘
 *                        ↓
 *               ┌────────────────┐
 *               │ Object Created │
 *               └──────┬─────────┘
 *                      ↓
 *               ┌────────────┐
 *               │  init()    │
 *               └────┬───────┘
 *                    ↓
 *       ┌──────────────────────────┐
 *       │ service() per request    │
 *       └────┬─────────────────────┘
 *            ↓
 *   doGet()/doPost()/doPut()...
 *            ↓
 *       ┌────────────┐
 *       │ destroy()  │
 *       └────────────┘
 * Interview-Level Deep Questions
 * 1. Who manages servlet lifecycle?
 *
 * Servlet Container / Web Container.
 *
 * Examples:
 *
 * Apache Tomcat
 * Jetty
 * 2. Difference Between init() and Constructor
 * Constructor	init()
 * Called by JVM	Called by container
 * Object creation	Servlet initialization
 * No servlet config	Has servlet config
 * Runs first	Runs after constructor
 * 3. Which method is called multiple times?
 * service()
 *
 * OR:
 *
 * doGet()
 * doPost()
 * 4. Which methods are called once?
 * init()
 * destroy()
 * 5. Can we override service()?
 *
 * Yes.
 *
 * Example:
 *
 * @Override
 * protected void service(HttpServletRequest req,
 *                        HttpServletResponse res)
 *
 * But usually we override:
 *
 * doGet()
 * doPost()
 * 6. What happens if load-on-startup is enabled?
 *
 * Servlet loads during server startup.
 *
 * Instead of first request.
 *
 * Example:
 *
 * <load-on-startup>1</load-on-startup>
 * Advanced Internal Flow
 * Tomcat starts
 * ↓
 * Reads web.xml / annotations
 * ↓
 * Loads servlet class
 * ↓
 * Creates object using reflection
 * ↓
 * Calls init()
 * ↓
 * Waits for requests
 * ↓
 * Creates thread per request
 * ↓
 * Calls service()
 * ↓
 * Maps to doGet()/doPost()
 * ↓
 * Sends response
 * ↓
 * On shutdown → destroy()
 * Super Important FAANG Interview Concepts
 * Servlet is NOT thread-safe
 *
 * Because:
 *
 * Single object + Multiple threads
 * Better Architecture in Modern Spring Boot
 *
 * Nowadays:
 *
 * Controllers replace servlets directly
 * But internally Spring MVC still uses:
 * DispatcherServlet
 *
 * which itself is a servlet.
 *
 * Real Enterprise Flow
 * Browser
 *   ↓
 * Tomcat
 *   ↓
 * DispatcherServlet
 *   ↓
 * Spring Controllers
 *   ↓
 * Service Layer
 *   ↓
 * Repository Layer
 *   ↓
 * Database
 * Final Revision Table
 * Phase	Method	Called How Many Times
 * Loading	Constructor	Once
 * Initialization	init()	Once
 * Request Processing	service()	Per request
 * Cleanup	destroy()	Once
 */