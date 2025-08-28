package com.example.HealthCare.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionResponseDTO {
        private UUID prescriptionId;
        private UUID patientId;
        private UUID doctorId;
        private String medicineName;
        private String dosage;
        private String instructions;
    }

