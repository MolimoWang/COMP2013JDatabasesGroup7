package com.example.model;

public class Teacher extends Person {
    // Unique identifier for the teacher
    private int teacherId;

    // Identifier for the person the teacher is associated with
    private int personId;

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

    // Getter method for personId
    public int getPersonId() {
        return personId;
    }

    // Setter method for personId
    public void setPersonId(int personId) {
        this.personId = personId;
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