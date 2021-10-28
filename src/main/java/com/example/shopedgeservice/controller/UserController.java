package com.example.shopedgeservice.controller;

import com.example.shopedgeservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
