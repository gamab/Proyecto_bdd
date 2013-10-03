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
CONSTRAINT fk_nro_patente_propietario FOREIGN KEY (nro_patente) REFERENCES vehiculo(nro_patente) ON DELETE CASCADE,
CONSTRAINT fk_dni_propietario FOREIGN KEY (dni) REFERENCES Persona(dni) ON DELETE CASCADE
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
VALUES ('31XZ47',11111111),
('11KH8O',22222222),
('09KMC5',33333333),
('583468',44444444);

-- http://www.taringa.net/posts/apuntes-y-monografias/12705800/Infracciones-de-Transito-CABA.html
-- Infraccion(*codigo,descripcion,valor)
INSERT INTO Infraccion
VALUES (6100,'Falta de Licencia',1000),
(6120,'Licencia Vencida',300),
(6170,'No exhibir documentación',1100),
(6270,'Cinturón de Seguridad',400),
(6490,'Teléfonos celulares, Mp4, etc',500),
(6540,'Circular en sentido contrario',5000),
(7000,'Circular sin casco (motos)',200),
(7050,'Violar Luz Roja',1200);

-- Multa(*nro_multa,nro_patente,codigo_infraccion,dni,hora,fecha,lugar)
INSERT INTO Multa
VALUES (1,'31XZ47',6100, 11111111,'13:25:00','2013-05-12','Rio Cuarto'),
(2,'11KH8O',6120,33333333,'04:25:00','2013-09-27','Toulouse'),
(3,'09KMC5',6490,44444444,'20:50:00','2012-12-23','Laponie'),
(4,'11KH8O',6120,33333333,'04:27:00','2007-12-27','Meras');

-- ##########################
-- ##       CONSULTAS      ##
-- ##########################

-- Personas(dni,nombreYApellido,fechaNacimiento) que cometieron la infraccion de
-- "Exceso de Velocidad" y aun tienen mas de 10 puntos en su carnet.

-- Personas(dni,nombreYApellido) que fueron sancionadas mas de una vez con la misma infraccion
SELECT dni,nombreYApellido FROM Persona
WHERE dni IN (SELECT dni FROM Multa GROUP BY dni HAVING COUNT(codigo_infraccion)>1); 


-- Vehiculos que contieron todas las infrecciones cuyo valor superan los 500 pesos.

-- Menores de 25 anos que nunca cometieron la infraccion por "Conducir alcoholizados en motocicleta"

-- Proponer 3 consultas donde 2 de ellas utilicen la clausula Group by
