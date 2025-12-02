package com.stressmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StressResultDTO {
    private Long assessmentId;
    private Integer stressScore;
    private String stressLevel;
    private String symptoms;
    private String recommendations;
}