package com.datajava.controller;

import com.datajava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        // Logique de déconnexion ici
        return ResponseEntity.ok("Logout successful");
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok("Authentication successful");
    }
}

class LoginRequest {
    public String username;
    public String password;
}
