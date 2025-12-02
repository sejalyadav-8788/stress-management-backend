package com.stressmanagement.controller;

import com.stressmanagement.dto.ChatMessageDTO;
import com.stressmanagement.dto.ChatResponseDTO;
import com.stressmanagement.model.ChatMessage;
import com.stressmanagement.service.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
public class ChatbotController {

    @Autowired
    private ChatbotService chatbotService;

    /**
     * Send a message to the chatbot
     * POST /api/chat/send
     *
     * Request Body: { "userId": 1, "message": "I'm feeling stressed" }
     * Response: { "messageId": 123, "message": "I'm feeling stressed", "responseText": "bot response", "sentiment": "NEGATIVE" }
     */
    @PostMapping("/send")
    public ResponseEntity<ChatResponseDTO> sendMessage(@RequestBody ChatMessageDTO chatMessageDTO) {
        try {
            // Validate input
            if (chatMessageDTO.getUserId() == null) {
                throw new IllegalArgumentException("User ID is required");
            }
            if (chatMessageDTO.getMessage() == null || chatMessageDTO.getMessage().trim().isEmpty()) {
                throw new IllegalArgumentException("Message cannot be empty");
            }

            // Process message through service
            ChatResponseDTO response = chatbotService.sendMessage(chatMessageDTO);

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            // Return bad request for validation errors
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            // Log the error
            e.printStackTrace();
            // Return internal server error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Get chat history for a user
     * GET /api/chat/history/{userId}
     *
     * Response: List of ChatMessage objects with messageText and responseText
     */
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<ChatMessage>> getChatHistory(@PathVariable Long userId) {
        try {
            List<ChatMessage> history = chatbotService.getUserChatHistory(userId);
            return ResponseEntity.ok(history);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Health check endpoint to verify API is running
     * GET /api/chat/health
     */
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Chatbot API is running successfully!");
    }
}