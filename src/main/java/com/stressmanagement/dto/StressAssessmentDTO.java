package com.stressmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StressAssessmentDTO {
    private Long userId;
    private Integer sleepHours;
    private Integer workHours;
    private Integer exerciseMinutes;
    private Integer anxietyLevel;
    private Integer fatigueLevel;
    private Integer socialInteraction;
    private Boolean hasHeadaches;
    private Boolean hasDifficultyConcentrating;
    private Boolean hasIrritability;
}
