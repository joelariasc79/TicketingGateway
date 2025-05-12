package com.ticketing.model;

import java.util.List;

import java.util.List;

public class UserResponse {
    private Long userId;
    private String userName;
    private String userPassword;
    private String email;
    private List<UserResponse> managedUsers;
    private List<RoleResponse> roles;
    private ProjectResponse project;
    private DepartmentResponse department;

    // Default constructor
    public UserResponse() {
    }

    // Constructor with all fields
    public UserResponse(Long userId, String userName, String userPassword, String email, List<UserResponse> managedUsers, List<RoleResponse> roles, ProjectResponse project, DepartmentResponse department) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.email = email;
        this.managedUsers = managedUsers;
        this.roles = roles;
        this.project = project;
        this.department = department;
    }

    // Getters
    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getEmail() {
        return email;
    }

    public List<UserResponse> getManagedUsers() {
        return managedUsers;
    }

    public List<RoleResponse> getRoles() {
        return roles;
    }

    public ProjectResponse getProject() {
        return project;
    }

    public DepartmentResponse getDepartment() {
        return department;
    }

    // Setters
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setManagedUsers(List<UserResponse> managedUsers) {
        this.managedUsers = managedUsers;
    }

    public void setRoles(List<RoleResponse> roles) {
        this.roles = roles;
    }

    public void setProject(ProjectResponse project) {
        this.project = project;
    }

    public void setDepartment(DepartmentResponse department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", email='" + email + '\'' +
                ", managedUsers=" + managedUsers +
                ", roles=" + roles +
                ", project=" + project +
                ", department=" + department +
                '}';
    }
}