package com.example.HealthCare.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class DoctorDashboardDTO {

    private int totalAppointments;
    private int totalPatientsAssigned;
}
