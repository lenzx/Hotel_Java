CREATE TABLE habitacion (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    numero INTEGER,
    tipo_habitacion_id BIGINT,
    activo BOOLEAN,
    FOREIGN KEY (tipo_habitacion_id) REFERENCES tipo_habitacion(id)
);