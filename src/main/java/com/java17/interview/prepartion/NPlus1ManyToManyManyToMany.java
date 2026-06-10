package com.java17.interview.prepartion;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class NPlus1ManyToManyManyToMany {
}
/**
 * Industry Practice
 * Relationship	Preferred Mapping
 * One Author → Many Books	@ManyToOne foreign key
 * Many Authors ↔ Many Books	@ManyToMany join table
 * Need metadata in relation	Explicit junction entity
 */

/**
 * Proper Join Table Design → ManyToMany
 *
 * If using separate table naturally, then @ManyToMany is cleaner.
 */


        //Author Entity
@Entity
@Table(name = "authors")
 class Auothor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Boook> books = new ArrayList<>();

    public void addBook(Boook book) {
        books.add(book);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Boook> getBooks() {
        return books;
    }

    public void setBooks(List<Boook> books) {
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
//Book Entity
@Entity
@Table(name = "books")
 class Boook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany(mappedBy = "books")
    private List<Auothor> authors = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Auothor> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Auothor> authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

@Service
 class AuthorService {

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public void printAuthorsAndBooksProblem() {

        List<Auothor> authors = repository.findAll();

        for (Auothor author : authors) {

            System.out.println(author.getName());

            // TRIGGERS EXTRA QUERY FOR EACH AUTHOR
            author.getBooks().forEach(book ->
                    System.out.println(book.getTitle()));
        }
    }



    public void printAuthorsAndBooksSolution() {

        List<Auothor> authors =
                repository.findAllAuthorsWithBooks();

        for (Auothor author : authors) {

            System.out.println(author.getName());

            author.getBooks().forEach(book ->
                    System.out.println(book.getTitle()));
        }
    }
}

interface AuthorRepository extends JpaRepository<Auothor, Long>{

//Problem
    //List<Auothor> findAll();


    //Solution 1 — Use JOIN FETCH

    @Query("SELECT DISTINCT a FROM Author a JOIN FETCH a.books")
    List<Auothor> findAllAuthorsWithBooks();

    //Solution 1.1 — Use LEFT JOIN FETCH
    @Query("""
       SELECT DISTINCT a
       FROM Author a
       LEFT JOIN FETCH a.books
       """)
    //List<Auothor> findAllAuthorsWithBooks();

    //Solution 2 — Use JOIN FETCH
    @EntityGraph(attributePaths = {"books"})
    List<Auothor> findAll();


/**
 * Difference Between JOIN FETCH and LEFT JOIN FETCH
 * Query	Result
 * JOIN FETCH a.books	Only authors having books
 * LEFT JOIN FETCH a.books	All authors, even without books
 */

}