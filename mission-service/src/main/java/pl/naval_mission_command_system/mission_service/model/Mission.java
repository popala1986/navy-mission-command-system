package pl.naval_mission_command_system.mission_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Represents a military mission with metadata such as name, description,
 * difficulty, duration, and current status.
 * <p>
 * Designed for use in mission-command or defense-critical systems.
 */
@Entity
@Table(name = "missions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mission {

    /**
     * Unique identifier for the mission.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

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
     * Difficulty level of the mission (e.g., LOW, MEDIUM, HIGH).
     */
    @Column(name = "difficulty", length = 20)
    private String difficulty;

    /**
     * Estimated duration of the mission in minutes.
     */
    @Column(name = "duration_minutes", nullable = false)
    private int durationMinutes;

    /**
     * Current status of the mission lifecycle.
     * Defaults to {@code PENDING}.
     */
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "status", nullable = false, length = 20)
    private MissionStatus status = MissionStatus.PENDING;

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
}