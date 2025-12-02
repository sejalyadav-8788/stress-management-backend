package com.stressmanagement.dto;

public class ChatMessageDTO {
    private Long userId;
    private String message;

    public ChatMessageDTO() {
    }

    public ChatMessageDTO(Long userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

// ChatResponseDTO.java
