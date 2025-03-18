package com.example.library.controller;

import com.example.library.entities.Patron;
import com.example.library.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/")
public class AuthController {

    private PatronService patronService;

    @Autowired
    public AuthController(PatronService patronService) {
        this.patronService = patronService;
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody Patron patron) {
        this.patronService.registerPatron(patron);
        return ResponseEntity.ok("Register Successfully");
    }
    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody Patron patron) {
        this.patronService.loginPatron(patron);

        return ResponseEntity.ok("Login Successfully");
    }

}
