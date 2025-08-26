package com.example.HealthCare.DTO.Response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddDoctorResponseDTO {
    private Long doctorId;
    private String doctorName;
    private String specialization;
    private int experience;

}
