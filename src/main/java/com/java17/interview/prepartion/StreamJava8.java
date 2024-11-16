package com.java17.interview.prepartion;


class StreamJava8{


    /***
     *  the Stream API was a significant enhancement in Java, introducing a powerful way to handle collections with functional-style operations. In Java 9, several improvements were made to this feature, adding more capabilities and refining its functionality. This tutorial will explore both the original Stream API and the enhancements introduced in Java 9, focusing on practical examples to illustrate their usage.

To understand this material, you need to have a basic, working knowledge of Java 8 (lambda expressions, Optional, method references).

Introduction
Java Streams, distinct from Java I/O streams (e.g., FileInputStream), are designed to facilitate efficient data processing operations. They act as wrappers around data sources, enabling functional-style operations without modifying the underlying data.


Streams are not data structures but tools for performing operations like map-reduce transformations on collections. This functionality—java.util.stream—supports functional-style operations on streams of elements.

This tutorial will guide you through the core concepts and new features, starting with basic stream operations and progressing to more advanced topics. Let’s dive into a few simple examples of stream creation and usage before getting into terminology and core concepts.
Java Stream Creation

Java Stream Creation
Let’s first obtain a stream from an existing array:

private static Employee[] arrayOfEmps = {
    new Employee(1, "Jeff Bezos", 100000.0), 
    new Employee(2, "Bill Gates", 200000.0), 
    new Employee(3, "Mark Zuckerberg", 300000.0)
};

Stream.of(arrayOfEmps);
We can also obtain a stream from an existing list:

private static List<Employee> empList = Arrays.asList(arrayOfEmps);
empList.stream();
And we can create a stream from individual objects using Stream.of():

Stream.of(arrayOfEmps[0], arrayOfEmps[1], arrayOfEmps[2]);
Or simply using Stream.builder():

Stream.Builder<Employee> empStreamBuilder = Stream.builder();

empStreamBuilder.accept(arrayOfEmps[0]);
empStreamBuilder.accept(arrayOfEmps[1]);
empStreamBuilder.accept(arrayOfEmps[2]);

Stream<Employee> empStream = empStreamBuilder.build();
There are also other ways to obtain a stream, some of which we’ll see in the sections below.

Java Stream Operations
Let’s now see some common usages and operations we can perform on and with the help of the stream support in the language.

forEach
forEach() is the simplest and most common operation; it loops over the stream elements, calling the supplied function on each element.

The method is so common that it has been introduced directly in Iterable, Map etc.:

@Test
public void whenIncrementSalaryForEachEmployee_thenApplyNewSalary() {    
    empList.stream().forEach(e -> e.salaryIncrement(10.0));
    
    assertThat(empList, contains(
      hasProperty("salary", equalTo(110000.0)),
      hasProperty("salary", equalTo(220000.0)),
      hasProperty("salary", equalTo(330000.0))
    ));
}
This will effectively call the salaryIncrement() on each element in the empList.

forEach() is a terminal operation, which means that, after the operation is performed, the stream pipeline is considered consumed, and can no longer be used. We’ll talk more about terminal operations in the next section.

map
map() produces a new stream after applying a function to each element of the original stream. The new stream could be of a different type.

The following example converts the stream of Integers into the stream of Employees:

@Test
public void whenMapIdToEmployees_thenGetEmployeeStream() {
    Integer[] empIds = { 1, 2, 3 };
    
    List<Employee> employees = Stream.of(empIds)
      .map(employeeRepository::findById)
      .collect(Collectors.toList());
    
    assertEquals(employees.size(), empIds.length);
}
Here, we obtain an Integer stream of employee IDs from an array. Each Integer is passed to the function employeeRepository::findById()—which returns the corresponding Employee object. This effectively forms an Employee stream.

collect
We saw how collect() works in the previous example; it’s one of the common ways to get stuff out of the stream once we are done with all the processing:

@Test
public void whenCollectStreamToList_thenGetList() {
    List<Employee> employees = empList.stream().collect(Collectors.toList());
    
    assertEquals(empList, employees);
}
collect() performs mutable fold operations (repackaging elements to some data structures and applying some additional logic, concatenating them, etc.) on data elements held in the Stream instance.

The strategy for this operation is provided via the Collector interface implementation. In the example above, we used the toList collector to collect all Stream elements into a List instance.

filter
Next, let’s have a look at filter(). This produces a new stream that contains elements of the original stream that pass a given test (specified by a predicate).

Let’s have a look at how that works:

@Test
public void whenFilterEmployees_thenGetFilteredStream() {
    Integer[] empIds = { 1, 2, 3, 4 };
    
    List<Employee> employees = Stream.of(empIds)
      .map(employeeRepository::findById)
      .filter(e -> e != null)
      .filter(e -> e.getSalary() > 200000)
      .collect(Collectors.toList());
    
    assertEquals(Arrays.asList(arrayOfEmps[2]), employees);
}
In the example above, we first filter out null references for invalid employee ids and then again apply a filter to only keep employees with salaries over a certain threshold.

findFirst
findFirst() returns an Optional for the first entry in the stream. The Optional can, of course, be empty:

@Test
public void whenFindFirst_thenGetFirstEmployeeInStream() {
    Integer[] empIds = { 1, 2, 3, 4 };
    
    Employee employee = Stream.of(empIds)
      .map(employeeRepository::findById)
      .filter(e -> e != null)
      .filter(e -> e.getSalary() > 100000)
      .findFirst()
      .orElse(null);
    
    assertEquals(employee.getSalary(), new Double(200000));
}
Here, the first employee with a salary greater than 100000 is returned. If no such employee exists, then null is returned.

toArray
We saw how we used collect() to get data out of the stream. If we need to get an array out of the stream, we can simply use toArray():

@Test
public void whenStreamToArray_thenGetArray() {
    Employee[] employees = empList.stream().toArray(Employee[]::new);

    assertThat(empList.toArray(), equalTo(employees));
}
The syntax Employee[]::new creates an empty array of Employee—which is then filled with elements from the stream.

flatMap
A stream can hold complex data structures like Stream<List<String>>. In cases like this, flatMap() helps us to flatten the data structure to simplify further operations:

@Test
public void whenFlatMapEmployeeNames_thenGetNameStream() {
    List<List<String>> namesNested = Arrays.asList( 
      Arrays.asList("Jeff", "Bezos"), 
      Arrays.asList("Bill", "Gates"), 
      Arrays.asList("Mark", "Zuckerberg"));

    List<String> namesFlatStream = namesNested.stream()
      .flatMap(Collection::stream)
      .collect(Collectors.toList());

    assertEquals(namesFlatStream.size(), namesNested.size() * 2);
}
Notice how we were able to convert the Stream<List<String>> to a simpler Stream<String>—using the flatMap() API.

peek
We saw forEach() earlier in this section, which is a terminal operation. However, sometimes we need to perform multiple operations on each element of the stream before any terminal operation is applied.

peek() can be useful in situations like this. Simply put, it performs the specified operation on each element of the stream and returns a new stream that can be used further. peek() is an intermediate operation:

@Test
public void whenIncrementSalaryUsingPeek_thenApplyNewSalary() {
    Employee[] arrayOfEmps = {
        new Employee(1, "Jeff Bezos", 100000.0), 
        new Employee(2, "Bill Gates", 200000.0), 
        new Employee(3, "Mark Zuckerberg", 300000.0)
    };

    List<Employee> empList = Arrays.asList(arrayOfEmps);
    
    empList.stream()
      .peek(e -> e.salaryIncrement(10.0))
      .peek(System.out::println)
      .collect(Collectors.toList());

    assertThat(empList, contains(
      hasProperty("salary", equalTo(110000.0)),
      hasProperty("salary", equalTo(220000.0)),
      hasProperty("salary", equalTo(330000.0))
    ));
}
Flat Mapping Operations: flatMapToInt, flatMapToLong, and flatMapToDouble
These methods transform a stream’s elements into an IntStream, LongStream, or DoubleStream respectively, which are specialized streams for handling primitive data types efficiently. By using these methods, you can avoid the overhead associated with boxing and unboxing objects.


mapToInt
Transforms elements to an IntStream.

List<String> numbersAsString = Arrays.asList("10000000000", "20000000000");
LongStream longStream = numbersAsString.stream()
                                       .mapToLong(Long::parseLong);

longStream.forEach(System.out::println);
This example converts a list of strings to an IntStream of integers.

mapToLong
Transforms elements to a LongStream.

List<String> numbersAsString = Arrays.asList("10000000000", "20000000000");
LongStream longStream = numbersAsString.stream()
                                       .mapToLong(Long::parseLong);

longStream.forEach(System.out::println);
Here, each string is parsed into a long value, producing a LongStream.

mapToDouble
Transforms elements to a DoubleStream.

List<String> numbersAsString = Arrays.asList("1.5", "2.5", "3.5");
DoubleStream doubleStream = numbersAsString.stream()
                                           .mapToDouble(Double::parseDouble);

doubleStream.forEach(System.out::println);
This operation converts strings to a DoubleStream of floating-point numbers.

Flat Mapping Operations: flatMapToInt, flatMapToLong, and flatMapToDouble
These operations are used when each element of a stream should be mapped to a stream of primitive values (IntStream, LongStream, or DoubleStream). They flatten the resulting streams into a single stream.

flatMapToInt
Maps each element to an IntStream and flattens the result.

Stream<String> strings = Stream.of("1,2,3", "4,5");
IntStream intStream = strings.flatMapToInt(s -> Arrays.stream(s.split(","))
                                             .mapToInt(Integer::parseInt));

intStream.forEach(System.out::println);
In this example, each string containing numbers separated by commas is split, parsed into integers, and flattened into a single IntStream.

flatMapToLong
Similar to flatMapToInt, flatMapToLong produces a LongStream.

Stream<String> strings = Stream.of("10000000000,20000000000", "30000000000");
LongStream longStream = strings.flatMapToLong(s -> Arrays.stream(s.split(","))
                                            .mapToLong(Long::parseLong));

longStream.forEach(System.out::println);
This maps each string to a LongStream of long values.

flatMapToDouble
Maps each element to a DoubleStream and flattens the result.

Stream<String> strings = Stream.of("1.1,2.2", "3.3,4.4");
DoubleStream doubleStream = strings.flatMapToDouble(s -> Arrays.stream(s.split(","))
                                            .mapToDouble(Double::parseDouble));

doubleStream.forEach(System.out::println);
Advanced Mapping with mapMulti and Variants
Introduced in Java 9, the mapMulti methods provide a powerful way to perform multi-level mappings, allowing you to handle more complex transformations that yield multiple results from a single input element.

mapMulti
mapMulti is a flexible version of flatMap, allowing more control over the mapping and the elements’ addition to the output.

Stream.of(1, 2, 3).<String>mapMulti((number, consumer) -> {
    consumer.accept(number + "a");
    consumer.accept(number + "b");
  }).forEach(System.out::println);
This example demonstrates generating two strings from each integer and adding them to the resulting stream.

mapMultiToInt, mapMultiToLong, and mapMultiToDouble
These are specialized versions of mapMulti for primitive streams.

mapMultiToInt
Similar to mapMulti but for IntStream.

Stream.of("1,2", "3,4").mapMultiToInt((s, consumer) -> {
    Arrays.stream(s.split(",")).mapToInt(Integer::parseInt).forEach(consumer);
  }).forEach(System.out::println);
This example splits each string and maps them to integers, collecting them into an IntStream.

mapMultiToLong
Used for generating a LongStream.

Stream.of("10000000000,20000000000").mapMultiToLong((s, consumer) -> {
    Arrays.stream(s.split(",")).mapToLong(Long::parseLong).forEach(consumer);
  }).forEach(System.out::println);
It splits strings and maps the parts to a LongStream.

mapMultiToDouble
For creating a DoubleStream.

Stream.of("1.1,2.2").mapMultiToDouble((s, consumer) -> {
    Arrays.stream(s.split(",")).mapToDouble(Double::parseDouble).forEach(consumer);
  }).forEach(System.out::println);
Here, each input string is split and converted into a DoubleStream.

These operations offer greater flexibility and efficiency, particularly when dealing with primitive data types or complex data transformations. They enhance the Java Streams API by providing more granular control over data processing, allowing for more concise and expressive code.

Method Types and Pipelines
As we’ve been discussing, Java stream operations are divided into intermediate and terminal operations.

Intermediate operations such as filter() return a new stream on which further processing can be done. Terminal operations, such as forEach(), mark the stream as consumed, after which point it can no longer be used further.

A stream pipeline consists of a stream source, followed by zero or more intermediate operations, and a terminal operation.

Here’s a sample stream pipeline, where empList is the source, filter() is the intermediate operation and count is the terminal operation:

@Test
public void whenStreamCount_thenGetElementCount() {
    Long empCount = empList.stream()
      .filter(e -> e.getSalary() > 200000)
      .count();

    assertEquals(empCount, new Long(1));
}
Some operations are deemed short-circuiting operations. Short-circuiting operations allow computations on infinite streams to be completed in finite time:

@Test
public void whenLimitInfiniteStream_thenGetFiniteElements() {
    Stream<Integer> infiniteStream = Stream.iterate(2, i -> i * 2);

    List<Integer> collect = infiniteStream
      .skip(3)
      .limit(5)
      .collect(Collectors.toList());

    assertEquals(collect, Arrays.asList(16, 32, 64, 128, 256));
}
Here, we use short-circuiting operations skip() to skip first three elements, and limit() to limit to five elements from the infinite stream generated using iterate().

We’ll talk more about infinite streams later on.

Lazy Evaluation
One of the most important characteristics of Java streams is that they allow for significant optimizations through lazy evaluations.

Computation on the source data is only performed when the terminal operation is initiated, and source elements are consumed only as needed.

All intermediate operations are lazy, so they’re not executed until a result of processing is needed.

For example, consider the findFirst() example we saw earlier. How many times is the map() operation performed here? four times since the input array contains four elements?

@Test
public void whenFindFirst_thenGetFirstEmployeeInStream() {
    Integer[] empIds = { 1, 2, 3, 4 };
    
    Employee employee = Stream.of(empIds)
      .map(employeeRepository::findById)
      .filter(e -> e != null)
      .filter(e -> e.getSalary() > 100000)
      .findFirst()
      .orElse(null);
    
    assertEquals(employee.getSalary(), new Double(200000));
}
Stream performs the map and two filter operations, one element at a time.

It first performs all the operations on ID 1. Since the salary of ID 1 is not greater than 100000, the processing moves on to the next element.

ID 2 satisfies both of the filter predicates and hence the stream evaluates the terminal operation findFirst() and returns the result.

No operations are performed on IDs 3 and 4.

Processing streams lazily allows for avoiding examining all the data when that’s not necessary. This behavior becomes even more important when the input stream is infinite and not just very large.


Comparison-Based Stream Operations
sorted
Let’s start with the sorted() operation—this sorts the stream elements based on the comparator passed we pass into it.

For example, we can sort Employees based on their names:

@Test
public void whenSortStream_thenGetSortedStream() {
    List<Employee> employees = empList.stream()
      .sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))
      .collect(Collectors.toList());

    assertEquals(employees.get(0).getName(), "Bill Gates");
    assertEquals(employees.get(1).getName(), "Jeff Bezos");
    assertEquals(employees.get(2).getName(), "Mark Zuckerberg");
}
Note that short-circuiting will not be applied for sorted().

This means, in the example above, even if we had used findFirst() after the sorted(), the sorting of all the elements is done before applying the findFirst(). This happens because the operation cannot know what the first element is until the entire stream is sorted.

min and max
As the names suggest, min() and max() return the minimum and maximum element in the stream respectively, based on a comparator. They return an Optional since a result may or may not exist (due to, say, filtering):

@Test
public void whenFindMin_thenGetMinElementFromStream() {
    Employee firstEmp = empList.stream()
      .min((e1, e2) -> e1.getId() - e2.getId())
      .orElseThrow(NoSuchElementException::new);

    assertEquals(firstEmp.getId(), new Integer(1));
}
We can also avoid defining the comparison logic by using Comparator.comparing():

@Test
public void whenFindMax_thenGetMaxElementFromStream() {
    Employee maxSalEmp = empList.stream()
      .max(Comparator.comparing(Employee::getSalary))
      .orElseThrow(NoSuchElementException::new);

    assertEquals(maxSalEmp.getSalary(), new Double(300000.0));
}
distinct
distinct() does not take any argument and returns the distinct elements in the stream, eliminating duplicates. It uses the equals() method of the elements to decide whether two elements are equal or not:

@Test
public void whenApplyDistinct_thenRemoveDuplicatesFromStream() {
    List<Integer> intList = Arrays.asList(2, 5, 3, 2, 4, 3);
    List<Integer> distinctIntList = intList.stream().distinct().collect(Collectors.toList());
    
    assertEquals(distinctIntList, Arrays.asList(2, 5, 3, 4));
}
allMatch, anyMatch, and noneMatch
These operations all take a predicate and return a boolean. Short-circuiting is applied and processing is stopped as soon as the answer is determined:

@Test
public void whenApplyMatch_thenReturnBoolean() {
    List<Integer> intList = Arrays.asList(2, 4, 5, 6, 8);
    
    boolean allEven = intList.stream().allMatch(i -> i % 2 == 0);
    boolean oneEven = intList.stream().anyMatch(i -> i % 2 == 0);
    boolean noneMultipleOfThree = intList.stream().noneMatch(i -> i % 3 == 0);
    
    assertEquals(allEven, false);
    assertEquals(oneEven, true);
    assertEquals(noneMultipleOfThree, false);
}
allMatch() checks if the predicate is true for all the elements in the stream. Here, it returns false as soon as it encounters 5, which is not divisible by 2.

anyMatch() checks if the predicate is true for any one element in the stream. Here, again short-circuiting is applied and true is returned immediately after the first element.

noneMatch() checks if no elements are matching the predicate. Here, it simply returns false as soon as it encounters 6, which is divisible by 3.

Java Stream Specializations
From what we discussed so far, a Stream is a stream of object references. However, there are also the IntStream, LongStream, and DoubleStream—which are primitive specializations for int, long and double respectively. These are quite convenient when dealing with a lot of numerical primitives.

These specialized streams do not extend Stream but extend BaseStream on top of which Stream is also built.

As a consequence, not all operations supported by Stream are present in these stream implementations. For example, the standard min() and max() take a comparator, whereas the specialized streams do not.

Creation
The most common way of creating an IntStream is to call mapToInt() on an existing stream:

@Test
public void whenFindMaxOnIntStream_thenGetMaxInteger() {
    Integer latestEmpId = empList.stream()
      .mapToInt(Employee::getId)
      .max()
      .orElseThrow(NoSuchElementException::new);
    
    assertEquals(latestEmpId, new Integer(3));
}
Here, we start with a Stream<Employee> and get an IntStream by supplying the Employee::getId to mapToInt. Finally, we call max() which returns the highest integer.

We can also use IntStream.of() for creating the IntStream:

IntStream.of(1, 2, 3);
or IntStream.range():

IntStream.range(10, 20)
This creates IntStream of numbers 10 to 19.

One important distinction to note before we move on to the next topic:

Stream.of(1, 2, 3)
This returns a Stream<Integer> and not IntStream.

Similarly, using map() instead of mapToInt() returns a Stream<Integer> and not an IntStream.:

empList.stream().map(Employee::getId);
Specialized Operations
Specialized streams provide additional operations as compared to the standard Stream—which are quite convenient when dealing with numbers.

For example sum(), average(), range() etc.:

@Test
public void whenApplySumOnIntStream_thenGetSum() {
    Double avgSal = empList.stream()
      .mapToDouble(Employee::getSalary)
      .average()
      .orElseThrow(NoSuchElementException::new);
    
    assertEquals(avgSal, new Double(200000));
}
Reduction Operations
A reduction operation (also called a fold) takes a sequence of input elements and combines them into a single summary result by repeated application of a combining operation. We already saw a few reduction operations like findFirst(), min() and max().

Let’s see the general-purpose reduce() operation in action.

reduce
The most common form of reduce() is:

T reduce(T identity, BinaryOperator<T> accumulator)
Here, identity is the starting value and accumulator is the binary operation we repeatedly apply.

For example:

@Test
public void whenApplyReduceOnStream_thenGetValue() {
    Double sumSal = empList.stream()
      .map(Employee::getSalary)
      .reduce(0.0, Double::sum);

    assertEquals(sumSal, new Double(600000));
}
Here, we start with the initial value of 0 and repeatedly apply Double::sum() on elements of the stream. Effectively we’ve implemented the DoubleStream.sum() by applying reduce() on Stream.

Advanced collect
We already saw how we used Collectors.toList() to get the list out of the stream. Let’s now see a few more ways to collect elements from the stream.

joining
@Test
public void whenCollectByJoining_thenGetJoinedString() {
    String empNames = empList.stream()
      .map(Employee::getName)
      .collect(Collectors.joining(", "))
      .toString();
    
    assertEquals(empNames, "Jeff Bezos, Bill Gates, Mark Zuckerberg");
}
Collectors.joining() will insert the delimiter between the two String elements of the stream. It internally uses a java.util.StringJoiner to perform the joining operation.

toSet
We can also use toSet() to get a set out of stream elements:

@Test
public void whenCollectBySet_thenGetSet() {
    Set<String> empNames = empList.stream()
            .map(Employee::getName)
            .collect(Collectors.toSet());
    
    assertEquals(empNames.size(), 3);
}
toCollection
We can use Collectors.toCollection() to extract the elements into any other collection by passing in a Supplier<Collection>. We can also use a constructor reference for the Supplier:

@Test
public void whenToVectorCollection_thenGetVector() {
    Vector<String> empNames = empList.stream()
            .map(Employee::getName)
            .collect(Collectors.toCollection(Vector::new));
    
    assertEquals(empNames.size(), 3);
}
Here, an empty collection is created internally, and its add() method is called on each element of the stream.

summarizingDouble
summarizingDouble() is another interesting collector—which applies a double-producing mapping function to each input element and returns a special class containing statistical information for the resulting values:

@Test
public void whenApplySummarizing_thenGetBasicStats() {
    DoubleSummaryStatistics stats = empList.stream()
      .collect(Collectors.summarizingDouble(Employee::getSalary));

    assertEquals(stats.getCount(), 3);
    assertEquals(stats.getSum(), 600000.0, 0);
    assertEquals(stats.getMin(), 100000.0, 0);
    assertEquals(stats.getMax(), 300000.0, 0);
    assertEquals(stats.getAverage(), 200000.0, 0);
}
Notice how we can analyze the salary of each employee and get statistical information on that data—such as min, max, average etc.

summaryStatistics() can be used to generate similar results when we’re using one of the specialized streams:

@Test
public void whenApplySummaryStatistics_thenGetBasicStats() {
    DoubleSummaryStatistics stats = empList.stream()
      .mapToDouble(Employee::getSalary)
      .summaryStatistics();

    assertEquals(stats.getCount(), 3);
    assertEquals(stats.getSum(), 600000.0, 0);
    assertEquals(stats.getMin(), 100000.0, 0);
    assertEquals(stats.getMax(), 300000.0, 0);
    assertEquals(stats.getAverage(), 200000.0, 0);
}
partitioningBy
We can partition a stream into two—based on whether the elements satisfy certain criteria or not.

Let’s split our List of numerical data, into even and odds:

@Test
public void whenStreamPartition_thenGetMap() {
    List<Integer> intList = Arrays.asList(2, 4, 5, 6, 8);
    Map<Boolean, List<Integer>> isEven = intList.stream().collect(
      Collectors.partitioningBy(i -> i % 2 == 0));
    
    assertEquals(isEven.get(true).size(), 4);
    assertEquals(isEven.get(false).size(), 1);
}
Here, the stream is partitioned into a Map, with even and odds stored as true and false keys.

groupingBy
groupingBy() offers advanced partitioning—where we can partition the stream into more than just two groups.

It takes a classification function as its parameter. This classification function is applied to each element of the stream.

The value returned by the function is used as a key to the map that we get from the groupingBy collector:

@Test
public void whenStreamGroupingBy_thenGetMap() {
    Map<Character, List<Employee>> groupByAlphabet = empList.stream().collect(
      Collectors.groupingBy(e -> new Character(e.getName().charAt(0))));

    assertEquals(groupByAlphabet.get('B').get(0).getName(), "Bill Gates");
    assertEquals(groupByAlphabet.get('J').get(0).getName(), "Jeff Bezos");
    assertEquals(groupByAlphabet.get('M').get(0).getName(), "Mark Zuckerberg");
}
In this quick example, we grouped the employees based on the initial character of their first name.

mapping
groupingBy() discussed in the section above, groups elements of the stream with the use of a Map.

However, sometimes we might need to group data into a type other than the element type.

Here’s how we can do that; we can use mapping(), which can adapt the collector to a different type—using a mapping function:

@Test
public void whenStreamMapping_thenGetMap() {
    Map<Character, List<Integer>> idGroupedByAlphabet = empList.stream().collect(
      Collectors.groupingBy(e -> new Character(e.getName().charAt(0)),
        Collectors.mapping(Employee::getId, Collectors.toList())));

    assertEquals(idGroupedByAlphabet.get('B').get(0), new Integer(2));
    assertEquals(idGroupedByAlphabet.get('J').get(0), new Integer(1));
    assertEquals(idGroupedByAlphabet.get('M').get(0), new Integer(3));
}
Here mapping() maps the stream element Employee into just the employee ID—which is an Integer—using the getId() mapping function. These IDs are still grouped based on the initial character of employee first name.

reducing
reducing() is similar to reduce() – which we explored before. It simply returns a collector which performs a reduction of its input elements:

@Test
public void whenStreamReducing_thenGetValue() {
    Double percentage = 10.0;
    Double salIncrOverhead = empList.stream().collect(Collectors.reducing(
        0.0, e -> e.getSalary() * percentage / 100, (s1, s2) -> s1 + s2));

    assertEquals(salIncrOverhead, 60000.0, 0);
}
Here, reducing() gets the salary increment of each employee and returns the sum.

reducing() is most useful when used in a multi-level reduction, downstream of groupingBy() or partitioningBy(). To perform a simple reduction on a stream, use reduce() instead.

For example, let’s see how we can use reducing() with groupingBy():

@Test
public void whenStreamGroupingAndReducing_thenGetMap() {
    Comparator<Employee> byNameLength = Comparator.comparing(Employee::getName);
    
    Map<Character, Optional<Employee>> longestNameByAlphabet = empList.stream().collect(
      Collectors.groupingBy(e -> new Character(e.getName().charAt(0)),
        Collectors.reducing(BinaryOperator.maxBy(byNameLength))));

    assertEquals(longestNameByAlphabet.get('B').get().getName(), "Bill Gates");
    assertEquals(longestNameByAlphabet.get('J').get().getName(), "Jeff Bezos");
    assertEquals(longestNameByAlphabet.get('M').get().getName(), "Mark Zuckerberg");
}
Here, we group the employees based on the initial character of their first name. Within each group, we find the employee with the longest name.

Parallel Streams
Using the support for parallel streams, we can perform stream operations in parallel without having to write any boilerplate code; we just have to designate the stream as parallel:

@Test
public void whenParallelStream_thenPerformOperationsInParallel() {
    Employee[] arrayOfEmps = {
      new Employee(1, "Jeff Bezos", 100000.0), 
      new Employee(2, "Bill Gates", 200000.0), 
      new Employee(3, "Mark Zuckerberg", 300000.0)
    };

    List<Employee> empList = Arrays.asList(arrayOfEmps);
    
    empList.stream().parallel().forEach(e -> e.salaryIncrement(10.0));
    
    assertThat(empList, contains(
      hasProperty("salary", equalTo(110000.0)),
      hasProperty("salary", equalTo(220000.0)),
      hasProperty("salary", equalTo(330000.0))
    ));
}
Here salaryIncrement() would get executed in parallel on multiple elements of the stream, by simply adding the parallel() syntax.

This functionality can, of course, be tuned and configured further, if you need more control over the performance characteristics of the operation.

As is the case when writing multi-threaded code. We need to be aware of a few things while using parallel streams:

We need to ensure that the code is thread-safe. Take special care if the operations performed in parallel modify shared data.
We should not use parallel streams if the order in which operations are performed or the order returned in the output stream matters. For example operations like findFirst() may generate different results in the case of parallel streams.
Also, we should ensure that it’s worth making the code execute in parallel. Understanding the performance characteristics of the operation in particular, but also of the system as a whole – is naturally very important here.
Infinite Streams
Sometimes, we might want to perform operations while the elements are still getting generated. We might not know beforehand how many elements we’ll need. Unlike using list or map, where all the elements are already populated, we can use infinite streams, also called unbounded streams.

There are two ways to generate infinite streams:

generate
We provide a Supplier to generate() which gets called whenever new stream elements need to be generated:

@Test
public void whenGenerateStream_thenGetInfiniteStream() {
    Stream.generate(Math::random)
      .limit(5)
      .forEach(System.out::println);
}
Here, we pass Math::random() as a Supplier, which returns the next random number.

With infinite streams, we need to provide a condition to eventually terminate the processing. One common way of doing this is using limit(). In the above example, we limit the stream to five random numbers and print them as they get generated.

Please note that the Supplier passed to generate() could be stateful and such a stream may not produce the same result when used in parallel.

iterate
iterate() takes two parameters: an initial value, called the seed element and a function that generates the next element using the previous value. By design, iterate() is stateful and hence may not be useful in parallel streams:

@Test
public void whenIterateStream_thenGetInfiniteStream() {
    Stream<Integer> evenNumStream = Stream.iterate(2, i -> i * 2);

    List<Integer> collect = evenNumStream
      .limit(5)
      .collect(Collectors.toList());

    assertEquals(collect, Arrays.asList(2, 4, 8, 16, 32));
}
Here, we pass 2 as the seed value, which becomes the first element of our stream. This value is passed as input to the lambda, which returns 4. This value, in turn, is passed as input in the next iteration.

This continues until we generate the number of elements specified by limit(), which acts as the terminating condition.

File Operations
Let’s see how we can use the stream in file operations.

File Write Operation
@Test
public void whenStreamToFile_thenGetFile() throws IOException {
    String[] words = {
      "hello", 
      "refer",
      "world",
      "level"
    };
    
    try (PrintWriter pw = new PrintWriter(
      Files.newBufferedWriter(Paths.get(fileName)))) {
        Stream.of(words).forEach(pw::println);
    }
}
Here we use forEach() to write each element of the stream into the file by calling PrintWriter.println().

File Read Operation
private List<String> getPalindrome(Stream<String> stream, int length) {
    return stream.filter(s -> s.length() == length)
      .filter(s -> s.compareToIgnoreCase(
        new StringBuilder(s).reverse().toString()) == 0)
      .collect(Collectors.toList());
}

@Test
public void whenFileToStream_thenGetStream() throws IOException {
    List<String> str = getPalindrome(Files.lines(Paths.get(fileName)), 5);
    assertThat(str, contains("refer", "level"));
}
Here Files.lines() returns the lines from the file as a Stream which is consumed by the getPalindrome() for further processing.

getPalindrome() works on the stream, completely unaware of how the stream was generated. This also increases code reusability and simplifies unit testing.

Java Streams Improvements in Java 9
Java Streams offers a robust and declarative approach to processing collections of data in Java, initially introduced in Java 8. With Java 9, several enhancements to the Streams API make it even more powerful and expressive. In this section, we’ll explore key improvements, including new methods like takeWhile, dropWhile, iterate, ofNullable, and concat.

takeWhile
The takeWhile method is a significant addition to the Streams API in Java 9. It allows you to consume elements from a stream as long as a specified condition holds. Once the condition becomes false, the method stops and returns a new stream containing only the elements that match the predicate.

View this as a conditional filter with a short-circuiting capability. Here’s a simple example to illustrate:

Stream.iterate(1, i -> i + 1)
                .takeWhile(n -> n <= 10)
                .map(x -> x * x)
                .forEach(System.out::println);
In the example above, we create an infinite stream starting from 1, then use takeWhile to select numbers less than or equal to 10, calculate their squares, and print them.

You might wonder how takeWhile differs from the filter method. Consider the following:

Stream.iterate(1, i -> i + 1)
      .filter(x -> x <= 10)
      .map(x -> x * x)
      .forEach(System.out::println);
While both examples yield the same result in this scenario, the difference lies in how takeWhile operates. It stops processing as soon as the predicate is false, whereas filter evaluates the entire stream:

Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0)
      .takeWhile(x -> x <= 5)
      .forEach(System.out::println);

Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0)
      .filter(x -> x <= 5)
      .forEach(System.out::println);
The output for takeWhile will be:

1
2
3
4
5
While the output for filter will be:

1
2
3
4
5
0
5
4
3
2
1
0
As demonstrated, takeWhile stops once it encounters the first non-matching element, whereas filter continues through the entire stream.

dropWhile
The dropWhile method, introduced in Java 9, acts as the complement to takeWhile. Instead of taking elements while a condition is true, dropWhile skips elements while the condition is true and starts returning elements once the condition becomes false.

Consider the following example:

Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0)
      .dropWhile(x -> x <= 5)
      .forEach(System.out::println);
The output will be:

6
7
8
9
0
9
8
7
6
5
4
3
2
1
0
In this example, dropWhile skips all elements less than or equal to 5 and starts including elements once it encounters the first element greater than 5.

Enhanced iterate Method
Java 8 introduced the iterate method, which allowed the creation of infinite streams using a seed and a unary operator. However, the method lacked a way to terminate the loop, making it suitable primarily for infinite streams. Java 9 addresses this limitation by introducing an overloaded version of iterate with an additional predicate parameter to control termination:

Stream.iterate(1, i -> i < 256, i -> i * 2)
      .forEach(System.out::println);
The above code generates a stream of powers of two, stopping when the value reaches or exceeds 256. This new iterate method behaves like a for loop and performs as a modern alternative:

for (int i = 1; i < 256; i *= 2) {
      System.out.println(i);

}
ofNullable
The ofNullable method, another valuable addition in Java 9, provides a convenient way to create a stream from a single nullable element. This method avoids potential NullPointerExceptions and helps keep your code cleaner:

Integer number = null;
Stream<Integer> result = Stream.ofNullable(number);
result.map(x -> x * x).forEach(System.out::println);
If the number is null, ofNullable returns an empty stream, effectively preventing runtime errors in cases where a null value would usually cause issues.

This approach simplifies scenarios where a value might be null, offering a more concise way to handle optional values:

Stream<Integer> result = number != null
    ? Stream.of(number)
    : Stream.empty();

concat
The contact method in Java Streams is another useful feature for combining streams. It allows you to concatenate two or more streams into a single stream, which is particularly handy when you need to merge data from multiple sources into a unified stream for further processing. The contact method merges two streams into a single stream.

This method can be used with streams of any type (Stream<T>, IntStream, LongStream, or DoubleStream). When applied, it creates a new stream that contains all elements from the first stream followed by all elements from the second stream.

Stream.concat(Stream<? super T> a, Stream<? extends T> b)
Combines two streams of the same type.

Stream<String> firstStream = Stream.of("A", "B", "C");
Stream<String> secondStream = Stream.of("D", "E", "F");

Stream<String> concatenatedStream = Stream.concat(firstStream, secondStream);
concatenatedStream.forEach(System.out::println);
In this example, the output will be:

A
B
C
D
E
F
The concat method merges firstStream and secondStream, resulting in a stream where all elements from firstStream are followed by all elements from secondStream.

Special Cases for Primitive Streams
The contact method also works with specialized streams like IntStream, LongStream, and DoubleStream. Here’s how it can be used with these primitive types:

IntStream.concat(IntStream a, IntStream b)
Concatenates two IntStream instances.

IntStream firstStream = IntStream.of(1, 2, 3);
IntStream secondStream = IntStream.of(4, 5, 6);

IntStream concatenatedStream = IntStream.concat(firstStream, secondStream);
concatenatedStream.forEach(System.out::println);
This will output:

1
2
3
4
5
6
LongStream.concat(LongStream a, LongStream b)
Concatenates two LongStream instances.

LongStream firstStream = LongStream.of(10L, 20L, 30L);
LongStream secondStream = LongStream.of(40L, 50L, 60L);

LongStream concatenatedStream = LongStream.concat(firstStream, secondStream);
concatenatedStream.forEach(System.out::println);
This will output:

10
20
30
40
50
60
DoubleStream.concat(DoubleStream a, DoubleStream b)

Concatenates two DoubleStream instances.

DoubleStream firstStream = DoubleStream.of(1.1, 2.2, 3.3);
DoubleStream secondStream = DoubleStream.of(4.4, 5.5, 6.6);

DoubleStream concatenatedStream = DoubleStream.concat(firstStream, secondStream);
concatenatedStream.forEach(System.out::println);
This will output:

1.1
2.2
3.3
4.4
5.5
6.6
The enhancements brought to the Streams API in Java 9, including takeWhile, dropWhile, iterate, ofNullable, and concat, provide developers with more control and flexibility in stream processing.

Java Streams: What Are the Next Steps?
This article introduces new details of Stream functionality released in Java 8. We saw various operations supported, as well as how lambdas and pipelines can be used to write concise code. We also saw some characteristics of streams like lazy evaluation, and parallel and infinite streams. You’ll find the sources of the examples on GitHub.
     */
}