CREATE TABLE reserva (
    id INT PRIMARY KEY AUTO_INCREMENT,
    habitacion_id BigInt,
    cliente_id INT,
    fecha_inicio DATE,
    fecha_termino DATE,
    precio DECIMAL(10, 2),
    FOREIGN KEY (habitacion_id) REFERENCES habitacion(id),
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);