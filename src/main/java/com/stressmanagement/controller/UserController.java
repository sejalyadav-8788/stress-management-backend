package com.stressmanagement.controller;

import com.stressmanagement.dto.ApiResponseDTO;
import com.stressmanagement.dto.AuthResponseDTO;
import com.stressmanagement.dto.LoginDTO;
import com.stressmanagement.dto.UserRegistrationDTO;
import com.stressmanagement.model.User;
import com.stressmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDTO dto) {
        try {
            System.out.println("========================================");
            System.out.println("Received Registration DTO:");
            System.out.println("Name: " + dto.getName());
            System.out.println("Email: " + dto.getEmail());
            System.out.println("Password: " + (dto.getPassword() != null ? "***" : "NULL"));
            System.out.println("========================================");

            // Check if name is null or empty
            if (dto.getName() == null || dto.getName().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponseDTO(false, "Name is required", null));
            }

            User user = userService.registerUser(dto);

            // Create response
            AuthResponseDTO authResponse = new AuthResponseDTO();
            authResponse.setToken("Bearer_" + user.getUserId() + "_" + System.currentTimeMillis());
            authResponse.setId(user.getUserId());
            authResponse.setName(user.getUsername());
            authResponse.setEmail(user.getEmail());
            authResponse.setRole("USER");

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponseDTO(true, "Registration successful", authResponse));

        } catch (Exception e) {
            System.out.println("========================================");
            System.out.println("Registration Error: " + e.getMessage());
            e.printStackTrace();
            System.out.println("========================================");

            return ResponseEntity.badRequest()
                    .body(new ApiResponseDTO(false, e.getMessage(), null));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO dto) {
        try {
            User user = userService.loginUser(dto);

            // Create response matching frontend expectations
            AuthResponseDTO authResponse = new AuthResponseDTO();
            authResponse.setToken("Bearer_" + user.getUserId() + "_" + System.currentTimeMillis());
            authResponse.setId(user.getUserId());
            authResponse.setName(user.getUsername());
            authResponse.setEmail(user.getEmail());
            authResponse.setRole("USER");

            return ResponseEntity.ok(new ApiResponseDTO(true, "Login successful", authResponse));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponseDTO(false, e.getMessage(), null));
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        try {
            User user = userService.getUserById(userId);
            return ResponseEntity.ok(new ApiResponseDTO(true, "User found", user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponseDTO(false, e.getMessage(), null));
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("✅ Backend Auth API is working!");
    }

}