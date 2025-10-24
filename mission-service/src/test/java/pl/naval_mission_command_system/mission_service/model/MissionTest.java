package pl.naval_mission_command_system.mission_service.model;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



import static org.assertj.core.api.Assertions.assertThat;

class MissionTest {

    private static Validator validator;

    @BeforeAll
    static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    void shouldBuildMissionCorrectly() {
        Mission mission = Mission.builder()
                .name("Recon Alpha")
                .description("Observation of enemy positions")
                .missionDifficulty(MissionDifficulty.HIGH) //
                .durationMinutes(120)
                .missionCriticality(MissionCriticality.TOP_SECRET) //
                .status(MissionStatus.PENDING)
                .build();

        assertThat(mission.getName()).isEqualTo("Recon Alpha");
        assertThat(mission.getDescription()).contains("enemy");
        assertThat(mission.getMissionDifficulty()).isEqualTo(MissionDifficulty.HIGH);
        assertThat(mission.getDurationMinutes()).isEqualTo(120);
        assertThat(mission.getStatus()).isEqualTo(MissionStatus.PENDING);
        assertThat(mission.getMissionCriticality()).isEqualTo(MissionCriticality.TOP_SECRET);
    }
}