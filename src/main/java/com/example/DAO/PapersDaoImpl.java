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
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Papers (PaperID, Title, Year, SubjectID) VALUES (?, ?, ?, ?)");
            ps.setInt(1, paper.getPaperId());
            ps.setString(2, paper.getTitle());
            ps.setInt(3, paper.getYear());
            ps.setInt(4, paper.getSubjectId());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int paperId) {
        Connection conn = null;
        PreparedStatement psQuestions = null;
        PreparedStatement psPaper = null;

        try {
            conn = DatabaseConnection.getConnection();

            // 开启事务
            conn.setAutoCommit(false);

            // 先删除与该 Paper 关联的 Questions
            psQuestions = conn.prepareStatement("DELETE FROM Questions WHERE PaperID = ?");
            psQuestions.setInt(1, paperId);
            psQuestions.executeUpdate();

            // 再删除 Paper
            psPaper = conn.prepareStatement("DELETE FROM Papers WHERE PaperID = ?");
            psPaper.setInt(1, paperId);
            psPaper.executeUpdate();

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
                if (psQuestions != null) psQuestions.close();
                if (psPaper != null) psPaper.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
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
                paper.setTitle(rs.getString("Title"));
                paper.setYear(rs.getInt("Year"));
                paper.setSubjectId(rs.getInt("SubjectID"));
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
                paper.setTitle(rs.getString("Title"));
                paper.setYear(rs.getInt("Year"));
                paper.setSubjectId(rs.getInt("SubjectID"));
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
            PreparedStatement ps = conn.prepareStatement("UPDATE Papers SET Title = ?, Year = ?, SubjectID = ? WHERE PaperID = ?");
            ps.setString(1, paper.getTitle());
            ps.setInt(2, paper.getYear());
            ps.setInt(3, paper.getSubjectId());
            ps.setInt(4, paper.getPaperId());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Paper> findByDynamicConditions(String title, String subjectName, String yearStr, String teacher) {
        List<Paper> papers = new ArrayList<>();
        StringBuilder query = new StringBuilder(
                "SELECT p.* FROM Papers p " +
                        "JOIN Subjects s ON p.SubjectID = s.SubjectID " +
                        "JOIN Teachers t ON s.SubjectID = t.SubjectID " +
                        "WHERE 1=1"
        );

        List<Object> parameters = new ArrayList<>();

        if (title != null && !title.isEmpty()) {
            query.append(" AND p.Title LIKE ?");
            parameters.add("%" + title + "%");
        }
        if (subjectName != null && !subjectName.isEmpty()) {
            query.append(" AND s.Name LIKE ?");
            parameters.add("%" + subjectName + "%");
        }
        if (yearStr != null && !yearStr.isEmpty()) {
            query.append(" AND p.Year = ?");
            parameters.add(Integer.parseInt(yearStr));
        }
        if (teacher != null && !teacher.isEmpty()) {
            query.append(" AND t.Name LIKE ?");
            parameters.add("%" + teacher + "%");
        }

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query.toString());

            for (int i = 0; i < parameters.size(); i++) {
                ps.setObject(i + 1, parameters.get(i));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Paper paper = new Paper();
                paper.setPaperId(rs.getInt("PaperID"));
                paper.setTitle(rs.getString("Title"));
                paper.setYear(rs.getInt("Year"));
                paper.setSubjectId(rs.getInt("SubjectID"));
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



}