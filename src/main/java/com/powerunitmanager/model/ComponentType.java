package com.powerunitmanager.model;

public enum ComponentType {
    ICE("Internal Combustion Engine"),
    TC("Turbo Compressor"),
    MGU_H("Motor Generator Unit - Heat"),
    MGU_K("Motor Generator Unit - Kinetic"),
    ES("Energy Store"),
    CE("Control Electronics");

    private final String description;

    ComponentType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
} 