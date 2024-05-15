package com.example.DAO;

import com.example.model.Subject;
import java.util.List;

public interface SubjectsDao {
    void insert(Subject subject);
    void deleteById(int subjectId);
    Subject findById(int subjectId);
    List<Subject> findAll();
}