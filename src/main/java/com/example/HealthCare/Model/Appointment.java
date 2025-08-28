package com.example.HealthCare.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID appointmentId;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private LocalDate appointmentDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    private Prescription prescription;

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    private Billing bill;

    public enum Status {
        BOOKED, RESCHEDULED, CANCELLED, COMPLETED
    }


}
