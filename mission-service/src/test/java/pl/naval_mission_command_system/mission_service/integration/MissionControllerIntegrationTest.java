package pl.naval_mission_command_system.mission_service.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import pl.naval_mission_command_system.mission_service.model.Mission;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureWebTestClient
@Sql(scripts = "/schema-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/data-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(statements = "DELETE FROM missions", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class MissionControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("Should get all missions")
    void shouldGetAllMissions() throws Exception {
        webTestClient
                .get()
                .uri("/missions")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Mission.class)
                .consumeWith(response -> {
                    List<Mission> missions = response.getResponseBody();
                    missions.forEach(m -> System.out.println(m.getName()));
                    assertThat(missions).isNotEmpty();
                });
    }

    @Test
    @DisplayName("Should get all missions from test Database")
    void shouldGetAllMissionsFromTestDatabase() {
        webTestClient.get()
                .uri("/missions")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Mission.class)
                .consumeWith(response -> {
                    List<Mission> missions = response.getResponseBody();
                    assertThat(missions).isNotNull();
                    assertThat(missions).hasSize(3);
                    assertThat(missions)
                            .extracting(Mission::getName)
                            .containsExactlyInAnyOrder("Operation Falcon", "Recon Bravo", "Rescue Echo");
                });
    }
}
