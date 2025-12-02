package com.stressmanagement.repository;

import com.stressmanagement.model.StressSolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StressSolutionRepository extends JpaRepository<StressSolution, Long> {

    List<StressSolution> findByStressLevel(String stressLevel);

    List<StressSolution> findByCategory(String category);
}