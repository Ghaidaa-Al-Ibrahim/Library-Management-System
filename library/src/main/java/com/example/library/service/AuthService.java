package com.example.library.service;

import com.example.library.entities.Patron;
import com.example.library.repository.PatronRepository;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {


    private PatronRepository patronRepository;

    @Autowired
    public AuthService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Patron> patron=patronRepository.findByUsername(username);
        if (patron.isPresent()){
            var object=patron.get();
            return User.builder()
                    .username(object.getUsername())
                    .password(object.getPassword()).build();
        }else {
            throw new  UsernameNotFoundException(username);
        }

    }
}
