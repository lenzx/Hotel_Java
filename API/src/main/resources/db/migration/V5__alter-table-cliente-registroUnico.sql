ALTER TABLE Cliente
ADD CONSTRAINT UC_Cliente UNIQUE (correo_electronico, numero_telefono);