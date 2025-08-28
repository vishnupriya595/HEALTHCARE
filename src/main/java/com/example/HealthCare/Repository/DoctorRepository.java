package com.example.HealthCare.Repository;

import com.example.HealthCare.Model.Doctor;
import com.example.HealthCare.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
   Doctor findByName(String doctorName);

    Doctor findByUsers(Users user);
}
