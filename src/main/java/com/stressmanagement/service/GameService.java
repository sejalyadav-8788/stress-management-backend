package com.stressmanagement.service;

import com.stressmanagement.dto.GameSessionDTO;
import com.stressmanagement.model.GameSession;
import com.stressmanagement.model.User;
import com.stressmanagement.repository.GameSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameSessionRepository gameSessionRepository;

    @Autowired
    private UserService userService;

    public GameSession saveGameSession(GameSessionDTO dto) throws Exception {
        User user = userService.getUserById(dto.getUserId());

        GameSession gameSession = new GameSession();
        gameSession.setUser(user);
        gameSession.setGameName(dto.getGameName());
        gameSession.setScore(dto.getScore());
        gameSession.setDurationMinutes(dto.getDurationMinutes());

        return gameSessionRepository.save(gameSession);
    }

    public List<GameSession> getUserGameHistory(Long userId) throws Exception {
        return gameSessionRepository.findByUser_UserIdOrderByPlayedAtDesc(userId);
    }

    public List<GameSession> getLeaderboard(String gameName) {
        return gameSessionRepository.findByGameNameOrderByScoreDesc(gameName);
    }
}