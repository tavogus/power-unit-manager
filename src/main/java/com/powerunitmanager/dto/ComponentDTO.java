package com.powerunitmanager.dto;

import java.time.LocalDateTime;

import com.powerunitmanager.model.ComponentType;

public record ComponentDTO(
    Long id,
    String name,
    ComponentType type,
    Integer baseDurability,
    Double currentDurability,
    LocalDateTime lastReplacementDate,
    Long manufacturerId,
    Long powerUnitId
) {} 