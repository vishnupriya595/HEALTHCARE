package com.example.HealthCare.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"patients"})
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        ADMIN, DOCTOR, NURSE, PATIENT
    }

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private Doctor doctor;

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private Patient patient;

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private Nurse nurse;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private AdminProfile adminProfile;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Report> report;




}
