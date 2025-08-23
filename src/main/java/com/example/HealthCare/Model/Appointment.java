package com.example.HealthCare.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "nurse_id", nullable = true)
    private Nurse nurse;

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
