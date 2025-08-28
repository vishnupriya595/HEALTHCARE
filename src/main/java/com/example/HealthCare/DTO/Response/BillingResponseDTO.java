package com.example.HealthCare.DTO.Response;

import com.example.HealthCare.Model.Billing;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder

public class BillingResponseDTO {
    private UUID billingId;
    private UUID patientId;
    private String patientName;
    private Double amount;
    private Billing.PaymentStatus paymentStatus;  // e.g., Paid / Pending
    private LocalDate paymentDate;
}
