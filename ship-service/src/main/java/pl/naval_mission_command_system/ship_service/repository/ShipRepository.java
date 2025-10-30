package pl.naval_mission_command_system.ship_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.naval_mission_command_system.ship_service.model.Ship;

import java.util.Optional;

/**
 * Repository interface for accessing and managing {@link Ship} entities.
 * Uses Long as the type of the entity identifier.
 */
@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {

    Optional<Ship> findByCaptainUsername(String captainUserName);
}
