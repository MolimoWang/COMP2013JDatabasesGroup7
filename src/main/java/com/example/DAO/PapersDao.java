package com.example.DAO;

import com.example.model.Paper;
import java.util.List;

public interface PapersDao {
    // Method to insert a new paper into the database
    void insert(Paper paper);

    // Method to delete a paper from the database by its ID
    void deleteById(int paperId);

    // Method to find a paper in the database by its ID
    Paper findById(int paperId);

    // Method to retrieve all papers from the database
    List<Paper> findAll();

    // Method to update an existing paper in the database
    void update(Paper paper);
}