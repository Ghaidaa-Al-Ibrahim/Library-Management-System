package com.example.library.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "book")
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long id;


    @NotBlank(message = "Title cannot be empty")
    @Column(name = "book_title")
    private String title;



    @NotBlank(message = "Author cannot be empty")
    @Column(name = "book_author")
    private String author;

    @NotNull(message = "Publication year cannot be null")
    @Column(name = "book_publication_year")
    private Date publication_year;


    @NotBlank(message = "ISBN cannot be empty")
    @Size(min = 10, max = 13, message = "ISBN must be between 10 and 13 characters")
    @Column(name = "book_isbn",unique = true)
    private String  isbn;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BorrowingRecord> borrowingRecords= new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(Date publication_year) {
        this.publication_year = publication_year;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<BorrowingRecord> getBorrowingRecords() {
        return borrowingRecords;
    }

    public void setBorrowingRecords(List<BorrowingRecord> borrowingRecords) {
        this.borrowingRecords = borrowingRecords;
    }
}
