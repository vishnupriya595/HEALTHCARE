package com.example.HealthCare.DTO;

import com.example.HealthCare.Model.Billing;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BillingDTO {
    private Double amount;
    private Billing.PaymentStatus paymentStatus;
    private LocalDate paymentDate;
}
