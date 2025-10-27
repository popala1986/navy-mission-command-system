package pl.naval_mission_command_system.mission_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.naval_mission_command_system.mission_service.model.Mission;
import pl.naval_mission_command_system.mission_service.repository.MissionRepository;

import java.util.List;
import java.util.Random;

/**
 * Service class responsible for mission-related business operations
 */
@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final Random random;

    /**
     * Retrieves all mission stored i the database
     * @return a list of all {@link Mission} entities
     * */
    public List<Mission> getAllMission() {
        return missionRepository.findAll();
    }

    /**
     * Returns a randomly selected mission from the database
     * @return a list of all {@link Mission} entities
     */
    public Mission getRandomMission() {
        List<Mission> allRandomMission = missionRepository.findAll();
        return allRandomMission.isEmpty() ? null : allRandomMission.get(random.nextInt(allRandomMission.size()));
    }

    /**
     * Persists a mission entity to the database.
     * If the mission already exists, it will be updated.
     *
     * @param mission the {@link Mission} to save
     * @return the saved {@link Mission} entity
     */
    public Mission saveMission(Mission mission) {
        return missionRepository.save(mission);
    }

}
