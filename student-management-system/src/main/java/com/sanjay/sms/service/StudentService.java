package com.sanjay.sms.service;

import com.sanjay.sms.model.Student;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    // ✅ Local MySQL Database Configuration
    private final String jdbcUrl = "jdbc:mysql://localhost:3306/student_db"; // your local DB name
    private final String username = "root"; // your MySQL username
    private final String password = "root";     // your MySQL password (default is empty for localhost)

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, username, password);
    }

    // Save student
    public String saveStudent(Student student) {
        String sql = "INSERT INTO students (id, name, email, department) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, student.getId()); // ✅ Must pass id manually
            ps.setString(2, student.getName());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getDepartment());

            ps.executeUpdate();
            return "Student saved successfully.";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Error saving student: " + e.getMessage();
        }
    }

    // Get all students
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setEmail(rs.getString("email"));
                s.setDepartment(rs.getString("department"));
                list.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Get student by ID
    public Student getStudentById(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        Student student = new Student();

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setDepartment(rs.getString("department"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    // Update student
    public String updateStudent(Student student) {
        String sql = "UPDATE students SET name = ?, email = ?, department = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getDepartment());
            ps.setInt(4, student.getId());

            int updated = ps.executeUpdate();
            if (updated > 0)
                return "Student updated successfully.";
            else
                return "Student not found.";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Error updating student: " + e.getMessage();
        }
    }

    // Delete student
    public String deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int deleted = ps.executeUpdate();

            if (deleted > 0)
                return "Student deleted successfully.";
            else
                return "Student not found.";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Error deleting student: " + e.getMessage();
        }
    }
}
