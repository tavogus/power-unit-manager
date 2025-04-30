package com.powerunitmanager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.powerunitmanager.dto.ReplacementHistoryDTO;
import com.powerunitmanager.model.Component;
import com.powerunitmanager.model.PowerUnit;
import com.powerunitmanager.model.ReplacementHistory;
import com.powerunitmanager.repository.ComponentRepository;
import com.powerunitmanager.repository.PowerUnitRepository;
import com.powerunitmanager.repository.ReplacementHistoryRepository;

@Service
public class ReplacementHistoryService {
    private final ReplacementHistoryRepository replacementHistoryRepository;
    private final ComponentRepository componentRepository;
    private final PowerUnitRepository powerUnitRepository;

    public ReplacementHistoryService(ReplacementHistoryRepository replacementHistoryRepository,
                                   ComponentRepository componentRepository,
                                   PowerUnitRepository powerUnitRepository) {
        this.replacementHistoryRepository = replacementHistoryRepository;
        this.componentRepository = componentRepository;
        this.powerUnitRepository = powerUnitRepository;
    }

    @Transactional
    public ReplacementHistoryDTO registerReplacement(ReplacementHistoryDTO replacementHistoryDTO) {
        Component component = componentRepository.findById(replacementHistoryDTO.componentId())
                .orElseThrow(() -> new RuntimeException("Component not found"));

        component.setLastReplacementDate(replacementHistoryDTO.replacementDate());
        component = componentRepository.save(component);

        PowerUnit powerUnit = powerUnitRepository.findById(replacementHistoryDTO.powerUnitId())
                .orElseThrow(() -> new RuntimeException("Power Unit not found"));

        ReplacementHistory replacementHistory = new ReplacementHistory(
                component,
                powerUnit,
                replacementHistoryDTO.durabilityAtReplacement(),
                replacementHistoryDTO.replacementReason()
        );

        ReplacementHistory savedHistory = replacementHistoryRepository.save(replacementHistory);
        return toDTO(savedHistory);
    }

    @Transactional(readOnly = true)
    public List<ReplacementHistoryDTO> getReplacementHistoryByPowerUnit(Long powerUnitId) {
        return replacementHistoryRepository.findByPowerUnitId(powerUnitId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReplacementHistoryDTO> getReplacementHistoryByComponent(Long componentId) {
        return replacementHistoryRepository.findByComponentId(componentId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private ReplacementHistoryDTO toDTO(ReplacementHistory replacementHistory) {
        return new ReplacementHistoryDTO(
                replacementHistory.getId(),
                replacementHistory.getReplacementDate(),
                replacementHistory.getComponent().getId(),
                replacementHistory.getPowerUnit().getId(),
                replacementHistory.getDurabilityAtReplacement(),
                replacementHistory.getReplacementReason()
        );
    }
} 