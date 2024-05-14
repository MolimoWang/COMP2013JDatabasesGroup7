package com.example.model;

public class Question {
    private int questionId;
    private String text;  // 修改为text

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getText() {  // 修改为getText
        return text;
    }

    public void setText(String text) {  // 修改为setText
        this.text = text;
    }
}