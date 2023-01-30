-- -----------------------------------------------------
-- ESQUEMA Gestion_Arbitros
-- -----------------------------------------------------
DROP DATABASE IF EXISTS Gestion_Arbitros;

CREATE SCHEMA IF NOT EXISTS Gestion_Arbitros;
USE Gestion_Arbitros;

-- -----------------------------------------------------
-- CREAR TABLAS
-- -----------------------------------------------------
-- TABLA ROL
-- -----------------------------------------------------
-- CREAR 4 ROLES (ARBITRO - SECRETARIA - PRESIDENTE - ADMINSTRADOR)
-- EMPIEZA CON VALORES PREDETERMINADOS EN 1
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS rol (
  id_rol INT NOT NULL AUTO_INCREMENT,
  nombre_rol VARCHAR(45) NOT NULL,
  create_at DATE NULL,
  delete_at DATE NULL,
  updated_at DATE NULL,
  estado VARCHAR(2) NULL,
  PRIMARY KEY (id_rol)
);

-- -----------------------------------------------------
-- TABLA USUARIO
-- CREAR 1 USUARIO CON CADA ROL
-- VALORES DE ID PREDETERMINADOS EN 200
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS usuario (
  id_usuario INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(45) NOT NULL,
  apellido VARCHAR(45) NOT NULL,
  nombre_usuario VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  contrasenia VARCHAR(45) NOT NULL,
  create_at DATE NULL,
  delete_at DATE NULL,
  updated_at DATE NULL,
  estado VARCHAR(2) NULL,
  rol_id_rol INT NOT NULL,
  PRIMARY KEY (id_usuario)
);

ALTER TABLE usuario AUTO_INCREMENT = 200;

ALTER TABLE usuario ADD CONSTRAINT fk_usuario_rol1
    FOREIGN KEY (rol_id_rol)    
    REFERENCES rol (id_rol);

-- -----------------------------------------------------
-- TABLA ARBITRO
-- VALORES DE ID PREDETERMINADOS EN 300
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS arbitro (
  id_arbitro INT NOT NULL AUTO_INCREMENT,
  categoria VARCHAR(45) NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  apellido VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  nombre_usuario VARCHAR(45) NOT NULL,
  contrasenia VARCHAR(45) NOT NULL,
  edad INT NOT NULL,
  nacionalidad VARCHAR(45) NOT NULL,
  cantidad_partidos INT NOT NULL,
  create_at DATE NULL,
  delete_at DATE NULL,
  updated_at DATE NULL,
  estado VARCHAR(2) NULL,
  PRIMARY KEY (id_arbitro)
);

ALTER TABLE arbitro AUTO_INCREMENT = 300;

-- -----------------------------------------------------
-- TABLA CLUB
-- VALORES DE ID PREDETERMINADOS EN 400
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS club(
  id_club INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(45) NOT NULL,
  director VARCHAR(45) NOT NULL,
  create_at DATE NULL,
  delete_at DATE NULL,
  updated_at DATE NULL,
  estado VARCHAR(2) NULL,
  PRIMARY KEY (id_club)
);

ALTER TABLE club AUTO_INCREMENT = 400;

-- -----------------------------------------------------
-- TABLA PARTIDO
-- VALORES DE ID PREDETERMINADOS EN 500
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS partido (
  id_partido INT NOT NULL AUTO_INCREMENT,
  club_id_local INT NOT NULL,
  club_id_rival INT NOT NULL,
  PRIMARY KEY (id_partido)
);
  
ALTER TABLE partido AUTO_INCREMENT = 500;
  
ALTER TABLE partido ADD CONSTRAINT fk_partido_club1
    FOREIGN KEY (club_id_local)    
    REFERENCES club (id_club);

ALTER TABLE partido ADD CONSTRAINT fk_partido_club2
    FOREIGN KEY (club_id_rival)
    REFERENCES club (id_club);

-- -----------------------------------------------------
-- TABLA SORTEO
-- VALORES DE ID PREDETERMINADOS EN 600
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS sorteo (
  id_sorteo INT NOT NULL AUTO_INCREMENT,
  fecha_sorteo DATE NOT NULL,
  create_at DATE NULL,
  delete_at DATE NULL,
  update_at DATE NULL,
  estado VARCHAR(2) NULL,
  arbitro_id_arbitro INT NOT NULL,
  partido_id_partido INT NOT NULL,
  arbitro_id_sustituto INT NOT NULL,
  PRIMARY KEY (`id_sorteo`)
);

ALTER TABLE sorteo AUTO_INCREMENT = 600;

ALTER TABLE sorteo ADD CONSTRAINT fk_sorteo_arbitro1
    FOREIGN KEY (arbitro_id_arbitro)
    REFERENCES arbitro (id_arbitro);
    
ALTER TABLE sorteo ADD CONSTRAINT fk_sorteo_partido1
    FOREIGN KEY (partido_id_partido)
    REFERENCES partido (id_partido);
    
