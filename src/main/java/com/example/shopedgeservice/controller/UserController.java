package com.example.shopedgeservice.controller;

import com.example.shopedgeservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${userservice.baseurl}")
    private String userServiceBaseUrl;

    @PostMapping("/users/newuser")
    public User newUser(){
        User user = restTemplate.postForObject("http://" + userServiceBaseUrl +"/users/newuser", new User("Test", "test1", "navid7373", Boolean.TRUE,Boolean.FALSE,Boolean.FALSE),User.class);

        return user;
    }

    @DeleteMapping("/users/user/{id}")
    public ResponseEntity deleteUser(@PathVariable String id){
        restTemplate.delete("http://" + userServiceBaseUrl +"/users/user/"+id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/users/user/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User newUser){
        ResponseEntity<User> userResponseEntity = restTemplate.exchange("http://" + userServiceBaseUrl +"/users/user/"+id, HttpMethod.PUT, new HttpEntity<>(newUser), User.class);
        User retrievedUser = userResponseEntity.getBody();
        return retrievedUser;
    }

    @GetMapping("/users/user/{id}")
    public User getUser(@PathVariable String id){
        return restTemplate.getForObject("http://" + userServiceBaseUrl +"/users/user/"+id, User.class);
    }
}
