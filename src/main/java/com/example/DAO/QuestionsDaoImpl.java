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
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Questions (QuestionID, PaperID, Text, AnswerID) VALUES (?, ?, ?, ?)");
            ps.setInt(1, question.getQuestionId());
            ps.setInt(2, question.getPaperId());
            ps.setString(3, question.getText());
            ps.setInt(4, question.getAnswerId());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int questionId) {
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
    public Question find(int questionId) {
        Question question = null;
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Questions WHERE QuestionID = ?");
            ps.setInt(1, questionId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                question = new Question();
                question.setQuestionId(rs.getInt("QuestionID"));
                question.setPaperId(rs.getInt("PaperID"));
                question.setText(rs.getString("Text"));
                question.setAnswerId(rs.getInt("AnswerID"));
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
                question.setPaperId(rs.getInt("PaperID"));
                question.setText(rs.getString("Text"));
                question.setAnswerId(rs.getInt("AnswerID"));
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
}