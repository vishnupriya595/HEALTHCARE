package com.example.HealthCare.Repository;

import com.example.HealthCare.Model.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface NurseRepository extends JpaRepository<Nurse, Long> {
}
