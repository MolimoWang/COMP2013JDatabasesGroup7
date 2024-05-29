package com.example.DAO;

import java.util.List;

public interface StudentPapersDao {
    void insert(int studentId, int paperId);
    void deleteByStudentIdAndPaperId(int studentId, int paperId);
    List<Integer> findPaperIdsByStudentId(int studentId);
    List<Integer> findStudentIdsByPaperId(int paperId);
}