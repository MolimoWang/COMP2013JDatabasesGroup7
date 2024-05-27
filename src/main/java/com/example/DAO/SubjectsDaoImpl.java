package com.example.DAO;

import com.example.model.Subject;
import com.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SubjectsDaoImpl implements SubjectsDao {
    // Method to insert a new subject into the database
    @Override
    public void insert(Subject subject) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Subjects (SubjectID, Name) VALUES (?, ?)");
            ps.setInt(1, subject.getSubjectId());
            ps.setString(2, subject.getName());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to delete a subject from the database by its ID
    // This method also deletes any associated Papers and Teachers
    @Override
    public void deleteById(int subjectId) {
        Connection conn = null;
        PreparedStatement psPapers = null;
        PreparedStatement psTeachers = null;
        PreparedStatement psSubject = null;

        try {
            conn = DatabaseConnection.getConnection();

            // Start transaction
            conn.setAutoCommit(false);

            // First, delete any Papers associated with this Subject
            psPapers = conn.prepareStatement("DELETE FROM Papers WHERE SubjectID = ?");
            psPapers.setInt(1, subjectId);
            psPapers.executeUpdate();

            // Then, delete any Teachers associated with this Subject
            psTeachers = conn.prepareStatement("DELETE FROM Teachers WHERE SubjectID = ?");
            psTeachers.setInt(1, subjectId);
            psTeachers.executeUpdate();

            // Finally, delete the Subject itself
            psSubject = conn.prepareStatement("DELETE FROM Subjects WHERE SubjectID = ?");
            psSubject.setInt(1, subjectId);
            psSubject.executeUpdate();

            // Commit transaction
            conn.commit();
        } catch (Exception e) {
            if (conn != null) {
                try {
                    // Rollback transaction in case of an error
                    conn.rollback();
                } catch (Exception rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (psPapers != null) psPapers.close();
                if (psTeachers != null) psTeachers.close();
                if (psSubject != null) psSubject.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    // Method to find a subject in the database by its ID
    @Override
    public Subject findById(int subjectId) {
        Subject subject = null;
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Subjects WHERE SubjectID = ?");
            ps.setInt(1, subjectId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                subject = new Subject();
                subject.setSubjectId(rs.getInt("SubjectID"));
                subject.setName(rs.getString("Name"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subject;
    }

    // Method to retrieve all subjects from the database
    @Override
    public List<Subject> findAll() {
        List<Subject> subjects = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Subjects");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(rs.getInt("SubjectID"));
                subject.setName(rs.getString("Name"));
                subjects.add(subject);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjects;
    }

    // Method to find a subject in the database by its name
    public Subject findByName(String name) {
        Subject subject = null;
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Subjects WHERE Name = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                subject = new Subject();
                subject.setSubjectId(rs.getInt("SubjectID"));
                subject.setName(rs.getString("Name"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subject;
    }
}