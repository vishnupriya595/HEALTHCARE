package com.example.HealthCare.Controller;

import com.example.HealthCare.DTO.AuthResponse;
import com.example.HealthCare.DTO.LoginRequest;
import com.example.HealthCare.DTO.RegisterRequest;
import com.example.HealthCare.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String > register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authService.Register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.Authenticate(loginRequest));
    }

}
