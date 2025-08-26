package com.example.HealthCare.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDoctorResponseDTO {
    private Long appointmentId;
    private Long patientId;
    private String patientName;
    private LocalDate appointmentDate;
    private String status;
}
