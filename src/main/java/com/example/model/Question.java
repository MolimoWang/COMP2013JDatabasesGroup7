package com.example.model;

public class Question {
    // Unique identifier for the question
    private int questionId;

    // Identifier for the paper the question is associated with
    private int paperId;

    // Text content of the question
    private String text;

    // Identifier for the answer to the question
    private int answerId;

    // Getter method for questionId
    public int getQuestionId() {
        return questionId;
    }

    // Setter method for questionId
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    // Getter method for paperId
    public int getPaperId() {
        return paperId;
    }

    // Setter method for paperId
    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    // Getter method for text
    public String getText() {
        return text;
    }

    // Setter method for text
    public void setText(String text) {
        this.text = text;
    }

    // Getter method for answerId
    public int getAnswerId() {
        return answerId;
    }

    // Setter method for answerId
    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }
}