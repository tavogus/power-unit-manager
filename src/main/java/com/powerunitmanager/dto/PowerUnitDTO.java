package com.powerunitmanager.dto;

import java.util.List;

public record PowerUnitDTO(
    Long id,
    String carIdentifier,
    Integer totalLaps,
    List<ComponentDTO> components
) {} 