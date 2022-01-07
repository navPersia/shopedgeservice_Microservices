package com.example.shopedgeservice.model;

public class UserReview {
    private int userId;
    private String comment;
    private int score;

    public UserReview(int userId, String comment, int score) {
        this.userId = userId;
        this.comment = comment;
        this.score = score;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
