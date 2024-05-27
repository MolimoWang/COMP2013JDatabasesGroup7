package com.example.model;

public class Student {
    // Unique identifier for the student
    private int studentId;

    // Name of the student
    private String name;

    // Identifier for the paper the student is associated with
    private int paperId;

    // Getter method for studentId
    public int getStudentId() {
        return studentId;
    }

    // Setter method for studentId
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    // Getter method for name
    public String getName() {
        return name;
    }

    // Setter method for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter method for paperId
    public int getPaperId() {
        return paperId;
    }

    // Setter method for paperId
    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }
}