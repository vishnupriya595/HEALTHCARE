package com.example.HealthCare.Controller;

import com.example.HealthCare.DTO.*;
import com.example.HealthCare.DTO.Request.AdminReqDTO;
import com.example.HealthCare.DTO.Request.AppointmentDoctorReqDTO;
import com.example.HealthCare.DTO.Request.DoctorProfileReqDTO;
import com.example.HealthCare.DTO.Request.ReportReqDTO;
import com.example.HealthCare.DTO.Response.AppointmentDoctorResponseDTO;
import com.example.HealthCare.DTO.Response.PatientDiseaseResponseDTO;
import com.example.HealthCare.DTO.Response.PatientResponseDTO;
import com.example.HealthCare.DTO.Response.PrescriptionResponseDTO;
import com.example.HealthCare.Model.Report;
import com.example.HealthCare.Model.Users;
import com.example.HealthCare.Repository.UserRepository;
import com.example.HealthCare.Service.DoctorService;
import com.example.HealthCare.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/doctor/")
@CrossOrigin(origins = "http://localhost:4200")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReportService reportService;


    @GetMapping("{doctorId}/patients")
    public ResponseEntity<List<PatientDiseaseResponseDTO>> listPatients(@PathVariable UUID doctorId) {
        return ResponseEntity.ok(doctorService.listPatientsAssigned(doctorId));
    }


    @GetMapping("/patients/{patientId}")
    public ResponseEntity<PatientResponseDTO> getPatientDetails(

            @PathVariable UUID patientId) {
        return ResponseEntity.ok(doctorService.getPatientDetails( patientId));
    }

    @PostMapping("/report/{username}")
    public String createReport(@PathVariable String username,@RequestBody ReportReqDTO reportReqDTO) {
        Users user =userRepository.findByUsername(username);
        reportService.getReport(reportReqDTO.getName(),reportReqDTO.getResponseData(),user);
        return "Report Created";
    }


    @PostMapping("{doctorId}/patients/{patientId}/prescriptions")
    public ResponseEntity<PrescriptionResponseDTO> addPrescription(
            @PathVariable UUID doctorId,
            @PathVariable UUID patientId,
            @RequestBody PrescriptionDTO prescriptionDTO) {
        return ResponseEntity
                .status(201) // Created
                .body(doctorService.addPrescription(doctorId, patientId, prescriptionDTO));
    }

    // Get upcoming appointments
    @GetMapping("/{doctorId}/appointments")
    public ResponseEntity<List<AppointmentDoctorResponseDTO>> getUpcomingAppointments(
            @PathVariable UUID doctorId) {
        return ResponseEntity.ok(doctorService.getUpcomingAppointments(doctorId));
    }

    // Reschedule appointment
    @PutMapping("/{doctorId}/appointments/{appointmentId}/reschedule")
    public ResponseEntity<AppointmentDoctorResponseDTO> rescheduleAppointment(
            @PathVariable UUID doctorId,
            @PathVariable UUID appointmentId,
            @RequestBody AppointmentDoctorReqDTO req) {
        return ResponseEntity.ok(doctorService.rescheduleAppointment(doctorId, appointmentId, req));
    }

    @PostMapping("update/profile/{id}")
    public String updateProfile(@PathVariable UUID id, @RequestBody DoctorProfileReqDTO  doctorProfileReqDTO) {
        return doctorService.updateDoctorProfile(id, doctorProfileReqDTO);


    }




}
