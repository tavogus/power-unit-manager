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

import com.powerunitmanager.dto.PowerUnitDTO;
import com.powerunitmanager.service.PowerUnitService;

@RestController
@RequestMapping("/api/power-units")
public class PowerUnitController {
    private final PowerUnitService powerUnitService;

    public PowerUnitController(PowerUnitService powerUnitService) {
        this.powerUnitService = powerUnitService;
    }

    @PostMapping
    public ResponseEntity<PowerUnitDTO> createPowerUnit(@RequestBody PowerUnitDTO powerUnitDTO) {
        PowerUnitDTO createdPowerUnit = powerUnitService.createPowerUnit(powerUnitDTO);
        return ResponseEntity.ok(createdPowerUnit);
    }

    @PutMapping("/{powerUnitId}/laps")
    public ResponseEntity<Void> addLaps(
            @PathVariable Long powerUnitId,
            @RequestBody Map<String, Integer> request) {
        Integer laps = request.get("laps");
        powerUnitService.addLaps(powerUnitId, laps);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PowerUnitDTO> getPowerUnitById(@PathVariable Long id) {
        PowerUnitDTO powerUnit = powerUnitService.getPowerUnitById(id);
        return ResponseEntity.ok(powerUnit);
    }

    @GetMapping
    public ResponseEntity<List<PowerUnitDTO>> getAllPowerUnits() {
        List<PowerUnitDTO> powerUnits = powerUnitService.getAllPowerUnits();
        return ResponseEntity.ok(powerUnits);
    }
} 