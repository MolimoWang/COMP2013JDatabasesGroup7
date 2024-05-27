package com.example.DAO;

import com.example.model.Subject;
import java.util.List;

public interface SubjectsDao {
    // Method to insert a new subject into the database
    void insert(Subject subject);

    // Method to delete a subject from the database by its ID
    void deleteById(int subjectId);

    // Method to find a subject in the database by its ID
    Subject findById(int subjectId);

    // Method to retrieve all subjects from the database
    List<Subject> findAll();
}