ALTER TABLE sorteo ADD CONSTRAINT fk_sorteo_arbitro2
    FOREIGN KEY (arbitro_id_sustituto)
    REFERENCES arbitro (id_arbitro);

-- -----------------------------------------------------
-- TABLE AGENDA
-- VALORES DE ID PREDETERMINADOS EN 700
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS agenda (
  id_agenda INT NOT NULL AUTO_INCREMENT,
  fecha_partido DATE NOT NULL,
  lugar_partido VARCHAR(45) NOT NULL,
  hora_partido TIME NOT NULL COMMENT 'Valores de time (hora: minuto: segundo)',
  create_at DATE NULL,
  delete_at DATE NULL,
  updated_at DATE NULL,
  estado VARCHAR(2) NULL,
  partido_id_partido INT NOT NULL,
  PRIMARY KEY (id_agenda)
);
  
ALTER TABLE agenda AUTO_INCREMENT = 700;
  
ALTER TABLE agenda ADD CONSTRAINT fk_agenda_partido1
    FOREIGN KEY (partido_id_partido)
    REFERENCES partido (id_partido);

-- -----------------------------------------------------
-- TABLA ACTA_PARTIDO
-- VALORES DE ID PREDETERMINADOS EN 800
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS acta_partido (
  id_acta_partido INT NOT NULL AUTO_INCREMENT,
  codigo_acta INT NOT NULL,
  fecha_emision_acta DATE NOT NULL,
  hora_inicio_partido TIME NOT NULL,
  hora_fin_partido TIME NOT NULL,
  equipo_local VARCHAR(45) NOT NULL,   -- tal vez deberia omitirse ¿?
  equipo_rival VARCHAR(45) NOT NULL,   -- tal vez deberia omitirse ¿?
  duracion_partido TIME NOT NULL,
  num_gol_equipo_local INT NOT NULL,
  num_gol_equipo_rival INT NOT NULL,
  equipo_ganador VARCHAR(45) NOT NULL,
  create_at DATE NULL,
  delete_at DATE NULL,
  update_at DATE NULL,
  estado VARCHAR(2) NULL,
  partido_id_partido INT NOT NULL,
  PRIMARY KEY (id_acta_partido)
);
  
ALTER TABLE acta_partido AUTO_INCREMENT = 800;
  
ALTER TABLE acta_partido ADD CONSTRAINT fk_acta_partido_partido1
    FOREIGN KEY (partido_id_partido)
    REFERENCES partido (id_partido);

-- -----------------------------------------------------
-- TABLA ASISTENCIA
-- VALORES DE ID PREDETERMINADOS EN 900
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS asistencia (
  id_asistencia INT NOT NULL AUTO_INCREMENT,
  partido VARCHAR(45) NOT NULL,
  lugar VARCHAR(45) NOT NULL,
  fecha_encuentro DATE NOT NULL,
  asistencia TINYINT NOT NULL COMMENT 'Tipo de dato BOOLEAN',
  estado VARCHAR(2) NULL,
  arbitro_id_arbitro INT NOT NULL,
  PRIMARY KEY (id_asistencia)
);

ALTER TABLE asistencia AUTO_INCREMENT = 900;
  
ALTER TABLE asistencia ADD CONSTRAINT fk_asistencia_arbitro1
    FOREIGN KEY (arbitro_id_arbitro)
    REFERENCES arbitro (id_arbitro);

-- ------------------------ FIN DEL ESQUEMA


-- ------------------------ PROCEDIMIENTOS ALAMCENADOS ------------------------
-- -----------------------------------------------------
-- PROCEDURE_CRUD_ROL
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS PR_insertar_rol;
DELIMITER $$
CREATE PROCEDURE PR_insertar_rol(
    IN xNombre_rol VARCHAR(45), 
    IN xCreate_at DATE, 
    IN xDelete_at DATE, 
    IN xUpdated_at DATE, 
    IN xEstado VARCHAR(2)
    )
BEGIN
    INSERT INTO rol (nombre_rol, create_at, delete_at, updated_at, estado)
    VALUES (xNombre_rol, xCreate_at, xDelete_at, xUpdated_at, xEstado);
END $$
DELIMITER ;
CALL PR_insertar_rol('arbitro', '2023-01-28', NULL, NULL, 'A');        -- -> 01
CALL PR_insertar_rol('secretaria', '2023-01-28', NULL, NULL, 'A');     -- -> 02
CALL PR_insertar_rol('presidente', '2023-01-28', NULL, NULL, 'A');     -- -> 03
CALL PR_insertar_rol('administrador', '2023-01-28', NULL, NULL, 'A');  -- -> 04
-- CALL PR_insertar_rol(?, ?, ?, ?, ?);           -> llamada en Java

DROP PROCEDURE IF EXISTS PR_consultar_rol;
DELIMITER $$
CREATE PROCEDURE PR_consultar_rol(IN xId_rol INT)
BEGIN
    SELECT * FROM rol WHERE id_rol = xId_rol;
