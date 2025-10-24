package pl.naval_mission_command_system.mission_service.model;

/**
 * Represents the lifecycle status of a mission.
 */
public enum MissionStatus {
    PENDING,

    ASSIGNED,

    IN_PROGRESS,

    COMPLETED,

    FAILED,

    ABORTED
}