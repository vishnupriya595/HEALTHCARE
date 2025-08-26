package com.example.HealthCare.DTO.Request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PatientRequestDTO {
    private String name;
    private int age;
    private String gender;
    private String address;
    private String phone;
    private String disease;

    private String patientUsername;
    private String patientPassword;
    private String patientRole;
}
