package com.example.audit.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.audit.model.AuditLog;

@Repository
public interface AuditRepository extends MongoRepository<AuditLog, String> {

}