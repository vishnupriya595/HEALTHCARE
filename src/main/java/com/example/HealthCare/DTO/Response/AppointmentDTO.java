package com.example.HealthCare.DTO.Response;

import com.example.HealthCare.Model.Appointment;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class AppointmentDTO {


    private UUID doctorId;
    private UUID patientId;
    private LocalDate appointmentDate;
    private Appointment.Status status;



}

