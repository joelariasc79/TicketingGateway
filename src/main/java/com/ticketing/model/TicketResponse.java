package com.ticketing.model;

import java.util.Date;
import java.util.List;

public class TicketResponse {
    private Long id;
    private String title;
    private String description;
    private UserResponse createdBy;
    private UserResponse assignee;
    private String priority;
    private String status;
    private Date creationDate;
    private String category;
    private String fileAttachmentPath;
    private List<TicketHistoryResponse> history;

    // Default constructor
    public TicketResponse() {
    }

    // Constructor with fields
    public TicketResponse(Long id, String title, String description, UserResponse createdBy, UserResponse assignee, String priority, String status, Date creationDate, String category, String fileAttachmentPath, List<TicketHistoryResponse> history) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdBy = createdBy;
        this.assignee = assignee;
        this.priority = priority;
        this.status = status;
        this.creationDate = creationDate;
        this.category = category;
        this.fileAttachmentPath = fileAttachmentPath;
        this.history = history;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public UserResponse getCreatedBy() {
        return createdBy;
    }

    public UserResponse getAssignee() {
        return assignee;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getCategory() {
        return category;
    }

    public String getFileAttachmentPath() {
        return fileAttachmentPath;
    }

    public List<TicketHistoryResponse> getHistory() {
        return history;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedBy(UserResponse createdBy) {
        this.createdBy = createdBy;
    }

    public void setAssignee(UserResponse assignee) {
        this.assignee = assignee;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setFileAttachmentPath(String fileAttachmentPath) {
        this.fileAttachmentPath = fileAttachmentPath;
    }

    public void setHistory(List<TicketHistoryResponse> history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "TicketResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdBy=" + createdBy +
                ", assignee=" + assignee +
                ", priority='" + priority + '\'' +
                ", status='" + status + '\'' +
                ", creationDate=" + creationDate +
                ", category='" + category + '\'' +
                ", fileAttachmentPath='" + fileAttachmentPath + '\'' +
                ", history=" + history +
                '}';
    }
}