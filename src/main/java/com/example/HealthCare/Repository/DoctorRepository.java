package com.example.HealthCare.Repository;

import com.example.HealthCare.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
   Doctor findByName(String doctorName);
}
