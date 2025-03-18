package com.example.library.controller;

import com.example.library.entities.Book;
import com.example.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("books")
    public List<Book> getAllBooks(){
        return this.bookService.getAllBooks();
    }

    @GetMapping("books/{id}")
    public Book getBookById(@PathVariable long id){
        return this.bookService.getBookById(id);
    }

    @PostMapping("books")
    public ResponseEntity<String> addBook( @Valid @RequestBody   Book book){
        try {
            this.bookService.addBook(book);
            return new ResponseEntity<>("Book created successfully", HttpStatus.CREATED);
        }catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    @PutMapping("books/{id}")
    public Book editBook(@PathVariable Book book,@PathVariable long id){
        book.setId(id);
        return this.bookService.addBook(book);
    }

    @DeleteMapping("books/{id}")
    public void deleteBook(@PathVariable  long id){
        this.bookService.deleteBook(id);
    }
}
