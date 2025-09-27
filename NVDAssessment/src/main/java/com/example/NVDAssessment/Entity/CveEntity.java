package com.example.NVDAssessment.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "cve_details")
public class CveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "cve_id", unique = true, nullable = false)
    private String cveId;
    private LocalDateTime publishedDate;
    private LocalDateTime lastModifiedDate;
}
