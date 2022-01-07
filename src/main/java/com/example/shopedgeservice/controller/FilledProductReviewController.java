package com.example.shopedgeservice.controller;

import com.example.shopedgeservice.model.FilledProductReview;
import com.example.shopedgeservice.model.Product;
import com.example.shopedgeservice.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FilledProductReviewController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${reviewservice.baseurl}")
    private String reviewServiceBaseUrl;

    @Value("${productservice.baseurl}")
    private String productServiceBaseUrl;

    @Value("${userservice.baseurl}")
    private String userServiceBaseUrl;
    @GetMapping("/productReviews/user/{userId}/product/{productId}")
    public FilledProductReview getReviewByUserIdAndProductId(@PathVariable int userId, @PathVariable int productId){
        Product product = restTemplate.getForObject("http://"+ productServiceBaseUrl+ "/products/product/{id}", Product.class, productId);
        Review review = restTemplate.getForObject("http://"+ reviewServiceBaseUrl+ "/reviews/user/"+userId+"/product/"+productId, Review.class);
        return new FilledProductReview(product, review);
    }
    @GetMapping("/productReviews/product/{productId}")
    public FilledProductReview getReviewsByProductId(@PathVariable int productId){
        Product product = restTemplate.getForObject("http://"+ productServiceBaseUrl+ "/products/product/{id}", Product.class, productId);
        ResponseEntity<List<Review>> responseEntityReviews = restTemplate.exchange("http://" + reviewServiceBaseUrl + "/reviews/product/{productId}", HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {

        }, productId);
                return new FilledProductReview(product, responseEntityReviews.getBody());
    }
    @GetMapping("/productReviews/user/{userId}")
    public List<FilledProductReview> getReviewsByUserId(@PathVariable int userId){
        List<FilledProductReview> retValue = new ArrayList<>();
        ResponseEntity<List<Review>> responseEntityReviews = restTemplate.exchange("http://" + reviewServiceBaseUrl + "/reviews/user/{userId}", HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {

        }, userId);
        List<Review> reviews = responseEntityReviews.getBody();
        for(Review review: reviews){
            Product product = restTemplate.getForObject("http://"+ productServiceBaseUrl+ "/products/product/{id}", Product.class, review.getProductId());
            retValue.add(new FilledProductReview(product, review));

        }
        return retValue;
    }
    @PostMapping("/productReviews")
    public FilledProductReview addProductReview(@RequestParam int userId, @RequestParam String productId, @RequestParam String comment, @RequestParam Integer score){
        Review review = restTemplate.postForObject("http://"+reviewServiceBaseUrl+"/reviews", new Review(userId, productId, comment, score), Review.class);
        Product product = restTemplate.getForObject("http://"+ productServiceBaseUrl+ "/products/product/{id}", Product.class, productId);
        return new FilledProductReview(product, review);
    }
    @PutMapping("/productReviews")
    public FilledProductReview updateProductReview(@RequestParam int userId, @RequestParam String productId, @RequestParam String comment, @RequestParam Integer score){
        Review review = restTemplate.getForObject("http://"+ reviewServiceBaseUrl+ "/reviews/user/"+userId+"/product/"+productId, Review.class);
        review.setScore(score);
        review.setComment(comment);
        ResponseEntity<Review> responseEntityReview = restTemplate.exchange("http://"+reviewServiceBaseUrl+"/reviews", HttpMethod.PUT, new HttpEntity<>(review), Review.class);
        Review updatedReview = responseEntityReview.getBody();
        Product product = restTemplate.getForObject("http://"+ productServiceBaseUrl+ "/products/product/{id}", Product.class, productId);
        return new FilledProductReview(product, updatedReview);
    }
    @DeleteMapping("/productReviews/user/{userId}/product/{productId}")
    public ResponseEntity deleteProductReview(@PathVariable Integer userId, @PathVariable Integer productId){
        restTemplate.delete("http://"+reviewServiceBaseUrl+"/reviews/user/"+userId+"/product/"+productId);
        return ResponseEntity.ok().build();
    }

}
