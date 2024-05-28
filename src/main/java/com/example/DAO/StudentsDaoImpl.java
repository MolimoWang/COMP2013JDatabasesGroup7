package com.example.DAO;

import com.example.model.Student;
import com.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentsDaoImpl implements StudentsDao {
    // Method to insert a new student into the database
    @Override
    public void insert(Student student) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Students (StudentID, Name, PaperID) VALUES (?, ?, ?)");
            ps.setInt(1, student.getStudentId());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getPaperId());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to delete a student from the database by its ID
    @Override
    public void deleteById(int studentId) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Students WHERE StudentID = ?");
            ps.setInt(1, studentId);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to find a student in the database by its ID
    @Override
    public Student findById(int studentId) {
        Student student = null;
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Students WHERE StudentID = ?");
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.setStudentId(rs.getInt("StudentID"));
                student.setName(rs.getString("Name"));
                student.setPaperId(rs.getInt("PaperID"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    // Method to retrieve all students from the database
    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Students");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("StudentID"));
                student.setName(rs.getString("Name"));
                student.setPaperId(rs.getInt("PaperID"));
                students.add(student);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    // Method to find students in the database by the ID of the paper they are associated with
    public List<Student> findByPaperId(int paperId) {
        List<Student> students = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Students WHERE PaperID = ?");
            ps.setInt(1, paperId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("StudentID"));
                student.setName(rs.getString("Name"));
                student.setPaperId(rs.getInt("PaperID"));
                students.add(student);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    // Method to update a student in the database
    @Override
    public void update(Student student) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE Students SET Name = ?, PaperID = ? WHERE StudentID = ?");
            ps.setString(1, student.getName());
            ps.setInt(2, student.getPaperId());
            ps.setInt(3, student.getStudentId());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}