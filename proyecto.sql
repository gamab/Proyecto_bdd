-- PROYECTO

-- Creacion de un dominio para el puntosCarnet
CREATE DOMAIN PUNTOS AS SMALLINT
CHECK (
	VALUE >= 0 AND VALUE <= 10
);

-- Creacion del typo T_MARCA que enumera las marcas de vehiculo.
CREATE TYPE T_MARCA AS ENUM ('FIAT','FORD','RENAULT');

CREATE TABLE Persona(
dni INTEGER NOT NULL,
nombreYApellido VARCHAR(80) NOT NULL,
fechaNacimiento DATE NOT NULL,
direccion TEXT NOT NULL,
telefono VARCHAR(11) NOT NULL,
puntosCarnet 
CONSTRAINT ck_positive CHECK (dni > 0),
CONSTRAINT pk_dni PRIMARY KEY (dni);
);

CREATE TABLE vehiculo(
patente VARCHAR(6),
tipo 
marca
modelo
ano 
CONSTRAINT pk_patente PRIMARY KEY (patente)
);

CREATE TABLE Propietario(
patente VARCHAR(6),
dni INTEGER NOT NULL,
CONSTRAINT pk_propietario PRIMARY KEY (patente,dni),
CONSTRAINT fk_patente_propietario FOREIGN KEY (patente) REFERENCES vehiculo(patente),
CONSTRAINT fk_dni_propietario FOREIGN KEY (dni) REFERENCES Persona(dni)
);

CREATE TABLE Infraccion(
codigo INTEGER NOT NULL
descripcion TEXT NOT NULL
valor FLOAT 
CONSTRAINT ck_inf_poc CHECK (codigo > 0)
CONSTRAINT pk_codigo PRIMARY KEY (codigo)
);

CREATE TABLE Multa(
multa
patente
codigo_infraccion
dni INTEGER NOT NULL
hora 
fecha DATE NOT NULL
lugar 
CONSTRAINT pk_multa PRIMARY KEY (multa),
CONSTRAINT fk_patente_multa FOREIGN KEY (patente) REFERENCES Vehiculo(patente),
CONSTRAINT fk_dni_multa FOREIGN KEY (dni) REFERENCES Persona(dni),
CONSTRAINT fk_codigo_infraccion FOREIGN KEY (codigo_infraccion) REFERENCES Infraccion(codigo)
);










