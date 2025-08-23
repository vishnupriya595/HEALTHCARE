package com.example.HealthCare.DTO;

import com.example.HealthCare.Model.Appointment;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AppointmentDTO {


    private Long doctorId;
    private Long patientId;

    private Long nurseId;
    private LocalDate appointmentDate;
    private Appointment.Status status;       // BOOKED, RESCHEDULED, CANCELLED, COMPLETED



}

