package com.powerunitmanager.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "components")
public class Component {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ComponentType type;

    @Column(name = "base_durability", nullable = false)
    private Integer baseDurability;

    @Column(name = "current_durability", nullable = false)
    private Double currentDurability;

    @Column(name = "last_replacement_date")
    private LocalDateTime lastReplacementDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer manufacturer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "power_unit_id", nullable = false)
    private PowerUnit powerUnit;

    @OneToMany(mappedBy = "component", cascade = CascadeType.ALL)
    private List<ReplacementHistory> replacementHistory = new ArrayList<>();

    public Component() {
    }

    public Component(String name, ComponentType type, Integer baseDurability, 
                    Manufacturer manufacturer, PowerUnit powerUnit) {
        this.name = name;
        this.type = type;
        this.baseDurability = baseDurability;
        this.currentDurability = 100.0;
        this.manufacturer = manufacturer;
        this.powerUnit = powerUnit;
        this.lastReplacementDate = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ComponentType getType() {
        return type;
    }

    public void setType(ComponentType type) {
        this.type = type;
    }

    public Integer getBaseDurability() {
        return baseDurability;
    }

    public void setBaseDurability(Integer baseDurability) {
        this.baseDurability = baseDurability;
    }

    public Double getCurrentDurability() {
        return currentDurability;
    }

    public void setCurrentDurability(Double currentDurability) {
        this.currentDurability = currentDurability;
    }

    public LocalDateTime getLastReplacementDate() {
        return lastReplacementDate;
    }

    public void setLastReplacementDate(LocalDateTime lastReplacementDate) {
        this.lastReplacementDate = lastReplacementDate;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public PowerUnit getPowerUnit() {
        return powerUnit;
    }

    public void setPowerUnit(PowerUnit powerUnit) {
        this.powerUnit = powerUnit;
    }

    public List<ReplacementHistory> getReplacementHistory() {
        return replacementHistory;
    }

    public void setReplacementHistory(List<ReplacementHistory> replacementHistory) {
        this.replacementHistory = replacementHistory;
    }
} 