package com.example.DAO;

import java.util.List;

public interface TeacherSubjectsDao {
    void insert(int teacherId, int subjectId);
    void deleteByTeacherIdAndSubjectId(int teacherId, int subjectId);
    List<Integer> findSubjectIdsByTeacherId(int teacherId);
    List<Integer> findTeacherIdsBySubjectId(int subjectId);
}