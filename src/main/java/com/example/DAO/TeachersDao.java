package com.example.DAO;

import com.example.model.Teacher;
import java.util.List;

public interface TeachersDao {
    void insert(Teacher teacher);
    void deleteById(int teacherId);
    Teacher findById(int teacherId);
    List<Teacher> findAll();
}