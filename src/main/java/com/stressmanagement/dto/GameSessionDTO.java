package com.stressmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameSessionDTO {
    private Long userId;
    private String gameName;
    private Integer score;
    private Integer durationMinutes;
}