package com.example.HealthCare.DTO;

import com.example.HealthCare.Model.Doctor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder

public class PrescriptionDTO {
    private UUID doctorId;
    private String medicineName;
    private String dosage;
    private String instructions;
}
