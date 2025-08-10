package com.sanjay.sms.service;

import com.sanjay.sms.model.Student;
import java.util.List;

public interface StudentService {
    Student createStudent(Student student);
    List<Student> getAllStudents();
    Student getStudentById(Long id);
    Student updateStudent(Long id, Student student);
    void deleteStudent(Long id);
}
