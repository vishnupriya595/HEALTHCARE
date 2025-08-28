package com.example.HealthCare.DTO;

import com.example.HealthCare.Model.Appointment;
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

public class PatientAppointmentDTO {
    private UUID AppointmentId;
    private LocalDate appointmentDate;
    private Appointment.Status status;
    private UUID doctorId;
    private String doctorName;
}
