package com.example.DAO;

import com.example.model.Paper;

import java.sql.SQLException;
import java.util.List;

public interface PapersDao {
    // Method to insert a new paper into the database
    void insert(Paper paper) throws SQLException;

    // Method to delete a paper from the database by its ID
    void deleteById(int paperId) throws SQLException;

    // Method to find a paper in the database by its ID
    Paper findById(int paperId);

    // Method to retrieve all papers from the database
    List<Paper> findAll();

    // Method to update an existing paper in the database
    void update(Paper paper);

    // Method to retrieve a specific number of papers from a specific position in the database
    List<Paper> findWithPagination(int start, int count);

    // Method to count the total number of papers in the database
    int count();

    // Method to count the total number of papers in the database by dynamic conditions
    int countByDynamicConditions(String title, String subjectName, String yearStr, String teacher);
}