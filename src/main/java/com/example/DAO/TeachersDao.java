package com.example.DAO;

import com.example.model.Teacher;

import java.util.List;

public interface TeachersDao {
    void insert(Teacher teacher);
    void delete(int teacherId);
    Teacher find(int teacherId);
    List<Teacher> findAll();
}