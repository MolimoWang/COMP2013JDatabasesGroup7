package com.example.model;

public class Answer {
    // Unique identifier for the answer
    private int answerId;

    // Text content of the answer
    private String text;

    // Getter method for answerId
    public int getAnswerId() {
        return answerId;
    }

    // Setter method for answerId
    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    // Getter method for text
    public String getText() {
        return text;
    }

    // Setter method for text
    public void setText(String text) {
        this.text = text;
    }
}