package com.example.HealthCare.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder


public class PatientResponseDTO {
    private long patientId;
    private String name;
    private int age;
    private String gender;
    private String address;
    private String phone;
    private String disease;
}
