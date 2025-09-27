package com.example.NVDAssessment.Controller;

import com.example.NVDAssessment.Entity.CveEntity;
import com.example.NVDAssessment.Service.CveFetchService;
import com.example.NVDAssessment.Service.CveService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cve")
public class CveController {
    @Autowired
    private CveService cveService;
    @Autowired
    private CveFetchService cveFetchService;

    @GetMapping("/id/{cveId}")
    public CveEntity getCveById(@PathVariable String cveId){
        return cveService.getCveById(cveId);
    }

    @GetMapping("/modified")
    public List<CveEntity> getCvesModified(@RequestParam int days){
        return cveService.getCvesModifiedInLastNDays(days);
    }

    @PostMapping("/fetch")
    public String fetchAndStoreCves() throws JsonProcessingException {
        cveFetchService.fetchAndStoreCves();
        return "CVE data fetch initiated and stored successfully!";
    }
}
