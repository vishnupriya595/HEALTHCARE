package com.example.HealthCare.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UpdatePatientReqDTO {
    private String name;
    private int age;
    private String gender;
    private String address;
    private String phone;
    private String disease;
}
