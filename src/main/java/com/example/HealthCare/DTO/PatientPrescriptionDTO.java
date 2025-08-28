package com.example.HealthCare.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder

public class PatientPrescriptionDTO {
    private UUID prescriptionId;
    private String medicineName;
    private String dosage;
    private String instructions;
    private UUID doctorId;
    private String doctorName;   // extra for patient readability
    private UUID patientId;
}
