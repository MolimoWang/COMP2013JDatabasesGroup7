package com.example.DAO;

import java.util.List;

public interface StudentPapersDao {
    void insert(int studentId, int paperId);

    void deleteByStudentIdAndPaperId(int studentId, int paperId);
    void deleteByStudentId(int studentId);  // New method
    void deleteByPaperId(int paperId);  // New method

    List<Integer> findPaperIdsByStudentId(int studentId);
    List<Integer> findStudentIdsByPaperId(int paperId);
}