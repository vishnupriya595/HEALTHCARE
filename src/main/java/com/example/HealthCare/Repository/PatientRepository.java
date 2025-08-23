package com.example.HealthCare.Repository;

import com.example.HealthCare.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT COUNT(DISTINCT p) FROM Patient p WHERE p.doctor.doctorId = :doctorId")
    int countPatientsAssigned(@Param("doctorId") Long doctorId);
}
