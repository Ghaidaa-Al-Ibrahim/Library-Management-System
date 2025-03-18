package com.example.library;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.*;

import com.example.library.controller.BookController;
import com.example.library.entities.Book;
import com.example.library.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;



public class BookControllerTest {
    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void testGetAllBooks() throws Exception {
        Book book1 = new Book();
        book1.setId(1);
        book1.setTitle("Book Title 1");
        book1.setAuthor("Author 1");
        book1.setIsbn("1234567890");

        List<Book> books = Arrays.asList(book1);

        when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(books)));

        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    public void testGetBookById() throws Exception {
        Book book = new Book();
        book.setId(1);
        book.setTitle("Book Title");
        book.setAuthor("Author");
        book.setIsbn("1234567890");

        when(bookService.getBookById(1)).thenReturn(book);

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(book)));

        verify(bookService, times(1)).getBookById(1);
    }

    @Test
    public void testAddBook() throws Exception {
        Book book = new Book();
        book.setTitle("New Book");
        book.setAuthor("New Author");
        book.setIsbn("1234567890");
        book.setPublication_year(Calendar.getInstance().getTime());

        when(bookService.addBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/api/books")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Book created successfully"));

        verify(bookService, times(1)).addBook(any(Book.class));
    }



    @Test
    public void testDeleteBook() throws Exception {
        doNothing().when(bookService).deleteBook(1);

        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isOk());

        verify(bookService, times(1)).deleteBook(1);
    }



}
