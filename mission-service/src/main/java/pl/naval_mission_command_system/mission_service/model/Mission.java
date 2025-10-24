package pl.naval_mission_command_system.mission_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

/**
 * Represents a military mission with metadata such as name, description,
 * difficulty, duration, criticality, and current status.
 */
@Entity
@Table(name = "missions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = "description")
public class Mission {

    /**
     * Unique identifier for the mission.
     * Using UUID for better uniqueness across microservices.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @EqualsAndHashCode.Include
    private String id;

    /**
     * Name of the mission.
     */
    @NotBlank
    @Column(name = "name", nullable = false, length = 100, unique = true)
    private String name;

    /**
     * Detailed description of the mission.
     */
    @NotBlank
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    /**
     * Difficulty level assigned to the mission.
     */
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "mission_difficulty", nullable = false, length = 20)
    private MissionDifficulty missionDifficulty;

    /**
     * Estimated duration of the mission in minutes.
     */
    @Column(name = "duration_minutes", nullable = false)
    private int durationMinutes;

    /**
     * Criticality level of the mission.
     */
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "mission_criticality", nullable = false, length = 20)
    private MissionCriticality missionCriticality;

    /**
     * Status of the mission lifecycle.
     */
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "status", nullable = false, length = 20)
    @Builder.Default
    private MissionStatus missionStatus = MissionStatus.PENDING;

    /**
     * Timestamp when the mission record was created.
     */
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /**
     * Timestamp of the last update to the mission record.
     */
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * Optional audit fields.
     */
    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;
}
