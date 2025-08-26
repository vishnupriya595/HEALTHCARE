package com.example.HealthCare.DTO.Response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder

public class AppointmentResponseDTO {
    private Long appointmentId;
    private LocalDate appointmentDate;
    private String status;
    private Long doctorId;
    private String doctorName;
    private Long patientId;
    private String patientName;
}
