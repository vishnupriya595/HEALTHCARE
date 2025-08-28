package com.example.HealthCare.DTO.Response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AuthResponse {
    private String token;
    private UUID id;
    private String username;
    private String role;

}
