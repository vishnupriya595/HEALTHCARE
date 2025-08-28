package com.example.HealthCare.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID adminId;
    private String name;
    private String email;
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "userId")
    private Users user;
}
