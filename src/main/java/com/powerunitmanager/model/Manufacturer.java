package com.powerunitmanager.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.MapKeyEnumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "manufacturers")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL)
    private List<Component> components = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "manufacturer_durability_versions", 
                    joinColumns = @JoinColumn(name = "manufacturer_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "component_type")
    @Column(name = "durability")
    private Map<ComponentType, Integer> durabilityVersions = new HashMap<>();

    public Manufacturer() {
    }

    public Manufacturer(String name) {
        this.name = name;
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

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public Map<ComponentType, Integer> getDurabilityVersions() {
        return durabilityVersions;
    }

    public void setDurabilityVersions(Map<ComponentType, Integer> durabilityVersions) {
        this.durabilityVersions = durabilityVersions;
    }
} 