package com.example.DAO;

import com.example.model.Paper;
import com.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PapersDaoImpl implements PapersDao {
    private final StudentPapersDao studentPapersDao = new StudentPapersDaoImpl();

    // Method to insert a new paper into the database
    @Override
    public void insert(Paper paper) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DatabaseConnection.getConnection();
            ps = conn.prepareStatement("INSERT INTO Papers (PaperID, Title, Year, SubjectID) VALUES (?, ?, ?, ?)");
            ps.setInt(1, paper.getPaperId());
            ps.setString(2, paper.getTitle());
            ps.setInt(3, paper.getYear());
            ps.setInt(4, paper.getSubjectId());
            ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
    }

    // Method to delete a paper from the database by its ID
    @Override
    public void deleteById(int paperId) throws SQLException {
        Connection conn = null;
        PreparedStatement psQuestions = null;
        PreparedStatement psPaper = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);
            psQuestions = conn.prepareStatement("DELETE FROM Questions WHERE PaperID = ?");
            psQuestions.setInt(1, paperId);
            psQuestions.executeUpdate();
            studentPapersDao.deleteByPaperId(paperId);
            psPaper = conn.prepareStatement("DELETE FROM Papers WHERE PaperID = ?");
            psPaper.setInt(1, paperId);
            psPaper.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (psQuestions != null) psQuestions.close();
            if (psPaper != null) psPaper.close();
            if (conn != null) conn.close();
        }
    }

    // Method to find a paper in the database by its ID
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

    // Method to retrieve all papers from the database
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

    // Method to update an existing paper in the database
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

    // Method to retrieve a specific number of papers from a specific position in the database
    @Override
    public List<Paper> findWithPagination(int start, int count) {
        List<Paper> papers = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Papers LIMIT ? OFFSET ?");
            ps.setInt(1, count);
            ps.setInt(2, start);
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

    // Method to count the total number of papers in the database
    @Override
    public int count() {
        int count = 0;
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM Papers");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    // Method to find papers in the database by dynamic conditions with pagination
    public List<Paper> findByDynamicConditions(String title, String subjectName, String yearStr, String teacher, int start, int count) {
        List<Paper> papers = new ArrayList<>();
        StringBuilder query = new StringBuilder(
            "SELECT p.* FROM Papers p " +
            "LEFT JOIN Subjects s ON p.SubjectID = s.SubjectID " +
            "LEFT JOIN TeacherSubjects ts ON s.SubjectID = ts.SubjectID " +
            "LEFT JOIN Teachers t ON ts.TeacherID = t.TeacherID " +
            "LEFT JOIN Person pe ON t.PersonID = pe.PersonID " +
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
            query.append(" AND pe.Name LIKE ?");
            parameters.add("%" + teacher + "%");
        }

        // Add LIMIT and OFFSET to the query
        query.append(" LIMIT ? OFFSET ?");
        parameters.add(count);
        parameters.add(start);

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

    // Method to count the total number of papers in the database by dynamic conditions
    @Override
    public int countByDynamicConditions(String title, String subjectName, String yearStr, String teacher) {
        int count = 0;
        StringBuilder query = new StringBuilder(
                "SELECT COUNT(*) FROM Papers p " +
                        "LEFT JOIN Subjects s ON p.SubjectID = s.SubjectID " +
                        "LEFT JOIN TeacherSubjects ts ON s.SubjectID = ts.SubjectID " +
                        "LEFT JOIN Teachers t ON ts.TeacherID = t.TeacherID " +
                        "LEFT JOIN Person pe ON t.PersonID = pe.PersonID " +
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
            query.append(" AND pe.Name LIKE ?");
            parameters.add("%" + teacher + "%");
        }


        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query.toString());

            for (int i = 0; i < parameters.size(); i++) {
                ps.setObject(i + 1, parameters.get(i));
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
}