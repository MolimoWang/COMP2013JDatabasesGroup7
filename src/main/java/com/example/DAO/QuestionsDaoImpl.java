package com.example.DAO;

import com.example.model.Question;
import com.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDaoImpl implements QuestionsDao {
    @Override
    public void insert(Question question) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Questions (QuestionID, Text) VALUES (?, ?)");  // 修改为Text
            ps.setInt(1, question.getQuestionId());
            ps.setString(2, question.getText());  // 修改为getText
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int questionId) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Questions WHERE QuestionID = ?");
            ps.setInt(1, questionId);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Question findById(int questionId) {
        Question question = null;
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Questions WHERE QuestionID = ?");
            ps.setInt(1, questionId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                question = new Question();
                question.setQuestionId(rs.getInt("QuestionID"));
                question.setText(rs.getString("Text"));  // 修改为setText
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return question;
    }

    @Override
    public List<Question> findAll() {
        List<Question> questions = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Questions");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Question question = new Question();
                question.setQuestionId(rs.getInt("QuestionID"));
                question.setText(rs.getString("Content"));
                questions.add(question);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questions;
    }

    @Override
    public void update(Question question) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE Questions SET Text = ? WHERE QuestionID = ?");
            ps.setString(1, question.getText());
            ps.setInt(2, question.getQuestionId());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}