package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Classroom;
import com.example.demo.Entity.Attendee;
import com.example.demo.Repository.ClassroomRepository;
import com.example.demo.Repository.AttendeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private AttendeeRepository attendeeRepository;

    public Classroom createClassroom(String name) {
        Classroom classroom = new Classroom();
        classroom.setName(name);
        classroom.setCode(generateUniqueCode());
        classroom.setActive(false); // Initially inactive
        return classroomRepository.save(classroom);
    }

    public Classroom startClass(String code) {
        Classroom classroom = classroomRepository.findByCode(code);
        if (classroom != null) {
            classroom.setActive(true);
            return classroomRepository.save(classroom);
        }
        throw new IllegalArgumentException("Classroom not found");
    }

    public Classroom endClass(String code) {
        Classroom classroom = classroomRepository.findByCode(code);
        if (classroom != null) {
            classroom.setActive(true);
            return classroomRepository.save(classroom);
        }
        throw new IllegalArgumentException("Classroom not found");
    }

    public Optional<Classroom> getByCode(String code) {
        return Optional.ofNullable(classroomRepository.findByCode(code));
    }

    public Classroom findByCode(String code) {
        return classroomRepository.findByCode(code);
    }

    public Classroom saveRecordingUrl(String code, String url) {
        Classroom classroom = classroomRepository.findByCode(code);
        if (classroom != null) {
            classroom.setRecordingUrl(url);
            return classroomRepository.save(classroom);
        }
        throw new IllegalArgumentException("Classroom not found");
    }

    public List<String> getAttendeeNames(String code) {
        Classroom classroom = classroomRepository.findByCode(code);
        if (classroom != null) {
            return attendeeRepository.findByClassroom(classroom)
                    .stream()
                    .map(attendee -> attendee.getStudent().getName())
                    .collect(Collectors.toList());
        }
        return List.of();
    }

    public List<Classroom> getAll() {
        return classroomRepository.findAll();
    }

    private String generateUniqueCode() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase(); // 8-character unique code
    }
}
