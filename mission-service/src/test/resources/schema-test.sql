DROP TABLE IF EXISTS missions;

CREATE TABLE IF NOT EXISTS missions (
  id VARCHAR(36) PRIMARY KEY,
  name VARCHAR(100) NOT NULL UNIQUE,
  description TEXT NOT NULL,
  mission_difficulty VARCHAR(20) NOT NULL,
  duration_minutes INT NOT NULL,
  mission_criticality VARCHAR(20) NOT NULL,
  status VARCHAR(20) NOT NULL,
  created_at DATETIME,
  updated_at DATETIME,
  created_by VARCHAR(255),
  updated_by VARCHAR(255)
);
