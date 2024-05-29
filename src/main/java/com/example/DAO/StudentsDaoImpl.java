package com.example.DAO;

import com.example.model.Student;
import com.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentsDaoImpl implements StudentsDao {
    private StudentPapersDao studentPapersDao = new StudentPapersDaoImpl();

    // Method to insert a new student into the database
    @Override
    public void insert(Student student) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Students (StudentID, PersonID) VALUES (?, ?)");
            ps.setInt(1, student.getStudentId());
            ps.setInt(2, student.getPersonId());
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

            // Delete all records related to the student from the StudentPapers table
            studentPapersDao.deleteByStudentId(studentId);

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
    public void deleteByPersonId(int personId) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Students WHERE PersonID = ?");
            ps.setInt(1, personId);
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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Students INNER JOIN Person ON Students.PersonID = Person.PersonID WHERE StudentID = ?");
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.setStudentId(rs.getInt("StudentID"));
                student.setPersonId(rs.getInt("PersonID"));
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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Students INNER JOIN Person ON Students.PersonID = Person.PersonID");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("StudentID"));
                student.setPersonId(rs.getInt("PersonID"));
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
            PreparedStatement ps = conn.prepareStatement("UPDATE Students SET PersonID = ? WHERE StudentID = ?");
            ps.setInt(1, student.getPersonId());
            ps.setInt(2, student.getStudentId());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}