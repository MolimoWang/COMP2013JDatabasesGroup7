package com.example.DAO;

import com.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentPapersDaoImpl implements StudentPapersDao {
    @Override
    public void insert(int studentId, int paperId) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO StudentPapers (StudentID, PaperID) VALUES (?, ?)");
            ps.setInt(1, studentId);
            ps.setInt(2, paperId);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByStudentIdAndPaperId(int studentId, int paperId) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM StudentPapers WHERE StudentID = ? AND PaperID = ?");
            ps.setInt(1, studentId);
            ps.setInt(2, paperId);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Integer> findPaperIdsByStudentId(int studentId) {
        List<Integer> paperIds = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT PaperID FROM StudentPapers WHERE StudentID = ?");
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                paperIds.add(rs.getInt("PaperID"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paperIds;
    }

    @Override
    public List<Integer> findStudentIdsByPaperId(int paperId) {
        List<Integer> studentIds = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT StudentID FROM StudentPapers WHERE PaperID = ?");
            ps.setInt(1, paperId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                studentIds.add(rs.getInt("StudentID"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentIds;
    }
}