package com.example.model;

public class Subject {
    // Unique identifier for the subject
    private int subjectId;

    // Name of the subject
    private String name;

    // Default constructor
    public Subject() {
    }

    // Constructor with parameters
    public Subject(int subjectId, String name) {
        this.subjectId = subjectId;
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

    // Getter method for name
    public String getName() {
        return name;
    }

    // Setter method for name
    public void setName(String name) {
        this.name = name;
    }
}