package com.example.shopedgeservice.controller;

import com.example.shopedgeservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${userservice.baseurl}")
    private String userServiceBaseUrl;

    @PostMapping("/users/newuser")
    public User newUser(@RequestBody User newuser){
        User user = restTemplate.postForObject("http://" + userServiceBaseUrl +"/users/newuser", newuser,User.class);
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

    @GetMapping("/users/user/{username}")
    public User getUser(@PathVariable String username){
        return restTemplate.getForObject("http://" + userServiceBaseUrl +"/users/user/"+username, User.class);
    }
    @GetMapping("/users")
    public List<User> getUsers(){
        return restTemplate.exchange("http://" + userServiceBaseUrl +"/users", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>(){}).getBody();
    }

    @PostMapping("/users/login")
    public String loginUser(@RequestBody User user){
        User retrivedUser = restTemplate.postForObject("http://" + userServiceBaseUrl +"/users/login", user, User.class);
        if (retrivedUser!=null){
            return "access";
        }else {
            return "can't access";
        }
    }

}