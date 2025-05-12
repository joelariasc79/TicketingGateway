package com.ticketing.model;

import java.util.Date;

public class TicketHistoryResponse {
    private Long id;
    private String action;
    private UserResponse actionBy; // Changed to UserResponse
    private Date actionDate;
    private String comments;

    // Default constructor
    public TicketHistoryResponse() {
    }

    // Constructor with fields
    public TicketHistoryResponse(Long id, String action, UserResponse actionBy, Date actionDate, String comments) {
        this.id = id;
        this.action = action;
        this.actionBy = actionBy;
        this.actionDate = actionDate;
        this.comments = comments;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public UserResponse getActionBy() {
        return actionBy;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public String getComments() {
        return comments;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setActionBy(UserResponse actionBy) {
        this.actionBy = actionBy;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "TicketHistoryResponse{" +
                "id=" + id +
                ", action='" + action + '\'' +
                ", actionBy=" + actionBy +
                ", actionDate=" + actionDate +
                ", comments='" + comments + '\'' +
                '}';
    }
}
