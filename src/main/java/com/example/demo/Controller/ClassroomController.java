package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Entity.Classroom;
import com.example.demo.Service.ClassroomService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/classroom")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @PostMapping("/create")
    public ResponseEntity<Classroom> createClassroom(@RequestBody Map<String, String> payload) {
        String name = payload.get("name");
        if (name == null || name.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Classroom classroom = classroomService.createClassroom(name);
        return ResponseEntity.ok(classroom);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Classroom> getClassroom(@PathVariable String code) {
        Classroom classroom = classroomService.findByCode(code);
        if (classroom != null) {
            return ResponseEntity.ok(classroom);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{code}/start")
    public ResponseEntity<Classroom> startClass(@PathVariable String code) {
        Classroom classroom = classroomService.findByCode(code);
        if (classroom == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(classroomService.startClass(code));
    }

    @PostMapping("/{code}/end")
    public ResponseEntity<Classroom> endClass(@PathVariable String code) {
        Classroom classroom = classroomService.findByCode(code);
        if (classroom == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(classroomService.endClass(code));
    }

    @PostMapping("/{code}/recording")
    public ResponseEntity<String> saveRecording(@PathVariable String code, @RequestBody Map<String, String> payload) {
        String url = payload.get("url");
        if (url == null || url.isEmpty()) {
            return ResponseEntity.badRequest().body("Missing recording URL");
        }
        classroomService.saveRecordingUrl(code, url);
        return ResponseEntity.ok("Recording saved for classroom " + code);
    }

    @GetMapping("/{code}/attendees")
    public ResponseEntity<List<String>> getAttendees(@PathVariable String code) {
        List<String> attendees = classroomService.getAttendeeNames(code);
        return ResponseEntity.ok(attendees);
    }

    @GetMapping
    public ResponseEntity<List<Classroom>> getAll() {
        return ResponseEntity.ok(classroomService.getAll());
    }
}
