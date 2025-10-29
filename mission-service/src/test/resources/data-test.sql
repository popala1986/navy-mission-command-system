INSERT INTO missions (
  id, name, description, mission_difficulty, duration_minutes,
  mission_criticality, status, created_by, updated_by
) VALUES
(
  'a1b2c3d4-e5f6-7890-abcd-1234567890ab',
  'Operation Falcon',
  'Secure the landing zone and establish perimeter.',
  'HIGH',
  180,
  'TOP_SECRET',
  'PENDING',
  'system',
  'system'
);

INSERT INTO missions (
  id, name, description, mission_difficulty, duration_minutes,
  mission_criticality, status, created_by, updated_by
) VALUES
(
  'b2c3d4e5-f6a7-8901-bcde-2345678901bc',
  'Recon Bravo',
  'Conduct reconnaissance in hostile territory.',
  'MEDIUM',
  120,
  'TOP_SECRET',
  'PENDING',
  'system',
  'system'
);

INSERT INTO missions (
  id, name, description, mission_difficulty, duration_minutes,
  mission_criticality, status, created_by, updated_by
) VALUES
(
  'c3d4e5f6-a7b8-9012-cdef-3456789012cd',
  'Rescue Echo',
  'Extract personnel from compromised zone.',
  'CRITICAL',
  240,
  'TOP_SECRET',
  'PENDING',
  'system',
  'system'
);
