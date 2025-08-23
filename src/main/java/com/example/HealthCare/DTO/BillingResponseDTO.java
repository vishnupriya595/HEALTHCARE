package com.example.HealthCare.DTO;

import com.example.HealthCare.Model.Billing;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder

public class BillingResponseDTO {
    private Long billingId;
    private Long patientId;
    private String patientName;
    private Double amount;
    private Billing.PaymentStatus paymentStatus;  // e.g., Paid / Pending
    private LocalDate paymentDate;
}
