package com.stressmanagement.controller;

import com.stressmanagement.dto.ApiResponseDTO;
import com.stressmanagement.dto.GameSessionDTO;
import com.stressmanagement.model.GameSession;
import com.stressmanagement.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
@CrossOrigin(origins = "*")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/session")
    public ResponseEntity<?> saveGameSession(@RequestBody GameSessionDTO dto) {
        try {
            GameSession session = gameService.saveGameSession(dto);
            return ResponseEntity.ok(new ApiResponseDTO(true, "Game session saved", session));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponseDTO(false, e.getMessage(), null));
        }
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<?> getUserGameHistory(@PathVariable Long userId) {
        try {
            List<GameSession> history = gameService.getUserGameHistory(userId);
            return ResponseEntity.ok(new ApiResponseDTO(true, "Game history retrieved", history));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponseDTO(false, e.getMessage(), null));
        }
    }

    @GetMapping("/leaderboard/{gameName}")
    public ResponseEntity<?> getLeaderboard(@PathVariable String gameName) {
        try {
            List<GameSession> leaderboard = gameService.getLeaderboard(gameName);
            return ResponseEntity.ok(new ApiResponseDTO(true, "Leaderboard retrieved", leaderboard));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponseDTO(false, e.getMessage(), null));
        }
    }
}
