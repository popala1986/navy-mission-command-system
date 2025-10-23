package pl.naval_mission_command_system.mission_service.model;

/**
 * Enumeration representing the lifecycle status of a military mission.
 * <p>
 * Used for tracking mission progress within command and control systems.
 */
public enum MissionStatus {

    /**
     * Mission has been assigned but not yet started.
     */
    PENDING,

    /**
     * Mission is currently in progress.
     */
    IN_PROGRESS,

    /**
     * Mission has been completed successfully.
     */
    COMPLETED,

    /**
     * Mission has failed or was not completed successfully.
     */
    FAILED
}