package com.example.DAO;

import com.example.model.Teacher;
import com.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TeachersDaoImpl implements TeachersDao {
    @Override
    public void insert(Teacher teacher) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Teachers (TeacherID, Name, SubjectID) VALUES (?, ?, ?)");
            ps.setInt(1, teacher.getTeacherId());
            ps.setString(2, teacher.getName());
            ps.setInt(3, teacher.getSubjectId());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int teacherId) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Teachers WHERE TeacherID = ?");
            ps.setInt(1, teacherId);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Teacher find(int teacherId) {
        Teacher teacher = null;
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Teachers WHERE TeacherID = ?");
            ps.setInt(1, teacherId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                teacher = new Teacher();
                teacher.setTeacherId(rs.getInt("TeacherID"));
                teacher.setName(rs.getString("Name"));
                teacher.setSubjectId(rs.getInt("SubjectID"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teacher;
    }

    @Override
    public List<Teacher> findAll() {
        List<Teacher> teachers = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Teachers");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setTeacherId(rs.getInt("TeacherID"));
                teacher.setName(rs.getString("Name"));
                teacher.setSubjectId(rs.getInt("SubjectID"));
                teachers.add(teacher);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teachers;
    }
}