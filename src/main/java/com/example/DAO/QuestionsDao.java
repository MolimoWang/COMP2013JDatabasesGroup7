package com.example.DAO;

import com.example.model.Question;
import java.util.List;

public interface QuestionsDao {
    void insert(Question question);
    void delete(int questionId);
    Question find(int questionId);
    List<Question> findAll();
}