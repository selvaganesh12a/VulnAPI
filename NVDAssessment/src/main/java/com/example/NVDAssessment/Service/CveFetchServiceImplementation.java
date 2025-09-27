package com.example.NVDAssessment.Service;

import com.example.NVDAssessment.Entity.CveEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class CveFetchServiceImplementation implements CveFetchService{
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CveService cveService;
    @Override
    public void fetchAndStoreCves() throws JsonProcessingException {
        String baseUrl = "https://services.nvd.nist.gov/rest/json/cves/2.0";
        int startIndex = 0;
        int resultsPerPage = 1000;
        boolean hasData = true;
        while(hasData){
            String url = baseUrl + "?startIndex=" + startIndex + "&resultsPerPage=" + resultsPerPage;
            ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);
            JsonNode root = objectMapper.readTree(response.getBody());
            JsonNode vulnerabilities = root.get("vulnerabilities");
            if(vulnerabilities == null || vulnerabilities.isEmpty()){
                hasData = false;
                break;
            }
            for(JsonNode vuln: vulnerabilities){
                JsonNode cveData = vuln.get("cve");
                String cveId = cveData.get("id").asText();
                String published = cveData.get("published").asText();
                String modified = cveData.get("lastModified").asText();
                CveEntity entity = new CveEntity();
                entity.setCveId(cveId);
                entity.setPublishedDate(LocalDateTime.parse(published));
                entity.setLastModifiedDate(LocalDateTime.parse(modified));
                cveService.saveCve(entity);
            }
            startIndex += resultsPerPage;
        }
    }
}
