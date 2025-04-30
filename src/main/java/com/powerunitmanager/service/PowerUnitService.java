package com.powerunitmanager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.powerunitmanager.dto.ComponentDTO;
import com.powerunitmanager.dto.PowerUnitDTO;
import com.powerunitmanager.model.PowerUnit;
import com.powerunitmanager.repository.PowerUnitRepository;

@Service
public class PowerUnitService {
    private final PowerUnitRepository powerUnitRepository;
    private final ComponentService componentService;

    public PowerUnitService(PowerUnitRepository powerUnitRepository,
                          ComponentService componentService) {
        this.powerUnitRepository = powerUnitRepository;
        this.componentService = componentService;
    }

    @Transactional
    public PowerUnitDTO createPowerUnit(PowerUnitDTO powerUnitDTO) {
        PowerUnit powerUnit = new PowerUnit(powerUnitDTO.carIdentifier());
        powerUnit.setTotalLaps(powerUnitDTO.totalLaps());
        
        PowerUnit savedPowerUnit = powerUnitRepository.save(powerUnit);
        return toDTO(savedPowerUnit);
    }

    @Transactional
    public void addLaps(Long powerUnitId, Integer laps) {
        PowerUnit powerUnit = powerUnitRepository.findById(powerUnitId)
                .orElseThrow(() -> new RuntimeException("Power Unit not found"));

        powerUnit.setTotalLaps(powerUnit.getTotalLaps() + laps);
        powerUnitRepository.save(powerUnit);

        // Update durability for all components
        List<ComponentDTO> components = componentService.getComponentsByPowerUnit(powerUnitId);
        components.forEach(component -> 
            componentService.updateComponentDurability(component.id(), laps)
        );
    }

    @Transactional(readOnly = true)
    public PowerUnitDTO getPowerUnitById(Long id) {
        return powerUnitRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Power Unit not found"));
    }

    @Transactional(readOnly = true)
    public List<PowerUnitDTO> getAllPowerUnits() {
        return powerUnitRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private PowerUnitDTO toDTO(PowerUnit powerUnit) {
        List<ComponentDTO> components = componentService.getComponentsByPowerUnit(powerUnit.getId());
        return new PowerUnitDTO(
                powerUnit.getId(),
                powerUnit.getCarIdentifier(),
                powerUnit.getTotalLaps(),
                components
        );
    }
} 