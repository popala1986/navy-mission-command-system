package pl.naval_mission_command_system.mission_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.naval_mission_command_system.mission_service.model.Mission;
import pl.naval_mission_command_system.mission_service.service.MissionService;

import java.util.List;

@RestController
@RequestMapping("missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @GetMapping()
    public List<Mission> getAlMissions() {
        return missionService.getAllMission();
    }

    @GetMapping("/random")
    public ResponseEntity<Mission> getRandomMission() {
        Mission mission = missionService.getRandomMission();
        return mission != null ? ResponseEntity.ok(mission) : ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Mission> createMission(@Valid @RequestBody Mission mission) {
        return ResponseEntity.status(HttpStatus.CREATED).body(missionService.saveMission(mission));
    }
}
