package com.stressmanagement.service;

import com.stressmanagement.dto.LoginDTO;
import com.stressmanagement.dto.UserRegistrationDTO;
import com.stressmanagement.model.User;
import com.stressmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User registerUser(UserRegistrationDTO dto) {
        System.out.println("========================================");
        System.out.println("REGISTRATION ATTEMPT");
        System.out.println("Name: " + dto.getName());
        System.out.println("Email: " + dto.getEmail());
        System.out.println("========================================");

        // Validate input
        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            System.out.println("ERROR: Name is required");
            throw new RuntimeException("Name is required");
        }

        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty()) {
            System.out.println("ERROR: Email is required");
            throw new RuntimeException("Email is required");
        }

        if (dto.getPassword() == null || dto.getPassword().length() < 6) {
            System.out.println("ERROR: Password must be at least 6 characters");
            throw new RuntimeException("Password must be at least 6 characters");
        }

        // Check if email already exists
        if (userRepository.existsByEmail(dto.getEmail())) {
            System.out.println("ERROR: Email already exists: " + dto.getEmail());
            throw new RuntimeException("Email already exists");
        }

        try {
            // Create new user
            User user = new User();
            user.setUsername(dto.getName());
            user.setEmail(dto.getEmail());
            user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
            user.setCreatedAt(LocalDateTime.now());

            System.out.println("Creating user:");
            System.out.println("  Username: " + user.getUsername());
            System.out.println("  Email: " + user.getEmail());
            System.out.println("  Password Hash Length: " + user.getPasswordHash().length());

            // Save user
            User savedUser = userRepository.save(user);

            System.out.println("USER REGISTERED SUCCESSFULLY!");
            System.out.println("  User ID: " + savedUser.getUserId());
            System.out.println("  Username: " + savedUser.getUsername());
            System.out.println("  Email: " + savedUser.getEmail());
            System.out.println("========================================");

            return savedUser;

        } catch (Exception e) {
            System.out.println("ERROR during user registration:");
            System.out.println("  Error message: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to register user: " + e.getMessage());
        }
    }

    @Transactional
    public User loginUser(LoginDTO dto) {
        System.out.println("========================================");
        System.out.println("LOGIN ATTEMPT");
        System.out.println("Email: " + dto.getEmail());
        System.out.println("========================================");

        // Validate input
        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty()) {
            System.out.println("ERROR: Email is required");
            throw new RuntimeException("Email is required");
        }

        if (dto.getPassword() == null || dto.getPassword().isEmpty()) {
            System.out.println("ERROR: Password is required");
            throw new RuntimeException("Password is required");
        }

        try {
            // Find user by email
            User user = userRepository.findByEmail(dto.getEmail())
                    .orElseThrow(() -> {
                        System.out.println("ERROR: User not found with email: " + dto.getEmail());
                        return new RuntimeException("Invalid email or password");
                    });

            System.out.println("User found!");
            System.out.println("  User ID: " + user.getUserId());
            System.out.println("  Username: " + user.getUsername());
            System.out.println("  Email: " + user.getEmail());
            System.out.println("Verifying password...");

            // Verify password
            boolean passwordMatches = passwordEncoder.matches(dto.getPassword(), user.getPasswordHash());

            if (!passwordMatches) {
                System.out.println("ERROR: Password does not match!");
                throw new RuntimeException("Invalid email or password");
            }

            System.out.println("✅ LOGIN SUCCESSFUL!");
            System.out.println("========================================");

            return user;

        } catch (Exception e) {
            System.out.println("ERROR during login:");
            System.out.println("  Error message: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public User getUserById(Long userId) {
        System.out.println("Fetching user by ID: " + userId);
        return userRepository.findById(userId)
                .orElseThrow(() -> {
                    System.out.println("ERROR: User not found with ID: " + userId);
                    return new RuntimeException("User not found with ID: " + userId);
                });
    }

    public User getUserByEmail(String email) {
        System.out.println("Fetching user by email: " + email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    System.out.println("ERROR: User not found with email: " + email);
                    return new RuntimeException("User not found with email: " + email);
                });
    }
}