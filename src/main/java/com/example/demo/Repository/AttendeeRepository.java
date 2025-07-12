package com.example.demo.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Attendee;
import com.example.demo.Entity.Classroom;
import com.example.demo.Entity.User;


public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
    List<Attendee> findByClassroom(Classroom classroom);
    boolean existsByStudentAndClassroom(User student, Classroom classroom);
}
