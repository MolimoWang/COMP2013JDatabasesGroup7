package com.example.model;

public class Paper {
    // Unique identifier for the paper
    private int paperId;

    // Title of the paper
    private String title;

    // Year the paper was published
    private int year;

    // Identifier for the subject the paper is associated with
    private int subjectId;

    // Getter method for paperId
    public int getPaperId() {
        return paperId;
    }

    // Setter method for paperId
    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    // Getter method for title
    public String getTitle() {
        return title;
    }

    // Setter method for title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter method for year
    public int getYear() {
        return year;
    }

    // Setter method for year
    public void setYear(int year) {
        this.year = year;
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