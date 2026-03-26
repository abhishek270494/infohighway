package com.example.systemconfig.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuditClient {
    private final RestTemplate restTemplate;
    private final String auditServiceUrl = "http://audit-service:8082/api/audit";

    public AuditClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void log(String userId, String actionType, String context) {
        Map<String, String> log = Map.of(
            "userId", userId,
            "actionType", actionType,
            "context", context
        );
        try {
            restTemplate.postForEntity(auditServiceUrl, log, String.class);
        } catch (Exception e) {
            System.err.println("Failed to send audit log: " + e.getMessage());
        }
    }
}