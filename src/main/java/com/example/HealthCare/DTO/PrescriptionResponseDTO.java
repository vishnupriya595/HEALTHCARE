package com.example.HealthCare.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionResponseDTO {
        private Long prescriptionId;
        private Long patientId;
        private Long doctorId;
        private String medicineName;
        private String dosage;
        private String instructions;
    }

