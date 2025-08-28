package com.example.HealthCare.Repository;


import com.example.HealthCare.Model.Appointment;
import com.example.HealthCare.Model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository

public interface BillingRepository extends JpaRepository<Billing, UUID> {
    List<Billing> findByPatient_PatientId(UUID patientId);
}
