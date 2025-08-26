package com.example.HealthCare.DTO.Request;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class AdminReqDTO {
    private String name;
    private String email;
    private String phoneNumber;
}
