package com.example.HealthCare.Repository;

import com.example.HealthCare.Model.Appointment;
import com.example.HealthCare.Model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, UUID> {

    List<Prescription> findByPatient_PatientId(UUID patientId);

}
