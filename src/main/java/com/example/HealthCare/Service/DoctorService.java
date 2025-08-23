package com.example.HealthCare.Service;

import com.example.HealthCare.DTO.DoctorDashboardDTO;
import com.example.HealthCare.Repository.AppointmentRepository;
import com.example.HealthCare.Repository.DoctorRepository;
import com.example.HealthCare.Repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor

public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;

    public DoctorDashboardDTO getDoctorDashboard(Long doctorId) {
        String today = LocalDate.now().toString(); // "2025-08-23"
        int todayAppointments = appointmentRepository.countTodayAppointments(doctorId, LocalDate.parse(today));
        int totalPatientsAssigned = patientRepository.countPatientsAssigned(doctorId);

        return DoctorDashboardDTO.builder()
                .totalAppointments(todayAppointments)
                .totalPatientsAssigned(totalPatientsAssigned)
                .build();
    }


}
