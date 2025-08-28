package com.example.HealthCare.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID doctorId;

    private String name;
    private String specialization;
    private int experience;
    private String email;
    private String phoneNumber;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Prescription> prescriptions;


    @OneToOne
    @JoinColumn(name = "userId")
    private Users users;

}
