package com.example.DAO;

import com.example.model.Subject;

import java.util.List;

public interface SubjectsDao {
    void insert(Subject subject);
    void delete(int subjectId);
    Subject find(int subjectId);
    List<Subject> findAll();
}