package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.Attendee;
import com.example.demo.Entity.Classroom;
import com.example.demo.Entity.User;
import com.example.demo.Repository.AttendeeRepository;
import com.example.demo.Repository.ClassroomRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.ClassroomService;

@RestController
@RequestMapping("/api/attend")
public class AttendanceController {

    @Autowired private ClassroomRepository classroomRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private AttendeeRepository attendeeRepository;
    @Autowired private ClassroomService classroomService;
    @Autowired private SimpMessagingTemplate messagingTemplate;

    /**
     * A student joins a class by classroom code and userId.
     */
    @PostMapping("/{code}")
    public ResponseEntity<String> join(@PathVariable String code, @RequestParam Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Classroom classroom = classroomRepository.findByCode(code);

        if (optionalUser.isEmpty() || classroom == null || !classroom.isActive()) {
            return ResponseEntity.badRequest().body("Invalid class or class not active");
        }

        User user = optionalUser.get();

        // Avoid duplicate attendees
        boolean alreadyJoined = attendeeRepository.existsByStudentAndClassroom(user, classroom);
        if (!alreadyJoined) {
            Attendee attendee = new Attendee();
            attendee.setStudent(user);
            attendee.setClassroom(classroom);
            attendeeRepository.save(attendee);
        }

        // Notify all about updated attendees via WebSocket
        List<String> attendees = classroomService.getAttendeeNames(code);
        messagingTemplate.convertAndSend("/topic/attendees/" + code, attendees);

        return ResponseEntity.ok("Joined class");
    }

    /**
     * Get all students joined to a given classroom code.
     */
    @GetMapping("/{code}/students")
    public ResponseEntity<List<Attendee>> getStudents(@PathVariable String code) {
        Classroom classroom = classroomRepository.findByCode(code);
        if (classroom != null) {
            return ResponseEntity.ok(attendeeRepository.findByClassroom(classroom));
        }
        return ResponseEntity.ok(List.of());
    }
}
