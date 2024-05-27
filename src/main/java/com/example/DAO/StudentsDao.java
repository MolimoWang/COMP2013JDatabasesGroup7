package com.example.DAO;

import com.example.model.Student;
import java.util.List;

public interface StudentsDao {
    // Method to insert a new student into the database
    void insert(Student student);

    // Method to delete a student from the database by its ID
    void deleteById(int studentId);

    // Method to find a student in the database by its ID
    Student findById(int studentId);

    // Method to retrieve all students from the database
    List<Student> findAll();
}