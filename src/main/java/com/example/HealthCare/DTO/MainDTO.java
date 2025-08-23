package com.example.HealthCare.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class MainDTO {
    private AppointmentDTO appointment;
    private PrescriptionDTO prescription;
    private BillingDTO billing;
}
