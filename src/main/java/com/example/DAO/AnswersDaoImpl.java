package com.example.DAO;

import com.example.model.Answer;
import com.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AnswersDaoImpl implements AnswersDao {
    @Override
    public void insert(Answer answer) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Answers (AnswerID, Text) VALUES (?, ?)");
            ps.setInt(1, answer.getAnswerId());
            ps.setString(2, answer.getText());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int answerId) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Answers WHERE AnswerID = ?");
            ps.setInt(1, answerId);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Answer findById(int answerId) {
        Answer answer = null;
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Answers WHERE AnswerID = ?");
            ps.setInt(1, answerId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                answer = new Answer();
                answer.setAnswerId(rs.getInt("AnswerID"));
                answer.setText(rs.getString("Text"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answer;
    }

    @Override
    public List<Answer> findAll() {
        List<Answer> answers = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Answers");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Answer answer = new Answer();
                answer.setAnswerId(rs.getInt("AnswerID"));
                answer.setText(rs.getString("Text"));
                answers.add(answer);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answers;
    }

    @Override
    public Answer findByQuestionId(int questionId) {
        Answer answer = null;
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Answers WHERE AnswerID = (SELECT AnswerID FROM Questions WHERE QuestionID = ?)");
            ps.setInt(1, questionId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                answer = new Answer();
                answer.setAnswerId(rs.getInt("AnswerID"));
                answer.setText(rs.getString("Text"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answer;
    }

    @Override
    public void update(Answer answer) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE Answers SET Text = ? WHERE AnswerID = ?");
            ps.setString(1, answer.getText());
            ps.setInt(2, answer.getAnswerId());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}