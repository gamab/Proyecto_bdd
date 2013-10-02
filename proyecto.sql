-- PROYECTO
CREATE DATABASE Proyecto;

CAN T RUN BECAUSE OF THIS LINE

-- #######################
-- ## CREACION DE TYPOS ##
-- #######################

-- Creacion de un dominio para el puntosCarnet
CREATE DOMAIN PUNTOS AS SMALLINT
CHECK (
	VALUE >= 0 AND VALUE <= 10
);

-- Creacion del typo T_MARCA que enumera las marcas de vehiculo.
CREATE TYPE T_MARCA AS ENUM ('FIAT','FORD','RENAULT');

-- ########################
-- ## CREACION DE TABLAS ##
-- ########################

-- Persona(*DNI,nombreYApellido,fechaNacimiento,direccion,telefono,puntosCarnet)
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

-- Vehiculo(*nro_patente,tipo,marca,modelo,ano)
CREATE TABLE Vehiculo(
nro_patente VARCHAR(6) NOT NULL,
tipo VARCHAR(30),
marca T_MARCA,
modelo VARCHAR(30),
ano INTEGER,
CONSTRAINT pk_vehiculo PRIMARY KEY (nro_patente)
);

-- Propietario(*nro_patente,*dni)
CREATE TABLE Propietario(
nro_patente VARCHAR(6) NOT NULL,
dni INTEGER NOT NULL,
CONSTRAINT pk_propietario PRIMARY KEY (nro_patente,dni),
CONSTRAINT fk_nro_patente_propietario FOREIGN KEY (nro_patente) REFERENCES vehiculo(nro_patente) ON DELETE CASACADE,
CONSTRAINT fk_dni_propietario FOREIGN KEY (dni) REFERENCES Persona(dni) ON DELETE CASACADE
);

-- Infraccion(*codigo,descripcion,valor)
CREATE TABLE Infraccion(
codigo INTEGER NOT NULL,
descripcion TEXT NOT NULL,
valor FLOAT,
CONSTRAINT ck_inf_poc CHECK (codigo > 0),
CONSTRAINT pk_infraccion PRIMARY KEY (codigo)
);

-- Multa(*nro_multa,nro_patente,codigo_infraccion,dni,hora,fecha,lugar)
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


-- ##########################
-- ## CREACION DE ARCHIVOS ##
-- ##########################

-- Persona(*DNI,nombreYApellido,fechaNacimiento,direccion,telefono,puntosCarnet)
INSERT INTO Persona
VALUES (11111111,'Gabriel Mabille','1992-12-05','Route de Sieuras - 09350 Meras - France','0636122000','10'),
(22222222,'Justine Compagnon','1993-09-30','10 Allée des Sciences Appliquées - 31400 Toulouse - France','0770141332','10'),
(33333333,'Brigitte Mabille','1960-04-12','La Grange - Route de Sieuras - 09350 Meras - France','0689865684','10'),
(44444444,'Papá Noel','280-12-25',' Myra - Distrito de Licia','xxxxxxxxxx','10');

-- Vehiculo(*nro_patente,tipo,marca,modelo,ano)
INSERT INTO Vehiculo
VALUES ('31XZ47','Coche','FORD','Focus',2010),
('11KH8O','Coche','FIAT','Punto',2008),
('09KMC5','Moto','RENAULT','K27',2003),
('583468','Reno de la Navidad','FORD','Pixie', 0001);

-- Propietario(*nro_patente,*dni)
INSERT INTO Propietario
VALUES ();

-- Infraccion(*codigo,descripcion,valor)
INSERT INTO Infraccion
VALUES ();

-- Multa(*nro_multa,nro_patente,codigo_infraccion,dni,hora,fecha,lugar)
INSERT INTO Multa
VALUES ();



