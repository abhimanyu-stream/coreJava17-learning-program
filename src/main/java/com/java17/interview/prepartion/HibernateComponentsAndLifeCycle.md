# Hibernate Architecture Components + Complete Lifecycle (Step-by-Step)

## Overview

Hibernate is an **ORM framework** that converts **Java objects ↔ relational database tables**.

---

## 1. Hibernate Main Components

### 1. Configuration

#### Purpose

Loads Hibernate configuration and mapping metadata.

Usually reads:

- `hibernate.cfg.xml`
- Entity annotations (`@Entity`)
- DB properties

#### Responsibilities

- Database URL
- Username/password
- Dialect
- Driver class
- Cache settings
- Mapping classes

#### Example

```xml
<hibernate-configuration>
    <session-factory>
        <property name="hibernate.connection.url">
            jdbc:mysql://localhost:3306/company
        </property>

        <property name="hibernate.dialect">
            org.hibernate.dialect.MySQL8Dialect
        </property>

        <mapping class="com.demo.Employee"/>
    </session-factory>
</hibernate-configuration>
```

---

### 2. ServiceRegistry

#### Purpose

Internal service container of Hibernate.

Stores services like:

- Connection pooling
- Transaction management
- SQL generation
- Caching
- JDBC services

#### Creation

```java
ServiceRegistry serviceRegistry =
    new StandardServiceRegistryBuilder()
        .configure()
        .build();
```

---

### 3. SessionFactory

#### Purpose

**Heavyweight** thread-safe factory object.

Creates Session objects.

#### Important Points

- Created once per database
- Thread-safe
- Expensive to create
- Shared across application

#### Internally contains

- DB metadata
- Mapping metadata
- 2nd level cache
- SQL generation plan

#### Example

```java
SessionFactory factory =
    new MetadataSources(serviceRegistry)
        .buildMetadata()
        .buildSessionFactory();
```

---

### 4. Session

#### Purpose

Represents one unit of work with database.

Acts like:

- Persistence context
- First-level cache
- DB connection wrapper

#### Responsibilities

- CRUD operations
- Object tracking
- Dirty checking
- Transaction interaction

#### Lightweight?

**YES.**

Created per request/transaction.

#### Example

```java
Session session = factory.openSession();
```

---

### 5. Transaction

#### Purpose

Provides ACID properties.

#### Responsibilities

- Commit
- Rollback
- Consistency

#### Example

```java
Transaction tx = session.beginTransaction();

tx.commit();
```

---

### 6. Persistent Objects / Entity Classes

#### Purpose

POJO mapped to database table.

#### Example

```java
@Entity
@Table(name="employee")
public class Employee {

    @Id
    private int id;

    private String name;
}
```

---

### 7. Query Objects

Hibernate provides:

#### HQL

```java
session.createQuery("from Employee");
```

#### Criteria API

```java
CriteriaBuilder cb = session.getCriteriaBuilder();
```

#### Native SQL

```java
session.createNativeQuery("select * from employee");
```

---

### 8. Dialect

#### Purpose

Hibernate generates DB-specific SQL.

Different DBs have different SQL syntax.

#### Examples:

- MySQLDialect
- OracleDialect
- PostgreSQLDialect

#### Example

```xml
<property name="hibernate.dialect">
org.hibernate.dialect.PostgreSQLDialect
</property>
```

---

### 9. Caching Components

#### First Level Cache

**Scope:** Per Session

**Enabled?** Always enabled.

**Example:**

```java
Employee e1 = session.get(Employee.class, 1);
Employee e2 = session.get(Employee.class, 1);

// Second call hits cache.
```

#### Second Level Cache

**Scope:** Across sessions.

**Providers:**
- Ehcache
- Hazelcast
- Redis

#### Query Cache

Caches query results.

---

### 10. Connection Provider

#### Purpose

Provides DB connections.

Can use:

- HikariCP
- C3P0
- JDBC pool

---

### 11. Mapping Metadata

Defines relation between:

**Java fields ↔ DB columns**

Using:

- Annotations
- XML mapping

**Example:**

```java
@Column(name="emp_name")
private String name;
```

---

## Complete Hibernate Lifecycle (Step-by-Step)

### Phase 1 — Application Startup

#### Step 1: Load Configuration

Hibernate reads:

- DB configuration
- Entity mappings
- Cache settings

```java
Configuration cfg = new Configuration();
cfg.configure();
```

#### Step 2: Build ServiceRegistry

```java
ServiceRegistry registry =
    new StandardServiceRegistryBuilder()
        .applySettings(cfg.getProperties())
        .build();
```

#### Step 3: Create SessionFactory

```java
SessionFactory factory =
    cfg.buildSessionFactory(registry);
```

**Internally Hibernate does:**

- Parse mappings
- Create metadata
- Prepare SQL generators
- Initialize caches
- Create connection pools

---

### Phase 2 — Request Processing

#### Step 4: Open Session

```java
Session session = factory.openSession();
```

**Internally:**

- DB connection allocated
- Persistence context created
- First-level cache initialized

#### Step 5: Begin Transaction

```java
Transaction tx = session.beginTransaction();
```

---

### Phase 3 — Entity Lifecycle

