package com.example.audit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.audit.model.AuditLog;
import com.example.audit.service.AuditService;

@RestController
@RequestMapping("/api/audit")
public class AuditController {

    @Autowired
    private AuditService service;

    @GetMapping
    public List<AuditLog> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public AuditLog get(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    public AuditLog create(@RequestBody AuditLog log) {
        return service.create(log);
    }
}