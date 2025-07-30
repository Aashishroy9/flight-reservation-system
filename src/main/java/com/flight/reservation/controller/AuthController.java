package com.flight.reservation.controller;

import com.flight.reservation.dto.AuthResponse;
import com.flight.reservation.dto.LoginRequest;
import com.flight.reservation.dto.RegisterRequest;
import com.flight.reservation.security.JwtUtil;
import com.flight.reservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        String message = userService.registerUser(request); // Save user in DB
        return ResponseEntity.ok(message);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {

        // Authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), loginRequest.getPassword()
                )
        );

        // Generate token
        String token = jwtUtil.generateToken(loginRequest.getEmail());

        // Return token
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
