package com.example.DAO;

import com.example.model.Student;
import com.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentsDaoImpl implements StudentsDao {
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
}