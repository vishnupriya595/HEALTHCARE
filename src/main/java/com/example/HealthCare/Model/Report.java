package com.example.HealthCare.Model;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;
    @Column(unique = true)
    private String reportType;
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private JsonNode queryConfig;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;
}
