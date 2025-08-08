package com.sanjay.sms.controller;

import com.sanjay.sms.model.Student;
import com.sanjay.sms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // ✅ Create student
    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        String result = studentService.saveStudent(student);
        return ResponseEntity.ok(result);
    }

    // ✅ Get all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    // ✅ Get student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Update student
    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable int id, @RequestBody Student student) {
        student.setId(id);
        String result = studentService.updateStudent(student);
        return ResponseEntity.ok(result);
    }

    // ✅ Delete student
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        String result = studentService.deleteStudent(id);
        return ResponseEntity.ok(result);
    }
}
