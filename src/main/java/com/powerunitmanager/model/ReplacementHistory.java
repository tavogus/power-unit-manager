package com.powerunitmanager.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "replacement_history")
public class ReplacementHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "replacement_date", nullable = false)
    private LocalDateTime replacementDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "component_id", nullable = false)
    private Component component;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "power_unit_id", nullable = false)
    private PowerUnit powerUnit;

    @Column(name = "durability_at_replacement", nullable = false)
    private Double durabilityAtReplacement;

    @Column(name = "replacement_reason", nullable = false)
    private String replacementReason;

    public ReplacementHistory() {
    }

    public ReplacementHistory(Component component, PowerUnit powerUnit, 
                            Double durabilityAtReplacement, String replacementReason) {
        this.replacementDate = LocalDateTime.now();
        this.component = component;
        this.powerUnit = powerUnit;
        this.durabilityAtReplacement = durabilityAtReplacement;
        this.replacementReason = replacementReason;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getReplacementDate() {
        return replacementDate;
    }

    public void setReplacementDate(LocalDateTime replacementDate) {
        this.replacementDate = replacementDate;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public PowerUnit getPowerUnit() {
        return powerUnit;
    }

    public void setPowerUnit(PowerUnit powerUnit) {
        this.powerUnit = powerUnit;
    }

    public Double getDurabilityAtReplacement() {
        return durabilityAtReplacement;
    }

    public void setDurabilityAtReplacement(Double durabilityAtReplacement) {
        this.durabilityAtReplacement = durabilityAtReplacement;
    }

    public String getReplacementReason() {
        return replacementReason;
    }

    public void setReplacementReason(String replacementReason) {
        this.replacementReason = replacementReason;
    }
} 