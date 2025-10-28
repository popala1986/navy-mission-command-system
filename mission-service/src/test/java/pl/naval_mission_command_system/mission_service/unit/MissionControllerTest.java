package pl.naval_mission_command_system.mission_service.unit;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.naval_mission_command_system.mission_service.controller.MissionController;
import pl.naval_mission_command_system.mission_service.model.Mission;
import pl.naval_mission_command_system.mission_service.model.MissionCriticality;
import pl.naval_mission_command_system.mission_service.model.MissionDifficulty;
import pl.naval_mission_command_system.mission_service.model.MissionStatus;
import pl.naval_mission_command_system.mission_service.service.MissionService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(MissionController.class)
class MissionControllerTest {

    @Autowired
    private  MockMvc mockMvc;

    @MockBean
    private MissionService missionService;

    @Test
    void shouldReturnAllMissions() throws Exception {

        //given
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

        //when
        when(missionService.getAllMission()).thenReturn(Arrays.asList(mission));

        //then
        mockMvc.perform(get("/missions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Alpha Golf"));
    }
}