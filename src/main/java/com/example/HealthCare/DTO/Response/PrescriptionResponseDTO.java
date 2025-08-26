package com.example.HealthCare.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

