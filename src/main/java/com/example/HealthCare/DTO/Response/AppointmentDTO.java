package com.example.HealthCare.DTO.Response;

import com.example.HealthCare.Model.Appointment;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AppointmentDTO {


    private Long doctorId;
    private Long patientId;
    private LocalDate appointmentDate;
    private Appointment.Status status;



}

