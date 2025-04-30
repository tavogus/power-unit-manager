package com.powerunitmanager.dto;

import java.time.LocalDateTime;

public record ReplacementHistoryDTO(
    Long id,
    LocalDateTime replacementDate,
    Long componentId,
    Long powerUnitId,
    Double durabilityAtReplacement,
    String replacementReason
) {} 