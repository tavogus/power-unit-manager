package com.powerunitmanager.dto;

import java.util.Map;

import com.powerunitmanager.model.ComponentType;

public record ManufacturerDTO(
    Long id,
    String name,
    Map<ComponentType, Integer> durabilityVersions
) {} 