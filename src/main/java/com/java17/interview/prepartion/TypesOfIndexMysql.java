package com.java17.interview.prepartion;

public class TypesOfIndexMysql {
}
/**
 * In MySQL, several types of indexes improve query performance by allowing faster data retrieval. Here are the primary index types:
 *
 * Primary Key Index
 *
 * Uniquely identifies each row in a table.
 * Created automatically when a primary key is defined.
 * Enforces uniqueness and prevents NULL values.
 * Unique Index
 *
 * Ensures that all values in the indexed column(s) are unique.
 * Allows one NULL value (for a single-column unique index).
 * Can be applied to one or more columns.
 * Regular (Non-Unique) Index
 *
 * Speeds up data retrieval without enforcing uniqueness.
 * Can have duplicate values.
 * Useful for columns frequently used in WHERE clauses or joins.
 * Full-Text Index
 *
 * Used for full-text searches in large text-based columns.
 * Available only for CHAR, VARCHAR, and TEXT columns.
 * Allows complex searching, such as searching for phrases and keywords.
 * Commonly used with the MATCH() function.
 * Spatial Index
 *
 * Used for spatial data types like GEOMETRY, POINT, LINESTRING, and POLYGON.
 * Supports spatial queries, such as distance and location-based searches.
 * Available in MySQL with spatial extensions (MyISAM or InnoDB).
 * Composite Index
 *
 * Combines multiple columns into a single index.
 * Useful for queries that filter by multiple columns.
 * Order of columns in a composite index matters, so it’s important to match it to query patterns.
 * Clustered Index
 *
 * Only available in storage engines like InnoDB.
 * Organizes rows based on primary key values.
 * Physical order of data in a clustered index matches the order of the primary key.
 * Covering Index
 *
 * Not a standalone index type but a usage pattern where the index “covers” all columns in a query.
 * Allows MySQL to satisfy the query without accessing the table data, making it faster.
 * Hash Index
 *
 * Used in memory-based tables, specifically MEMORY storage engine.
 * Based on hashing algorithms rather than B-trees.
 * Very fast for exact lookups but not suitable for range queries or sorting.
 * Bitmap Index (Not in standard MySQL)
 *
 * Common in data warehousing databases (e.g., Oracle).
 * Efficient for queries on columns with a small number of distinct values (low cardinality).
 * */