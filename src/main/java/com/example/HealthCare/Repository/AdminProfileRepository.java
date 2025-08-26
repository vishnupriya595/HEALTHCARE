package com.example.HealthCare.Repository;

import com.example.HealthCare.Model.AdminProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AdminProfileRepository extends JpaRepository<AdminProfile, Long> {
}
