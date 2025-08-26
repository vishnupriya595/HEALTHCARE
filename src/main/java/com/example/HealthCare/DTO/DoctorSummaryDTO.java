package com.example.HealthCare.DTO;

import com.example.HealthCare.DTO.Response.PatientResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DoctorSummaryDTO {
    private Long doctorId;
    private String doctorName;
    private String specialization;
    private int experience;
    private List<PatientResponseDTO> patients;
}
