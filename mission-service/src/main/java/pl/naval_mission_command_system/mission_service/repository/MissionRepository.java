package pl.naval_mission_command_system.mission_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.naval_mission_command_system.mission_service.model.Mission;

/**
 * Repository interface for accessing and managing {@link Mission} entities.
 * <p>
 * Extends {@link JpaRepository} to provide standard CRUD operations and query derivation based on method names.
 * Uses {@code String} as the type of the entity identifier, corresponding to UUID stored as text.
 * <p>
 * This repository is automatically implemented by Spring Data JPA at runtime.
 */
@Repository
public interface MissionRepository extends JpaRepository<Mission, String> {
}
