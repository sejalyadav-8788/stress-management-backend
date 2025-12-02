package com.stressmanagement.repository;

import com.stressmanagement.model.StressAssessment;
import com.stressmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StressAssessmentRepository extends JpaRepository<StressAssessment, Long> {

    List<StressAssessment> findByUserOrderByAssessmentDateDesc(User user);

    List<StressAssessment> findByUser_UserIdOrderByAssessmentDateDesc(Long userId);
}