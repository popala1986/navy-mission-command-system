DROP TABLE IF EXISTS ship;

CREATE TABLE Ship (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    ship_name VARCHAR(255) NOT NULL,
    captain_username VARCHAR(255) NOT NULL,
    assigned_mission_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL
);