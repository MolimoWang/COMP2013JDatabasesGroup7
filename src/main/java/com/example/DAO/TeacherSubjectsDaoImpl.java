package com.example.DAO;

import com.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TeacherSubjectsDaoImpl implements TeacherSubjectsDao {
    @Override
    public void insert(int teacherId, int subjectId) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO TeacherSubjects (TeacherID, SubjectID) VALUES (?, ?)");
            ps.setInt(1, teacherId);
            ps.setInt(2, subjectId);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByTeacherIdAndSubjectId(int teacherId, int subjectId) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM TeacherSubjects WHERE TeacherID = ? AND SubjectID = ?");
            ps.setInt(1, teacherId);
            ps.setInt(2, subjectId);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Integer> findSubjectIdsByTeacherId(int teacherId) {
        List<Integer> subjectIds = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT SubjectID FROM TeacherSubjects WHERE TeacherID = ?");
            ps.setInt(1, teacherId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                subjectIds.add(rs.getInt("SubjectID"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjectIds;
    }

    @Override
    public List<Integer> findTeacherIdsBySubjectId(int subjectId) {
        List<Integer> teacherIds = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT TeacherID FROM TeacherSubjects WHERE SubjectID = ?");
            ps.setInt(1, subjectId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                teacherIds.add(rs.getInt("TeacherID"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teacherIds;
    }
}