package com.example.library.controller;


import com.example.library.entities.BorrowingRecord;

import com.example.library.service.BorrowingRecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/")
public class BorrowingRecordController {

    private BorrowingRecordService borrowingRecordService;


    @Autowired
    public BorrowingRecordController(BorrowingRecordService borrowingRecordService) {
        this.borrowingRecordService = borrowingRecordService;
    }

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<String> borrowingABook(@Valid  @PathVariable  long bookId, @Valid @PathVariable long patronId){
            this.borrowingRecordService.borrowingABook(bookId,patronId);
        return new ResponseEntity<>("Book borrowed successfully", HttpStatus.CREATED);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public BorrowingRecord returnABook(@PathVariable long bookId,@PathVariable long patronId){
        return   this.borrowingRecordService.returnABook(bookId,patronId);
    }
}
