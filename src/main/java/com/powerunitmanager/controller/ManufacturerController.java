package com.powerunitmanager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.powerunitmanager.dto.ManufacturerDTO;
import com.powerunitmanager.model.ComponentType;
import com.powerunitmanager.service.ManufacturerService;

@RestController
@RequestMapping("/api/manufacturers")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @PostMapping
    public ResponseEntity<ManufacturerDTO> createManufacturer(@RequestBody ManufacturerDTO manufacturerDTO) {
        ManufacturerDTO createdManufacturer = manufacturerService.createManufacturer(manufacturerDTO);
        return ResponseEntity.ok(createdManufacturer);
    }

    @PutMapping("/{manufacturerId}/durability")
    public ResponseEntity<ManufacturerDTO> updateDurabilityVersion(
            @PathVariable Long manufacturerId,
            @RequestBody Map<String, Object> request) {
        ComponentType type = ComponentType.valueOf((String) request.get("type"));
        Integer durability = (Integer) request.get("durability");
        
        ManufacturerDTO updatedManufacturer = manufacturerService.updateDurabilityVersion(
                manufacturerId, type, durability);
        return ResponseEntity.ok(updatedManufacturer);
    }

    @GetMapping
    public ResponseEntity<List<ManufacturerDTO>> getAllManufacturers() {
        List<ManufacturerDTO> manufacturers = manufacturerService.getAllManufacturers();
        return ResponseEntity.ok(manufacturers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManufacturerDTO> getManufacturerById(@PathVariable Long id) {
        ManufacturerDTO manufacturer = manufacturerService.getManufacturerById(id);
        return ResponseEntity.ok(manufacturer);
    }
} 