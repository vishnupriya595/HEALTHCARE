package com.example.HealthCare.Controller;

import com.example.HealthCare.DTO.PatientSummaryDTO;
import com.example.HealthCare.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nurse")
public class NurseController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/patient/{id}/summary")
    public ResponseEntity<PatientSummaryDTO> getPatientSummary(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getPatientSummary(id));
    }
}
