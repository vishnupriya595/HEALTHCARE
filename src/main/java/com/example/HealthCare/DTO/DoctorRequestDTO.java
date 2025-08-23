package com.example.HealthCare.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DoctorRequestDTO {
    private String doctorName;
    private String specialization;
    private int experience;
    private String doctorUsername;
    private String doctorPassword;
    private String doctorRole;
}
