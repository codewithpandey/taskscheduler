package com.taskscheduler.auth.service;

import com.taskscheduler.auth.dto.AuthRequest;
import com.taskscheduler.auth.model.User;
import com.taskscheduler.auth.repository.UserRepository;
import com.taskscheduler.auth.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository repo, PasswordEncoder encoder, JwtUtil jwtUtil) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    public void register(AuthRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        repo.save(user);
    }

    public String login(AuthRequest request) {
        User user = repo.findByUsername(request.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));
        if (encoder.matches(request.getPassword(), user.getPassword())) {
            return jwtUtil.generateToken(user.getUsername());
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
