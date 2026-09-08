# Composition and Aggregation — OOP HAS-A Relationships

> Both Composition and Aggregation are **HAS-A** relationships in OOP.
> The key difference is **ownership and lifecycle**.

---

## Table of Contents

1. [Quick Comparison](#1-quick-comparison)
2. [Aggregation — Weak HAS-A](#2-aggregation--weak-has-a)
3. [Composition — Strong HAS-A](#3-composition--strong-has-a)
4. [Side-by-Side Code Comparison](#4-side-by-side-code-comparison)
5. [Lifecycle Diagram](#5-lifecycle-diagram)
6. [Real-World Examples](#6-real-world-examples)
7. [UML Notation](#7-uml-notation)
8. [Interview-Ready Summary](#8-interview-ready-summary)

---

## 1. Quick Comparison

| Feature                             | Aggregation                   | Composition                     |
|-------------------------------------|-------------------------------|---------------------------------|
| Relationship type                   | HAS-A                         | HAS-A                           |
| Ownership                           | Weak                          | Strong                          |
| Child lifecycle                     | Independent of parent         | Depends on parent               |
| Child can exist without parent?     | ✅ Yes                        | ❌ Usually No                   |
| Parent controls child creation?     | ❌ No — child passed in       | ✅ Yes — parent creates child   |
| UML notation                        | Empty diamond ◇               | Filled diamond ◆                |

---

## 2. Aggregation — Weak HAS-A

**Meaning:** A has B, but B can exist independently of A.

```java
class Employee {
    private String name;

    Employee(String name) {
        this.name = name;
    }
}

class Department {
    private List<Employee> employees;

    // Employee objects are passed in — they exist outside Department
    Department(List<Employee> employees) {
        this.employees = employees;
    }
}
```

```
Department
    │
    │ HAS-A (weak)
    ▼
Employee

If Department is deleted:
  Department ❌
  Employee   ✅  — still exists
```

### Real-world Aggregation examples

| Parent        | Child     | Why Aggregation?                           |
|---------------|-----------|--------------------------------------------|
| University    | Student   | Student exists without the university      |
| Team          | Player    | Player can join another team               |
| Department    | Employee  | Employee can move to another department    |
| Library       | Book      | Book exists independently of any library  |

---

## 3. Composition — Strong HAS-A

**Meaning:** A owns B. B's lifecycle is entirely controlled by A.

```java
class Room {
    // Room has no meaning outside a House
}

class House {
    private Room room;

    House() {
        // House creates and owns its Room
        this.room = new Room();
    }
}
```

```
House
  │
  │ HAS-A (strong)
  ▼
Room

House created  → Room created
House destroyed → Room no longer has meaning
```

### Real-world Composition examples

| Parent    | Child        | Why Composition?                              |
|-----------|--------------|-----------------------------------------------|
| Car       | Engine       | Engine is built into the car, not swappable   |
| Order     | OrderItem    | OrderItem has no meaning without the Order    |
| Human     | Heart        | Heart belongs to one human                    |
| Computer  | Motherboard  | Motherboard is part of that specific machine  |

---

## 4. Side-by-Side Code Comparison

```java
// ─── AGGREGATION ──────────────────────────────────────────────
// Employee exists independently; Department just holds a reference
List<Employee> employees = List.of(
    new Employee("Ravi"),
    new Employee("Meera")
);
Department dept = new Department(employees);
// employees list still valid even if dept goes out of scope


// ─── COMPOSITION ──────────────────────────────────────────────
// Room is created by House and has no independent existence
House house = new House();  // Room is created inside the constructor
// You cannot access the Room without going through the House
```

---

## 5. Lifecycle Diagram

```
AGGREGATION
─────────────────────────────────────────────
Parent: Department   [created] ──── [deleted]
Child:  Employee     [created] ────────────────── [still alive]

COMPOSITION
─────────────────────────────────────────────
Parent: House        [created] ──── [deleted]
Child:  Room            [created]── [deleted with House]
```

---

## 6. Real-World Examples

### Aggregation in Spring Boot

```java
// OrderService aggregates PaymentService
// PaymentService exists independently and can be injected elsewhere
@Service
public class OrderService {

    private final PaymentService paymentService;  // injected, not created here

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
```

### Composition in domain model

```java
// Order owns its OrderItems — they have no meaning without the Order
@Entity
public class Order {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public void addItem(String product, int qty) {
        items.add(new OrderItem(product, qty));  // Order creates and owns items
    }
}
```

---

## 7. UML Notation

```
AGGREGATION (empty diamond ◇)

Department ◇──────── Employee
           "has a"

COMPOSITION (filled diamond ◆)

House ◆──────── Room
      "owns"
```

---

## 8. Interview-Ready Summary

```
HAS-A Relationship
      │
      ├── Aggregation  → "I have it"    → weak ownership → independent lifecycle
      │
      └── Composition  → "It belongs to me" → strong ownership → dependent lifecycle
```

> **One-line interview answer:**
> "Both are HAS-A relationships. Aggregation represents weak ownership — the contained object
> can exist independently. Composition represents strong ownership — the contained object's
> lifecycle is controlled by and depends on the owner."
