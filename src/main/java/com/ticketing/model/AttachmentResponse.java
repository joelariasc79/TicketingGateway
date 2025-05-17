package com.ticketing.model;

import java.time.LocalDateTime;

public class AttachmentResponse {
    private Long attachmentId;
    private String fileName;
    private String fileType;
    private LocalDateTime uploadDateTime;
    private String filePath;  // Added filePath

    public AttachmentResponse() {
    }

    public AttachmentResponse(Long attachmentId, String fileName, String fileType, LocalDateTime uploadDateTime, String filePath) {
        this.attachmentId = attachmentId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.uploadDateTime = uploadDateTime;
        this.filePath = filePath;
    }

    public Long getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public LocalDateTime getUploadDateTime() {
        return uploadDateTime;
    }

    public void setUploadDateTime(LocalDateTime uploadDateTime) {
        this.uploadDateTime = uploadDateTime;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "AttachmentResponse{" +
                "attachmentId=" + attachmentId +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", uploadDateTime=" + uploadDateTime +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
