package com.stressmanagement.controller;

import com.stressmanagement.dto.ApiResponseDTO;
import com.stressmanagement.dto.StressAssessmentDTO;
import com.stressmanagement.dto.StressResultDTO;
import com.stressmanagement.model.StressAssessment;
import com.stressmanagement.service.StressAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stress")
@CrossOrigin(origins = "*")
public class StressAssessmentController {

    @Autowired
    private StressAssessmentService stressAssessmentService;

    @PostMapping("/assess")
    public ResponseEntity<?> submitAssessment(@RequestBody StressAssessmentDTO dto) {
        try {StressResultDTO result = stressAssessmentService.calculateStressLevel(dto);
            return ResponseEntity.ok(new ApiResponseDTO(true, "Assessment completed", result));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponseDTO(false, e.getMessage(), null));
        }
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<?> getUserHistory(@PathVariable Long userId) {
        try {
            List<StressAssessment> history = stressAssessmentService.getUserHistory(userId);
            return ResponseEntity.ok(new ApiResponseDTO(true, "History retrieved", history));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponseDTO(false, e.getMessage(), null));
        }
    }

}