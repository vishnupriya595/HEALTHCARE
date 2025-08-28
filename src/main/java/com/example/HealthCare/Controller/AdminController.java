package com.example.HealthCare.Controller;

import com.example.HealthCare.DTO.*;
import com.example.HealthCare.DTO.Request.*;
import com.example.HealthCare.DTO.Response.*;
import com.example.HealthCare.Model.Doctor;
import com.example.HealthCare.Model.Nurse;
import com.example.HealthCare.Model.Patient;
import com.example.HealthCare.Service.AdminService;
import com.example.HealthCare.Service.AppointmentExcelService;
import com.example.HealthCare.Service.AuthService;
import com.example.HealthCare.Service.ExcelImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:4200")
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
    public ResponseEntity<AddDoctorResponseDTO> addDoctor(@RequestBody DoctorRequestDTO dto) {
        AddDoctorResponseDTO doctor = adminService.addDoctor(dto);
        return ResponseEntity.ok(doctor);
    }

    // Get all doctors
    @GetMapping("/all/doctors")
    public ResponseEntity<List<DoctorResponseDTO>> getAllDoctors() {
        return ResponseEntity.ok(adminService.getAllDoctors());
    }

    // Get doctor by ID
    @GetMapping("/doctor/{id}")
    public ResponseEntity<DoctorResponseDTO> getDoctorById(@PathVariable UUID id) {
        return ResponseEntity.ok(adminService.getDoctorById(id));
    }

    // Update doctor
    @PutMapping("/update/doctor/{id}")
    public ResponseEntity<DoctorResponseDTO> updateDoctor(@PathVariable UUID id,
                                                          @RequestBody DoctorRequestDTO dto) {
        return ResponseEntity.ok(adminService.updateDoctor(id, dto));
    }

    // Delete doctor
    @DeleteMapping("/delete/doctor/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable UUID id) {
        adminService.deleteDoctor(id);
        return ResponseEntity.ok("Doctor deleted successfully!");
    }

    // Add patient
    @PostMapping("/add/patient")
    public ResponseEntity<PatientResponseDTO> addPatient(@RequestBody PatientRequestDTO dto) {
        PatientResponseDTO patient = adminService.addPatient(dto);
        return ResponseEntity.ok(patient);
    }

    // Get all patients
    @GetMapping("/all/patients")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        return ResponseEntity.ok(adminService.getAllPatients());
    }

    // Get patient by ID
    @GetMapping("/patient/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable UUID id) {
        return ResponseEntity.ok(adminService.getPatientById(id));
    }

    // Update patient
    @PutMapping("/update/patient/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id,
                                                            @RequestBody UpdatePatientReqDTO dto) {
        return ResponseEntity.ok(adminService.updatePatient(id, dto));
    }

    // Delete patient
    @DeleteMapping("/delete/patient/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable UUID id) {
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
    public ResponseEntity<NurseResponseDTO> getNurseById(@PathVariable UUID id) {
        return ResponseEntity.ok(adminService.getNurseById(id));
    }

    // Update nurse
    @PutMapping("/update/nurse/{id}")
    public ResponseEntity<NurseResponseDTO> updateNurse(@PathVariable UUID id,
                                                        @RequestBody NurseUpdateReqDTO dto) {
        return ResponseEntity.ok(adminService.updateNurse(id, dto));
    }

    // Delete nurse
    @DeleteMapping("/delete/nurse/{id}")
    public ResponseEntity<String> deleteNurse(@PathVariable UUID id) {
        adminService.deleteNurse(id);
        return ResponseEntity.ok("Nurse deleted successfully!");
    }

    // View appointments by date
    @GetMapping("/appointments/date/{date}")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByDate(@PathVariable String date) {
        return ResponseEntity.ok(adminService.getAppointmentsByDate(LocalDate.parse(date)));
    }


    @GetMapping("/patient/{id}/summary")
    public ResponseEntity<PatientSummaryDTO> getPatientSummary(@PathVariable UUID id) {
        return ResponseEntity.ok(adminService.getPatientSummary(id));
    }

    @GetMapping("/doctor/{doctorId}/summary")
    public ResponseEntity<DoctorSummaryDTO> getDoctorSummary(@PathVariable UUID doctorId) {
        DoctorSummaryDTO summary = adminService.getDoctorSummary(doctorId);
        return ResponseEntity.ok(summary);
    }

    @PostMapping("/profile/{id}")
    public String updateProile(@PathVariable UUID id,@RequestBody AdminReqDTO adminReqDTO) {
        return adminService.updateProileAdmin(id, adminReqDTO);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<AdminResponseDTO> getProile(@PathVariable UUID id) {
        AdminResponseDTO dto=adminService.getProfile(id);
        return ResponseEntity.ok(dto);
    }



}
