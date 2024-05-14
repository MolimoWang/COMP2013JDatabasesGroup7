package com.example.DAO;

import com.example.model.Answer;

import java.util.List;

public interface AnswersDao {
    void insert(Answer answer);
    void deleteById(int answerId);
    Answer findById(int answerId);
    List<Answer> findAll();
    Answer findByQuestionId(int questionId);
    void update(Answer answer);
}