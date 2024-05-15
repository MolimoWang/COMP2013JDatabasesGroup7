package com.example.DAO;

import com.example.model.Paper;
import java.util.List;

public interface PapersDao {
    void insert(Paper paper);
    void deleteById(int paperId);
    Paper findById(int paperId);
    List<Paper> findAll();
    void update(Paper paper);
}