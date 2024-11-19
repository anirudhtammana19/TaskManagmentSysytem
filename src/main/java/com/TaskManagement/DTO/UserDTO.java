package com.TaskManagement.DTO;

public class UserDTO {

    private Long userId;
    private String username;
    private String password;

    // Default constructor
    public UserDTO() {
    }

    // Parameterized constructor
    public UserDTO(Long userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Override toString method
    @Override
    public String toString() {
        return "UserDTO [userId=" + userId + ", username=" + username + ", password=" + password + "]";
    }
}
