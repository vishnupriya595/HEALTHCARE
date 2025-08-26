package com.example.HealthCare.Service;

import com.example.HealthCare.Model.Report;
import com.example.HealthCare.Model.Users;
import com.example.HealthCare.Repository.ReportRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service

public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    public Report getReport(String name, JsonNode responseData, Users user ){
        Report report = Report.builder()
                .reportType(name)
                .queryConfig(responseData)
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();
        return reportRepository.save(report);
    }
}
