package com.stressmanagement.service;

import com.stressmanagement.dto.ChatMessageDTO;
import com.stressmanagement.dto.ChatResponseDTO;
import com.stressmanagement.model.ChatMessage;
import com.stressmanagement.model.User;
import com.stressmanagement.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatbotService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private UserService userService;

    public ChatResponseDTO sendMessage(ChatMessageDTO dto) throws Exception {
        User user = userService.getUserById(dto.getUserId());

        String userMessage = dto.getMessage().toLowerCase();
        String botResponse = generateResponse(userMessage);
        String sentiment = analyzeSentiment(userMessage);

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setUser(user);
        chatMessage.setMessageText(dto.getMessage());
        chatMessage.setResponseText(botResponse);
        chatMessage.setSentiment(sentiment);

        ChatMessage savedMessage = chatMessageRepository.save(chatMessage);

        return new ChatResponseDTO(
                savedMessage.getMessageId(),
                dto.getMessage(),
                botResponse,
                sentiment
        );
    }

    private String generateResponse(String message) {
        if (message.contains("anxious") || message.contains("anxiety") || message.contains("worried")) {
            return "I understand you're feeling anxious. Let's try a simple breathing exercise: " +
                    "Take a deep breath in for 4 counts, hold for 4, and breathe out for 4. " +
                    "Repeat this 3-4 times. Would you like to talk more about what's making you anxious?";
        }

        if (message.contains("stressed") || message.contains("stress") || message.contains("overwhelmed")) {
            return "I hear that you're feeling stressed. That's completely normal, and I'm here to help. " +
                    "Have you tried taking a short break? Sometimes a 5-minute walk or listening to calming music can help. " +
                    "What specific situation is causing you stress?";
        }

        if (message.contains("sad") || message.contains("depressed") || message.contains("down") ||
                message.contains("hopeless")) {
            return "I'm sorry you're feeling this way. Your feelings are valid, and it's brave of you to share. " +
                    "Remember that these feelings are temporary. Have you considered talking to a friend, family member, " +
                    "or mental health professional? I'm here to listen if you want to share more.";
        }

        if (message.contains("sleep") || message.contains("insomnia") || message.contains("tired") ||
                message.contains("exhausted")) {
            return "Sleep issues can really affect our mental health. Here are some tips: " +
                    "Try to maintain a regular sleep schedule, avoid screens 1 hour before bed, " +
                    "keep your room cool and dark, and try relaxation techniques before sleeping. " +
                    "How long have you been experiencing sleep difficulties?";
        }

        if (message.contains("work") || message.contains("job") || message.contains("study") ||
                message.contains("exam") || message.contains("deadline")) {
            return "Work and study pressures can be challenging. Remember to: " +
                    "1) Break tasks into smaller, manageable pieces, " +
                    "2) Take regular breaks (try the Pomodoro technique), " +
                    "3) Prioritize tasks by importance, " +
                    "4) Don't forget to take care of yourself. Would you like some specific time management strategies?";
        }

        if (message.contains("relationship") || message.contains("friend") || message.contains("family") ||
                message.contains("lonely") || message.contains("alone")) {
            return "Relationship challenges can be difficult. Remember that healthy communication is key. " +
                    "Try expressing your feelings using 'I' statements, listen actively, and be patient. " +
                    "If you're feeling lonely, consider reaching out to someone you trust or joining a community activity. " +
                    "Would you like to talk more about your specific situation?";
        }

        if (message.contains("happy") || message.contains("good") || message.contains("great") ||
                message.contains("better") || message.contains("thanks")) {
            return "That's wonderful to hear! I'm so glad you're feeling better. " +
                    "Keep up the positive momentum! Remember to celebrate your small wins. " +
                    "Is there anything else I can help you with today?";
        }

        if (message.contains("hello") || message.contains("hi") || message.contains("hey")) {
            return "Hello! I'm here to support you with your mental health and stress management. " +
                    "Feel free to share what's on your mind, and I'll do my best to help. " +
                    "How are you feeling today?";
        }

        if (message.contains("help") || message.contains("support")) {
            return "I'm here to help! You can talk to me about: " +
                    "• Stress and anxiety management, " +
                    "• Sleep issues, " +
                    "• Work/study pressure, " +
                    "• Relationship concerns, " +
                    "• General mental health support. " +
                    "What would you like to discuss?";
        }

        return "Thank you for sharing that with me. I'm here to listen and support you. " +
                "Can you tell me more about how you're feeling? Sometimes talking about our emotions " +
                "can help us understand them better. Remember, it's okay to not be okay sometimes.";
    }

    private String analyzeSentiment(String message) {
        String[] negativeWords = {"sad", "anxious", "stressed", "depressed", "worried", "scared",
                "angry", "frustrated", "hopeless", "tired", "exhausted"};
        String[] positiveWords = {"happy", "good", "great", "better", "wonderful", "excellent",
                "calm", "peaceful", "relaxed", "thankful"};

        int negativeCount = 0;
        int positiveCount = 0;

        for (String word : negativeWords) {
            if (message.contains(word)) negativeCount++;
        }

        for (String word : positiveWords) {
            if (message.contains(word)) positiveCount++;
        }

        if (negativeCount > positiveCount) {
            return "NEGATIVE";
        } else if (positiveCount > negativeCount) {
            return "POSITIVE";
        } else {
            return "NEUTRAL";
        }
    }

    public List<ChatMessage> getUserChatHistory(Long userId) throws Exception {
        return chatMessageRepository.findByUser_UserIdOrderByTimestampAsc(userId);
    }
}
