package com.example.DAO;

import com.example.model.Student;
import java.util.List;

public interface StudentsDao {
    void insert(Student student);
    void deleteById(int studentId);
    Student findById(int studentId);
    List<Student> findAll();
}