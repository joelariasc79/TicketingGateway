package com.ticketing.model;


public class ProjectResponse {
    private Long projectId;
    private String projectName;

    // Constructors
    public ProjectResponse() {
    }

    public ProjectResponse(Long projectId, String projectName) {
        this.projectId = projectId;
        this.projectName = projectName;
    }

    // Getters and setters
    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return "ProjectResponse{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                '}';
    }
}