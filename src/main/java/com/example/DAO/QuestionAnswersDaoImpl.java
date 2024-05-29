package com.example.DAO;

import com.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuestionAnswersDaoImpl implements QuestionAnswersDao {
    @Override
    public void insert(int questionId, int answerId) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO QuestionAnswers (QuestionID, AnswerID) VALUES (?, ?)");
            ps.setInt(1, questionId);
            ps.setInt(2, answerId);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByQuestionIdAndAnswerId(int questionId, int answerId) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM QuestionAnswers WHERE QuestionID = ? AND AnswerID = ?");
            ps.setInt(1, questionId);
            ps.setInt(2, answerId);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByQuestionId(int questionId) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM QuestionAnswers WHERE QuestionID = ?");
            ps.setInt(1, questionId);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByAnswerId(int answerId) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM QuestionAnswers WHERE AnswerID = ?");
            ps.setInt(1, answerId);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Integer> findAnswerIdsByQuestionId(int questionId) {
        List<Integer> answerIds = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT AnswerID FROM QuestionAnswers WHERE QuestionID = ?");
            ps.setInt(1, questionId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                answerIds.add(rs.getInt("AnswerID"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answerIds;
    }

    @Override
    public List<Integer> findQuestionIdsByAnswerId(int answerId) {
        List<Integer> questionIds = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT QuestionID FROM QuestionAnswers WHERE AnswerID = ?");
            ps.setInt(1, answerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                questionIds.add(rs.getInt("QuestionID"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questionIds;
    }
}