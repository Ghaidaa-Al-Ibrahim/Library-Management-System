package com.example.library.service;

import com.example.library.entities.Book;
import com.example.library.entities.BorrowingRecord;
import com.example.library.entities.Patron;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowingRecordRepository;
import com.example.library.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BorrowingRecordService {

    private BorrowingRecordRepository borrowingRecordRepository;
    private BookRepository bookRepository;
    private PatronRepository patronRepository;


    @Autowired
    public BorrowingRecordService(BorrowingRecordRepository borrowingRecordRepository, BookRepository bookRepository, PatronRepository patronRepository) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }

    public BorrowingRecord getByBookIdAndPatronId(long bookId,long patronId){
        return this.borrowingRecordRepository.findByBookIdAndPatronId(bookId,patronId);
    }

    public BorrowingRecord borrowingABook(long bookId,long patronId){
        BorrowingRecord borrowingRecord= new BorrowingRecord();
        Book book= this.bookRepository.getById(bookId);
        borrowingRecord.setBook(book);
        Patron patron = this.patronRepository.getById(patronId);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowDate(LocalDate.now());
      return   this.borrowingRecordRepository.save(borrowingRecord);
    }

    public BorrowingRecord returnABook(long bookId,long patronId){
        BorrowingRecord borrowingRecord= this.getByBookIdAndPatronId(bookId,patronId);
        borrowingRecord.setReturnDate(LocalDate.now());
        return   this.borrowingRecordRepository.save(borrowingRecord);
    }
}
