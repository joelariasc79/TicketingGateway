package com.ticketing.model;

import java.util.List;

import com.ticketing.domain.User;

public class UserResponseRefactors {
    private Long userId;
    private String userName;
//    private String userPassword;
    private String password;
    private String email;
    private List<UserResponse> managedUsers;
    private List<RoleResponse> roles;
    private ProjectResponse project;
    private DepartmentResponse department;
    private UserResponse manager; 

    // Default constructor
    public UserResponseRefactors() {
    }

    // Constructor with all fields
    public UserResponseRefactors(Long userId, String userName, String password, String email, List<UserResponse> managedUsers, 
    		List<RoleResponse> roles, ProjectResponse project, DepartmentResponse department, UserResponse manager) {
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
    
    public UserResponseRefactors(Long userId, String userName, String email, String password, UserResponse manager, 
    		DepartmentResponse department, 
    		ProjectResponse project, List<RoleResponse> roles) {
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

//    public String getUserPassword() {
//        return userPassword;
//    }
    
    public String getpassword() {
        return password;
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

//    public void setUserPassword(String userPassword) {
//        this.userPassword = userPassword;
//    }
    
    public void setPassword(String password) {
        this.password = password;
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
    
    public UserResponse getManager() {
        return manager;
    }

    public void setManager(UserResponse manager) {
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
    
//    @Override
//    public String toString() {
//        return "UserResponse{" +
//                "userId=" + userId +
//                ", userName='" + userName + '\'' +
//                ", userPassword='" + userPassword + '\'' +
//                ", email='" + email + '\'' +
//                ", managedUsers=" + managedUsers +
//                ", roles=" + roles +
//                ", project=" + project +
//                ", department=" + department +
//                ", manager=" + manager +
//                '}';
//    }
}