use gestion_arbitros;
-- Agregar columna estado a tabla
alter table acta_partido add column estado varchar(5);
-- actualizar estado
SET SQL_SAFE_UPDATES=0;
update acta_partido set estado = 'A' where estado is null;
-- Creacion de procedimiento guardar
drop procedure if exists GUARDAR_ACTAS_PARTIDO;
DELIMITER //
CREATE PROCEDURE GUARDAR_ACTAS_PARTIDO
 (
 IN id_acta int,
 IN hora_inicio varchar(8),
 IN hora_fin varchar(8),
IN equipo_rival varchar(100),
IN equipo_local varchar(100),
IN duracion_partido int,
IN num_goles_rival int,
IN num_goles_local int,
IN equipo_ganador varchar(100),
IN id_arbitro int,
IN estado varchar(5)
)
BEGIN
        INSERT INTO acta_partido(id_acta_partido , hora_inicio_partido, hora_fin_partido, equipo_rival, equipo_local, duracion_partido, num_gole_equip_rival, num_gole_equip_local, equipo_ganador, arbitro_id_arbitro1, estado)
   		VALUES (id_acta, hora_inicio, hora_fin, equipo_rival, equipo_local, duracion_partido, num_goles_rival, num_goles_local, equipo_ganador, id_arbitro, estado);
END//
delimiter ;

-- Creacion de procedimiento eliminar
drop procedure if exists eliminar_acta_partido;
delimiter //
create procedure eliminar_acta_partido (in id_acta int, in estado varchar(5) )
begin
	update acta_partido set estado = estado where id_acta_partido = id_acta;
end//
delimiter ;


-- Creacion de sp listar
drop procedure if exists listar_actas_partido;
DELIMITER //
create procedure LISTAR_ACTAS_PARTIDO()
begin
	SELECT * FROM ACTA_PARTIDO where estado <> 'E';
END//
delimiter ;

-- Creacion de sp actualizar
drop procedure if exists actualizar_actas_partido;
delimiter //
create procedure actualizar_actas_partido
(
IN id_acta int,
IN hora_inicio varchar(8),
IN hora_fin varchar(8),
IN equipo_rival varchar(100),
IN equipo_local varchar(100),
IN duracion_partido int,
IN num_goles_rival int,
IN num_goles_local int,
IN equipo_ganador varchar(100),
IN id_arbitro int
)
begin
	update acta_partido set hora_inicio_partido = hora_inicio, 
    hora_fin_partido = hora_fin, equipo_rival = equipo_rival,
    equipo_local = equipo_local, duracion_partido = duracion_partido,
    num_gole_equip_rival = num_goles_rival, num_gole_equip_local = num_goles_local,
    equipo_ganador = equipo_ganador, arbitro_id_arbitro1 = id_arbitro where id_acta_partido = id_acta ;
end //
DELIMITER ;