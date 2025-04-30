package com.powerunitmanager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.powerunitmanager.dto.ComponentDTO;
import com.powerunitmanager.model.Component;
import com.powerunitmanager.model.Manufacturer;
import com.powerunitmanager.model.PowerUnit;
import com.powerunitmanager.repository.ComponentRepository;
import com.powerunitmanager.repository.ManufacturerRepository;
import com.powerunitmanager.repository.PowerUnitRepository;

@Service
public class ComponentService {
    private final ComponentRepository componentRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final PowerUnitRepository powerUnitRepository;

    public ComponentService(ComponentRepository componentRepository,
                          ManufacturerRepository manufacturerRepository,
                          PowerUnitRepository powerUnitRepository) {
        this.componentRepository = componentRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.powerUnitRepository = powerUnitRepository;
    }

    @Transactional
    public ComponentDTO createComponent(ComponentDTO componentDTO) {
        Manufacturer manufacturer = manufacturerRepository.findById(componentDTO.manufacturerId())
                .orElseThrow(() -> new RuntimeException("Manufacturer not found"));
        
        PowerUnit powerUnit = powerUnitRepository.findById(componentDTO.powerUnitId())
                .orElseThrow(() -> new RuntimeException("Power Unit not found"));

        Component component = new Component(
                componentDTO.name(),
                componentDTO.type(),
                componentDTO.baseDurability(),
                manufacturer,
                powerUnit
        );

        Component savedComponent = componentRepository.save(component);
        return toDTO(savedComponent);
    }

    @Transactional(readOnly = true)
    public List<ComponentDTO> getComponentsByPowerUnit(Long powerUnitId) {
        return componentRepository.findByPowerUnitId(powerUnitId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateComponentDurability(Long componentId, Integer laps) {
        Component component = componentRepository.findById(componentId)
                .orElseThrow(() -> new RuntimeException("Component not found"));

        double durabilityLoss = (double) laps / component.getBaseDurability() * 100;
        double newDurability = Math.max(0, component.getCurrentDurability() - durabilityLoss);
        component.setCurrentDurability(newDurability);

        componentRepository.save(component);
    }

    @Transactional(readOnly = true)
    public List<ComponentDTO> getComponentsWithLowDurability() {
        return componentRepository.findByCurrentDurabilityLessThan(20.0)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ComponentDTO updateComponent(Long id, ComponentDTO componentDTO) {
        Component component = componentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Component not found"));

        Manufacturer manufacturer = manufacturerRepository.findById(componentDTO.manufacturerId())
                .orElseThrow(() -> new RuntimeException("Manufacturer not found"));

        PowerUnit powerUnit = powerUnitRepository.findById(componentDTO.powerUnitId())
                .orElseThrow(() -> new RuntimeException("Power Unit not found"));

        component.setName(componentDTO.name());
        component.setType(componentDTO.type());
        component.setBaseDurability(componentDTO.baseDurability());
        component.setCurrentDurability(componentDTO.currentDurability());
        component.setManufacturer(manufacturer);
        component.setPowerUnit(powerUnit);

        Component updatedComponent = componentRepository.save(component);
        return toDTO(updatedComponent);
    }

    private ComponentDTO toDTO(Component component) {
        return new ComponentDTO(
                component.getId(),
                component.getName(),
                component.getType(),
                component.getBaseDurability(),
                component.getCurrentDurability(),
                component.getLastReplacementDate(),
                component.getManufacturer().getId(),
                component.getPowerUnit().getId()
        );
    }
} 