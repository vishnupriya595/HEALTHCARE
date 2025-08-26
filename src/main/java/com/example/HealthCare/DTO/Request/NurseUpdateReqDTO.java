package com.example.HealthCare.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class NurseUpdateReqDTO {
    private String nurseName;
    private String shift;
    private String qualification;
}
