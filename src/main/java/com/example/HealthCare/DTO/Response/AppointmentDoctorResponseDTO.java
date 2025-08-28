package com.example.HealthCare.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDoctorResponseDTO {
    private UUID appointmentId;
    private UUID patientId;
    private String patientName;
    private LocalDate appointmentDate;
    private String status;
}