END $$
DELIMITER ;
CALL PR_consultar_rol(1);
-- CALL PR_consultar_rol(?);           -> llamada en Java

DROP PROCEDURE IF EXISTS PR_modificar_rol;
DELIMITER $$
CREATE PROCEDURE PR_modificar_rol(
    IN xId_rol INT,
    IN xNombre_rol VARCHAR(45), 
    IN xCreate_at DATE, 
    IN xDelete_at DATE, 
    IN xUpdated_at DATE, 
    IN xEstado VARCHAR(2)
    )
BEGIN
    UPDATE rol SET nombre_rol = xNombre_rol, create_at = xCreate_at, delete_at = xDelete_at, updated_at = xUpdated_at, estado = xEstado 
    WHERE id_rol = xId_rol;
END $$
DELIMITER ;
CALL PR_modificar_rol(1, 'arbitro', '2023-01-28', NULL, NULL, 'A');
-- CALL PR_modificar_rol(?, ?, ?, ?, ?, ?)      -> llamada en Java

DROP PROCEDURE IF EXISTS PR_eliminar_rol;
DELIMITER $$
CREATE PROCEDURE PR_eliminar_rol(IN xId_rol INT)
BEGIN
    DELETE FROM rol WHERE id_rol = xId_rol;
END $$
DELIMITER ;
-- CALL PR_eliminar_rol(1);
-- CALL PR_eliminar_rol(?);     -> llamada en Java


-- -----------------------------------------------------
-- PROCEDURE_CRUD_USUARIO
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS PR_insertar_usuario;
DELIMITER $$
CREATE PROCEDURE PR_insertar_usuario(
    IN xNombre VARCHAR(45), 
    IN xApellido VARCHAR(45), 
    IN xNombre_usuario VARCHAR(45), 
    IN xEmail VARCHAR(45), 
    IN xContrasenia VARCHAR(45), 
    IN xCreate_at DATE, 
    IN xDelete_at DATE, 
    IN xUpdated_at DATE, 
    IN xEstado VARCHAR(2),
    IN xRol_id_rol INT
    )
BEGIN
    INSERT INTO usuario (nombre, apellido, nombre_usuario, email, contrasenia, create_at, delete_at, updated_at, estado, rol_id_rol)
    VALUES (xNombre, xApellido, xNombre_usuario, xEmail, xContrasenia, xCreate_at, xDelete_at, xUpdated_at, xEstado, xRol_id_rol);
END $$
DELIMITER ;
CALL PR_insertar_usuario('Juan', 'Perez', 'juanperez', 'juanperez@example.com', '123', '2023-01-28', NULL, NULL, 'A', 1);          -- 200
CALL PR_insertar_usuario('Jose', 'Perez', 'joseperez', 'joseperez@example.com', '123', '2023-01-28', NULL, NULL, 'A', 2);          -- 201
CALL PR_insertar_usuario('Julio', 'Perez', 'julioperez', 'julioperez@example.com', '123', '2023-01-28', NULL, NULL, 'A', 3);       -- 202
CALL PR_insertar_usuario('Juanito', 'Perez', 'juanitoperez', 'juanitoperez@example.com', '123', '2023-01-28', NULL, NULL, 'A', 4); -- 203
-- CALL PR_insertar_usuario(?, ?, ?, ?, ?, ?);           -> llamada en Java

DROP PROCEDURE IF EXISTS PR_consultar_usuario;
DELIMITER $$
CREATE PROCEDURE PR_consultar_usuario(IN xId_usuario INT)
BEGIN
    SELECT * FROM usuario WHERE id_usuario = xId_usuario;
END $$
DELIMITER ;
CALL PR_consultar_usuario(200);
-- CALL PR_consultar_rol(?);           -> llamada en Java

DROP PROCEDURE IF EXISTS PR_modificar_usuario;
DELIMITER $$
CREATE PROCEDURE PR_modificar_usuario(
    IN xId_usuario INT,
    IN xNombre VARCHAR(45), 
    IN xApellido VARCHAR(45), 
    IN xNombre_usuario VARCHAR(45), 
    IN xEmail VARCHAR(45), 
    IN xContrasenia VARCHAR(45), 
    IN xCreate_at DATE, 
    IN xDelete_at DATE, 
    IN xUpdated_at DATE, 
    IN xEstado VARCHAR(2),
    IN xRol_id_rol INT
    )
BEGIN
    UPDATE usuario SET nombre = xNombre, apellido = xApellido, nombre_usuario = xNombre_usuario, email = xEmail, contrasenia = xContrasenia, 
    create_at = xCreate_at, delete_at = xDelete_at, updated_at = xUpdated_at, estado = xEstado, rol_id_rol = xRol_id_rol 
    WHERE id_usuario = xId_usuario;
