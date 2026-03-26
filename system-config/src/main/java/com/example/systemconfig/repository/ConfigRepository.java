package com.example.systemconfig.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.systemconfig.model.ConfigSetting;

@Repository
public interface ConfigRepository extends CrudRepository<ConfigSetting, String> {


}