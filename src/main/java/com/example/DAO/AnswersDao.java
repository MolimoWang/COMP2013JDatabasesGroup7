package com.example.DAO;

import com.example.model.Answer;
import java.util.List;

public interface AnswersDao {
    // Method to insert a new answer into the database
    void insert(Answer answer);

    // Method to delete an answer from the database by its ID
    void deleteById(int answerId);

    // Method to find an answer in the database by its ID
    Answer findById(int answerId);

    // Method to retrieve all answers from the database
    List<Answer> findAll();

    // Method to find an answer in the database by the ID of the question it is associated with
    Answer findByQuestionId(int questionId);

    // Method to update an existing answer in the database
    void update(Answer answer);
}