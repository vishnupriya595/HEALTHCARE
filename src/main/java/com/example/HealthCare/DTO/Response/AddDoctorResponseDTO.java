package com.example.HealthCare.DTO.Response;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddDoctorResponseDTO {
    private UUID doctorId;
    private String doctorName;
    private String specialization;
    private int experience;

}
