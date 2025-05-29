package com.ticketing.model;

import java.util.List;

import com.ticketing.domain.User;

public class UserResponseLongs {
    private Long userId;
    private String userName;
    private String password;
    private String email;
    private List<Long> managedUsers;
    private List<Long> roles;
    private Long project;
    private Long department;
    private Long manager; 

    // Default constructor
    public UserResponseLongs() {
    }

    // Constructor with all fields
    public UserResponseLongs(Long userId, String userName, String password, String email, List<Long> managedUsers, 
    		List<Long> roles, Long project, Long department, Long manager) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.managedUsers = managedUsers;
        this.roles = roles;
        this.project = project;
        this.department = department;
        this.manager = manager;
    }
    
    
    public UserResponseLongs(Long userId, String userName, String email, String password, Long manager, Long department, 
    		Long project, List<Long> roles) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.project = project;
        this.department = department;
        this.manager = manager;
    }

    // Getters
    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public List<Long> getManagedUsers() {
        return managedUsers;
    }

    public List<Long> getRoles() {
        return roles;
    }

    public Long getProject() {
        return project;
    }

    public Long getDepartment() {
        return department;
    }

    // Setters
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setManagedUsers(List<Long> managedUsers) {
        this.managedUsers = managedUsers;
    }

    public void setRoles(List<Long> roles) {
        this.roles = roles;
    }

    public void setProject(Long project) {
        this.project = project;
    }

    public void setDepartment(Long department) {
        this.department = department;
    }
    
    public Long getManager() {
        return manager;
    }

    public void setManager(Long manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + password + '\'' +
                ", email='" + email + '\'' +
                ", managedUsers=" + managedUsers +
                ", roles=" + roles +
                ", project=" + project +
                ", department=" + department +
                ", manager=" + manager +
                '}';
    }
}