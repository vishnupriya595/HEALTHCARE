package com.example.HealthCare.Controller;

import com.example.HealthCare.DTO.*;
import com.example.HealthCare.Model.Doctor;
import com.example.HealthCare.Model.Nurse;
import com.example.HealthCare.Model.Patient;
import com.example.HealthCare.Service.AdminService;
import com.example.HealthCare.Service.AppointmentExcelService;
import com.example.HealthCare.Service.ExcelImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor

public class AdminController {

    private final ExcelImportService excelImportService;
    private final AppointmentExcelService appointmentExcelService;
    private final AdminService adminService;


    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcel( @RequestParam("doctor") MultipartFile doctor, @RequestParam("patient") MultipartFile patient, @RequestParam("nurse") MultipartFile nurse){

        excelImportService.importExcel(doctor, "doctor");
        excelImportService.importExcel(patient, "patient");
        excelImportService.importExcel(nurse, "nurse");

        return ResponseEntity.ok("Imported data Successfully");
    }

    @PostMapping("upload/appointment")
    public ResponseEntity<String> uploadAppointment(@RequestParam("appointment")  MultipartFile appointment){
        try {
            appointmentExcelService.importData(appointment);
            return ResponseEntity.ok("Imported data Successfully");
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("dashboard/summary")
    public ResponseEntity<Map<String, Object>> getDashboardSummary() {
        return ResponseEntity.ok(adminService.getDashboardSummary());
    }

    @PostMapping("/add/doctor")
    public ResponseEntity<Doctor> addDoctor(@RequestBody DoctorRequestDTO dto) {
        Doctor doctor = adminService.addDoctor(dto);
        return ResponseEntity.ok(doctor);
    }

    // Get all doctors
    @GetMapping("/all/doctors")
    public ResponseEntity<List<DoctorResponseDTO>> getAllDoctors() {
        return ResponseEntity.ok(adminService.getAllDoctors());
    }

    // Get doctor by ID
    @GetMapping("/doctor/{id}")
    public ResponseEntity<DoctorResponseDTO> getDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getDoctorById(id));
    }

    // Update doctor
    @PutMapping("/update/doctor/{id}")
    public ResponseEntity<DoctorResponseDTO> updateDoctor(@PathVariable Long id,
                                                          @RequestBody DoctorRequestDTO dto) {
        return ResponseEntity.ok(adminService.updateDoctor(id, dto));
    }

    // Delete doctor
    @DeleteMapping("/delete/doctor/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
        adminService.deleteDoctor(id);
        return ResponseEntity.ok("Doctor deleted successfully!");
    }

    // Add patient
    @PostMapping("/add/patient")
    public ResponseEntity<Patient> addPatient(@RequestBody PatientRequestDTO dto) {
        Patient patient = adminService.addPatient(dto);
        return ResponseEntity.ok(patient);
    }

    // Get all patients
    @GetMapping("/all/patients")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        return ResponseEntity.ok(adminService.getAllPatients());
    }

    // Get patient by ID
    @GetMapping("/patient/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getPatientById(id));
    }

    // Update patient
    @PutMapping("/update/patient/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable Long id,
                                                            @RequestBody PatientRequestDTO dto) {
        return ResponseEntity.ok(adminService.updatePatient(id, dto));
    }

    // Delete patient
    @DeleteMapping("/delete/patient/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        adminService.deletePatient(id);
        return ResponseEntity.ok("Patient deleted successfully!");
    }

    // Add nurse
    @PostMapping("/add/nurse")
    public ResponseEntity<Nurse> addNurse(@RequestBody NurseRequestDTO dto) {
        Nurse nurse = adminService.addNurse(dto);
        return ResponseEntity.ok(nurse);
    }

    // Get all nurses
    @GetMapping("/all/nurses")
    public ResponseEntity<List<NurseResponseDTO>> getAllNurses() {
        return ResponseEntity.ok(adminService.getAllNurses());
    }

    // Get nurse by ID
    @GetMapping("/nurse/{id}")
    public ResponseEntity<NurseResponseDTO> getNurseById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getNurseById(id));
    }

    // Update nurse
    @PutMapping("/update/nurse/{id}")
    public ResponseEntity<NurseResponseDTO> updateNurse(@PathVariable Long id,
                                                        @RequestBody NurseRequestDTO dto) {
        return ResponseEntity.ok(adminService.updateNurse(id, dto));
    }

    // Delete nurse
    @DeleteMapping("/delete/nurse/{id}")
    public ResponseEntity<String> deleteNurse(@PathVariable Long id) {
        adminService.deleteNurse(id);
        return ResponseEntity.ok("Nurse deleted successfully!");
    }

    // View appointments by date
    @GetMapping("/appointments/date/{date}")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByDate(@PathVariable String date) {
        return ResponseEntity.ok(adminService.getAppointmentsByDate(LocalDate.parse(date)));
    }

    // Reschedule appointment
    @PutMapping("/appointments/reschedule/{id}")
    public ResponseEntity<AppointmentResponseDTO> rescheduleAppointment(
            @PathVariable Long id,
            @RequestParam String newDate) {
        return ResponseEntity.ok(adminService.rescheduleAppointment(id, LocalDate.parse(newDate)));
    }

    // Cancel appointment
    @PutMapping("/appointments/cancel/{id}")
    public ResponseEntity<String> cancelAppointment(@PathVariable Long id) {
        adminService.cancelAppointment(id);
        return ResponseEntity.ok("Appointment cancelled successfully!");
    }

    @GetMapping("/patient/{id}/summary")
    public ResponseEntity<PatientSummaryDTO> getPatientSummary(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getPatientSummary(id));
    }

    @GetMapping("/doctor/{doctorId}/summary")
    public ResponseEntity<DoctorSummaryDTO> getDoctorSummary(@PathVariable Long doctorId) {
        DoctorSummaryDTO summary = adminService.getDoctorSummary(doctorId);
        return ResponseEntity.ok(summary);
    }




}
