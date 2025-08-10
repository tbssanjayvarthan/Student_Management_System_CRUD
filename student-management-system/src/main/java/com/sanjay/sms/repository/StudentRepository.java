package com.sanjay.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sanjay.sms.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
