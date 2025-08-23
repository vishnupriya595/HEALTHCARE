package com.example.HealthCare.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class NurseResponseDTO {
    private long nurseId;
    private String nurseName;
    private String shift;
    private String qualification;

}
