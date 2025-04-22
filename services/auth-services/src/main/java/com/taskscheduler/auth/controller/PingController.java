package com.taskscheduler.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class PingController {

    @GetMapping("/ping")
    public String ping() {
        return "Auth Service is up 🚀";
    }
}