END $$
DELIMITER ;
CALL PR_modificar_usuario(200, 'Juan', 'Perez', 'juanperez', 'juanperez@example.com', 'password123', '2023-01-28', NULL, NULL, 'A', 1);
-- CALL PR_modificar_usuario(?, ?, ?, ?, ?, ?, ?);         -> llamada en Java

DROP PROCEDURE IF EXISTS PR_eliminar_usuario;
DELIMITER $$
CREATE PROCEDURE PR_eliminar_usuario(IN xId_usuario INT)
BEGIN
    DELETE FROM usuario WHERE id_usuario = xId_usuario;
END $$
DELIMITER ;
-- CALL PR_eliminar_usuario(200);
-- CALL PR_eliminar_usuario(?);           -> llamada en Java


-- -----------------------------------------------------
-- PROCEDURE_CRUD_ARBITRO
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS PR_insertar_arbitro;
DELIMITER $$
CREATE PROCEDURE PR_insertar_arbitro(
    IN xCategoria VARCHAR(45), 
    IN xNombre VARCHAR(45), 
    IN xApellido VARCHAR(45), 
    IN xEmail VARCHAR(45), 
    IN xNombre_usuario VARCHAR(45), 
    IN xContrasenia VARCHAR(45), 
    IN xEdad INT, 
    IN xNacionalidad VARCHAR(45), 
    IN xCantidad_partidos INT, 
    IN xCreate_at DATE, 
    IN xDelete_at DATE, 
    IN xUpdated_at DATE, 
    IN xEstado VARCHAR(2)
    )
BEGIN
    INSERT INTO arbitro (categoria, nombre, apellido, email, nombre_usuario, contrasenia, edad, nacionalidad, cantidad_partidos, 
    create_at, delete_at, updated_at, estado)
    VALUES (xCategoria, xNombre, xApellido, xEmail, xNombre_usuario, xContrasenia, xEdad, xNacionalidad, xCantidad_partidos, 
    xCreate_at, xDelete_at, xUpdated_at, xEstado);
END $$
DELIMITER ;
CALL PR_insertar_arbitro('Profesional', 'Pedro', 'Santamaria', 'pedroSantamaria@example.com', 'PedroSantamaria', '123', 25, 'Venezolano', 
30, '2023-01-28', NULL, NULL, 'A');    -- 300
CALL PR_insertar_arbitro('Profesional', 'Miquel', 'Villalba', 'miquelVillalba@example.com', 'MiquelVillalba', '123', 25, 'Ecuatoriano', 
30, '2023-01-28', NULL, NULL, 'A');     -- 301
-- CALL PR_insertar_arbitro(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);           -> llamada en Java

DROP PROCEDURE IF EXISTS PR_consultar_arbitro;
DELIMITER $$
CREATE PROCEDURE PR_consultar_arbitro(IN xId_arbitro INT)
BEGIN
    SELECT * FROM arbitro WHERE id_arbitro = xId_arbitro;
END $$
DELIMITER ;
CALL PR_consultar_arbitro(300);
-- CALL PR_consultar_arbitro(?);           -> llamada en Java

DROP PROCEDURE IF EXISTS PR_modificar_arbitro;
DELIMITER $$
CREATE PROCEDURE PR_modificar_arbitro(
    IN xId_arbitro INT,
    IN xCategoria VARCHAR(45), 
    IN xNombre VARCHAR(45), 
    IN xApellido VARCHAR(45), 
    IN xEmail VARCHAR(45), 
    IN xNombre_usuario VARCHAR(45), 
    IN xContrasenia VARCHAR(45), 
    IN xEdad INT, 
    IN xNacionalidad VARCHAR(45), 
    IN xCantidad_partidos INT, 
    IN xCreate_at DATE, 
    IN xDelete_at DATE, 
    IN xUpdated_at DATE, 
    IN xEstado VARCHAR(2)
    )
BEGIN
    UPDATE arbitro SET categoria = xCategoria, nombre = xNombre, apellido = xApellido, email = xEmail, nombre_usuario = xNombre_usuario, 
    contrasenia = xContrasenia, edad = xEdad, nacionalidad = xNacionalidad, cantidad_partidos = xCantidad_partidos, create_at = xCreate_at, 
    delete_at = xDelete_at, updated_at = xUpdated_at, estado = xEstado 
    WHERE id_arbitro = xId_arbitro;
END $$
DELIMITER ;
CALL PR_modificar_arbitro(300, 'Profesional', 'Pedro', 'Santamaria', 'pedroSantamaria@example.com', 'PedroSantamaria', '123', 25, 'Venezolano', 
30, '2023-01-28', NULL, NULL, 'A');
-- CALL PR_modificar_arbitro(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);               -> llamada en Java

DROP PROCEDURE IF EXISTS PR_eliminar_arbitro;
DELIMITER $$
CREATE PROCEDURE PR_eliminar_arbitro(IN xId_arbitro INT)
BEGIN
    DELETE FROM arbitro WHERE id_arbitro = xId_arbitro;
