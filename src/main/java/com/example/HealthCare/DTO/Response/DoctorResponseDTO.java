package com.example.HealthCare.DTO.Response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder

public class DoctorResponseDTO {

    private UUID doctorId;
    private String doctorName;
    private String specialization;
    private int experience;

}
