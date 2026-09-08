# Practical SOLID — What Each Principle Promotes

> A better way to remember SOLID for interviews is not as definitions, but as:
> **"What does each principle promote us to do, and what should we avoid?"**

---

## Table of Contents

1. [SOLID at a Glance](#1-solid-at-a-glance)
2. [S — Single Responsibility Principle (SRP)](#2-s--single-responsibility-principle-srp)
3. [O — Open/Closed Principle (OCP)](#3-o--openclosed-principle-ocp)
4. [L — Liskov Substitution Principle (LSP)](#4-l--liskov-substitution-principle-lsp)
5. [I — Interface Segregation Principle (ISP)](#5-i--interface-segregation-principle-isp)
6. [D — Dependency Inversion Principle (DIP)](#6-d--dependency-inversion-principle-dip)
7. [SOLID in a Real Application](#7-solid-in-a-real-application)
8. [Easy Memory Formula](#8-easy-memory-formula)
9. [The Overall Goal](#9-the-overall-goal)

---

## 1. SOLID at a Glance

| Principle                         | What It Promotes                                                          | What to Avoid                                                              | Problem It Solves                                     |
|-----------------------------------|---------------------------------------------------------------------------|----------------------------------------------------------------------------|-------------------------------------------------------|
| **S** — Single Responsibility     | One class = one business responsibility, one reason to change             | Mixing order processing, payment, email, logging in one class              | High coupling; one change breaks unrelated behaviour  |
| **O** — Open/Closed               | Add new behaviour by creating new implementations, not modifying old code | Adding `if-else` / `switch` inside existing logic for every new requirement | Regression risk; fragile code when requirements change |
| **L** — Liskov Substitution       | Child must fully honour the parent's behavioural contract                 | Overriding methods with `UnsupportedOperationException` or invalid logic   | Broken polymorphism; incorrect inheritance hierarchies |
| **I** — Interface Segregation     | Many small, capability-specific interfaces rather than one fat interface  | Forcing a class to implement methods it doesn't need                       | Fat interfaces; unnecessary compile-time dependencies  |
| **D** — Dependency Inversion      | High-level logic depends on abstractions, not concrete implementations    | `OrderService` directly creating `MySQLRepository`, `KafkaProducer`, etc. | Tight coupling; hard to test or swap implementations  |

---

## 2. S — Single Responsibility Principle (SRP)

**Promote:** Each class should represent exactly one business responsibility and have exactly one reason to change.

```
❌  Avoid                              ✅  Prefer
─────────────────────────────          ──────────────────────────
OrderService                           OrderService
  ├── processOrder()                     └── processOrder()
  ├── sendEmail()                      
  ├── chargePayment()                  EmailService
  ├── generateReport()                   └── sendEmail()
  └── writeToDatabase()                
                                       PaymentService
                                         └── chargePayment()
                                       
                                       ReportService
                                         └── generateReport()
```

**Key question to ask:** "If this responsibility changes, how many unrelated parts of this class must also change?"

---

## 3. O — Open/Closed Principle (OCP)

**Promote:** Add new business behaviour by adding new implementations. Existing, stable code should not need to change.

```
❌  Avoid — modifying existing logic for each new payment type

processPayment(type) {
    if (type == "CARD")  { ... }
    if (type == "UPI")   { ... }
    if (type == "CASH")  { ... }   // new requirement → must open and modify
}
```

```
✅  Prefer — extend by adding a new implementation

interface PaymentProcessor {
    void process(Payment payment);
}

class CardPaymentProcessor  implements PaymentProcessor { ... }
class UPIPaymentProcessor   implements PaymentProcessor { ... }
class CashPaymentProcessor  implements PaymentProcessor { ... }  // new → no existing code touched
```

**Key question:** "Can I add this new requirement without opening any existing class?"

---

## 4. L — Liskov Substitution Principle (LSP)

**Promote:** A subclass must be safely usable wherever its parent is expected — same behaviour, no surprises.

```
❌  Avoid — breaking the parent's contract

class Bird {
    void fly() { ... }
}

class Penguin extends Bird {
    @Override
    void fly() {
        throw new UnsupportedOperationException("Penguins can't fly");
    }
}

// This breaks LSP — Penguin cannot substitute Bird
```

```
✅  Prefer — model the hierarchy honestly

interface Bird { }

interface FlyingBird extends Bird {
    void fly();
}

class Sparrow  implements FlyingBird { ... }
class Penguin  implements Bird       { ... }  // no fly() contract forced
```

**Key question:** "Can every subclass be substituted for its parent without any caller needing to know the difference?"

---

## 5. I — Interface Segregation Principle (ISP)

**Promote:** Create multiple small, capability-specific interfaces so a class implements only what it actually needs.

```
❌  Avoid — one fat interface

interface Payment {
    void processPayment();
    void refund();
    void generateInvoice();
    void sendReceipt();
    void validateCard();
}

// CashPayment is forced to implement refund(), validateCard()
// even though it doesn't support those operations
```

```
✅  Prefer — split by capability

interface Processable  { void processPayment(); }
interface Refundable   { void refund(); }
interface Invoiceable  { void generateInvoice(); }

class CardPayment  implements Processable, Refundable, Invoiceable { ... }
class CashPayment  implements Processable, Invoiceable             { ... }
class UPIPayment   implements Processable, Refundable              { ... }
```

**Key question:** "Is this class forced to depend on methods it will never use?"

---

## 6. D — Dependency Inversion Principle (DIP)

**Promote:** High-level business logic should depend on abstractions (interfaces), not on concrete implementations.

```
❌  Avoid — high-level module directly depends on low-level detail

class OrderService {
    private MySQLOrderRepository repository = new MySQLOrderRepository();
    private KafkaNotificationService notifier = new KafkaNotificationService();
}
```

```
✅  Prefer — depend on abstractions; inject concrete implementations

class OrderService {
    private final OrderRepository     repository;
    private final NotificationService notifier;

    // Injected via constructor (Spring @Autowired, CDI, etc.)
    public OrderService(OrderRepository repository,
                        NotificationService notifier) {
        this.repository = repository;
        this.notifier   = notifier;
    }
}
```

```
High-Level Module (OrderService)
        │
        ▼
  Abstraction (OrderRepository interface)
        │
        ▼
Low-Level Module (MySQLOrderRepository / MongoOrderRepository)
```

**Key question:** "If I swap the database or message broker, how many business classes need to change?"

---

## 7. SOLID in a Real Application

**Domain example — Payment with Refund capability:**

```
❌  Wrong — one fat interface forces all types to implement everything

interface Payment {
    void process();
    void refund();
}

class CashPayment implements Payment {
    public void refund() {
        throw new UnsupportedOperationException();  // violates LSP + ISP
    }
}
```

```
✅  Correct — model by genuine capability

interface Payment   { void process(); }
interface Refundable { void refund(); }

class CardPayment implements Payment, Refundable { ... }
class UPIPayment  implements Payment, Refundable { ... }
class CashPayment implements Payment             { ... }  // no refund — honest model
```

**How each principle contributes in a real backend:**

| Principle | Role in Real Application                                              |
|-----------|-----------------------------------------------------------------------|
| **SRP**   | Separate `OrderService`, `PaymentService`, `NotificationService`     |
| **OCP**   | Add new payment type without touching existing payment processors    |
| **LSP**   | Ensure `CardPayment` can substitute `Payment` safely                 |
| **ISP**   | `CashPayment` doesn't implement `Refundable`                         |
| **DIP**   | `OrderService` depends on `PaymentRepository` interface, not MySQL   |

---

## 8. Easy Memory Formula

```
S → Separate responsibilities
O → Open for extension, Closed for modification
L → Legitimate replacement of parent (child must honour the contract)
I → Interface by capability (don't force unused methods)
D → Depend on abstraction (not on concrete details)
```

---

## 9. The Overall Goal

```
Apply SOLID
    │
    ▼
Low Coupling + High Cohesion
    │
    ▼
Easy to Change
    │
    ▼
Easy to Extend
    │
    ▼
Easy to Test
    │
    ▼
Lower Regression Risk
```

> **The interview-level insight:** SOLID doesn't mean "create an interface for everything."
> It means — model your business responsibilities and capabilities correctly, then choose abstractions
> that make **invalid combinations difficult or impossible** to express.
