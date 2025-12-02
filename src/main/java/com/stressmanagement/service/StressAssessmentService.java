package com.stressmanagement.service;

import com.stressmanagement.dto.StressAssessmentDTO;
import com.stressmanagement.dto.StressResultDTO;
import com.stressmanagement.model.StressAssessment;
import com.stressmanagement.model.StressSolution;
import com.stressmanagement.model.User;
import com.stressmanagement.repository.StressAssessmentRepository;
import com.stressmanagement.repository.StressSolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StressAssessmentService {

    @Autowired
    private StressAssessmentRepository assessmentRepository;

    @Autowired
    private StressSolutionRepository solutionRepository;

    @Autowired
    private UserService userService;

    public StressResultDTO calculateStressLevel(StressAssessmentDTO dto) throws Exception {
        User user = userService.getUserById(dto.getUserId());

        int stressScore = 0;

        if (dto.getSleepHours() < 6) {
            stressScore += 20;
        } else if (dto.getSleepHours() < 7) {
            stressScore += 10;
        } else if (dto.getSleepHours() > 9) {
            stressScore += 5;
        }

        if (dto.getWorkHours() > 10) {
            stressScore += 15;
        } else if (dto.getWorkHours() > 8) {
            stressScore += 8;
        }

        if (dto.getExerciseMinutes() < 15) {
            stressScore += 10;
        } else if (dto.getExerciseMinutes() < 30) {
            stressScore += 5;
        }

        stressScore += (int)(dto.getAnxietyLevel() * 1.5);
        stressScore += (int)(dto.getFatigueLevel() * 1.5);

        if (dto.getSocialInteraction() < 3) {
            stressScore += 10;
        } else if (dto.getSocialInteraction() < 5) {
            stressScore += 5;
        }

        if (dto.getHasHeadaches()) stressScore += 5;
        if (dto.getHasDifficultyConcentrating()) stressScore += 5;
        if (dto.getHasIrritability()) stressScore += 5;

        String stressLevel;
        String symptoms;

        if (stressScore <= 30) {
            stressLevel = "LOW";
            symptoms = "You're managing well! Minor stress symptoms.";
        } else if (stressScore <= 60) {
            stressLevel = "MEDIUM";
            symptoms = "Moderate stress detected. Some physical and mental symptoms present.";
        } else if (stressScore <= 85) {
            stressLevel = "HIGH";
            symptoms = "High stress levels. Multiple symptoms affecting daily life.";
        } else {
            stressLevel = "SEVERE";
            symptoms = "Severe stress detected. Immediate intervention recommended.";
        }

        List<StressSolution> solutions = solutionRepository.findByStressLevel(stressLevel);
        String recommendations = solutions.stream()
                .map(s -> "• " + s.getSolutionTitle() + ": " + s.getSolutionDescription())
                .collect(Collectors.joining("\n"));

        StressAssessment assessment = new StressAssessment();
        assessment.setUser(user);
        assessment.setStressScore(stressScore);
        assessment.setStressLevel(stressLevel);
        assessment.setSymptoms(symptoms);
        assessment.setRecommendations(recommendations);

        StressAssessment savedAssessment = assessmentRepository.save(assessment);

        return new StressResultDTO(
                savedAssessment.getAssessmentId(),
                stressScore,
                stressLevel,
                symptoms,
                recommendations
        );
    }

    public List<StressAssessment> getUserHistory(Long userId) throws Exception {
        User user = userService.getUserById(userId);
        return assessmentRepository.findByUserOrderByAssessmentDateDesc(user);
    }

}