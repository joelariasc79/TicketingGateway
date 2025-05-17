package com.ticketing.model;

import java.util.Date;
import java.util.List;

public class TicketResponse {
    private Long ticketId;
    private String title;
    private String description;
    private UserResponse createdBy;
    private UserResponse assignee;
    private String priority;
    private String status;
    private Date creationDate;
    private String category;
    //  private String fileAttachmentPath;  // Removed fileAttachmentPath
    private List<TicketHistoryResponse> history;
    private List<AttachmentResponse> attachments; // Added attachments

    // Default constructor
    public TicketResponse() {
    }

    // Constructor with fields
    public TicketResponse(Long ticketId, String title, String description, UserResponse createdBy, UserResponse assignee, String priority, String status, Date creationDate, String category, List<TicketHistoryResponse> history, List<AttachmentResponse> attachments) { //modified constructor
        this.ticketId = ticketId;
        this.title = title;
        this.description = description;
        this.createdBy = createdBy;
        this.assignee = assignee;
        this.priority = priority;
        this.status = status;
        this.creationDate = creationDate;
        this.category = category;
        this.history = history;
        this.attachments = attachments; //added attachments to constructor
    }

    // Getters
    public Long getTicketId() {
        return ticketId;
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

    // public String getFileAttachmentPath() {  // Removed getter
    //      return fileAttachmentPath;
    // }

    public List<TicketHistoryResponse> getHistory() {
        return history;
    }

    public List<AttachmentResponse> getAttachments() {
        return attachments;
    }

    // Setters
    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
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

    // public void setFileAttachmentPath(String fileAttachmentPath) {  // Removed setter
    //      this.fileAttachmentPath = fileAttachmentPath;
    // }

    public void setHistory(List<TicketHistoryResponse> history) {
        this.history = history;
    }

    public void setAttachments(List<AttachmentResponse> attachments) {
        this.attachments = attachments;
    }

    @Override
    public String toString() {
        return "TicketResponse{" +
                "ticketId=" + ticketId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdBy=" + createdBy +
                ", assignee=" + assignee +
                ", priority='" + priority + '\'' +
                ", status='" + status + '\'' +
                ", creationDate=" + creationDate +
                ", category='" + category + '\'' +
                //  ", fileAttachmentPath='" + fileAttachmentPath + '\'' +  // Removed
                ", history=" + history +
                ", attachments=" + attachments +
                '}';
    }
}
