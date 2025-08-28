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

public class Nurse {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID nurseId;

    private String nurseName;
    private String shift;
    private String qualification;
    private String email;
    private String phoneNumber;




    @OneToOne
    @JoinColumn(name = "userId")
    private Users users;


}
