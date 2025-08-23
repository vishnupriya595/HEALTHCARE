package com.example.HealthCare.DTO;

import com.example.HealthCare.Model.Doctor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class PrescriptionDTO {
    private long doctorId;
    private String medicineName;
    private String dosage;
    private String instructions;
}
