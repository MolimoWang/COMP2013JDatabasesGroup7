package com.example.DAO;

import com.example.model.Question;

import java.util.List;

public interface QuestionsDao {
    void insert(Question question);
    void deleteById(int questionId);
    Question findById(int questionId);
    List<Question> findAll();
    void update(Question question);
}