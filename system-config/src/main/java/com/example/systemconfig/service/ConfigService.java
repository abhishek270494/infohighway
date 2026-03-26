package com.example.systemconfig.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.systemconfig.model.ConfigSetting;
import com.example.systemconfig.repository.ConfigRepository;

@Service
public class ConfigService {

    @Autowired
    private ConfigRepository repo;

    @Autowired
    private AuditClient auditClient;

    private final String systemUser = "system";

    public List<ConfigSetting> getAll() {
        return StreamSupport.stream(repo.findAll().spliterator(), false).toList();
    }

    public ConfigSetting getByKey(String key) {
        return repo.findById(key).orElse(null);
    }

    public ConfigSetting save(ConfigSetting config) {
        ConfigSetting existing = repo.findById(config.getKey()).orElse(null);
        String action;
        if (existing != null) {
            config.setVersion(existing.getVersion() + 1);
            action = "UPDATE";
        } else {
            config.setVersion(1);
            action = "CREATE";
        }
        ConfigSetting saved = repo.save(config);
        //auditClient.log(systemUser, action, "Config key=" + config.getKey() + ", value=" + config.getValue());
        return saved;
    }

    public void delete(String key) {
        repo.deleteById(key);
        //auditClient.log(systemUser, "DELETE", "Config key=" + key);
    }
}