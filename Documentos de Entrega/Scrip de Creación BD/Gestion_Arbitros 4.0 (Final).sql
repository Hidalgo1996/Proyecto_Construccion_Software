
-- -----------------------------------------------------
-- Schema Gestion_Arbitros
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS Gestion_Arbitros;
USE Gestion_Arbitros;

-- Crear Tablas
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS administrador (
  id_administrador INT NOT NULL,
  contrasenia VARCHAR(30) NOT NULL,
  nombre VARCHAR(30) NOT NULL,
  apellido VARCHAR(30) NOT NULL,
  email VARCHAR(30) NOT NULL,
  nombreUsuario VARCHAR(20) NOT NULL);


-- -----------------------------------------------------
-- Table secretaria
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS secretaria (
  id_secretaria INT NOT NULL,
  contrasenia VARCHAR(30) NOT NULL,
  nombre VARCHAR(30) NOT NULL,
  apellido VARCHAR(30) NOT NULL,
  email VARCHAR(30) NOT NULL,
  nombre_usuario VARCHAR(30) NOT NULL,
  administrador_id_administrador INT NOT NULL);
  

-- -----------------------------------------------------
-- Table calendario_partido
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS calendario_partido (
  id_calendario_partido INT NOT NULL,
  equipo_local VARCHAR(100) NOT NULL,
  equipo_rival VARCHAR(100) NOT NULL,
  lugar_partido VARCHAR(200) NOT NULL,
  hora_partido VARCHAR(30) NOT NULL,
  fecha_partido VARCHAR(30) NOT NULL,
  secretaria_id_secretaria INT NOT NULL);
  
  
-- -----------------------------------------------------
-- Table presidente
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS presidente (
  id_presidente INT NOT NULL,
  nombre VARCHAR(30) NOT NULL,
  apellido VARCHAR(30) NOT NULL,
  nombre_usuario VARCHAR(30) NOT NULL,
  contrasenia VARCHAR(30) NOT NULL,
  email VARCHAR(30) NOT NULL,
  administrador_id_administrador INT NOT NULL);


-- -----------------------------------------------------
-- Table arbitro
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS arbitro(
  id_arbitro INT NOT NULL,
  categoria VARCHAR(45) NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  apellido VARCHAR(45) NOT NULL,
  nombre_usuario VARCHAR(45) NOT NULL,
  contrasenia VARCHAR(45) NOT NULL,
  email VARCHAR(45) NULL,
  edad INT NOT NULL,
  nacionalidad VARCHAR(100) NULL,
  cantidad_partidos VARCHAR(45) NULL,
  administrador_id_administrador INT NOT NULL,
  secretaria_id_secretaria INT NOT NULL);
  

-- -----------------------------------------------------
-- Table acta_partido
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS acta_partido (
  id_acta_partido INT NOT NULL,
  hora_inicio_partido VARCHAR(30) NOT NULL,
  hora_fin_partido VARCHAR(30) NOT NULL,
  equipo_rival VARCHAR(50) NOT NULL,
  equipo_local VARCHAR(50) NOT NULL,
  duracion_partido INT NOT NULL,
  num_gole_equip_rival INT NOT NULL,
  num_gole_equip_local INT NOT NULL,
  equipo_ganador VARCHAR(50) NOT NULL,
  arbitro_id_arbitro1 INT NOT NULL);
  

-- -----------------------------------------------------
-- Table club
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS club (
  id_club INT NOT NULL,
  nombre_club VARCHAR(50) NOT NULL,
  email_club VARCHAR(50) NOT NULL,
  secretaria_id_secretaria INT NOT NULL);
  

-- -----------------------------------------------------
-- Table asistencia
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS asistencia (
  id_asistencia INT NOT NULL,
  fecha_asistencia VARCHAR(45) NOT NULL,
  estado VARCHAR(45) NOT NULL,
  arbitro_id_arbitro INT NOT NULL);



-- CREAR CLAVES PRIMARIAS (PK) --
alter table administrador add constraint pk_administrador
  PRIMARY KEY (id_administrador);

alter table secretaria add constraint pk_secretaria
	PRIMARY KEY (id_secretaria);

