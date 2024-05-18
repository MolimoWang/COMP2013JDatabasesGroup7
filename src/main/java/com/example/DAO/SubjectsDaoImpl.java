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
        Connection conn = null;
        PreparedStatement psPapers = null;
        PreparedStatement psTeachers = null;
        PreparedStatement psSubject = null;

        try {
            conn = DatabaseConnection.getConnection();

            // 开启事务
            conn.setAutoCommit(false);

            // 先删除与该 Subject 关联的 Papers
            psPapers = conn.prepareStatement("DELETE FROM Papers WHERE SubjectID = ?");
            psPapers.setInt(1, subjectId);
            psPapers.executeUpdate();

            // 再删除与该 Subject 关联的 Teachers
            psTeachers = conn.prepareStatement("DELETE FROM Teachers WHERE SubjectID = ?");
            psTeachers.setInt(1, subjectId);
            psTeachers.executeUpdate();

            // 最后删除 Subject
            psSubject = conn.prepareStatement("DELETE FROM Subjects WHERE SubjectID = ?");
            psSubject.setInt(1, subjectId);
            psSubject.executeUpdate();

            // 提交事务
            conn.commit();
        } catch (Exception e) {
            if (conn != null) {
                try {
                    // 回滚事务
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