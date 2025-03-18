package com.example.library.service;

import com.example.library.entities.Book;
import com.example.library.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(){
       return this.bookRepository.findAll();
    }

    public Book getBookById(long id){
        return this.bookRepository.findById(id).get();
    }

    @Transactional
    public Book addBook(Book book){
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new RuntimeException("ISBN is already in use");
        }
        return this.bookRepository.save(book);
    }



    public void deleteBook(long id){
         this.bookRepository.deleteById(id);
    }
}
