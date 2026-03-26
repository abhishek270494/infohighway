package com.example.audit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.audit.model.AuditLog;
import com.example.audit.repository.AuditRepository;

@Service
public class AuditService {

    @Autowired
    private AuditRepository repo;

    public List<AuditLog> getAll() {
        return repo.findAll();
    }

    public AuditLog getById(String id) {
        return repo.findById(id).orElse(null);
    }

    public AuditLog create(AuditLog log) {
        return repo.save(log);
    }
}