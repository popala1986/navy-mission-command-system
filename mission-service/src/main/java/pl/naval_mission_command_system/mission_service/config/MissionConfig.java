package pl.naval_mission_command_system.mission_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * Configuration class for mission-related beans.
 */
@Configuration
public class MissionConfig {

    /**
     * Provides a shared Random instance for mission selection logic.
     *
     * @return a new {@link Random} bean
     */
    @Bean
    public Random random() {
        return new Random();
    }
}