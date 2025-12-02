package com.stressmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stress_solutions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StressSolution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long solutionId;

    @Column(nullable = false)
    private String stressLevel;

    @Column(nullable = false)
    private String solutionTitle;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String solutionDescription;

    @Column(nullable = false)
    private String category;
}