package com.powerunitmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.powerunitmanager.model.PowerUnit;

@Repository
public interface PowerUnitRepository extends JpaRepository<PowerUnit, Long> {
    PowerUnit findByCarIdentifier(String carIdentifier);
} 