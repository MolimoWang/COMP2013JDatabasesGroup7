package com.example.DAO;

import com.example.model.Teacher;
import com.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TeachersDaoImpl implements TeachersDao {
    // Method to insert a new teacher into the database
    @Override
    public void insert(Teacher teacher) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Teachers (TeacherID, PersonID) VALUES (?, ?)");
            ps.setInt(1, teacher.getTeacherId());
            ps.setInt(2, teacher.getPersonId());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to delete a teacher from the database by its ID
    @Override
    public void deleteById(int teacherId) {
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

    // Method to find a teacher in the database by its ID
    @Override
    public Teacher findById(int teacherId) {
        Teacher teacher = null;
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Teachers INNER JOIN Person ON Teachers.PersonID = Person.PersonID WHERE TeacherID = ?");
            ps.setInt(1, teacherId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                teacher = new Teacher();
                teacher.setTeacherId(rs.getInt("TeacherID"));
                teacher.setPersonId(rs.getInt("PersonID"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teacher;
    }

    // Method to retrieve all teachers from the database
    @Override
    public List<Teacher> findAll() {
        List<Teacher> teachers = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Teachers INNER JOIN Person ON Teachers.PersonID = Person.PersonID");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setTeacherId(rs.getInt("TeacherID"));
                teacher.setPersonId(rs.getInt("PersonID"));
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

    // Method to update a teacher in the database
    @Override
    public void update(Teacher teacher) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE Teachers SET PersonID = ? WHERE TeacherID = ?");
            ps.setInt(1, teacher.getPersonId());
            ps.setInt(2, teacher.getTeacherId());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}