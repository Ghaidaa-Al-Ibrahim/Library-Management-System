package com.example.library.service;

import com.example.library.entities.Patron;
import com.example.library.repository.PatronRepository;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PatronService {


    private PatronRepository patronRepository;


    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public PatronService(PatronRepository patronRepository, BCryptPasswordEncoder passwordEncoder) {
        this.patronRepository = patronRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Patron> getAllPatrons(){
        return this.patronRepository.findAll();
    }

    public Patron getPatronById(long id){
        return this.patronRepository.findById(id).get();
    }

    public Patron addPatron(Patron patron){

        return this.patronRepository.save(patron);
    }

    public void deletePatron(long id) {
        this.patronRepository.deleteById(id);
    }
    @Transactional
    public  void  registerPatron(Patron patron) {
            if (patronRepository.existsByUsername(patron.getUsername()) ) {
                throw new RuntimeException("Username is already in use");
            }
        patron.setPassword(passwordEncoder.encode(patron.getPassword()));
            patronRepository.save(patron);


        }
    public  void   loginPatron(Patron patronDto) {
        Optional<Patron> patron = patronRepository.findByUsername(patronDto.getUsername());
        if (patron == null || !passwordEncoder.matches(patronDto.getPassword(), patron.get().getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

    }
    }



