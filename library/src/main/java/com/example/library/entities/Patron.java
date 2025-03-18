package com.example.library.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patron")
@NoArgsConstructor
@AllArgsConstructor
public class Patron {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patron_id")
    private long id;

    @NotBlank(message = "Name cannot be empty")
    @Column(name = "patron_name")
    private String name;
    @NotBlank(message = "Username cannot be empty")
    @Column(name = "patron_username",unique = true)
    private String username;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be empty")
    @Column(name = "patron_email")
    private String email;

    @NotBlank(message = "Phone number cannot be empty")
    @Column(name = "patron_phone")
    private String phoneNumber;

    @NotBlank(message = "Password cannot be empty")
    @Column(name = "patron_password")
    private String password;

    @OneToMany(mappedBy = "patron",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BorrowingRecord> borrowingRecords= new ArrayList<>();

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public List<BorrowingRecord> getBorrowingRecords() {
        return borrowingRecords;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBorrowingRecords(List<BorrowingRecord> borrowingRecords) {
        this.borrowingRecords = borrowingRecords;
    }
}
