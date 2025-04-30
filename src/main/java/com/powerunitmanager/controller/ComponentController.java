package com.powerunitmanager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.powerunitmanager.dto.ComponentDTO;
import com.powerunitmanager.service.ComponentService;

@RestController
@RequestMapping("/api/components")
public class ComponentController {
    private final ComponentService componentService;

    public ComponentController(ComponentService componentService) {
        this.componentService = componentService;
    }

    @PostMapping
    public ResponseEntity<ComponentDTO> createComponent(@RequestBody ComponentDTO componentDTO) {
        return ResponseEntity.ok(componentService.createComponent(componentDTO));
    }

    @GetMapping("/power-unit/{powerUnitId}")
    public ResponseEntity<List<ComponentDTO>> getComponentsByPowerUnit(@PathVariable Long powerUnitId) {
        return ResponseEntity.ok(componentService.getComponentsByPowerUnit(powerUnitId));
    }

    @GetMapping("/low-durability")
    public ResponseEntity<List<ComponentDTO>> getComponentsWithLowDurability() {
        return ResponseEntity.ok(componentService.getComponentsWithLowDurability());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComponentDTO> updateComponent(@PathVariable Long id, @RequestBody ComponentDTO componentDTO) {
        return ResponseEntity.ok(componentService.updateComponent(id, componentDTO));
    }
} 