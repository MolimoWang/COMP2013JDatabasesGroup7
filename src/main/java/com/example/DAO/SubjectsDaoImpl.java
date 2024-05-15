package com.example.DAO;

import com.example.model.Subject;
import com.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SubjectsDaoImpl implements SubjectsDao {
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

    @Override
    public void deleteById(int subjectId) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Subjects WHERE SubjectID = ?");
            ps.setInt(1, subjectId);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
}