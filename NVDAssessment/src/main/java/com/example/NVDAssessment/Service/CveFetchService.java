package com.example.NVDAssessment.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface CveFetchService {
    void fetchAndStoreCves() throws JsonProcessingException;
}
