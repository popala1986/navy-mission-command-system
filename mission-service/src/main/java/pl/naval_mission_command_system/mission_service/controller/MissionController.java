package pl.naval_mission_command_system.mission_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.naval_mission_command_system.mission_service.model.Mission;
import pl.naval_mission_command_system.mission_service.service.MissionService;

import java.util.List;

/**
 * REST controller responsible for handling HTTP requests related to military missions.
 * Provides endpoints for retrieving all missions, fetching a random mission,
 * and creating new missions.
 * Base path: /missions
 */
@RestController
@RequestMapping("missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    /**
     * Retrieves all missions stored in the database.
     *
     * @return a list of {@link Mission} entities
     */
    @GetMapping()
    public List<Mission> getAlMissions() {
        return missionService.getAllMission();
    }


    /**
     * Returns a randomly selected mission from the database.
     *
     * @return {@link ResponseEntity} containing a random {@link Mission} if available,
     *         or HTTP 204 No Content if no missions exist
     */
    @GetMapping("/random")
    public ResponseEntity<Mission> getRandomMission() {
        Mission mission = missionService.getRandomMission();
        return mission != null ? ResponseEntity.ok(mission) : ResponseEntity.noContent().build();
    }

    /**
     * Creates a new mission and persists it to the database.
     *
     * @param mission the {@link Mission} object to be created, validated before saving
     * @return {@link ResponseEntity} containing the saved {@link Mission} and HTTP 201 Created status
     */
    @PostMapping
    public ResponseEntity<Mission> createMission(@Valid @RequestBody Mission mission) {
        return ResponseEntity.status(HttpStatus.CREATED).body(missionService.saveMission(mission));
    }
}
