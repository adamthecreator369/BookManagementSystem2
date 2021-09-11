package com.example.demo.book;


import javax.persistence.*;

@Entity
@Table
public class Book {

    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    private Long id;
    private String isbn;
    private String title;
    private String author;
    private String status;

    public Book() {
    }

    public Book(Long id, String isbn, String title, String author, String status) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.status = status;
    }

    public Book(String isbn, String title, String author, String status) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", status=" + status +
                '}';
    }
}
