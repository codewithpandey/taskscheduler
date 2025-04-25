package com.taskscheduler.auth.controller;

import com.taskscheduler.auth.dto.AuthRequest;
import com.taskscheduler.auth.dto.AuthResponse;
import com.taskscheduler.auth.service.AuthService;
import com.taskscheduler.auth.util.LoggerUtil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
        service.register(request);
        return ResponseEntity.ok("User registered");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        LoggerUtil.info(AuthController.class, "Login attempt for username: " + request.getUsername());
        String token = service.login(request);
        // return ResponseEntity.ok(new AuthResponse(token));
        if (token != null) {
            LoggerUtil.info(AuthController.class, "Login successful, token: " + token);
            return ResponseEntity.ok(new AuthResponse(token));
        } else {
            LoggerUtil.error(AuthController.class, "Login failed for username: " + request.getUsername(), new Exception("Invalid credentials"));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
