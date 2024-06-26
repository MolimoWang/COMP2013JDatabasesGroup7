package com.example.DAO;

import com.example.model.Teacher;
import java.util.List;

public interface TeachersDao {
    // Method to insert a new teacher into the database
    void insert(Teacher teacher);

    // Method to delete a teacher from the database by its ID
    void deleteById(int teacherId);
    // Method to delete a teacher from the database by its PersonID
    void deleteByPersonId(int personId);

    // Method to find a teacher in the database by its ID
    Teacher findById(int teacherId);

    // Method to retrieve all teachers from the database
    List<Teacher> findAll();

    // Method to update an existing teacher in the database
    void update(Teacher teacher);
}