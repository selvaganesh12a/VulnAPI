package com.example.NVDAssessment.Repository;

import com.example.NVDAssessment.Entity.CveEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

public interface CveRepository extends JpaRepository<CveEntity,Long> {
    Optional<CveEntity> findByCveId(String cveId);
    List<CveEntity> findByLastModifiedDateAfter(LocalDateTime cutoffDate);
}
