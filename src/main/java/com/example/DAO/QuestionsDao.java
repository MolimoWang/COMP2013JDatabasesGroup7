package com.example.DAO;

import com.example.model.Question;
import java.util.List;

public interface QuestionsDao {
    // Method to insert a new question into the database
    void insert(Question question);

    // Method to delete a question from the database by its ID
    void deleteById(int questionId);

    // Method to find a question in the database by its ID
    Question findById(int questionId);

    // Method to retrieve all questions from the database
    List<Question> findAll();

    // Method to update an existing question in the database
    void update(Question question);
}