package com.example.HealthCare.DTO.Response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder

public class NurseResponseDTO {
    private UUID nurseId;
    private String nurseName;
    private String shift;
    private String qualification;

}
