package com.java17.interview.prepartion;

public class TransactionRollbackCodeReview {
    public static void main(String[] args) {
        /**
         * Transaction rollback issue — Why this sometimes does NOT roll back
         * The code under review
         * @Transactional
         * public void save() {
         *     repository.save(entity);
         *     throw new RuntimeException();
         * }
         *
         *
         * At first glance this should roll back.
         * RuntimeException is unchecked → Spring marks the transaction for rollback.
         *
         * Yet in real projects, teams see data still committed. That’s the trap.
         *
         * Core reason (the real culprit)
         * ❗ Spring transactions are proxy-based
         *
         * @Transactional works only when the method is invoked through a Spring proxy.
         *
         * If this method is called like this:
         *
         * this.save();   // ❌ self-invocation
         *
         *
         * or from:
         *
         * same class
         *
         * non-Spring-managed object
         *
         * constructor
         *
         * private method
         *
         * ➡️ Transaction is never started
         * ➡️ repository.save() commits immediately
         * ➡️ Exception changes nothing
         *
         * No proxy → no transaction → no rollback.
         *
         * This is the #1 production bug behind “rollback not working”.
         *
         * Common failure scenarios (code review gold)
         * 1️⃣ Self-invocation
         * public void process() {
         *     save(); // ❌ bypasses proxy
         * }
         *
         *
         * Result: Transaction annotation is ignored.
         *
         * 2️⃣ Method visibility is wrong
         * @Transactional
         * private void save() { ... } // ❌
         *
         *
         * Spring cannot proxy private methods.
         *
         * 3️⃣ Exception is caught and swallowed
         * @Transactional
         * public void save() {
         *     try {
         *         repository.save(entity);
         *         throw new RuntimeException();
         *     } catch (Exception e) {
         *         // ❌ transaction commits
         *     }
         * }
         *
         *
         * Spring rolls back only if the exception escapes the method.
         *
         * 4️⃣ Wrong exception type
         * throw new Exception(); // checked exception
         *
         *
         * Spring does NOT rollback on checked exceptions by default.
         *
         * Correct fix:
         *
         * @Transactional(rollbackFor = Exception.class)
         *
         * 5️⃣ Wrong propagation
         * @Transactional(propagation = Propagation.REQUIRES_NEW)
         *
         *
         * Creates a new transaction, commits independently, even if caller fails.
         *
         * 6️⃣ Asynchronous execution
         * @Async
         * @Transactional
         * public void save() { ... }
         *
         *
         * Transaction does not propagate to async threads.
         *
         * How to fix correctly (review-approved)
         * ✅ Move transactional method to another service
         * @Service
         * public class SaveService {
         *
         *     @Transactional
         *     public void save() {
         *         repository.save(entity);
         *         throw new RuntimeException();
         *     }
         * }
         *
         *
         * Call it via Spring bean:
         *
         * @Autowired
         * SaveService saveService;
         *
         * saveService.save(); // ✔ proxy active
         *
         * ✅ Or use TransactionTemplate (explicit & bulletproof)
         * transactionTemplate.execute(status -> {
         *     repository.save(entity);
         *     throw new RuntimeException();
         * });
         *
         * What to say in interview / review
         *
         * “Rollback fails because Spring uses proxies. If the method is self-invoked or not called via a Spring bean, @Transactional is ignored and the save commits immediately.”
         *
         * That sentence signals real production experience.
         *
         * Summary (one-screen review note)
         *
         * @Transactional ≠ magic
         *
         * Works only via proxy
         *
         * RuntimeException rolls back only if uncaught
         *
         * Self-invocation is the silent killer
         *
         * If you want, next we can:
         *
         * Draw the proxy call flow
         *
         * Show a failing unit test vs working test
         *
         * Compare @Transactional vs TransactionTemplate
         *
         * Explain DB-level auto-commit confusion
         *
         * Just point the direction.
         */
    }
}