END $$
DELIMITER ;
-- CALL PR_eliminar_arbitro(300);
-- CALL PR_eliminar_arbitro(?);           -> llamada en Java


-- -----------------------------------------------------
-- PROCEDURE_CRUD_CLUB
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS PR_insertar_club;
DELIMITER $$
CREATE PROCEDURE PR_insertar_club(
    IN xNombre VARCHAR(45),
    IN xDirector VARCHAR(45),
    IN xCreate_at DATE,
    IN xDelete_at DATE,
    IN xUpdated_at DATE,
    IN xEstado VARCHAR(2)
    )
BEGIN
    INSERT INTO club (nombre, director, create_at, delete_at, updated_at, estado)
    VALUES (xNombre, xDirector, xCreate_at, xDelete_at, xUpdated_at, xEstado);
END $$
DELIMITER ;
CALL PR_insertar_club('club_Ecuador', 'Dir Juan', '2023-01-28', NULL, NULL, 'A');   -- 400
CALL PR_insertar_club('club_Alemania', 'Dir Diego', '2023-01-28', NULL, NULL, 'A');  -- 401
CALL PR_insertar_club('club_Argentina', 'Dir Kevin', '2023-01-28', NULL, NULL, 'A'); -- 402
CALL PR_insertar_club('club_Venezuela', 'Dir Carlos', '2023-01-28', NULL, NULL, 'A'); -- 403
-- CALL PR_insertar_club(?, ?, ?, ?, ?, ?);           -> llamada en Java

DROP PROCEDURE IF EXISTS PR_consultar_club;
DELIMITER $$
CREATE PROCEDURE PR_consultar_club(IN xId_club INT)
BEGIN
    SELECT * FROM club WHERE id_club = xId_club;
END $$
DELIMITER ;
CALL PR_consultar_club(400);
-- CALL PR_consultar_club(?);           -> llamada en Java

DROP PROCEDURE IF EXISTS PR_modificar_club;
DELIMITER $$
CREATE PROCEDURE PR_modificar_club(
    IN xId_club INT,
    IN xNombre VARCHAR(45),
    IN xDirector VARCHAR(45),
    IN xCreate_at DATE,
    IN xDelete_at DATE,
    IN xUpdated_at DATE,
    IN xEstado VARCHAR(2)
    )
BEGIN
    UPDATE club SET nombre = xNombre, director = xDirector, create_at = xCreate_at, delete_at = xDelete_at, 
    updated_at = xUpdated_at, estado = xEstado 
    WHERE id_club = xId_club;
END $$
DELIMITER ;
CALL PR_modificar_club(400, 'club_Ecuador', 'Dir Juan', '2023-01-28', NULL, NULL, 'A');   -- 400
-- CALL PR_modificar_club(?, ?, ?, ?, ?, ?, ?);           -> llamada en Java

DROP PROCEDURE IF EXISTS PR_eliminar_club;
DELIMITER $$
CREATE PROCEDURE PR_eliminar_club(IN xId_club INT)
BEGIN
    DELETE FROM club WHERE id_club = xId_club;
END $$
DELIMITER ;
-- CALL PR_eliminar_club(400);
-- CALL PR_eliminar_club(?);           -> llamada en Java


-- -----------------------------------------------------
-- PROCEDURE_CRUD_PARTIDO
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS PR_insertar_partido;
DELIMITER $$
CREATE PROCEDURE PR_insertar_partido(
    IN xClub_id_local INT,
    IN xClub_id_rival INT
    )
BEGIN
    INSERT INTO partido (club_id_local, club_id_rival)
    VALUES (xClub_id_local, xClub_id_rival);
END $$
DELIMITER ;
CALL PR_insertar_partido(400, 403); -- 500
-- CALL PR_insertar_partido(?, ?);           -> llamada en Java

DROP PROCEDURE IF EXISTS PR_consultar_partido;
DELIMITER $$
CREATE PROCEDURE PR_consultar_partido(IN xId_partido INT)
BEGIN
    SELECT * FROM partido WHERE id_partido = xId_partido;
END $$
DELIMITER ;
CALL PR_consultar_partido(500);
-- CALL PR_consultar_partido(?);           -> llamada en Java

DROP PROCEDURE IF EXISTS PR_modificar_partido;
DELIMITER $$
CREATE PROCEDURE PR_modificar_partido(
    IN xId_partido INT,
    IN xClub_id_local INT,
    IN xClub_id_rival INT
    )
BEGIN
    UPDATE partido SET club_id_local = xClub_id_local, club_id_rival = xClub_id_rival 
    WHERE id_partido = xId_partido;
END $$
DELIMITER ;
CALL PR_modificar_partido(500, 400, 403);
-- CALL PR_modificar_partido(?, ?, ?);           -> llamada en Java

DROP PROCEDURE IF EXISTS PR_eliminar_partido;
DELIMITER $$
CREATE PROCEDURE PR_eliminar_partido(IN xId_partido INT)
BEGIN
    DELETE FROM partido WHERE id_partido = xId_partido;
