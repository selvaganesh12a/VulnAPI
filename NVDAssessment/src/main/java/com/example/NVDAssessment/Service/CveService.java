package com.example.NVDAssessment.Service;

import com.example.NVDAssessment.Entity.CveEntity;
import java.util.List;

public interface CveService {
    CveEntity getCveById(String cveId);
    List<CveEntity> getCvesModifiedInLastNDays(int days);
    void saveCve(CveEntity cve);
}
