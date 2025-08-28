package com.example.HealthCare.Controller;

import com.example.HealthCare.DTO.*;
import com.example.HealthCare.DTO.Request.PatientBookReqDTO;
import com.example.HealthCare.DTO.Response.BillingResponseDTO;
import com.example.HealthCare.DTO.Response.DoctorDetailsResDTO;
import com.example.HealthCare.DTO.Response.PatientResponseDTO;
import com.example.HealthCare.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patient")
@CrossOrigin(origins = "http://localhost:4200")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/{patientId}/upcoming-appointments")
    public ResponseEntity<List<PatientAppointmentDTO>> getUpcomingAppointments(
            @PathVariable UUID patientId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate) {

        return ResponseEntity.ok(patientService.getUpcomingAppointments(patientId, fromDate));
    }

    @GetMapping("/prescriptions/{patientId}")
    public ResponseEntity<List<PatientPrescriptionDTO>> getPrescriptions(@PathVariable UUID patientId) {
        return ResponseEntity.ok(patientService.getPrescriptionsByPatient(patientId));
    }

    // 2. Cancel Appointment
    @PutMapping("/cancel/{appointmentId}")
    public ResponseEntity<PatientAppointmentDTO> cancelAppointment(@PathVariable UUID appointmentId) {
        return ResponseEntity.ok(patientService.cancelAppointment(appointmentId));
    }

    // 3. Reschedule Appointment
    @PutMapping("/reschedule/{appointmentId}")
    public ResponseEntity<PatientAppointmentDTO> rescheduleAppointment(
            @PathVariable UUID appointmentId,
            @RequestParam String newDate) {
        LocalDate localDate = LocalDate.parse(newDate);
        return ResponseEntity.ok(patientService.rescheduleAppointment(appointmentId, localDate));
    }

    // 4. View All Appointments for a Patient
    @GetMapping("/{patientId}")
    public ResponseEntity<List<PatientAppointmentDTO>> getAppointments(@PathVariable UUID patientId) {
        return ResponseEntity.ok(patientService.getAppointmentsByPatient(patientId));
    }

    @GetMapping("/bill/{patientId}")
    public ResponseEntity<List<BillingResponseDTO>> getBillingDetails(@PathVariable UUID patientId) {
        List<BillingResponseDTO> response = patientService.getBillingByPatientId(patientId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/profile/{patientId}")
    public ResponseEntity<PatientResponseDTO> getPatientProfile(@PathVariable UUID patientId) {
        return ResponseEntity.ok(patientService.getPatientProfile(patientId));
    }

    @GetMapping("/getDoctors")
    public ResponseEntity<List<DoctorDetailsResDTO>> getDoctors() {
        return ResponseEntity.ok(patientService.getAllDoctorDetails());
    }

    @PostMapping("/appointment/patient/{patientId}")
    public String scheduleAppointment(@PathVariable UUID patientId, @RequestBody PatientBookReqDTO patientBookReqDTO) {
        return patientService.getPatientBookRequest(patientId,patientBookReqDTO.getDoctorName(),patientBookReqDTO.getDate());
    }

}
