package com.example.HealthCare.Controller;

import com.example.HealthCare.DTO.Response.AuthResponse;
import com.example.HealthCare.DTO.Request.LoginRequest;
import com.example.HealthCare.DTO.Request.RegisterRequest;
import com.example.HealthCare.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<String > register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authService.Register(registerRequest));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        System.out.println("Login Request");
        return ResponseEntity.ok(authService.Authenticate(loginRequest));

    }

}
