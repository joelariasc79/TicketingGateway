package com.ticketing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;


import java.util.List;

public class DepartmentResponse {
    private Long departmentId;
    private String departmentName;
    private List<UserResponse> users;

    // Constructors (default and with arguments)
    public DepartmentResponse() {
    }

    public DepartmentResponse(Long departmentId, String departmentName, List<UserResponse> users) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.users = users;
    }

    // Getters and setters
    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<UserResponse> getUsers() {
        return users;
    }

    public void setUsers(List<UserResponse> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "DepartmentResponse{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", users=" + users +
                '}';
    }
}