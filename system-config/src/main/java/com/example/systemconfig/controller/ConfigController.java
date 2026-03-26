package com.example.systemconfig.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.systemconfig.model.ConfigSetting;
import com.example.systemconfig.service.ConfigService;

@RestController
@RequestMapping("/api/config")
public class ConfigController {

    @Autowired
    private ConfigService service;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping
    public List<ConfigSetting> getAll() {
        return service.getAll();
    }

    @GetMapping("/{key}")
    public ConfigSetting get(@PathVariable String key) {
        return service.getByKey(key);
    }

    @PostMapping
    public ConfigSetting create(@RequestBody ConfigSetting config) {
        return service.save(config);
    }

    @PutMapping("/{key}")
    public ConfigSetting update(@PathVariable String key, @RequestBody ConfigSetting config) {
        config.setKey(key);
        return service.save(config);
    }

    @DeleteMapping("/{key}")
    public void delete(@PathVariable String key) {
        service.delete(key);
    }

    @GetMapping("/test-redis")
    public String testRedis() {
        return redisTemplate.getConnectionFactory()
                .getConnection()
                .ping();
    }
}