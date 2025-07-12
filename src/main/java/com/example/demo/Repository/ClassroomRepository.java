package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    Classroom findByCode(String code);
}
