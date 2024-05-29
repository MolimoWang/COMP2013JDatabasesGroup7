package com.example.DAO;

import com.example.model.Answer;
import com.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class AnswersDaoImpl implements AnswersDao {

    // Method to insert a new answer into the database
    @Override
    public void insert(Answer answer, int questionId) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Answers (AnswerID, Text) VALUES (?, ?)");
            ps.setInt(1, answer.getAnswerId());
            ps.setString(2, answer.getText());
            ps.executeUpdate();

            // Update the corresponding question's answer ID
            PreparedStatement psUpdate = conn.prepareStatement("UPDATE Questions SET AnswerID = ? WHERE QuestionID = ?");
            psUpdate.setInt(1, answer.getAnswerId());
            psUpdate.setInt(2, questionId);
            psUpdate.executeUpdate();
            psUpdate.close();

            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to delete an answer from the database by its ID
    @Override
    public void deleteById(int answerId) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            // Set the AnswerID to null for any questions that reference the answer being deleted
            PreparedStatement psUpdate = conn.prepareStatement("UPDATE Questions SET AnswerID = NULL WHERE AnswerID = ?");
            psUpdate.setInt(1, answerId);
            psUpdate.executeUpdate();
            psUpdate.close();

            // Delete the answer
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Answers WHERE AnswerID = ?");
            ps.setInt(1, answerId);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to find an answer in the database by its ID
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

    // Method to retrieve all answers from the database
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

    // Method to update an existing answer in the database
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

    // Method to find an answer in the database by question ID
    @Override
    public Answer findByQuestionId(int questionId) {
        Answer answer = null;
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT AnswerID FROM Questions WHERE QuestionID = ?");
            ps.setInt(1, questionId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int answerId = rs.getInt("AnswerID");
                answer = findById(answerId);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answer;
    }
}