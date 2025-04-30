package com.powerunitmanager.controller;

import com.powerunitmanager.controller.model.UpdateDurabilityRequest;
import com.powerunitmanager.dto.ManufacturerDTO;
import com.powerunitmanager.service.ManufacturerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manufacturers")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @PostMapping
    public ResponseEntity<ManufacturerDTO> createManufacturer(@RequestBody ManufacturerDTO manufacturerDTO) {
        return ResponseEntity.ok(manufacturerService.createManufacturer(manufacturerDTO));
    }

    @PutMapping("/{manufacturerId}/durability")
    public ResponseEntity<ManufacturerDTO> updateDurabilityVersion(
            @PathVariable Long manufacturerId,
            @RequestBody UpdateDurabilityRequest request) {
        return ResponseEntity.ok(manufacturerService.updateDurabilityVersion(manufacturerId, request));
    }

    @GetMapping
    public ResponseEntity<List<ManufacturerDTO>> getAllManufacturers() {
        return ResponseEntity.ok(manufacturerService.getAllManufacturers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManufacturerDTO> getManufacturerById(@PathVariable Long id) {
        return ResponseEntity.ok(manufacturerService.getManufacturerById(id));
    }
} 