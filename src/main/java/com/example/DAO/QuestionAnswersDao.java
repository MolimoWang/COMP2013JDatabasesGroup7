package com.example.DAO;

import java.util.List;

public interface QuestionAnswersDao {
    void insert(int questionId, int answerId);
    void deleteByQuestionIdAndAnswerId(int questionId, int answerId);
    List<Integer> findAnswerIdsByQuestionId(int questionId);
    List<Integer> findQuestionIdsByAnswerId(int answerId);
}