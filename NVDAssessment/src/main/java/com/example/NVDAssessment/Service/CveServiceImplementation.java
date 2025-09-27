package com.example.NVDAssessment.Service;

import com.example.NVDAssessment.Entity.CveEntity;
import com.example.NVDAssessment.Repository.CveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CveServiceImplementation implements CveService {
    @Autowired
    private CveRepository cveRepository;

    @Override
    public CveEntity getCveById(String cveId) {
        Optional<CveEntity> cve = cveRepository.findByCveId(cveId);
        if(cve.isPresent()) return cve.get();
        throw new RuntimeException("CVE not found with ID: " + cveId);
    }

    @Override
    public List<CveEntity> getCvesModifiedInLastNDays(int days) {
        LocalDateTime cutoffDate = LocalDateTime.now().minusDays(days);
        List<CveEntity> cves = cveRepository.findByLastModifiedDateAfter(cutoffDate);
        return cves;
    }

    @Override
    public void saveCve(CveEntity cve) {
        Optional<CveEntity> existing = cveRepository.findByCveId(cve.getCveId());
        if(existing.isPresent()){
            CveEntity existingEntity = existing.get();
            existingEntity.setCveId(cve.getCveId());
            existingEntity.setPublishedDate(cve.getPublishedDate());
            existingEntity.setLastModifiedDate(cve.getLastModifiedDate());
            cveRepository.save(existingEntity);
        }else cveRepository.save(cve);
    }
}
