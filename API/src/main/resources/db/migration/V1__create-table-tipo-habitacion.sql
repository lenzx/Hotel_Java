CREATE TABLE tipo_habitacion (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255),
    descripcion VARCHAR(255),
    numero_camas INTEGER,
    numero_piezas INTEGER
);