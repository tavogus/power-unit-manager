package com.powerunitmanager.controller;

import com.powerunitmanager.dto.ComponentDTO;
import com.powerunitmanager.service.ComponentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
} 