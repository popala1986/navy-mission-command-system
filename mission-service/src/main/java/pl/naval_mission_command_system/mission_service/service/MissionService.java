package pl.naval_mission_command_system.mission_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.naval_mission_command_system.mission_service.model.Mission;
import pl.naval_mission_command_system.mission_service.repository.MissionRepository;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final Random random;

    public List<Mission> getAllMission() {
        return missionRepository.findAll();
    }

    public Mission getRandomMission() {
        List<Mission> allMission = missionRepository.findAll();
        return allMission.isEmpty() ? null : allMission.get(random.nextInt(allMission.size()));
    }

    public Mission saveMission(Mission mission) {
        return missionRepository.save(mission);
    }

}
