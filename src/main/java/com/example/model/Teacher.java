package com.example.model;

public class Teacher {
    // Unique identifier for the teacher
    private int teacherId;

    // Name of the teacher
    private String name;

    // Identifier for the subject the teacher is associated with
    private int subjectId;

    // Getter method for teacherId
    public int getTeacherId() {
        return teacherId;
    }

    // Setter method for teacherId
    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    // Getter method for name
    public String getName() {
        return name;
    }

    // Setter method for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter method for subjectId
    public int getSubjectId() {
        return subjectId;
    }

    // Setter method for subjectId
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}