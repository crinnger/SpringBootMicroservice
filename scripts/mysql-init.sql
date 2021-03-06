DROP DATABASE IF EXISTS springstatemachine;
DROP USER IF EXISTS `state_machine`@`%`;
CREATE DATABASE IF NOT EXISTS springstatemachine CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `state_machine`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `springstatemachine`.* TO `state_machine`@`%`;
FLUSH PRIVILEGES;