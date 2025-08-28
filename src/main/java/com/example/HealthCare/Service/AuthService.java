package com.example.HealthCare.Service;

import com.example.HealthCare.Config.JWTService;
import com.example.HealthCare.DTO.Response.AuthResponse;
import com.example.HealthCare.DTO.Request.LoginRequest;
import com.example.HealthCare.DTO.Request.RegisterRequest;
import com.example.HealthCare.Model.Users;
import com.example.HealthCare.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTService  jwtService;

    public String Register(RegisterRequest registerRequest){
        Users user2= Users.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(registerRequest.getRole())
                .build();
        userRepository.save(user2);
        return "Admin Registered Successfully";
    }

    public AuthResponse Authenticate(LoginRequest loginRequest){
        Users user1 = userRepository.findByUsername(loginRequest.getUsername());
        if(user1 == null){
            throw new UsernameNotFoundException("Username not found");
        }

        if(!passwordEncoder.matches(loginRequest.getPassword(),user1.getPassword())){
            throw new BadCredentialsException("Incorrect password");
        }

        String token = jwtService.generateToken(user1.getUsername());
        return AuthResponse.builder().token(token)
                .id(user1.getUserId())
                .username(user1.getUsername())
                .role(user1.getRole().name())
                .build();


    }


}
