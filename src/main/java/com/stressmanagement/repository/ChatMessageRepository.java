package com.stressmanagement.repository;

import com.stressmanagement.model.ChatMessage;
import com.stressmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findByUserOrderByTimestampDesc(User user);

    List<ChatMessage> findByUser_UserIdOrderByTimestampAsc(Long userId);
}