package com.example.HealthCare.DTO;

import com.example.HealthCare.DTO.Response.AppointmentDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class MainDTO {
    private AppointmentDTO appointment;
    private PrescriptionDTO prescription;
    private BillingDTO billing;
}
