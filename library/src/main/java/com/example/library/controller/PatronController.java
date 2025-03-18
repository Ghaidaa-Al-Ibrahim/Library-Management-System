package com.example.library.controller;

import com.example.library.entities.Patron;
import com.example.library.service.PatronService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/")
public class PatronController {
    private PatronService patronService;

    @Autowired
    public PatronController(PatronService patronService) {
        this.patronService = patronService;

    }

    @GetMapping("patrons")
    public List<Patron> getAllPatrons(){
        return this.patronService.getAllPatrons();
    }

    @GetMapping("patrons/{id}")
    public Patron getPatronById(@PathVariable  long id){
        return this.patronService.getPatronById(id);
    }

    @PostMapping("patrons")
    public ResponseEntity<String> addPatron(@Valid  @RequestBody Patron patron){
          this.patronService.addPatron(patron);
        return new ResponseEntity<>("Patron created successfully", HttpStatus.CREATED);
    }


    @DeleteMapping("patrons/{id}")
    public void deletePatron(@PathVariable  long id){
        this.patronService.deletePatron(id);
    }
}
