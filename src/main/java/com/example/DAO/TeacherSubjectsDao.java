package com.example.DAO;

import java.util.List;

public interface TeacherSubjectsDao {
    void insert(int teacherId, int subjectId);

    void deleteByTeacherIdAndSubjectId(int teacherId, int subjectId);
    void deleteByTeacherId(int teacherId);  // Add this line
    void deleteBySubjectId(int subjectId);  // Add this line

    List<Integer> findSubjectIdsByTeacherId(int teacherId);
    List<Integer> findTeacherIdsBySubjectId(int subjectId);
}