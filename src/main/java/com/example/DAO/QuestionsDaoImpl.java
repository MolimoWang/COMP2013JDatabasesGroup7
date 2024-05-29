package com.example.DAO;

import com.example.model.Question;
import com.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDaoImpl implements QuestionsDao {
    // Method to insert a new question into the database
    @Override
    public void insert(Question question) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Questions (QuestionID, PaperID, Text) VALUES (?, ?, ?)");
            ps.setInt(1, question.getQuestionId());
            ps.setInt(2, question.getPaperId());
            ps.setString(3, question.getText());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to delete a question from the database by its ID
    @Override
    public void deleteById(int questionId) {
        Connection conn = null;
        PreparedStatement psQuestionAnswers = null;
        PreparedStatement psQuestion = null;

        try {
            conn = DatabaseConnection.getConnection();

            // Start transaction
            conn.setAutoCommit(false);

            // First delete the QuestionAnswers associated with this Question
            psQuestionAnswers = conn.prepareStatement("DELETE FROM QuestionAnswers WHERE QuestionID = ?");
            psQuestionAnswers.setInt(1, questionId);
            psQuestionAnswers.executeUpdate();

            // Finally delete the Question
            psQuestion = conn.prepareStatement("DELETE FROM Questions WHERE QuestionID = ?");
            psQuestion.setInt(1, questionId);
            psQuestion.executeUpdate();

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
                if (psQuestionAnswers != null) psQuestionAnswers.close();
                if (psQuestion != null) psQuestion.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    // Method to find a question in the database by its ID
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

    // Method to retrieve all questions from the database
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

    // Method to find questions in the database by the ID of the paper they are associated with
    public List<Question> findByPaperId(int paperId) {
        List<Question> questions = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Questions WHERE PaperID = ?");
            ps.setInt(1, paperId);
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

    // Method to update a question in the database
    @Override
    public void update(Question question) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE Questions SET PaperID = ?, Text = ? WHERE QuestionID = ?");
            ps.setInt(1, question.getPaperId());
            ps.setString(2, question.getText());
            ps.setInt(3, question.getQuestionId());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}