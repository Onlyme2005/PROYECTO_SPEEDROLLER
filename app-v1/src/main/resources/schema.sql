-- Tabla de usuarios
CREATE TABLE users (
    id SERIAL PRIMARY KEY,               -- o AUTO_INCREMENT en MySQL
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN DEFAULT TRUE
);

-- Tabla de roles
CREATE TABLE roles (
    id INT PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);


-- Relación usuario - rol
CREATE TABLE user_roles (
    user_id serial NOT NULL,
    PRIMARY KEY (user_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);



