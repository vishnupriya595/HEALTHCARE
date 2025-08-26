package com.example.HealthCare.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"users"})
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;

    private String name;
    private int age;
    private String gender;
    private String address;
    private String phone;
    private String disease;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Appointment> appointments;


    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Prescription> prescriptions;



    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Billing> bills;

    @OneToOne
    @JoinColumn(name = "userId")
    private Users users;


}
