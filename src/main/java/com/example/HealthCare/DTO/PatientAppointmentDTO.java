package com.example.HealthCare.DTO;

import com.example.HealthCare.Model.Appointment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PatientAppointmentDTO {
    private Long AppointmentId;
    private LocalDate appointmentDate;
    private Appointment.Status status;
    private Long doctorId;
    private String doctorName;
}