alter table calendario_partido add constraint pk_calendario_partido
	PRIMARY KEY (id_calendario_partido);

alter table presidente add constraint pk_presidente
	PRIMARY KEY (id_presidente);

alter table arbitro add constraint pk_arbitro
	PRIMARY KEY (id_arbitro);

alter table acta_partido add constraint pk_acta_partido
	PRIMARY KEY (id_acta_partido);

alter table club add constraint pk_club
	PRIMARY KEY (id_club);

alter table asistencia add constraint pk_asistencia
	PRIMARY KEY (id_asistencia);
    
  
-- CREAR CLAVES FORÁNEAS (FK)--

alter table secretaria add constraint  fk_secretaria_administrador1
    FOREIGN KEY (administrador_id_administrador)
    REFERENCES Gestion_Arbitros.administrador (id_administrador);

alter table calendario_partido add constraint fk_calendario_partido_secretaria1
	FOREIGN KEY (secretaria_id_secretaria)
    REFERENCES secretaria (id_secretaria);

alter table presidente add constraint fk_presidente_administrador1
    FOREIGN KEY (administrador_id_administrador)
    REFERENCES administrador (id_administrador);
    
alter table arbitro add constraint fk_arbitro_presidente1
    FOREIGN KEY (administrador_id_administrador)
    REFERENCES administrador (id_administrador);

alter table arbitro add constraint fk_arbitro_secretario2
    FOREIGN KEY (secretaria_id_secretaria)
    REFERENCES secretaria (id_secretaria);

alter table acta_partido add constraint fk_acta_partido_arbitro2
    FOREIGN KEY (arbitro_id_arbitro1)
    REFERENCES arbitro (id_arbitro);
     
alter table club add constraint fk_club_secretaria1
    FOREIGN KEY (secretaria_id_secretaria)
    REFERENCES secretaria (id_secretaria);

alter table asistencia add constraint pk_asistencia_arbitro1
    FOREIGN KEY (arbitro_id_arbitro)
    REFERENCES arbitro (id_arbitro);
    
    
-- INSERSION DE INFORMACIÓN

-- TABLA ADMINISTRADOR
INSERT INTO administrador VALUES ('100', 'ad01', 'Juan', 'Perez', 'juan.perez@gmail.com', 'Juan1940');

-- TABLA SECRETARIA
INSERT INTO secretaria VALUES ('200', '200se', 'Yago', 'Valles', 'yago.valles@gmail.com', 'Yago157', '100');
INSERT INTO secretaria VALUES ('201', '201se', 'Maria', 'Espino', 'maria.espino@gmail.com', 'Maria147', '100');
INSERT INTO secretaria VALUES ('202', '202se', 'Rufino', 'Morcillo', 'rufino.morcillo@gmail.com', 'Rufino167', '100');
INSERT INTO secretaria VALUES ('203', '203se', 'Bernardino', 'Sebastian', 'bernar.sebas@gmail.com', 'Bernar137', '100');
INSERT INTO secretaria VALUES ('204', '204se', 'Sara', 'Molina', 'sara.molina@gmail.com', 'Sara127', '100');

-- TABLA CALENDARIO_PARTIDO
INSERT INTO calendario_partido VALUES ('300', 'Argentina', 'Paises Bajos', 'Qatar', '10:15:00', '11/11/2022', '204');
INSERT INTO calendario_partido VALUES ('301', 'Ecuador', 'Colombia', 'Qatar', '12:45:00', '15/11/2022', '200');
INSERT INTO calendario_partido VALUES ('302', 'Francia', 'Peru', 'Qatar', '07:00:00', '15/11/2022', '201');
INSERT INTO calendario_partido VALUES ('303', 'Senegal', 'Estados Unidos', 'Qatar', '11:50:00', '19/11/2022', '203');
INSERT INTO calendario_partido VALUES ('304', 'Gales', 'Japon', 'Qatar', '08:30:00', '20/11/2022', '202');

