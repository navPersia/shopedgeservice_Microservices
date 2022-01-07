package com.example.shopedgeservice.model;

import java.util.ArrayList;
import java.util.List;

public class FilledProductReview {
    private String productName;
    private String productId;
    private List<UserReview> userReviews;

    public FilledProductReview(Product product, List<Review> reviews){
        setProductName(product.getName());
        setProductId(product.getId());
        userReviews = new ArrayList<>();
        reviews.forEach(review -> {
            userReviews.add(new UserReview(review.getUserId(), review.getComment(), review.getScore()));
        });
        setUserReviews(userReviews);
    }
    public FilledProductReview(Product product, Review review) {
        setProductName(product.getName());
        setProductId(product.getId());
        userReviews = new ArrayList<>();
        userReviews.add(new UserReview(review.getUserId(),review.getComment(), review.getScore()));
        setUserReviews(userReviews);
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public List<UserReview> getUserReviews() {
        return userReviews;
    }

    public void setUserReviews(List<UserReview> userReviews) {
        this.userReviews = userReviews;
    }
}
