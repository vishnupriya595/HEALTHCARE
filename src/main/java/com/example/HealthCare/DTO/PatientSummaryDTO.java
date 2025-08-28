package com.example.HealthCare.DTO;

import com.example.HealthCare.DTO.Response.AppointmentDTO;
import com.example.HealthCare.DTO.Response.DoctorResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class PatientSummaryDTO {
    private UUID patientId;
    private String name;
    private int age;
    private String gender;
    private String address;
    private String phone;
    private String disease;

    private DoctorResponseDTO doctor;

    private List<AppointmentDTO> appointments;
    private List<PrescriptionDTO> prescriptions;
    private List<BillingDTO> billings;


}
