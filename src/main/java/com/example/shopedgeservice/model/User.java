package com.example.shopedgeservice.model;

import java.time.LocalDateTime;
import java.util.Date;

public class User {
    private String id;
    private String name;
    private String username;
    private String passwordHash;
    private Boolean active;
    private Boolean admin;
    private Boolean seller;
    private LocalDateTime createdAt;
    private String modifiedAt;

    public User() {
    }


    public User(String name, String username, String passwordHash, Boolean active, Boolean admin, Boolean seller) {
        this.name = name;
        this.username = username;
        this.passwordHash = passwordHash;
        this.active = active;
        this.admin = admin;
        this.seller = seller;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getSeller() {
        return seller;
    }

    public void setSeller(Boolean seller) {
        this.seller = seller;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }

}
