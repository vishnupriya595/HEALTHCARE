package com.example.HealthCare.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class PatientResponseDTO {
    private UUID patientId;
    private String name;
    private int age;
    private String gender;
    private String address;
    private String phone;
    private String disease;
}
