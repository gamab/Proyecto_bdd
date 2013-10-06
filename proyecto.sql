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
DELETE FROM Persona;
INSERT INTO Persona
VALUES (11111111,'Gabriel Mabille','1992-12-05','Route de Sieuras - 09350 Meras - France','0636122000','10'),
(22222222,'Justine Compagnon','1993-09-30','10 Allée des Sciences Appliquées - 31400 Toulouse - France','0770141332','10'),
(33333333,'Brigitte Mabille','1960-04-12','La Grange - Route de Sieuras - 09350 Meras - France','0689865684','10'),
(44444444,'Papá Noel','280-12-25',' Myra - Distrito de Licia','xxxxxxxxxx','10'),
(55555555,'Delinquant','1990-06-07','190 - Rue Claude Bertholler - 34090 Montepellier - France','0467563214','1');


-- Vehiculo(*nro_patente,tipo,marca,modelo,ano)
DELETE FROM Vehiculo;
INSERT INTO Vehiculo
VALUES ('31XZ47','Coche','FORD','Focus',2010),
('11KH8O','Coche','FIAT','Punto',2008),
('09KMC5','Moto','RENAULT','K27',2003),
('583468','Reno de la Navidad','FORD','Pixie', 0001),
('078PC5','Moto','RENAULT','K289',2010),
('123ABC','Moto','FIAT','K290',2013);

-- Propietario(*nro_patente,*dni)
DELETE FROM Propietario;
INSERT INTO Propietario
VALUES ('31XZ47',11111111),
('11KH8O',22222222),
('09KMC5',33333333),
('583468',44444444),
('078PC5',55555555),
('123ABC',11111111);

-- http://www.taringa.net/posts/apuntes-y-monografias/12705800/Infracciones-de-Transito-CABA.html
-- Infraccion(*codigo,descripcion,valor)
DELETE FROM Infraccion;
INSERT INTO Infraccion
VALUES (6100,'Falta de Licencia',1000),
(6120,'Licencia Vencida',300),
(6170,'No exhibir documentación',1100),
(6270,'Cinturón de Seguridad',400),
(6490,'Teléfonos celulares, Mp4, etc',500),
(6540,'Circular en sentido contrario',5000),
(7000,'Circular sin casco (motos)',200),
(7050,'Violar Luz Roja',1200),
(7170,'Exceso de Velocidad',100),
(7270,'Conducir alcoholizado',300);

-- Multa(*nro_multa,nro_patente,codigo_infraccion,dni,hora,fecha,lugar)
DELETE FROM Multa;
INSERT INTO Multa
VALUES(1,'31XZ47',7050, 11111111,'13:25:00','2013-05-12','Rio Cuarto'),
(2,'11KH8O',6170,33333333,'04:25:00','2013-09-27','Toulouse'),
(3,'09KMC5',6490,44444444,'20:50:00','2012-12-23','Laponie'),
(4,'11KH8O',7050,33333333,'04:27:00','2007-12-27','Meras'),
(5,'09KMC5',7170,44444444,'10:10:00','2002-05-23','Narbonne'),
(6,'078PC5',6100,55555555,'17:20:00','2012-04-30','Montpellier'),
(7,'078PC5',6170,55555555,'18:30:00','2012-05-25','Montpellier'),
(8,'078PC5',6490,55555555,'19:40:00','2013-01-20','Montpellier'),
(9,'078PC5',6540,55555555,'20:50:00','2013-05-15','Montpellier'),
(10,'078PC5',7050,55555555,'22:22:00','2013-09-01','Montpellier'),
(11,'078PC5',7050,55555555,'22:23:00','2013-09-01','Montpellier');

-- ##########################
-- ##       CONSULTAS      ##
-- ##########################

-- Personas(dni,nombreYApellido,fechaNacimiento) que cometieron la infraccion de
-- "Exceso de Velocidad" y aun tienen mas de 5 puntos en su carnet.
-- /!\ Ejercicio inicial dice 10 puntos
SELECT dni,nombreYApellido,fechaNacimiento FROM Persona NATURAL JOIN Multa
WHERE (puntosCarnet >= 5) AND (codigo_infraccion = 7170);

-- Personas(dni,nombreYApellido) que fueron sancionadas mas de una vez con la misma infraccion
SELECT dni,nombreYApellido FROM Persona
WHERE dni IN (SELECT dni FROM Multa GROUP BY dni HAVING COUNT(codigo_infraccion)>1); 

SELECT dni,nombreYApellido FROM Persona NATURAL JOIN Multa
GROUP BY dni
HAVING COUNT(codigo_infraccion)>1;

-- Vehiculos que contieron todas las infracciones cuyo valor superan los 500 pesos.
SELECT nro_patente,SUM(valor) FROM Vehiculo 
NATURAL JOIN (Multa JOIN Infraccion ON (Multa.codigo_infraccion = Infraccion.codigo))
WHERE valor >= 500  GROUP BY nro_patente HAVING SUM(valor) >= (SELECT SUM(valor) FROM Infraccion WHERE valor >= 500) ;

