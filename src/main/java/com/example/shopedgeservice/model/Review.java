package com.example.shopedgeservice.model;

import java.util.Date;

public class Review {
    private Integer id;
    private String userId;
    private String productId;
    private String comment;
    private Integer score;
    private Date createdAt;
    private Date updatedAt;
    public Review(String userId, String productId, String comment, Integer score) {
        this.userId = userId;
        this.productId = productId;
        this.comment = comment;
        this.score = score;
        this.createdAt = new Date();
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}

