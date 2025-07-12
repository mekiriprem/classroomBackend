package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "app_user")
public class User {
    @Id 
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String role; // "student" or "teacher"

    // Getters and Setters
}