-- TABLA PRESIDENTE
INSERT INTO presidente VALUES ('400', 'Gaspar', 'Rosello', 'Gaspar R.', '400pre', 'gaspar.rosero@gmail.com', '100');
INSERT INTO presidente VALUES ('401', 'Juan', 'Paez', 'Juan P.', '401pre', 'juan.paez@gmail.com', '100');
INSERT INTO presidente VALUES ('402', 'Rita', 'Morera', 'Rita M.', '402pre', 'rita.morena@gmail.com', '100');
INSERT INTO presidente VALUES ('403', 'Adam', 'Almeida', 'Adam A.', '403pre', 'adam.almeida@gmail.com', '100');
INSERT INTO presidente VALUES ('404', 'Santos', 'Lima', 'Santos L.', '404pre', 'santos.lima@gmail.com', '100');

-- TABLA ARBITRO
INSERT INTO arbitro VALUES ('500', 'Profesional', 'Pedro', 'Carrillo', 'Pedro C.', '500ar', 'pedro.carrillo@gmail.com', '36', 'Ecuatoriano', '45', '100', '201');
INSERT INTO arbitro VALUES ('501', 'Principiante', 'Eduard', 'Calderon', 'Eduard C.', '501ar', 'eduard.calderon@gmail.com', '34', 'Colombiano', '63', '100', '203');
INSERT INTO arbitro VALUES ('502', 'Principiante', 'Nicolae', 'Izquierdo', 'Nicolae I.', '502ar', 'nico.izquierdo@gmail.com', '29', 'Portugues', '51', '100', '204');
INSERT INTO arbitro VALUES ('503', 'Principiante', 'Saul', 'Llanos', 'Saul L.', '503ar', 'saul.llanos@gmail.com', '41', 'Frances', '94', '100', '200');
INSERT INTO arbitro VALUES ('504', 'Profesional', 'Vicente', 'Mendez', 'Vicente M.', '504ar', 'vicente.mendez@gmail.com', '38', 'Italiano', '60', '100', '202');

-- ACTA PARTIDO
INSERT INTO acta_partido VALUES ('600', '10:15:00', '11:45:00', 'Argentina', 'Paises Bajos', '95', '3', '1', 'Argentina', '504');
INSERT INTO acta_partido VALUES ('601', '12:45:00', '14:00:00', 'Ecuador', 'Colombia', '94', '1', '0', 'Ecuador', '500');
INSERT INTO acta_partido VALUES ('602', '07:00:00', '08:30:00', 'Francia', 'Peru', '96', '2', '0', 'Francia', '502');
INSERT INTO acta_partido VALUES ('603', '11:50:00', '13:20:00', 'Senegal', 'Estados Unidos', '98', '3', '2', 'Senegal', '503');
INSERT INTO acta_partido VALUES ('604', '08:30:00', '10:00:00', 'Gales', 'Japon', '93', '1', '4', 'Japon', '501');

-- CLUB
INSERT INTO club VALUES ('700', 'Ecuador', 'argentina.arg@gmail.com', '204');
INSERT INTO club VALUES ('701', 'Argentina', 'ecuador.ec@gmail.com', '200');
INSERT INTO club VALUES ('702', 'Francia', 'francia.fr@gmail.com', '202');
INSERT INTO club VALUES ('703', 'Senegal', 'senegal.se@gmail.com', '201');
INSERT INTO club VALUES ('704', 'Gales', 'gales.gl@gmail.com', '204');
INSERT INTO club VALUES ('705', 'Paises Bajos', 'paises.ba@gmail.com', '203');
INSERT INTO club VALUES ('706', 'Colombia', 'colombia.co@gmail.com', '200');
INSERT INTO club VALUES ('707', 'Peru', 'peru.p@gmail.com', '201');
INSERT INTO club VALUES ('708', 'Estados Unidos', 'estados.u@gmail.com', '203');
INSERT INTO club VALUES ('709', 'Japon', 'japon.j@gmail.com', '200');

-- ASISTENCIA
INSERT INTO asistencia VALUES ('800', '11/11/2022', 'Realizado', '500');
INSERT INTO asistencia VALUES ('801', '15/11/2022', 'Realizado', '501');
INSERT INTO asistencia VALUES ('802', '15/11/2022', 'Realizado', '502');
INSERT INTO asistencia VALUES ('803', '11:50:00', 'Realizado', '503');
INSERT INTO asistencia VALUES ('804', '20/11/2022', 'Realizado', '504');
