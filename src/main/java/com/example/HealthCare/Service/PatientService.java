package com.example.HealthCare.Service;

import com.example.HealthCare.DTO.*;
import com.example.HealthCare.DTO.Response.BillingResponseDTO;
import com.example.HealthCare.DTO.Response.DoctorDetailsResDTO;
import com.example.HealthCare.DTO.Response.PatientResponseDTO;
import com.example.HealthCare.Model.Appointment;
import com.example.HealthCare.Model.Doctor;
import com.example.HealthCare.Model.Patient;
import com.example.HealthCare.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class PatientService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final BillingRepository billingRepository;


    public List<PatientAppointmentDTO> getUpcomingAppointments(UUID patientId, LocalDate fromDate) {
        return appointmentRepository.findUpcomingAppointments(patientId, fromDate)
                .stream()
                .map(a -> PatientAppointmentDTO.builder()
                        .AppointmentId(a.getAppointmentId())
                        .appointmentDate(a.getAppointmentDate())
                        .status(a.getStatus())
                        .doctorId(a.getDoctor().getDoctorId())
                        .doctorName(a.getDoctor().getName())
                        .build())
                .collect(Collectors.toList());
    }

    public List<PatientPrescriptionDTO> getPrescriptionsByPatient(UUID patientId) {
        return prescriptionRepository.findByPatient_PatientId(patientId)
                .stream()
                .map(p -> PatientPrescriptionDTO.builder()
                        .prescriptionId(p.getPrescriptionId())
                        .medicineName(p.getMedicineName())
                        .dosage(p.getDosage())
                        .instructions(p.getInstructions())
                        .doctorId(p.getDoctor().getDoctorId())
                        .doctorName(p.getDoctor().getName())
                        .patientId(p.getPatient().getPatientId())
                        .build())
                .collect(Collectors.toList());
    }



    // Cancel Appointment
    public PatientAppointmentDTO cancelAppointment(UUID appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setStatus(Appointment.Status.CANCELLED);
        Appointment updated = appointmentRepository.save(appointment);

        return mapToDTO(updated);
    }

    // Reschedule Appointment
    public PatientAppointmentDTO rescheduleAppointment(UUID appointmentId, LocalDate newDate) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setAppointmentDate(newDate);
        appointment.setStatus(Appointment.Status.RESCHEDULED);
        Appointment updated = appointmentRepository.save(appointment);

        return mapToDTO(updated);
    }

    // View All Appointments for a Patient
    public List<PatientAppointmentDTO> getAppointmentsByPatient(UUID patientId) {
        return appointmentRepository.findByPatient_PatientId(patientId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private PatientAppointmentDTO mapToDTO(Appointment a) {
        PatientAppointmentDTO dto = new PatientAppointmentDTO();
        dto.setAppointmentId(a.getAppointmentId());
        dto.setAppointmentDate(a.getAppointmentDate());
        dto.setStatus(a.getStatus());
        dto.setDoctorId(a.getDoctor().getDoctorId());
        dto.setDoctorName(a.getDoctor().getName());
//        dto.setPatientId(a.getPatient().getPatientId());
        return dto;
    }

    public List<BillingResponseDTO> getBillingByPatientId(UUID patientId) {
        return billingRepository.findByPatient_PatientId(patientId)
                .stream()
                .map(billing -> BillingResponseDTO.builder()
                        .billingId(billing.getBillingId())
                        .patientId(billing.getPatient().getPatientId())
                        .patientName(billing.getPatient().getName())
                        .amount(billing.getAmount())
                        .paymentStatus(billing.getPaymentStatus())
                        .paymentDate(billing.getPaymentDate())
                        .build())
                .collect(Collectors.toList());
    }

    public List<DoctorDetailsResDTO> getAllDoctorDetails() {
        return doctorRepository.findAll().stream().map(doctor -> DoctorDetailsResDTO.builder()
                .doctorName(doctor.getName())
                .specialization(doctor.getSpecialization())
                .build()).collect(Collectors.toList());
    }

    public String getPatientBookRequest(UUID patientId,String doctorName,LocalDate appointmentDate) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + patientId));

        Doctor doctor = doctorRepository.findByName(doctorName);

        Appointment appointment = Appointment.builder()
                .doctor(doctor)
                .patient(patient)
                .status(Appointment.Status.BOOKED)
                .appointmentDate(appointmentDate)
                .build();
        appointmentRepository.save(appointment);
        return "Appointment Scheduled!!!";
    }

    public PatientResponseDTO getPatientProfile(UUID patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + patientId));

        return PatientResponseDTO.builder()
                .patientId(patient.getPatientId())
                .name(patient.getName())
                .age(patient.getAge())
                .gender(patient.getGender())
                .address(patient.getAddress())
                .phone(patient.getPhone())
                .disease(patient.getDisease())
                .build();
    }
}
