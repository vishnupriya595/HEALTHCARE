package com.example.HealthCare.Controller;

import com.example.HealthCare.DTO.BillingResponseDTO;
import com.example.HealthCare.DTO.PatientAppointmentDTO;
import com.example.HealthCare.DTO.PatientPrescriptionDTO;
import com.example.HealthCare.DTO.PatientResponseDTO;
import com.example.HealthCare.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/{patientId}/upcoming-appointments")
    public ResponseEntity<List<PatientAppointmentDTO>> getUpcomingAppointments(
            @PathVariable Long patientId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate) {

        return ResponseEntity.ok(patientService.getUpcomingAppointments(patientId, fromDate));
    }

    @GetMapping("/prescriptions/{patientId}")
    public ResponseEntity<List<PatientPrescriptionDTO>> getPrescriptions(@PathVariable Long patientId) {
        return ResponseEntity.ok(patientService.getPrescriptionsByPatient(patientId));
    }

    // 2. Cancel Appointment
    @PutMapping("/cancel/{appointmentId}")
    public ResponseEntity<PatientAppointmentDTO> cancelAppointment(@PathVariable Long appointmentId) {
        return ResponseEntity.ok(patientService.cancelAppointment(appointmentId));
    }

    // 3. Reschedule Appointment
    @PutMapping("/reschedule/{appointmentId}")
    public ResponseEntity<PatientAppointmentDTO> rescheduleAppointment(
            @PathVariable Long appointmentId,
            @RequestParam String newDate) {
        LocalDate localDate = LocalDate.parse(newDate);
        return ResponseEntity.ok(patientService.rescheduleAppointment(appointmentId, localDate));
    }

    // 4. View All Appointments for a Patient
    @GetMapping("/{patientId}")
    public ResponseEntity<List<PatientAppointmentDTO>> getAppointments(@PathVariable Long patientId) {
        return ResponseEntity.ok(patientService.getAppointmentsByPatient(patientId));
    }

    @GetMapping("/bill/{patientId}")
    public ResponseEntity<List<BillingResponseDTO>> getBillingDetails(@PathVariable Long patientId) {
        List<BillingResponseDTO> response = patientService.getBillingByPatientId(patientId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/profile/{patientId}")
    public ResponseEntity<PatientResponseDTO> getPatientProfile(@PathVariable Long patientId) {
        return ResponseEntity.ok(patientService.getPatientProfile(patientId));
    }
}
