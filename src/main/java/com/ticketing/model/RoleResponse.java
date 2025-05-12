package com.ticketing.model;


public class RoleResponse {
    private Long roleId;
    private String roleName;
    private boolean empty; // This field is present in the JSON

    // Constructors (default and with arguments)
    public RoleResponse() {
    }

    public RoleResponse(Long roleId, String roleName, boolean empty) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.empty = empty;
    }

    // Getters and setters
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    @Override
    public String toString() {
        return "RoleResponse{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", empty=" + empty +
                '}';
    }
}