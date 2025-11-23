CREATE DATABASE base_agenda;
use base_agenda;

CREATE TABLE contactos (
   id INT PRIMARY KEY AUTO_INCREMENT,
   nombre VARCHAR(50) NOT NULL,
   apellido VARCHAR(50) NOT NULL,
   telefono VARCHAR(20) NOT NULL,
   correo VARCHAR(100) NOT NULL,
   fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO contactos (nombre, apellido, telefono, correo) VALUES ('Juan', 'Pérez', '81-1234-5678', 'juan.perez@email.com'), ('María', 'González', '81-8765-4321', 'maria.gonzalez@email.com'), ('Carlos', 'Rodríguez', '81-5555-1234', 'carlos.rodriguez@email.com');
