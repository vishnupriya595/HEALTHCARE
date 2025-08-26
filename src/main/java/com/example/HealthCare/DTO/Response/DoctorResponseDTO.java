package com.example.HealthCare.DTO.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class DoctorResponseDTO {

    private Long doctorId;
    private String doctorName;
    private String specialization;
    private int experience;

}
