package com.stressmanagement.repository;

import com.stressmanagement.model.GameSession;
import com.stressmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameSessionRepository extends JpaRepository<GameSession, Long> {

    List<GameSession> findByUserOrderByPlayedAtDesc(User user);

    List<GameSession> findByUser_UserIdOrderByPlayedAtDesc(Long userId);

    List<GameSession> findByGameNameOrderByScoreDesc(String gameName);
}