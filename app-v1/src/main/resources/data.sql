-- Inserta roles
USE rollerspeed;
INSERT INTO roles (name_string) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name_string) VALUES ('ROLE_INSTRUCTOR');
INSERT INTO roles (name_string) VALUES ('ROLE_ALUMNO');

-- Inserta usuarios
INSERT INTO users (username, password, enabled)
VALUES
('admin', '$2a$10$Tz7JZ9M1yZlO1KZKDQmBce/kZgU3KjLrdN6/qKjCHU4uC/Ez/EWBW', TRUE), -- admin123
('instructor', '$2a$10$TVjZqC7z0D7hIVafz5UyMeLoAmBrLZx7eu7zGqujDcPvUe.lAaeV6', TRUE), -- instructor123
('estudiante', '$2a$10$hH05sGQd04Q4gTgmEEc3tuhhQqX5pKp4thz8Gv8/BL7cDNbT1xXLe', TRUE); -- estudiante123

-- Relaciona usuarios con roles
INSERT INTO user_roles (user_id)
VALUES
(1), -- admin → ROLE_ADMIN
(2), -- instructor → ROLE_INSTRUCTOR
(3); -- estudiante → ROLE_ALUMNO
