package pl.naval_mission_command_system.ship_service.unit;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.naval_mission_command_system.ship_service.model.MissionStatus;
import pl.naval_mission_command_system.ship_service.model.Ship;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ShipTest {

    private final Validator validator;

    public ShipTest() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();
    }

    @Test
    @DisplayName("should build ship correctly")
    void shouldBuildShipCorrectly() {
        Ship ship = Ship.builder()
                .shipName("ORP WIGRY")
                .captainUsername("Kowalski")
                .assignedMissionId(234L)
                .missionStatus(MissionStatus.PENDING)
                .build();

        assertThat(ship.getShipName()).isEqualTo("ORP WIGRY");
        assertThat(ship.getCaptainUsername()).isEqualTo("Kowalski");
        assertThat(ship.getAssignedMissionId()).isEqualTo(234L);
        assertThat(ship.getMissionStatus()).isEqualTo(MissionStatus.PENDING);
    }

    @Test
    @DisplayName("Should fail validation when shipName is blank")
    void shouldFailValidationWhenShipNameIsBlank() {
        Ship ship = Ship.builder()
                .shipName("  ")
                .captainUsername("Kowalski")
                .assignedMissionId(234L)
                .missionStatus(MissionStatus.PENDING)
                .build();

        Set<ConstraintViolation<Ship>> violations = validator.validate(ship);
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("shipName"));
    }

    @Test
    @DisplayName("Should fail validation when captainUsername is blank")
    void shouldFailValidationWhenCaptainUsernameIsBlank() {
        Ship ship = Ship.builder()
                .shipName("ORP WIGRY")
                .captainUsername("  ")
                .assignedMissionId(234L)
                .missionStatus(MissionStatus.PENDING)
                .build();

        Set<ConstraintViolation<Ship>> violations = validator.validate(ship);
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("captainUsername"));

    }

    @Test
    @DisplayName("Should fail validation when assigned mission id is Null")
    void shouldFailValidationWhenAssignedMissionIdIsNull() {
        Ship ship = Ship.builder()
                .shipName("ORP WIGRY")
                .captainUsername("Kowalski")
                .assignedMissionId(null)
                .missionStatus(MissionStatus.PENDING)
                .build();

        Set<ConstraintViolation<Ship>> violations = validator.validate(ship);
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("assignedMissionId"));
    }

    @Test
    @DisplayName("Should have default mission status pending")
    void shouldHaveDefaultMissionStatusPending() {
        Ship ship = Ship.builder()
                .shipName("ORP WIGRY")
                .captainUsername("Kowalski")
                .assignedMissionId(234L)
                .build();

        assertThat(ship.getMissionStatus()).isEqualTo(MissionStatus.PENDING);
    }


}
