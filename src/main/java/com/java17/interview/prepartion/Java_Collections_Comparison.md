# Java Collections and Maps - Comprehensive Comparison

## Main Comparison Table

| Collection / Map | Interface | Internal Data Structure | Duplicate Elements | Null Key Allowed | Null Values Allowed | Insertion Order Preserved | Sorted Order | Thread Safe |
|---|---|---|---|---|---|---|---|---|
| ArrayList | List | Dynamic Array | Yes | N/A | Yes | Yes | No | No |
| LinkedList | List, Deque | Doubly Linked List | Yes | N/A | Yes | Yes | No | No |
| Vector | List | Dynamic Array | Yes | N/A | Yes | Yes | No | Yes |
| Stack | List | Dynamic Array (extends Vector) | Yes | N/A | Yes | Yes | No | Yes |
| HashMap | Map | Hash Table (Array + Bucket + Tree after threshold) | Key No, Value Yes | 1 Null Key | Multiple Null Values | No | No | No |
| LinkedHashMap | Map | Hash Table + Doubly Linked List | Key No, Value Yes | 1 Null Key | Multiple Null Values | Yes | No | No |
| Hashtable | Map | Hash Table | Key No, Value Yes | No | No | No | No | Yes |
| TreeMap | NavigableMap | Red-Black Tree | Key No, Value Yes | No (Natural Ordering) | Yes | No | Yes (Key Sorted) | No |
| ConcurrentHashMap | ConcurrentMap | Segmented Hash Table / CAS Based | Key No, Value Yes | No | No | No | No | Yes |
| HashSet | Set | HashMap Internally | No | One Null Element | N/A | No | No | No |
| LinkedHashSet | Set | LinkedHashMap Internally | No | One Null Element | N/A | Yes | No | No |
| TreeSet | NavigableSet | TreeMap Internally (Red-Black Tree) | No | No | N/A | No | Yes | No |
| EnumSet | Set | Bit Vector | No | No | N/A | Enum Order | Yes | No |
| PriorityQueue | Queue | Binary Heap | Yes | No | N/A | No | Priority Order | No |
| ArrayDeque | Deque | Resizable Circular Array | Yes | No | N/A | FIFO/LIFO | No | No |

## Internal Structure Visualization

### ArrayList

```text
[10][20][30][40][50]
```

- Backed by dynamic array.
- Fast random access O(1).
- Insertion in middle O(n).

### LinkedList

```text
NULL <- [10] <-> [20] <-> [30] <-> [40] -> NULL
```

- Doubly linked list.
- Random access O(n).
- Insert/Delete O(1) if node known.

### HashMap (Java 8+)

```text
Array
  |
  +-- Bucket 0 --> Node
  |
  +-- Bucket 1 --> Node -> Node -> Node
  |
  +-- Bucket 2 --> Red-Black Tree (if collisions > 8)
```

```java
HashMap<String,Integer> map = new HashMap<>();
```

- Average Search: O(1)
- Worst Case: O(log n) after treeification

### LinkedHashMap

```text
Hash Table
     +
Doubly Linked List

A <-> B <-> C <-> D
```

Maintains insertion order.

```java
{A=1, B=2, C=3}
```

Output:

```text
A B C
```

### TreeMap

```text
         50
       /    \
     30      70
    / \     / \
   20 40   60 80
```

Implemented using Red-Black Tree.

Operations:

```text
put()    O(log n)
get()    O(log n)
remove() O(log n)
```

### HashSet

```java
HashSet<String> set = new HashSet<>();
```

Actually:

```java
private transient HashMap<E,Object> map;
```

Stored as:

```text
Java -> PRESENT
Spring -> PRESENT
Kafka -> PRESENT
```

### LinkedHashSet

Internally:

```text
LinkedHashMap
```

```text
Java -> Spring -> Kafka
```

Insertion order maintained.

### TreeSet

Internally:

```text
TreeMap
```

Input:

```text
50 10 30 20
```

Output:

```text
10 20 30 50
```

Stored in Red-Black Tree.

## Time Complexity Comparison

| Operation | ArrayList | LinkedList | HashMap | LinkedHashMap | TreeMap | HashSet | LinkedHashSet | TreeSet |
|---|---|---|---|---|---|---|---|---|
| Search | O(1) | O(n) | O(1) | O(1) | O(log n) | O(1) | O(1) | O(log n) |
| Insert | O(1)* | O(1)** | O(1) | O(1) | O(log n) | O(1) | O(1) | O(log n) |
| Delete | O(n) | O(1)** | O(1) | O(1) | O(log n) | O(1) | O(1) | O(log n) |

* Amortized O(1)
* O(1) when node reference is available

## Null Handling Summary

| Collection | Null Allowed |
|---|---|
| ArrayList | Yes |
| LinkedList | Yes |
| Vector | Yes |
| HashMap Key | One Null |
| HashMap Value | Multiple Nulls |
| LinkedHashMap Key | One Null |
| LinkedHashMap Value | Multiple Nulls |
| Hashtable | No |
| ConcurrentHashMap | No |
| HashSet | One Null |
| LinkedHashSet | One Null |
| TreeMap | No (Natural Ordering) |
| TreeSet | No |
| PriorityQueue | No |
| ArrayDeque | No |

## Interview Quick Notes

| Requirement | Best Choice |
|---|---|
| Fast Random Access | ArrayList |
| Frequent Insert/Delete Middle | LinkedList |
| Fast Key Lookup | HashMap |
| Maintain Insertion Order + Map | LinkedHashMap |
| Sorted Keys | TreeMap |
| Unique Elements | HashSet |
| Unique + Insertion Order | LinkedHashSet |
| Unique + Sorted | TreeSet |
| Thread-Safe Legacy Map | Hashtable |
| Concurrent Multi-threaded Map | ConcurrentHashMap |
| Priority Processing | PriorityQueue |

These are the collection types most frequently discussed in Java interviews ranging from 3 to 15+ years of experience.
