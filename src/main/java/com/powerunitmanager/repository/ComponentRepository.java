package com.powerunitmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.powerunitmanager.model.Component;

@Repository
public interface ComponentRepository extends JpaRepository<Component, Long> {
    List<Component> findByPowerUnitId(Long powerUnitId);
    List<Component> findByCurrentDurabilityLessThan(Double durability);
} 