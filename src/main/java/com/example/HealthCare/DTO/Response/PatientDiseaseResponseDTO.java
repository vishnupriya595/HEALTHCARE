package com.example.HealthCare.DTO.Response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PatientDiseaseResponseDTO {

    private String patientName;
    private String disease;
}
