package com.example.DAO;

import com.example.model.Question;
import com.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDaoImpl implements QuestionsDao {
    private QuestionAnswersDao questionAnswersDao = new QuestionAnswersDaoImpl();

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
        try {
            Connection conn = DatabaseConnection.getConnection();

            // Delete all records related to the question from the QuestionAnswers table
            questionAnswersDao.deleteByQuestionId(questionId);

            PreparedStatement ps = conn.prepareStatement("DELETE FROM Questions WHERE QuestionID = ?");
            ps.setInt(1, questionId);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
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

    @Override
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