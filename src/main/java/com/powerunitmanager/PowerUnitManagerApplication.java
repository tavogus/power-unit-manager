package com.powerunitmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PowerUnitManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(PowerUnitManagerApplication.class, args);
    }
} 