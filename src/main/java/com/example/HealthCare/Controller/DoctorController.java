package com.example.HealthCare.Controller;

import com.example.HealthCare.DTO.DoctorDashboardDTO;
import com.example.HealthCare.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/doctor/")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorDashboardDTO> getDashboard(@PathVariable Long doctorId) {
        DoctorDashboardDTO dashboard = doctorService.getDoctorDashboard(doctorId);
        return ResponseEntity.ok(dashboard);
    }
}
