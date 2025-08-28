package com.example.HealthCare.Repository;

import com.example.HealthCare.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository

public interface UserRepository extends JpaRepository<Users, UUID> {

    Users findByUsername(String username);
}