END $$
DELIMITER ;
-- CALL PR_eliminar_partido(500);
-- CALL PR_eliminar_partido(?);           -> llamada en Java


-- -----------------------------------------------------
-- PROCEDURE_CRUD_SORTEO
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS PR_insertar_sorteo;
DELIMITER $$
CREATE PROCEDURE PR_insertar_sorteo(
    IN xFecha_sorteo DATE,
    IN xCreate_at DATE,
    IN xDelete_at DATE,
    IN xUpdate_at DATE,
    IN xEstado VARCHAR(2),
    IN xArbitro_id_arbitro INT,
    IN xPartido_id_partido INT,
    IN xArbitro_id_sustituto INT
    )
BEGIN
    INSERT INTO sorteo (fecha_sorteo, create_at, delete_at, update_at, estado, arbitro_id_arbitro, partido_id_partido, arbitro_id_sustituto)
    VALUES (xFecha_sorteo, xCreate_at, xDelete_at, xUpdate_at, xEstado, xArbitro_id_arbitro, xPartido_id_partido, xArbitro_id_sustituto);
END $$
DELIMITER ;
CALL PR_insertar_sorteo('2023-01-10', '2023-01-29', NULL, NULL, 'A', 300, 500, 301);     -- 600
-- CALL PR_insertar_sorteo(?, ?, ?, ?, ?, ?, ?, ?);           -> llamada en Java

DROP PROCEDURE IF EXISTS PR_consultar_sorteo;
DELIMITER $$
CREATE PROCEDURE PR_consultar_sorteo(IN xId_sorteo INT)
BEGIN
    SELECT * FROM sorteo WHERE id_sorteo = xId_sorteo;
END $$
DELIMITER ;
CALL PR_consultar_sorteo(600);
-- CALL PR_consultar_sorteo(?);

DROP PROCEDURE IF EXISTS PR_modificar_sorteo;
DELIMITER $$
CREATE PROCEDURE PR_modificar_sorteo(
    IN xId_sorteo INT,
    IN xFecha_sorteo DATE,
    IN xCreate_at DATE,
    IN xDelete_at DATE,
    IN xUpdate_at DATE,
    IN xEstado VARCHAR(2),
    IN xArbitro_id_arbitro INT,
    IN xPartido_id_partido INT,
    IN xArbitro_id_sustituto INT
    )
BEGIN
    UPDATE sorteo SET fecha_sorteo = xFecha_sorteo, create_at = xCreate_at, delete_at = xDelete_at, update_at = xUpdate_at, estado = xEstado, 
    arbitro_id_arbitro = xArbitro_id_arbitro, partido_id_partido = xPartido_id_partido, arbitro_id_sustituto = xArbitro_id_sustituto
    WHERE id_sorteo = xId_sorteo;
END $$
DELIMITER ;
CALL PR_modificar_sorteo(600, '2023-01-10', '2023-01-29', NULL, NULL, 'A', 300, 500, 301);
-- CALL PR_modificar_sorteo(?, ?, ?, ?, ?, ?, ?, ?, ?);           -> llamada en Java

DROP PROCEDURE IF EXISTS PR_eliminar_sorteo;
DELIMITER $$
CREATE PROCEDURE PR_eliminar_sorteo(IN xId_sorteo INT)
BEGIN
    DELETE FROM sorteo WHERE id_sorteo = xId_sorteo;
END $$
DELIMITER ;
-- CALL PR_eliminar_sorteo(600);
-- CALL PR_eliminar_sorteo(?);       -> llamada en java


-- -----------------------------------------------------
-- PROCEDURE_CRUD_AGENDA
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS PR_insertar_agenda;
DELIMITER $$
CREATE PROCEDURE PR_insertar_agenda(
    IN xFecha_partido DATE,
    IN xLugar_partido VARCHAR(45),
    IN xHora_partido TIME,
    IN xCreate_at DATE,
    IN xDelete_at DATE,
    IN xUpdated_at DATE,
    IN xEstado VARCHAR(2),
    IN xPartido_id_partido INT
    )
BEGIN
    INSERT INTO agenda (fecha_partido, lugar_partido, hora_partido, create_at, delete_at, updated_at, estado, partido_id_partido)
    VALUES (xFecha_partido, xLugar_partido, xHora_partido, xCreate_at, xDelete_at, xUpdated_at, xEstado, xPartido_id_partido);
END $$
DELIMITER ;
CALL PR_insertar_agenda('2023-01-11', 'BRASIL', '12:30:22', '2023-01-29', NULL, NULL, 'A', 500);     -- 700
-- CALL PR_insertar_agenda(?, ?, ?, ?, ?, ?, ?, ?);           -> llamada en Java

DROP PROCEDURE IF EXISTS PR_consultar_agenda;
DELIMITER $$
CREATE PROCEDURE PR_consultar_agenda(IN xId_agenda INT)
BEGIN
    SELECT * FROM agenda WHERE id_agenda = xId_agenda;
