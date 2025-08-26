package com.example.HealthCare.Controller;

import com.example.HealthCare.DTO.PatientSummaryDTO;
import com.example.HealthCare.DTO.Request.DoctorProfileReqDTO;
import com.example.HealthCare.DTO.Request.NurseProfileReqDTO;
import com.example.HealthCare.Service.AdminService;
import com.example.HealthCare.Service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nurse")
public class NurseController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private NurseService nurseService;

    @GetMapping("/patient/{id}/summary")
    public ResponseEntity<PatientSummaryDTO> getPatientSummary(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getPatientSummary(id));
    }

    @PostMapping("update/profile/{id}")
    public String updateProfile(@PathVariable Long id, @RequestBody NurseProfileReqDTO nurseProfileReqDTO) {
        return nurseService.updateNurseProfile(id, nurseProfileReqDTO);


    }
}
