package com.example.HealthCare.Repository;


import com.example.HealthCare.Model.Appointment;
import com.example.HealthCare.Model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface BillingRepository extends JpaRepository<Billing,Long> {
    List<Billing> findByPatient_PatientId(Long patientId);
}
