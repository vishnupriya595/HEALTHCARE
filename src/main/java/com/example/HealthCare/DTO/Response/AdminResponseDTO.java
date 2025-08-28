package com.example.HealthCare.DTO.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class AdminResponseDTO {
    private String name;
    private String email;
    private String phone;
}
