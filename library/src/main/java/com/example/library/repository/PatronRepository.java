package com.example.library.repository;

import com.example.library.entities.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatronRepository extends JpaRepository<Patron,Long> {
    Optional<Patron> findByUsername(String username);
    boolean existsByUsername(String username);
}
