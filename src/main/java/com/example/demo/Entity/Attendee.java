package com.example.demo.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Attendee {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    @JsonIgnoreProperties({"attendees"})
    private Classroom classroom;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User student;

  
    // Getters and setters
}