SELECT SUM(valor) FROM Infraccion WHERE valor >= 500;

SELECT nro_patente,SUM(valor) FROM Vehiculo 
NATURAL JOIN (Multa JOIN Infraccion ON (Multa.codigo_infraccion = Infraccion.codigo))
WHERE valor >= 500 GROUP BY nro_patente;

-- Otra solucion
-- cuantas infracciones hay con valor que supera 500 pesos?
SELECT COUNT(*) FROM Infraccion
WHERE valor >= 500;
-- Quien tiene multas?
SELECT nro_patente,codigo_infraccion,valor FROM Multa 
JOIN Infraccion ON (Multa.codigo_infraccion = Infraccion.codigo);
-- Cuantas differentes tienen cada uno?
SELECT nro_patente,COUNT(DISTINCT codigo_infraccion) FROM Multa 
JOIN Infraccion ON (Multa.codigo_infraccion = Infraccion.codigo)
GROUP BY (nro_patente);
-- Quien tiene el numero de multas que superan 500 pesos
SELECT nro_patente,COUNT(DISTINCT codigo_infraccion) FROM Multa 
JOIN Infraccion ON (Multa.codigo_infraccion = Infraccion.codigo)
GROUP BY (nro_patente)
HAVING COUNT(DISTINCT codigo_infraccion) = (SELECT COUNT(*) FROM Infraccion WHERE valor >= 500);


-- Menores de 25 anos que nunca cometieron la infraccion por "Conducir alcoholizados en motocicleta"
SELECT DNI,nombreYApellido,fechaNacimiento FROM Persona
WHERE (date_part('year',age(current_date,fechaNacimiento)) <= 25)
EXCEPT
SELECT DNI,nombreYApellido,fechaNacimiento FROM Persona NATURAL JOIN (Multa NATURAL JOIN Vehiculo)
WHERE (codigo_infraccion = 7270) AND (tipo = 'Moto') ;

-- Explicaciones : (http://www.postgresql.org/docs/8.2/static/functions-datetime.html)
-- date_part('year',fecha) da el ano de la fecha
-- age(current_date,fechaNacimiento) da el tiempo entre la fecha de hoy y una fecha de nacimiento

-- Proponer 3 consultas donde 2 de ellas utilicen la clausula Group by

--0) ??
SELECT nro_patente,COUNT(nro_patente) ,SUM(valor) FROM Vehiculo 
NATURAL JOIN (Multa JOIN Infraccion ON (Multa.codigo_infraccion = Infraccion.codigo))
GROUP BY nro_patente HAVING SUM(valor) >= COUNT(nro_patente)*500;

--1) Proprietarios que tienen la misma edad que sus vehiculos.
SELECT Persona.*,Vehiculo.* FROM Persona JOIN (Propietario NATURAL JOIN Vehiculo)
ON (Persona.DNI = Propietario.DNI)
WHERE (date_part('year',age(current_date,fechaNacimiento)) = Vehiculo.ano);

--2) Proprietarios que tienen mas de un vehiculo
--y cuantos vehiculos tienen 
SELECT dni,COUNT(DISTINCT nro_patente) FROM Propietario NATURAL JOIN Vehiculo
GROUP BY (dni)
HAVING (COUNT(DISTINCT nro_patente) > 1)
ORDER BY COUNT(DISTINCT nro_patente) DESC;

--3) Ordenar personas por dinero pagado en multas
SELECT nombreYApellido,SUM(valor) FROM Persona NATURAL JOIN
(Multa JOIN Infraccion ON (Multa.codigo_infraccion = Infraccion.codigo))
GROUP BY (dni)
ORDER BY SUM(valor) DESC;

-- 4. Realizar un Programa Java que permita :
--	* Insertar un vehiculo
--	* Eliminar una persona
--	* Consultar una multa por su numero
--	* Listar todas las infracciones

-- Para eso vamos a crear un usuario.
CREATE ROLE programm WITH
LOGIN
UNENCRYPTED PASSWORD 'programm';

--	* Insertar un vehiculo
GRANT INSERT ON Vehiculo TO programm;
--	* Eliminar una persona
GRANT SELECT,DELETE ON Persona TO programm;
--	* Consultar una multa por su numero
-- GRANT SELECT (nro_multa) ON Multa TO programm;
GRANT SELECT ON Multa TO programm;
--	* Listar todas las infracciones
GRANT SELECT ON Infraccion TO programm;

-- Borrar todos los privilegios y el role
REVOKE ALL ON Vehiculo FROM programm;
REVOKE ALL ON Persona FROM programm;
REVOKE ALL ON Multa FROM programm;
REVOKE ALL ON Infraccion FROM programm;
DROP ROLE programm;
