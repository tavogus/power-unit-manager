package com.powerunitmanager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.powerunitmanager.dto.ReplacementHistoryDTO;
import com.powerunitmanager.service.ReplacementHistoryService;

@RestController
@RequestMapping("/api/replacements")
public class ReplacementHistoryController {
    private final ReplacementHistoryService replacementHistoryService;

    public ReplacementHistoryController(ReplacementHistoryService replacementHistoryService) {
        this.replacementHistoryService = replacementHistoryService;
    }

    @PostMapping
    public ResponseEntity<ReplacementHistoryDTO> registerReplacement(
            @RequestBody ReplacementHistoryDTO replacementHistoryDTO) {
        ReplacementHistoryDTO registeredReplacement = replacementHistoryService
                .registerReplacement(replacementHistoryDTO);
        return ResponseEntity.ok(registeredReplacement);
    }

    @GetMapping("/power-unit/{powerUnitId}")
    public ResponseEntity<List<ReplacementHistoryDTO>> getReplacementHistoryByPowerUnit(
            @PathVariable Long powerUnitId) {
        List<ReplacementHistoryDTO> history = replacementHistoryService
                .getReplacementHistoryByPowerUnit(powerUnitId);
        return ResponseEntity.ok(history);
    }

    @GetMapping("/component/{componentId}")
    public ResponseEntity<List<ReplacementHistoryDTO>> getReplacementHistoryByComponent(
            @PathVariable Long componentId) {
        List<ReplacementHistoryDTO> history = replacementHistoryService
                .getReplacementHistoryByComponent(componentId);
        return ResponseEntity.ok(history);
    }
} 