Hibernate entity objects have their own lifecycle.

#### Hibernate Entity States

There are **4 major states:**

1. Transient
2. Persistent
3. Detached
4. Removed

---

#### 1. Transient State

**Meaning**

Object exists only in JVM memory.

- NOT connected to Hibernate
- No DB row exists

**Example:**

```java
Employee emp = new Employee();
emp.setName("Rahul");
```

**Characteristics:**

- No Session
- No tracking
- No DB identity

---

#### 2. Persistent State

**Meaning**

Object attached to Session.

Hibernate tracks changes.

**Enter Persistent State:**

```java
session.save(emp);

// OR

Employee emp = session.get(Employee.class,1);
```

**Characteristics:**

- Managed by Hibernate
- Dirty checking enabled
- Stored in first-level cache

##### Dirty Checking

If object changes:

```java
emp.setName("Amit");
```

Hibernate automatically detects changes.

On commit:

```sql
UPDATE employee SET name='Amit'
```

generated automatically.

---

#### 3. Detached State

**Meaning**

Object was persistent earlier, but Session closed.

**Example:**

```java
session.close();

// Now entity becomes detached.
```

**Characteristics:**

- Exists in memory
- Not tracked
- Changes NOT auto-saved

##### Reattach Detached Object

```java
session.update(emp);

// OR

session.merge(emp);
```

---

#### 4. Removed State

**Meaning**

Entity marked for deletion.

**Example:**

```java
session.delete(emp);

// On commit:
// DELETE FROM employee
```

---

### Phase 4 — Flush Operation

#### What is Flush?

Synchronizes Session state with database.

Hibernate converts object changes into SQL.

#### Happens During

- `commit()`
- explicit `flush()`
- query execution sometimes

#### Example

```java
session.flush();
```

#### Flush Internally

Hibernate checks:

- New objects → INSERT
- Modified objects → UPDATE
- Deleted objects → DELETE

---

### Phase 5 — Commit Transaction

```java
tx.commit();
```

**Internally:**

- Flush session
- Execute SQL
- Commit DB transaction

---

### Phase 6 — Close Session

```java
session.close();
```

**Internally:**

- JDBC connection released
- First-level cache destroyed
- Entities become detached

---

### Phase 7 — Shutdown

```java
factory.close();
```

**Internally:**

- Close caches
- Release connection pool
- Destroy metadata

---

## Full Hibernate Flow Diagram

```
START APPLICATION
       |
       v
Load Configuration
       |
       v
Build ServiceRegistry
       |
       v
Create SessionFactory
       |
       v
Open Session
       |
       v
Begin Transaction
       |
       v
Create Entity (Transient)
       |
       v
save()/persist()
       |
       v
Persistent State
       |
       v
Dirty Checking
       |
       v
Flush
       |
       v
Commit Transaction
       |
       v
Close Session
       |
       v
Detached State
       |
       v
SessionFactory Close
       |
      END
```

---

## Most Important Interview Concepts

### Difference: Session vs SessionFactory

| Feature | Session | SessionFactory |
|---------|---------|---|
| Weight | Lightweight | Heavyweight |
| Thread-safe | No | Yes |
| Created | Per request | Once |
| Cache | First-level | Second-level |

---

### save() vs persist()

| Aspect | save() | persist() |
|---|---|---|
| Returns | ID | void |
| Standard | Hibernate specific | JPA standard |
| Insert Timing | Immediate insert possible | Delayed insert |

---

### update() vs merge()

| Aspect | update() | merge() |
|---|---|---|
| Reattachment | Reattaches same object | Copies state |
| Duplicates | Fails if duplicate exists | Safer |
| Flexibility | Less flexible | More flexible |

---

### get() vs load()

| Aspect | get() | load() |
|---|---|---|
| DB Hit | Immediate DB hit | Lazy proxy |
| Not Found | Returns null | Throws exception |
| Loading | Eager | Lazy |

---

## Hibernate Internal Execution Flow

```
Java Object
   ↓
Session
   ↓
Persistence Context
   ↓
Dirty Checking
   ↓
SQL Generation
   ↓
JDBC
   ↓
Database
```

---

## Real Enterprise Flow

```
Controller
   ↓
Service Layer
   ↓
@Transactional
   ↓
Hibernate Session
   ↓
JDBC Connection
   ↓
Database
```

---

## Advanced Topics Usually Asked in Senior Interviews

- **Persistence Context** - Understanding the entity lifecycle container
- **Dirty Checking Internals** - How Hibernate detects changes
- **Flush Modes** - Different flushing strategies
- **Lazy vs Eager Loading** - Performance implications
- **N+1 Problem** - Query optimization
- **Proxy Objects** - Lazy loading proxies
- **Cascading** - Automatic operations on related entities
- **Transaction Propagation** - Transaction scope management
- **Optimistic vs Pessimistic Locking** - Concurrency control
- **Batch Processing** - Bulk operations
- **Hibernate Interceptors** - Custom lifecycle hooks
- **Event Listeners** - Event-driven programming
- **Second-Level Cache Internals** - Distributed caching
- **Query Plan Cache** - SQL optimization
- **Bytecode Enhancement** - Performance tuning
- **Entity Graphs** - Custom fetch strategies
