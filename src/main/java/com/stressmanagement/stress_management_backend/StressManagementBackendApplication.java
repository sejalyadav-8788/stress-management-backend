package com.stressmanagement.stress_management_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.stressmanagement.stress_management_backend",
        "com.stressmanagement.config",
        "com.stressmanagement.controller",
        "com.stressmanagement.service"
})
@EntityScan(basePackages = "com.stressmanagement.model")
@EnableJpaRepositories(basePackages = "com.stressmanagement.repository")
public class StressManagementBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(StressManagementBackendApplication.class, args);
        System.out.println("==================================================");
        System.out.println("Stress Management Backend Started Successfully!");
        System.out.println("Server running at: http://localhost:8080");
        System.out.println("==================================================");
    }
}