END $$
DELIMITER ;
CALL PR_consultar_agenda(700);
-- CALL PR_consultar_agenda(?);                   -> llamada en Java

DROP PROCEDURE IF EXISTS PR_modificar_agenda;
DELIMITER $$
CREATE PROCEDURE PR_modificar_agenda(
    IN xId_agenda INT,
    IN xFecha_partido DATE,
    IN xLugar_partido VARCHAR(45),
    IN xHora_partido TIME,
    IN xCreate_at DATE,
    IN xDelete_at DATE,
    IN xUpdated_at DATE,
    IN xEstado VARCHAR(2),
    IN xPartido_id_partido INT
    )
BEGIN
    UPDATE agenda SET fecha_partido = xFecha_partido, lugar_partido = xLugar_partido, hora_partido = xHora_partido, create_at = xCreate_at,
    delete_at = xDelete_at, updated_at = xUpdated_at, estado = xEstado, partido_id_partido = xPartido_id_partido
    WHERE id_agenda = xId_agenda;
END $$
DELIMITER ;
CALL PR_modificar_agenda(700, '2023-01-11', 'BRASIL', '12:30:22', '2023-01-29', NULL, NULL, 'A', 500);
-- CALL PR_modificar_agenda(?, ?, ?, ?, ?, ?, ?, ?, ?);           -> llamada en Java

DROP PROCEDURE IF EXISTS PR_eliminar_agenda;
DELIMITER $$
CREATE PROCEDURE PR_eliminar_agenda(IN xId_agenda INT)
BEGIN
    DELETE FROM agenda WHERE id_agenda = xId_agenda;
END $$
DELIMITER ;
-- CALL PR_eliminar_agenda(700);
-- CALL PR_eliminar_agenda(?);                   -> llamada en Java


-- -----------------------------------------------------
-- PROCEDURE_CRUD_ACTA_PARTIDO
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS PR_insertar_acta_partido;
DELIMITER $$
CREATE PROCEDURE PR_insertar_acta_partido(
    IN xCodigo_acta INT,
    IN xFecha_emision_acta DATE,
    IN xHora_inicio_partido TIME,
    IN xHora_fin_partido TIME,
    IN xEquipo_local VARCHAR(45),       -- tal vez deberia omitirse ¿?
    IN xEquipo_rival VARCHAR(45),       -- tal vez deberia omitirse ¿?
    IN xDuracion_partido TIME,
    IN xNum_gol_equipo_local INT,
    IN xNum_gol_equipo_rival INT,
    IN xEquipo_ganador VARCHAR(45),
    IN xCreate_at DATE,
    IN xDelete_at DATE,
    IN xUpdate_at DATE,
    IN xEstado VARCHAR(2),
    IN xPartido_id_partido INT
    )
BEGIN
    INSERT INTO acta_partido (codigo_acta, fecha_emision_acta, hora_inicio_partido, hora_fin_partido, equipo_local, equipo_rival, duracion_partido, 
    num_gol_equipo_local, num_gol_equipo_rival, equipo_ganador, create_at, delete_at, update_at, estado, partido_id_partido)
    VALUES (xCodigo_acta, xFecha_emision_acta, xHora_inicio_partido, xHora_fin_partido, xEquipo_local, xEquipo_rival, xDuracion_partido, 
    xNum_gol_equipo_local, xNum_gol_equipo_rival, xEquipo_ganador, xCreate_at, xDelete_at, xUpdate_at, xEstado, xPartido_id_partido);
END $$
DELIMITER ;
CALL PR_insertar_acta_partido(121, '2023-01-11', '12:22:00', '15:44:55', 'Ecuador', 'Venzuela', '01:33:33', 2, 4, 'Venezuela', 
'2023-01-29', NULL, NULL, 'A', 500);       -- 800
-- CALL PR_insertar_acta_partido(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);           -> llamada en Java

DROP PROCEDURE IF EXISTS PR_consultar_acta_partido;
DELIMITER $$
CREATE PROCEDURE PR_consultar_acta_partido(IN xId_acta_partido INT)
BEGIN
    SELECT * FROM acta_partido WHERE id_acta_partido = xId_acta_partido;
END $$
DELIMITER ;
CALL PR_consultar_acta_partido(800);
-- CALL PR_consultar_acta_partido(?);                   -> llamada en Java

DROP PROCEDURE IF EXISTS PR_modificar_acta_partido;
DELIMITER $$
CREATE PROCEDURE PR_modificar_acta_partido(
    IN xId_acta_partido INT,
    IN xCodigo_acta INT,
    IN xFecha_emision_acta DATE,
    IN xHora_inicio_partido TIME,
    IN xHora_fin_partido TIME,
    IN xEquipo_local VARCHAR(45),       -- tal vez deberia omitirse ¿?
    IN xEquipo_rival VARCHAR(45),       -- tal vez deberia omitirse ¿?
    IN xDuracion_partido TIME,
    IN xNum_gol_equipo_local INT,
    IN xNum_gol_equipo_rival INT,
    IN xEquipo_ganador VARCHAR(45),
    IN xCreate_at DATE,
    IN xDelete_at DATE,
    IN xUpdate_at DATE,
    IN xEstado VARCHAR(2),
    IN xPartido_id_partido INT
    )
