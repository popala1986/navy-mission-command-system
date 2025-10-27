package pl.naval_mission_command_system.mission_service.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.naval_mission_command_system.mission_service.model.Mission;
import pl.naval_mission_command_system.mission_service.model.MissionCriticality;
import pl.naval_mission_command_system.mission_service.model.MissionDifficulty;
import pl.naval_mission_command_system.mission_service.model.MissionStatus;
import pl.naval_mission_command_system.mission_service.repository.MissionRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MissionServiceTest {

    @Mock
    private MissionRepository missionRepository;

    @Mock
    private Random random;

    @InjectMocks
    private MissionService missionService;

    @Test
    @DisplayName("Should return all Missions")
    void shouldReturnAllMissions() {

        // given
        Mission mission = Mission.builder()
                .name("Recon Alpha")
                .description("Observation of enemy positions")
                .missionDifficulty(MissionDifficulty.HIGH)
                .durationMinutes(120)
                .missionCriticality(MissionCriticality.TOP_SECRET)
                .missionStatus(MissionStatus.PENDING)
                .build();

        Mission mission1 = Mission.builder()
                .name("Recon Bravo2")
                .description("Attack Enemy position")
                .missionDifficulty(MissionDifficulty.HIGH)
                .durationMinutes(120)
                .missionCriticality(MissionCriticality.TOP_SECRET)
                .missionStatus(MissionStatus.PENDING)
                .build();

        List<Mission> mockMissions = Arrays.asList(mission, mission1);

        // when
        when(missionRepository.findAll()).thenReturn(mockMissions);

        List<Mission> result = missionService.getAllMission();

        // then
        assertEquals(2, result.size());
        assertEquals("Recon Alpha", result.get(0).getName());
        assertEquals("Recon Bravo2", result.get(1).getName());
        verify(missionRepository, times(1)).findAll();
    }

    @DisplayName("Should return null when no missions are available")
    @Test
    void shouldReturnNullWhenNoMissionsAvailable() {
        // given
        when(missionRepository.findAll()).thenReturn(List.of());

        // when
        Mission result = missionService.getRandomMission();

        // then
        assertNull(result);
        verify(missionRepository).findAll();
    }

    @Test
    @DisplayName("Should return random mission from list")
    void shouldReturnRandomMissionFromList() {

        //given
        Mission mission3 = Mission.builder()
                .name("Zulu 5")
                .description("change position")
                .missionDifficulty(MissionDifficulty.LOW)
                .durationMinutes(340)
                .missionCriticality(MissionCriticality.MEDIUM)
                .missionStatus(MissionStatus.PENDING)
                .build();

        Mission mission4 = Mission.builder()
                .name("Delta 8 ")
                .description("relocation")
                .missionDifficulty(MissionDifficulty.MEDIUM)
                .durationMinutes(340)
                .missionCriticality(MissionCriticality.LOW)
                .missionStatus(MissionStatus.PENDING)
                .build();

        List<Mission> mockMissions = Arrays.asList(mission3, mission4);

        when(missionRepository.findAll()).thenReturn(mockMissions);
        when(random.nextInt(2)).thenReturn(0);

        //when
        Mission result = missionService.getRandomMission();

        //then
        assertNotNull(result);
        assertEquals("Zulu 5", result.getName());
        verify(missionRepository).findAll();
        verify(random).nextInt(2);

    }

    @Test
    @DisplayName("Should Save Mission Correctly")
    void shouldSaveMissionCorrectly() {

        //given
        Mission mission5 = Mission.builder()
                .name("Hotel 9")
                .description("Destroyed enemy")
                .missionDifficulty(MissionDifficulty.HIGH)
                .durationMinutes(340)
                .missionCriticality(MissionCriticality.TOP_SECRET)
                .missionStatus(MissionStatus.PENDING)
                .build();

        when(missionRepository.save(mission5)).thenReturn(mission5);

        //when
        Mission result = missionService.saveMission(mission5);

        //then
        assertEquals("Hotel 9", result.getName());
        verify(missionRepository).save(mission5);
    }
}
