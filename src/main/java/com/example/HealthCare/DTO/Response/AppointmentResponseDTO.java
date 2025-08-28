package com.example.HealthCare.DTO.Response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder

public class AppointmentResponseDTO {
    private UUID appointmentId;
    private LocalDate appointmentDate;
    private String status;
    private UUID doctorId;
    private String doctorName;
    private UUID patientId;
    private String patientName;
}
