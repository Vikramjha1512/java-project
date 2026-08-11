package com.login.model;

import java.io.Serializable;

public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private int userId;
    private String name;
    private String email;
    private String password;
    private String role;
    
    // Default constructor
    public User() {
        super();
    }
    
    // Parameterized constructor
    public User(String name, String email, String password, String role) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    
    // Getters and Setters
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    @Override
    public String toString() {
        return "User [userId=" + userId + ", name=" + name + ", email=" + email 
                + ", role=" + role + "]";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User other = (User) obj;
        return userId == other.userId;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(userId);
    }
}
