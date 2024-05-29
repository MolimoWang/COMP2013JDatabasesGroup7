package com.example.DAO;

import java.util.List;

public interface QuestionAnswersDao {
    void insert(int questionId, int answerId);

    void deleteByQuestionIdAndAnswerId(int questionId, int answerId);
    void deleteByQuestionId(int questionId);  // Add this line
    void deleteByAnswerId(int answerId);  // Add this line

    List<Integer> findAnswerIdsByQuestionId(int questionId);
    List<Integer> findQuestionIdsByAnswerId(int answerId);
}