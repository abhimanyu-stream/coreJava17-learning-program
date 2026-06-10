package com.java17.interview.prepartion;

/**
 * Industry Practice
 * Relationship          Preferred Mapping
 * One Author -> Many Books    @ManyToOne foreign key
 * Many Authors <-> Many Books @ManyToMany join table
 * Need metadata in relation   Explicit junction entity
 *
 * ============================================================
 * N+1 PROBLEM — Author / Book (OneToMany / ManyToOne)
 * ============================================================
 *
 * WHAT IS THE N+1 PROBLEM?
 * When you load N parent entities (Authors) and then lazily
 * access their child collections (Books), Hibernate fires:
 *   1 query  → SELECT all authors
 *   N queries → SELECT books WHERE author_id = ? (once per author)
 * Total = N + 1 queries — a major performance issue.
 *
 * TRIGGER EXAMPLE:
 *   List<Author> authors = authorRepository.findAll();
 *   for (Author a : authors) {
 *       System.out.println(a.getBooks().size()); // triggers lazy load
 *   }
 *
 * FIX — JOIN FETCH (single query):
 *   List<Author> authors = entityManager.createQuery(
 *       "SELECT DISTINCT a FROM Author a JOIN FETCH a.books", Author.class
 *   ).getResultList();
 *
 * ============================================================
 * OWNER vs INVERSE SIDE
 * ============================================================
 *
 * Owner side   → contains @JoinColumn (Book has author_id FK)
 *                Hibernate uses this side for INSERT/UPDATE.
 * Inverse side → contains mappedBy (Author.books is read-only nav)
 *
 * CORRECT WAY to add a book (always update the owner side):
 *   book.setAuthor(author);         // owner side — persists FK
 *   author.getBooks().add(book);    // inverse side — keeps in-memory in sync
 *   bookRepository.save(book);
 *
 * Or use the helper method on Author:
 *   author.addBook(book);
 *   bookRepository.save(book);
 *
 * ============================================================
 * MANY-TO-MANY -> Replace with Join Entity (Enrollment)
 * ============================================================
 * Student <-> Course decomposed into:
 *   Student (1) ---< Enrollment >--- (1) Course
 * Enrollment holds extra fields: enrollmentDate, grade.
 * Owner side of @ManyToMany is the entity with @JoinTable.
 */

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

// ── Empty marker class (entry point for this demo file) ──────────────────────
public class NPlus1OneToManyManyToOne {
}

// ── Author entity (inverse / non-owning side) ────────────────────────────────

/**
 * Recommended Real-World Design: ManyToOne + Foreign Key.
 * Author is the logical parent; Book is the owning side (holds the FK).
 */
@Entity
@Table(name = "authors")
class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Inverse side — mappedBy points to the field in Book that owns the relation
    @OneToMany(
            mappedBy = "author",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Book> books = new ArrayList<>();

    // ── Helper methods — keep both sides in sync ──────────────────────────────

    /** Adds a book and sets the back-reference so the FK is persisted. */
    public void addBook(Book book) {
        books.add(book);
        book.setAuthor(this);
    }

    /** Removes a book and clears the back-reference. */
    public void removeBook(Book book) {
        books.remove(book);
        book.setAuthor(null);
    }

    // ── Getters / Setters ─────────────────────────────────────────────────────
    public Long getId()               { return id; }
    public void setId(Long id)        { this.id = id; }
    public String getName()           { return name; }
    public void setName(String name)  { this.name = name; }
    public List<Book> getBooks()      { return books; }
}

// ── Book entity (owning side — holds the foreign key) ────────────────────────
@Entity
@Table(name = "books")
class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // Owner side — @JoinColumn creates the author_id FK column in the books table
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    // ── Getters / Setters ─────────────────────────────────────────────────────
    public Long getId()                 { return id; }
    public void setId(Long id)          { this.id = id; }
    public String getTitle()            { return title; }
    public void setTitle(String title)  { this.title = title; }
    public Author getAuthor()           { return author; }
    public void setAuthor(Author author){ this.author = author; }
}
