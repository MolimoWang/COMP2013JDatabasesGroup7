package com.example.DAO;

import com.example.model.Student;
import java.util.List;

public interface StudentsDao {
    void insert(Student student);
    void delete(int studentId);
    Student find(int studentId);
    List<Student> findAll();
}