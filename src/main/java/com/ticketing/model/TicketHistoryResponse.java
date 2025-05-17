package com.ticketing.model;

import java.util.Date;

public class TicketHistoryResponse {
    private Long ticketHistoryId;
    private String action;
    private UserResponse actionBy; // Changed to UserResponse
    private Date actionDate;
    private String comments;

    // Default constructor
    public TicketHistoryResponse() {
    }

    // Constructor with fields
    public TicketHistoryResponse(Long ticketHistoryId, String action, UserResponse actionBy, Date actionDate, String comments) {
        this.ticketHistoryId = ticketHistoryId;
        this.action = action;
        this.actionBy = actionBy;
        this.actionDate = actionDate;
        this.comments = comments;
    }

    // Getters
    public Long getTicketHistoryId() {
        return ticketHistoryId;
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
    public void setTicketHistoryId(Long ticketHistoryId) {
        this.ticketHistoryId = ticketHistoryId;
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
                "ticketHistoryId=" + ticketHistoryId +
                ", action='" + action + '\'' +
                ", actionBy=" + actionBy +
                ", actionDate=" + actionDate +
                ", comments='" + comments + '\'' +
                '}';
    }
}
