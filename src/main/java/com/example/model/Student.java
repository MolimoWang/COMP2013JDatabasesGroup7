package com.example.model;

public class Student extends Person {
    // Unique identifier for the student
    private int studentId;

    // Identifier for the person the student is associated with
    private int personId;

    // Getter method for studentId
    public int getStudentId() {
        return studentId;
    }

    // Setter method for studentId
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    // Getter method for personId
    public int getPersonId() {
        return personId;
    }

    // Setter method for personId
    public void setPersonId(int personId) {
        this.personId = personId;
    }
}