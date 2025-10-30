package pl.naval_mission_command_system.mission_service.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import pl.naval_mission_command_system.mission_service.model.Mission;
import pl.naval_mission_command_system.mission_service.model.MissionCriticality;
import pl.naval_mission_command_system.mission_service.model.MissionDifficulty;
import pl.naval_mission_command_system.mission_service.model.MissionStatus;
import pl.naval_mission_command_system.mission_service.repository.MissionRepository;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = {"/schema-test.sql", "/data-test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(statements = "DELETE FROM missions", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

class MissionRepositoryTest {

    @Autowired
    private MissionRepository missionRepository;

    @Test
    void shouldSaveAndFindMissionByID() {

        // given
        Mission mission = Mission.builder()
                .name("Alpha Golf")
                .description("Night Mission")
                .missionDifficulty(MissionDifficulty.CRITICAL)
                .durationMinutes(120)
                .missionCriticality(MissionCriticality.TOP_SECRET)
                .missionStatus(MissionStatus.PENDING)
                .createdBy("John Rambo")
                .updatedBy("Nicolas Cage")
                .build();

        // when
        Mission savedMission = missionRepository.save(mission);
        Optional<Mission> result = missionRepository.findById(savedMission.getId());

        //Then
        assertThat(result.isPresent());
        assertThat(result.get().getName()).isEqualTo(mission.getName());
        assertThat(result.get().getDescription()).isEqualTo(mission.getDescription());
        assertThat(result.get().getMissionDifficulty()).isEqualTo(MissionDifficulty.CRITICAL);
        assertThat(result.get().getDurationMinutes()).isEqualTo(120);
        assertThat(result.get().getMissionStatus()).isEqualTo(MissionStatus.PENDING);
        assertThat(result.get().getCreatedBy()).isEqualTo("John Rambo");
        assertThat(result.get().getUpdatedBy()).isEqualTo("Nicolas Cage");
    }
}