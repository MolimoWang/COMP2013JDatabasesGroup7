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

    // Method to delete an answer from the database by its ID
    @Override
    public void deleteById(int answerId) {
        Connection conn = null;
        PreparedStatement psQuestions = null;
        PreparedStatement psAnswer = null;

        try {
            conn = DatabaseConnection.getConnection();

            // Start transaction
            conn.setAutoCommit(false);

            // First delete the Questions associated with this Answer
            psQuestions = conn.prepareStatement("DELETE FROM Questions WHERE AnswerID = ?");
            psQuestions.setInt(1, answerId);
            psQuestions.executeUpdate();

            // Finally delete the Answer
            psAnswer = conn.prepareStatement("DELETE FROM Answers WHERE AnswerID = ?");
            psAnswer.setInt(1, answerId);
            psAnswer.executeUpdate();

            // Commit transaction
            conn.commit();
        } catch (Exception e) {
            if (conn != null) {
                try {
                    // Rollback transaction
                    conn.rollback();
                } catch (Exception rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (psQuestions != null) psQuestions.close();
                if (psAnswer != null) psAnswer.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
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

    // Method to find an answer in the database by the ID of the question it is associated with
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
}