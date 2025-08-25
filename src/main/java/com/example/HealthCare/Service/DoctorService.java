package com.example.HealthCare.Service;

import com.example.HealthCare.DTO.*;
import com.example.HealthCare.Model.Appointment;
import com.example.HealthCare.Model.Doctor;
import com.example.HealthCare.Model.Patient;
import com.example.HealthCare.Model.Prescription;
import com.example.HealthCare.Repository.AppointmentRepository;
import com.example.HealthCare.Repository.DoctorRepository;
import com.example.HealthCare.Repository.PatientRepository;
import com.example.HealthCare.Repository.PrescriptionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final PrescriptionRepository prescriptionRepository;



    public List<PatientDiseaseResponseDTO> listPatientsAssigned(Long doctorId) {
        doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found"));

        return appointmentRepository.findDistinctPatientsByDoctorId(doctorId)
                .stream()
                .map(p -> PatientDiseaseResponseDTO.builder()
                        .patientName(p.getName())
                        .disease(p.getDisease())
                        .build())
                .collect(Collectors.toList());
    }


    public PatientResponseDTO getPatientDetails(Long doctorId, Long patientId) {


        Patient p = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));

        return PatientResponseDTO.builder()
                .patientId(p.getPatientId())
                .name(p.getName())
                .age(p.getAge())
                .gender(p.getGender())
                .address(p.getAddress())
                .phone(p.getPhone())
                .disease(p.getDisease())
                .build();
    }

    @Transactional
    public PrescriptionResponseDTO addPrescription(Long doctorId, Long patientId, PrescriptionDTO req) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found"));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));



        Prescription entity = new Prescription();
        entity.setDoctor(doctor);
        entity.setPatient(patient);
        entity.setMedicineName(req.getMedicineName());
        entity.setDosage(req.getDosage());
        entity.setInstructions(req.getInstructions());


        Prescription saved = prescriptionRepository.save(entity);

        return PrescriptionResponseDTO.builder()
                .prescriptionId(saved.getPrescriptionId())
                .patientId(patientId)
                .doctorId(doctorId)
                .medicineName(saved.getMedicineName())
                .dosage(saved.getDosage())
                .instructions(saved.getInstructions())
                .build();
    }

    // Get all upcoming appointments for doctor
    public List<AppointmentDoctorResponseDTO> getUpcomingAppointments(Long doctorId) {
        return appointmentRepository.findByDoctorDoctorIdAndAppointmentDateAfter(doctorId, LocalDate.now())
                .stream()
                .map(appt -> AppointmentDoctorResponseDTO.builder()
                        .appointmentId(appt.getAppointmentId())
                        .patientId(appt.getPatient().getPatientId())
                        .patientName(appt.getPatient().getName())
                        .appointmentDate(appt.getAppointmentDate())
                        .status(appt.getStatus().name())
                        .build())
                .collect(Collectors.toList());
    }

    // Reschedule appointment (if allowed)
    public AppointmentDoctorResponseDTO rescheduleAppointment(Long doctorId, Long appointmentId, AppointmentDoctorReqDTO req) {
        Appointment appointment = appointmentRepository.findByDoctorDoctorIdAndAppointmentId(doctorId,appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        if (!appointment.getStatus().equals(Appointment.Status.BOOKED)) {
            throw new RuntimeException("Only scheduled appointments can be rescheduled");
        }

        appointment.setAppointmentDate(req.getNewDate());
        appointment.setStatus(Appointment.Status.RESCHEDULED);

        appointmentRepository.save(appointment);

        return AppointmentDoctorResponseDTO.builder()
                .appointmentId(appointment.getAppointmentId())
                .patientId(appointment.getPatient().getPatientId())
                .patientName(appointment.getPatient().getName())
                .appointmentDate(appointment.getAppointmentDate())
                .status(appointment.getStatus().name())
                .build();
    }







}
