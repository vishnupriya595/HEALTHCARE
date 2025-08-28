package com.example.HealthCare.Repository;

import com.example.HealthCare.Model.AdminProfile;
import com.example.HealthCare.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository

public interface AdminProfileRepository extends JpaRepository<AdminProfile, UUID> {
    AdminProfile findByUser(Users user);
}
