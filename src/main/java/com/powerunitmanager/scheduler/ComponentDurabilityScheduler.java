package com.powerunitmanager.scheduler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.powerunitmanager.dto.ComponentDTO;
import com.powerunitmanager.service.ComponentService;

@Component
public class ComponentDurabilityScheduler {
    private static final Logger logger = LoggerFactory.getLogger(ComponentDurabilityScheduler.class);
    private final ComponentService componentService;

    public ComponentDurabilityScheduler(ComponentService componentService) {
        this.componentService = componentService;
    }

    @Scheduled(cron = "0 0 9 * * ?") // Executa todos os dias às 9h
    public void checkComponentDurability() {
        logger.info("Iniciando verificação diária de durabilidade dos componentes");
        
        List<ComponentDTO> lowDurabilityComponents = componentService.getComponentsWithLowDurability();
        
        if (!lowDurabilityComponents.isEmpty()) {
            logger.warn("Componentes com durabilidade crítica encontrados:");
            lowDurabilityComponents.forEach(component -> 
                logger.warn("Componente: {} - Tipo: {} - Durabilidade: {}%", 
                    component.name(), 
                    component.type(), 
                    component.currentDurability())
            );
        } else {
            logger.info("Nenhum componente com durabilidade crítica encontrado");
        }
    }
} 