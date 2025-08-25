package com.example.HealthCare.Controller;

import com.example.HealthCare.DTO.*;
import com.example.HealthCare.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor/")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;




    @GetMapping("{doctorId}/patients")
    public ResponseEntity<List<PatientDiseaseResponseDTO>> listPatients(@PathVariable Long doctorId) {
        return ResponseEntity.ok(doctorService.listPatientsAssigned(doctorId));
    }


    @GetMapping("{doctorId}/patients/{patientId}")
    public ResponseEntity<PatientResponseDTO> getPatientDetails(
            @PathVariable Long doctorId,
            @PathVariable Long patientId) {
        return ResponseEntity.ok(doctorService.getPatientDetails(doctorId, patientId));
    }


    @PostMapping("{doctorId}/patients/{patientId}/prescriptions")
    public ResponseEntity<PrescriptionResponseDTO> addPrescription(
            @PathVariable Long doctorId,
            @PathVariable Long patientId,
            @RequestBody PrescriptionDTO prescriptionDTO) {
        return ResponseEntity
                .status(201) // Created
                .body(doctorService.addPrescription(doctorId, patientId, prescriptionDTO));
    }

    // Get upcoming appointments
    @GetMapping("/{doctorId}/appointments")
    public ResponseEntity<List<AppointmentDoctorResponseDTO>> getUpcomingAppointments(
            @PathVariable Long doctorId) {
        return ResponseEntity.ok(doctorService.getUpcomingAppointments(doctorId));
    }

    // Reschedule appointment
    @PutMapping("/{doctorId}/appointments/{appointmentId}/reschedule")
    public ResponseEntity<AppointmentDoctorResponseDTO> rescheduleAppointment(
            @PathVariable Long doctorId,
            @PathVariable Long appointmentId,
            @RequestBody AppointmentDoctorReqDTO req) {
        return ResponseEntity.ok(doctorService.rescheduleAppointment(doctorId, appointmentId, req));
    }




}
