package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;
    private LocalDateTime createdAt;
    private boolean Active;
    private String recordingUrl; // Supabase file URL

    @OneToMany(mappedBy = "classroom")
    private List<Attendee> attendees = new ArrayList<>();

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
}