package com.example.HealthCare.DTO.Request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NurseRequestDTO {
    private String nurseName;
    private String shift;
    private String qualification;

    private String nurseUsername;
    private String nursePassword;
    private String nurseRole;
}
