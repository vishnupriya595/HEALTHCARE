package com.example.HealthCare.Repository;

import com.example.HealthCare.Model.Nurse;
import com.example.HealthCare.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface NurseRepository extends JpaRepository<Nurse, Long> {
    Nurse findByUsers(Users users);
}
