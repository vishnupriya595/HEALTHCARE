package com.example.HealthCare.Repository;

import com.example.HealthCare.Model.Appointment;
import com.example.HealthCare.Model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription,Long> {

    List<Prescription> findByPatient_PatientId(Long patientId);

}
