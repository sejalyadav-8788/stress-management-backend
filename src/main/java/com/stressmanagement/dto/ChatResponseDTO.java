package com.stressmanagement.dto;


public class ChatResponseDTO {
    private Long messageId;
    private String message;        // Original user message
    private String responseText;   // Bot response
    private String sentiment;      // POSITIVE, NEGATIVE, NEUTRAL

    public ChatResponseDTO() {
    }

    public ChatResponseDTO(Long messageId, String message, String responseText, String sentiment) {
        this.messageId = messageId;
        this.message = message;
        this.responseText = responseText;
        this.sentiment = sentiment;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }
}
