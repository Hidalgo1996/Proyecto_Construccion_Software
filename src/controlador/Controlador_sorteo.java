package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import modelo.Sorteo;

public class Controlador_sorteo {

    List<Sorteo> listaSorteo = new ArrayList<Sorteo>();

    /**
     * 
     * @param Sorteo
     * @return Mensaje
     */
    public String guardarSorteo(Sorteo sorteo) {
        String message = "";

        Connection conexion = Conexion.conectar();
        PreparedStatement consulta;

        try {

            consulta = conexion.prepareStatement("call sp (?, ?, ?, ?, ?)");
            consulta.setDate(1, sorteo.getFecha_sorteo());
            consulta.setInt(2, sorteo.getArbitro_id());
            consulta.setInt(3, sorteo.getArbitro_id_sustituto());
            consulta.setInt(3, sorteo.getPartido_id());
            consulta.setString(3, sorteo.getEstado());

            if (consulta.executeUpdate() > 0) {
                message = "Sorteo registrada";
            }
            conexion.close();

            return message;
        } catch (SQLException e) {
            message = "Error en registro Sorteo";
            System.out.println("Error en registro Sorteo: " + e.getMessage());
            return message;
        }

    }

    /**
     * Actualizacion de registro Sorteo
     * 
     * @param Sorteo
     * @return Mensaje
     */
    public String actualizarSorteo(Sorteo sorteo) {

        String mensaje = "";

        Connection conector = Conexion.conectar();
        PreparedStatement consulta;
        try {
            consulta = conector.prepareStatement("call sp (?, ?, ?, ?, ?);");
            consulta.setDate(1, sorteo.getFecha_sorteo());
            consulta.setInt(2, sorteo.getArbitro_id());
            consulta.setInt(3, sorteo.getArbitro_id_sustituto());
            consulta.setInt(3, sorteo.getPartido_id());
            consulta.setString(3, sorteo.getEstado());

            if (consulta.executeUpdate() > 0) {
                mensaje = "Sorteo actualizada correctamente";
            }
            conector.close();
            return mensaje;
        } catch (SQLException e) {
            mensaje = "Error en actualizar " + e.getMessage();
            System.out.println("Error en modificar Sorteo con id: " + sorteo.getId_sorteo());
            return mensaje;
        }
    }

    /**
     * 
     * @return Listado de Sorteos
     */
    public List<Sorteo> listarSorteos() {

        Connection conector = Conexion.conectar();
        try {
            Statement consulta = conector.createStatement();
            ResultSet result = consulta.executeQuery("call sp();");
            // ResultSetMetaData metaData = result.getMetaData();

            while (result.next()) {
                Sorteo tmp = new Sorteo(
                        result.getInt("id_sorteo"),
                        result.getDate("fecha_sorteo"),
                        result.getInt("arbitro_id"),
                        result.getInt("arbitro_id_sustituto"),
                        result.getInt("partido_id"),
                        result.getString("estado")
                        );

                listaSorteo.add(tmp);
            }

            conector.close();

            return listaSorteo;
        } catch (SQLException e) {
            System.out.println("Error en listar las actas Sorteo :" + e.getMessage());
            return null;
        }
    }

    /**
     * Eliminacion de Sorteo
     * 
     * @param id
     * @return
     */
    public String eliminarSorteo(Integer id) {
        String mensaje = "";

        if (id <= 0) {
            // throw new SorteoException("Id no puede ser menor o igual a cero");
        }

        Connection conector = Conexion.conectar();
        PreparedStatement consulta;
        try {
            consulta = conector.prepareStatement("call sp (?, ?);");
            consulta.setInt(1, id);
            consulta.setString(2, "E");

            if (consulta.executeUpdate() > 0) {
                mensaje = "Sorteo eliminado correctamente";
            }

            conector.close();
            return mensaje;
        } catch (SQLException e) {
            mensaje = "No se pudo eliminar correctamente " + e.getMessage();
            System.out.println("Error en eliminar Sorteo con id: " + id);

            return mensaje;
        }
    }

}
