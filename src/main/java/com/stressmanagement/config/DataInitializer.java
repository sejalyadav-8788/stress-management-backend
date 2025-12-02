package com.stressmanagement.config;

import com.stressmanagement.model.StressSolution;
import com.stressmanagement.repository.StressSolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private StressSolutionRepository solutionRepository;

    @Override
    public void run(String... args) throws Exception {
        if (solutionRepository.count() > 0) {
            return;
        }

        // LOW level solutions
        solutionRepository.save(new StressSolution(null, "LOW",
                "Regular Exercise",
                "Engage in at least 30 minutes of physical activity daily. This can include walking, jogging, yoga, or any activity you enjoy.",
                "EXERCISE"));

        solutionRepository.save(new StressSolution(null, "LOW",
                "Maintain Sleep Schedule",
                "Aim for 7-8 hours of quality sleep each night. Keep a consistent sleep and wake time.",
                "SLEEP"));

        solutionRepository.save(new StressSolution(null, "LOW",
                "Practice Mindfulness",
                "Spend 10 minutes daily on meditation or mindfulness exercises to stay grounded.",
                "MEDITATION"));

        // MEDIUM level solutions
        solutionRepository.save(new StressSolution(null, "MEDIUM",
                "Deep Breathing Exercises",
                "Practice 4-7-8 breathing: Inhale for 4 counts, hold for 7, exhale for 8. Repeat 3-4 times when feeling stressed.",
                "BREATHING"));

        solutionRepository.save(new StressSolution(null, "MEDIUM",
                "Time Management",
                "Use techniques like the Pomodoro method. Break tasks into 25-minute focused sessions with 5-minute breaks.",
                "PRODUCTIVITY"));

        solutionRepository.save(new StressSolution(null, "MEDIUM",
                "Social Connection",
                "Connect with friends or family at least 2-3 times a week. Social support is crucial for mental health.",
                "SOCIAL"));

        solutionRepository.save(new StressSolution(null, "MEDIUM",
                "Limit Caffeine and Alcohol",
                "Reduce intake of stimulants and depressants that can worsen anxiety and disrupt sleep patterns.",
                "LIFESTYLE"));

        // HIGH level solutions
        solutionRepository.save(new StressSolution(null, "HIGH",
                "Progressive Muscle Relaxation",
                "Systematically tense and relax different muscle groups. Start from toes and work up to your head.",
                "RELAXATION"));

        solutionRepository.save(new StressSolution(null, "HIGH",
                "Journaling",
                "Write down your thoughts and feelings for 15-20 minutes daily. This helps process emotions and identify stress triggers.",
                "THERAPY"));

        solutionRepository.save(new StressSolution(null, "HIGH",
                "Professional Counseling",
                "Consider speaking with a therapist or counselor. Professional guidance can provide valuable coping strategies.",
                "THERAPY"));

        solutionRepository.save(new StressSolution(null, "HIGH",
                "Reduce Workload",
                "Evaluate your commitments and learn to say no. Prioritize essential tasks and delegate when possible.",
                "PRODUCTIVITY"));

        // SEVERE level solutions
        solutionRepository.save(new StressSolution(null, "SEVERE",
                "Seek Immediate Professional Help",
                "Contact a mental health professional or crisis helpline immediately. Your well-being is the top priority.",
                "THERAPY"));

        solutionRepository.save(new StressSolution(null, "SEVERE",
                "Consider Medication",
                "Consult with a psychiatrist about whether medication might be helpful alongside therapy.",
                "THERAPY"));

        solutionRepository.save(new StressSolution(null, "SEVERE",
                "Take Medical Leave",
                "If possible, take time off work or school to focus on recovery. Your health comes first.",
                "LIFESTYLE"));

        solutionRepository.save(new StressSolution(null, "SEVERE",
                "Build a Support Network",
                "Inform trusted friends or family about your situation. Having a support system is crucial during difficult times.",
                "SOCIAL"));

        solutionRepository.save(new StressSolution(null, "SEVERE",
                "Emergency Contacts",
                "Keep emergency mental health hotline numbers accessible: National Suicide Prevention Lifeline: 988",
                "EMERGENCY"));

        System.out.println("Stress solutions initialized successfully!");
    }
}