package com.example.HealthCare.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class PatientPrescriptionDTO {
    private Long prescriptionId;
    private String medicineName;
    private String dosage;
    private String instructions;
    private Long doctorId;
    private String doctorName;   // extra for patient readability
    private Long patientId;
}
