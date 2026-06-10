package com.java17.interview.prepartion;
/**
 * Industry Practice
 * Relationship	Preferred Mapping
 * One Author → Many Books	@ManyToOne foreign key
 * Many Authors ↔ Many Books	@ManyToMany join table
 * Need metadata in relation	Explicit junction entity
 */
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class NPlus1OneToManyManyToOne {
}


/**
 * Recommended Real-World Design → ManyToOne + Foreign Key
 *
 * This is the standard approach.
 */


       // Author
@Entity
@Table(name = "authors")
 class Auoothor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(
            mappedBy = "author",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Booook> books = new ArrayList<>();
}
//Book
@Entity
@Table(name = "books")
class Booook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Auoothor author;
}