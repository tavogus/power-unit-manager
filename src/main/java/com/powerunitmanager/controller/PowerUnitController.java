package com.powerunitmanager.controller;

import com.powerunitmanager.controller.model.AddLapsRequest;
import com.powerunitmanager.dto.PowerUnitDTO;
import com.powerunitmanager.service.PowerUnitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/power-units")
public class PowerUnitController {
    private final PowerUnitService powerUnitService;

    public PowerUnitController(PowerUnitService powerUnitService) {
        this.powerUnitService = powerUnitService;
    }

    @PostMapping
    public ResponseEntity<PowerUnitDTO> createPowerUnit(@RequestBody PowerUnitDTO powerUnitDTO) {
        return ResponseEntity.ok(powerUnitService.createPowerUnit(powerUnitDTO));
    }

    @PutMapping("/{powerUnitId}/laps")
    public ResponseEntity<Void> addLaps(@PathVariable Long powerUnitId, @RequestBody AddLapsRequest request) {
        powerUnitService.addLaps(powerUnitId, request.laps());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PowerUnitDTO> getPowerUnitById(@PathVariable Long id) {
        return ResponseEntity.ok(powerUnitService.getPowerUnitById(id));
    }

    @GetMapping
    public ResponseEntity<List<PowerUnitDTO>> getAllPowerUnits() {
        return ResponseEntity.ok(powerUnitService.getAllPowerUnits());
    }
} 