BEGIN
    UPDATE acta_partido SET codigo_acta = xCodigo_acta, fecha_emision_acta = xFecha_emision_acta, hora_inicio_partido = xHora_inicio_partido, 
    hora_fin_partido = xHora_fin_partido, equipo_local = xEquipo_local, equipo_rival = xEquipo_rival, duracion_partido = xDuracion_partido, 
    num_gol_equipo_local = xNum_gol_equipo_local, num_gol_equipo_rival = xNum_gol_equipo_rival, equipo_ganador = xEquipo_ganador, 
    create_at = xCreate_at, delete_at = xDelete_at, update_at = xUpdate_at, estado = xEstado, partido_id_partido = xPartido_id_partido
    WHERE id_acta_partido = xId_acta_partido;
END $$
DELIMITER ;
CALL PR_modificar_acta_partido(800, 121, '2023-01-11', '12:22:00', '15:44:55', 'Ecuador', 'Venzuela', '01:33:33', 2, 4, 'Venezuela', 
'2023-01-29', NULL, NULL, 'A', 500);
-- CALL PR_modificar_acta_partido(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);           -> llamada en Java

DROP PROCEDURE IF EXISTS PR_eliminar_acta_partido;
DELIMITER $$
CREATE PROCEDURE PR_eliminar_acta_partido(IN xId_acta_partido INT)
BEGIN
    DELETE FROM acta_partido WHERE id_acta_partido = xId_acta_partido;
END $$
DELIMITER ;
-- CALL PR_eliminar_acta_partido(800);
-- CALL PR_eliminar_acta_partido(?);                   -> llamada en Java


-- -----------------------------------------------------
-- PROCEDURE_CRUD_ASISTENCIA
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS PR_insertar_asistencia;
DELIMITER $$
CREATE PROCEDURE PR_insertar_asistencia(
    IN xPartido VARCHAR(45),
    IN xLugar VARCHAR(45),
    IN xFecha_encuentro DATE,
    IN xAsistencia TINYINT,
    IN xEstado VARCHAR(2),
    IN xArbitro_id_arbitro INT
    )
BEGIN
    INSERT INTO asistencia (partido, lugar, fecha_encuentro, asistencia, estado, arbitro_id_arbitro)
    VALUES (xPartido, xLugar, xFecha_encuentro, xAsistencia, xEstado, xArbitro_id_arbitro);
END $$
DELIMITER ;
CALL PR_insertar_asistencia('Ecuadir VS Venezuela', 'BRASIL', '2023-01-25', 1, 'A', 300);       -- 900
-- CALL PR_insertar_asistencia(?, ?, ?, ?, ?, ?);           -> llamada en Java

DROP PROCEDURE IF EXISTS PR_consultar_asistencia;
DELIMITER $$
CREATE PROCEDURE PR_consultar_asistencia(IN xId_asistencia INT)
BEGIN
    SELECT * FROM asistencia WHERE id_asistencia = xId_asistencia;
END $$
DELIMITER ;
CALL PR_consultar_asistencia(900);
-- CALL PR_consultar_asistencia(?);                   -> llamada en Java

DROP PROCEDURE IF EXISTS PR_modificar_asistencia;
DELIMITER $$
CREATE PROCEDURE PR_modificar_asistencia(
    IN xId_asistencia INT,
    IN xPartido VARCHAR(45),
    IN xLugar VARCHAR(45),
    IN xFecha_encuentro DATE,
    IN xAsistencia TINYINT,
    IN xEstado VARCHAR(2),
    IN xArbitro_id_arbitro INT
    )
BEGIN
    UPDATE asistencia SET partido = xPartido, lugar = xLugar, fecha_encuentro = xFecha_encuentro, asistencia = xAsistencia, 
    estado = xEstado, arbitro_id_arbitro = xArbitro_id_arbitro
    WHERE id_asistencia = xId_asistencia;
END $$
DELIMITER ;
CALL PR_modificar_asistencia(900, 'Ecuadir VS Venezuela', 'BRASIL', '2023-01-25', 1, 'A', 300);
-- CALL PR_modificar_asistencia(?, ?, ?, ?, ?, ?, ?);           -> llamada en Java

DROP PROCEDURE IF EXISTS PR_eliminar_asistencia;
DELIMITER $$
CREATE PROCEDURE PR_eliminar_asistencia(IN xId_asistencia INT)
BEGIN
    DELETE FROM asistencia WHERE id_asistencia = xId_asistencia;
END $$
DELIMITER ;
-- CALL PR_eliminar_asistencia(900);
-- CALL PR_eliminar_asistencia(?);                   -> llamada en Java

