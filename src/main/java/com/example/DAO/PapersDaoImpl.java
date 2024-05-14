package com.example.DAO;

import com.example.model.Paper;
import com.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PapersDaoImpl implements PapersDao {
    @Override
    public void insert(Paper paper) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Papers (PaperID, Content) VALUES (?, ?)");
            ps.setInt(1, paper.getPaperId());
            ps.setString(2, paper.getContent());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int paperId) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Papers WHERE PaperID = ?");
            ps.setInt(1, paperId);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Paper findById(int paperId) {
        Paper paper = null;
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Papers WHERE PaperID = ?");
            ps.setInt(1, paperId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                paper = new Paper();
                paper.setPaperId(rs.getInt("PaperID"));
                paper.setContent(rs.getString("Content"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paper;
    }

    @Override
    public List<Paper> findAll() {
        List<Paper> papers = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Papers");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Paper paper = new Paper();
                paper.setPaperId(rs.getInt("PaperID"));
                paper.setContent(rs.getString("Content"));
                papers.add(paper);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return papers;
    }

    @Override
    public void update(Paper paper) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE Papers SET Content = ? WHERE PaperID = ?");
            ps.setString(1, paper.getContent());
            ps.setInt(2, paper.getPaperId());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}