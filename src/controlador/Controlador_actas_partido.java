
package controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;

import java.sql.Connection;
import conexion.Conexion;
import excepciones.ActasException;
import modelo.Actas_partido;

/**
 * Controlador actas partido
 */
public class Controlador_actas_partido {

    private List<Actas_partido> listadoActas = new ArrayList<Actas_partido>();

    /**
     * 
     * @param object_acta
     * @return True or False
     */
    public boolean guardar(Actas_partido object_acta) {

        boolean respuesta = false;

        Connection conector = Conexion.conectar();
        PreparedStatement consulta;

        try {

            // Aqui se guardara la sentencia sql de ingresos
            consulta = conector.prepareStatement("call guardar_actas_partido (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

            // Esto sirve para ir agregando cada uno de los valores en la tabla de las
            // actas.
            consulta.setInt(1, object_acta.getId_acta_partido());
            consulta.setString(2, object_acta.getHora_inicio_partido());
            consulta.setString(3, object_acta.getHora_fin_partido());
            consulta.setString(4, object_acta.getNombre_equipo_rival());
            consulta.setString(5, object_acta.getNombre_equipo_local());
            consulta.setInt(6, object_acta.getDuracion_partido());
            consulta.setInt(7, object_acta.getGoles_equipo_rival());
            consulta.setInt(8, object_acta.getGoles_equipo_local());
            consulta.setString(9, object_acta.getEquipo_ganador());
            consulta.setInt(10, object_acta.getId_arbitro());
            consulta.setString(11, "A");

            // Cambia el estado de la variable respuesta.
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            conector.close();// Sirve para cerrar la conexion.

        } catch (SQLException e) {
            System.out.println("Error de ingreso en la base: " + e.getMessage());
        }

        return respuesta;
    }

    /**
     * 
     * @param object_acta
     * @return Mensaje
     */
    public String actualizarActa(Actas_partido object_acta) {
        String mensaje = "";

        Connection conector = Conexion.conectar();
        PreparedStatement consulta;
        try {
            consulta = conector.prepareStatement("call actualizar_actas_partido (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            consulta.setInt(1, object_acta.getId_acta_partido());
            consulta.setString(2, object_acta.getHora_inicio_partido());
            consulta.setString(3, object_acta.getHora_fin_partido());
            consulta.setString(4, object_acta.getNombre_equipo_rival());
            consulta.setString(5, object_acta.getNombre_equipo_local());
            consulta.setInt(6, object_acta.getDuracion_partido());
            consulta.setInt(7, object_acta.getGoles_equipo_rival());
            consulta.setInt(8, object_acta.getGoles_equipo_local());
            consulta.setString(9, object_acta.getEquipo_ganador());
            consulta.setInt(10, object_acta.getId_arbitro());

            if (consulta.executeUpdate() > 0) {
                mensaje = "Acta actualizada correctamente";
            }

            conector.close();
        } catch (SQLException e) {
            mensaje = "Error en actualizar " + e.getMessage();
            System.out.println("Error en modificar acta con id: " + object_acta.getId_acta_partido());
        } finally {
        }

        return mensaje;
    }

    /**
     * 
     * @return Lista ActasPartido
     */
    public List<Actas_partido> listarActas() {

        Connection conector = Conexion.conectar();
        try {
            Statement consulta = conector.createStatement();

            ResultSet result = consulta.executeQuery("call listar_actas_partido();");
            // ResultSetMetaData metaData = result.getMetaData();

            while (result.next()) {
                Actas_partido tmp = new Actas_partido(
                        result.getInt("id_acta_partido"),
                        result.getString("hora_inicio_partido"),
                        result.getString("hora_fin_partido"),
                        result.getString("equipo_rival"),
                        result.getString("equipo_local"),
                        result.getInt("duracion_partido"),
                        result.getInt("num_gole_equip_local"),
                        result.getInt("num_gole_equip_local"),
                        result.getString("equipo_ganador"),
                        result.getInt("arbitro_id_arbitro1"));

                listadoActas.add(tmp);
            }

            conector.close();

            return listadoActas;
        } catch (SQLException e) {
            System.out.println("Error en listar las actas partido :" + e.getMessage());
        } finally {
        }

        return null;
    }

    /**
     * Elimina acta de partido segun el id
     * 
     * @param id
     * @return mensaje -
     * @throws ActasException
     */
    public String eliminarActa(Integer id) throws ActasException {
        String mensaje = "";

        if (id <= 0) {
            throw new ActasException("Id no puede ser menor o igual a cero");
        }

        Connection conector = Conexion.conectar();
        PreparedStatement consulta;
        try {
            consulta = conector.prepareStatement("call eliminar_acta_partido (?, ?);");
            consulta.setInt(1, id);
            consulta.setString(2, "E");

            if (consulta.executeUpdate() > 0) {
                mensaje = "Acta eliminada correctamente";
            }

            conector.close();
        } catch (SQLException e) {
            mensaje = "No se pudo eliminar correctamente " + e.getMessage();
            System.out.println("Error en eliminar acta con id: " + id);
        } finally {
        }

        return mensaje;
    }

}
