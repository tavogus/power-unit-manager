package com.powerunitmanager.service;

import java.util.List;
import java.util.stream.Collectors;

import com.powerunitmanager.controller.model.UpdateDurabilityRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.powerunitmanager.dto.ManufacturerDTO;
import com.powerunitmanager.model.ComponentType;
import com.powerunitmanager.model.Manufacturer;
import com.powerunitmanager.repository.ManufacturerRepository;

@Service
public class ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Transactional
    public ManufacturerDTO createManufacturer(ManufacturerDTO manufacturerDTO) {
        Manufacturer manufacturer = new Manufacturer(manufacturerDTO.name());
        manufacturer.setDurabilityVersions(manufacturerDTO.durabilityVersions());
        
        Manufacturer savedManufacturer = manufacturerRepository.save(manufacturer);
        return toDTO(savedManufacturer);
    }

    @Transactional
    public ManufacturerDTO updateDurabilityVersion(Long manufacturerId, UpdateDurabilityRequest request) {
        ComponentType type = ComponentType.valueOf(request.type());
        Integer durability = request.durability();

        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new RuntimeException("Manufacturer not found"));

        manufacturer.getDurabilityVersions().put(type, durability);
        Manufacturer updatedManufacturer = manufacturerRepository.save(manufacturer);
        return toDTO(updatedManufacturer);
    }

    @Transactional(readOnly = true)
    public List<ManufacturerDTO> getAllManufacturers() {
        return manufacturerRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ManufacturerDTO getManufacturerById(Long id) {
        return manufacturerRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Manufacturer not found"));
    }

    private ManufacturerDTO toDTO(Manufacturer manufacturer) {
        return new ManufacturerDTO(
                manufacturer.getId(),
                manufacturer.getName(),
                manufacturer.getDurabilityVersions()
        );
    }
} 