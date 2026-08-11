-- Create Database
CREATE DATABASE IF NOT EXISTS login_system 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

-- Use Database
USE login_system;

-- Create User Table
CREATE TABLE IF NOT EXISTS User (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(64) NOT NULL,
    role VARCHAR(10) NOT NULL DEFAULT 'User',
    INDEX idx_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Insert Admin User
-- Email: admin@example.com
-- Password: admin123
INSERT INTO User (name, email, password, role)
VALUES ('Administrator', 'admin@example.com',
'240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9', 'Admin')
ON DUPLICATE KEY UPDATE email = email;

-- Insert Sample Users
INSERT INTO User (name, email, password, role) VALUES
('John Doe', 'john@example.com',
'5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'User'),
('Jane Smith', 'jane@example.com',
'6ca13d52ca70c883e0f0bb101e425a89e8624de51db2d2392593af6a84118090', 'User');

-- Verify Data
SELECT * FROM User;
