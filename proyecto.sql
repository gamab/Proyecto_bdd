-- PROYECTO

CAN T RUN BECAUSE OF THIS LINE

--####################
--## CREATING TYPES ##
--####################

-- Creacion de un dominio para el puntosCarnet
CREATE DOMAIN PUNTOS AS SMALLINT
CHECK (
	VALUE >= 0 AND VALUE <= 10
);

-- Creacion del typo T_MARCA que enumera las marcas de vehiculo.
CREATE TYPE T_MARCA AS ENUM ('FIAT','FORD','RENAULT');

--#####################
--## CREATING TABLES ##
--#####################


CREATE TABLE Persona(
dni INTEGER NOT NULL,
nombreYApellido VARCHAR(80) NOT NULL,
fechaNacimiento DATE NOT NULL,
direccion TEXT NOT NULL,
telefono VARCHAR(11) NOT NULL,
puntosCarnet PUNTOS NOT NULL,
CONSTRAINT ck_positive CHECK (dni > 0),
CONSTRAINT pk_persona PRIMARY KEY (dni)
);

CREATE TABLE Vehiculo(
nro_patente VARCHAR(6) NOT NULL,
tipo VARCHAR(30),
marca T_MARCA,
modelo VARCHAR(30),
ano INTEGER,
CONSTRAINT pk_vehiculo PRIMARY KEY (nro_patente)
);

CREATE TABLE Propietario(
nro_patente VARCHAR(6) NOT NULL,
dni INTEGER NOT NULL,
CONSTRAINT pk_propietario PRIMARY KEY (nro_patente,dni),
CONSTRAINT fk_nro_patente_propietario FOREIGN KEY (nro_patente) REFERENCES vehiculo(nro_patente) ON DELETE CASACADE,
CONSTRAINT fk_dni_propietario FOREIGN KEY (dni) REFERENCES Persona(dni) ON DELETE CASACADE
);

CREATE TABLE Infraccion(
codigo INTEGER NOT NULL,
descripcion TEXT NOT NULL,
valor FLOAT,
CONSTRAINT ck_inf_poc CHECK (codigo > 0),
CONSTRAINT pk_infraccion PRIMARY KEY (codigo)
);

CREATE TABLE Multa(
nro_multa INTEGER NOT NULL,
nro_patente VARCHAR(6) NOT NULL,
codigo_infraccion INTEGER NOT NULL,
dni INTEGER NOT NULL,
hora TIME,
fecha DATE NOT NULL,
lugar TEXT,
CONSTRAINT pk_multa PRIMARY KEY (nro_multa),
CONSTRAINT fk_patente_multa FOREIGN KEY (nro_patente) REFERENCES Vehiculo(nro_patente) ON DELETE RESTRICT,
CONSTRAINT fk_dni_multa FOREIGN KEY (dni) REFERENCES Persona(dni) ON DELETE CASCADE,
CONSTRAINT fk_codigo_infraccion_multa FOREIGN KEY (codigo_infraccion) REFERENCES Infraccion(codigo) ON DELETE CASCADE
);










