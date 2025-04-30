package com.powerunitmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.powerunitmanager.model.ReplacementHistory;

@Repository
public interface ReplacementHistoryRepository extends JpaRepository<ReplacementHistory, Long> {
    List<ReplacementHistory> findByPowerUnitId(Long powerUnitId);
    List<ReplacementHistory> findByComponentId(Long componentId);
} 