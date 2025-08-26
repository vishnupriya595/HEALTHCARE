package com.example.HealthCare.DTO.Request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class NurseProfileReqDTO {
    private String email;
    private String phoneNumber;

}
