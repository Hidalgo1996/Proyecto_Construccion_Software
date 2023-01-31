package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import conexion.Conexion;
import modelo.Agenda;

public class Controlador_agenda {

    List<Agenda> listaAgenda = new ArrayList<Agenda>();

    /**
     * 
     * @param agenda
     * @return Mensaje
     */
    public String guardarAgenda(Agenda agenda) {
        String message = "";

        Connection conexion = Conexion.conectar();
        PreparedStatement consulta;

        try {

            consulta = conexion.prepareStatement("call sp (?, ?, ?, ?, ?)");

            consulta.setDate(1, agenda.getFecha_partido());
            consulta.setString(2, agenda.getLugar_partido());
            consulta.setTime(3, agenda.getHora_partido());
            consulta.setString(4, agenda.getEstado());
            consulta.setInt(5, agenda.getPartido_id());

            if (consulta.executeUpdate() > 0) {
                message = "Agenda registrada";
            }
            conexion.close();

            return message;
        } catch (SQLException e) {
            message = "Error en registro agenda";
            System.out.println("Error en registro agenda: " + e.getMessage());
            return message;
        }

    }

    /**
     * Actualizacion de registro Agenda
     * 
     * @param agenda
     * @return Mensaje
     */
    public String actualizarAgenda(Agenda agenda) {

        String mensaje = "";

        Connection conector = Conexion.conectar();
        PreparedStatement consulta;
        try {
            consulta = conector.prepareStatement("call sp (?, ?, ?, ?);");
            consulta.setDate(1, agenda.getFecha_partido());
            consulta.setString(2, agenda.getLugar_partido());
            consulta.setTime(3, agenda.getHora_partido());
            consulta.setInt(4, agenda.getPartido_id());

            if (consulta.executeUpdate() > 0) {
                mensaje = "Agenda actualizada correctamente";
            }
            conector.close();
            return mensaje;
        } catch (SQLException e) {
            mensaje = "Error en actualizar " + e.getMessage();
            System.out.println("Error en modificar agenda con id: " + agenda.getId_agenda());
            return mensaje;
        }
    }

    /**
     * 
     * @return Listado de Agendas
     */
    public List<Agenda> listarAgendas() {

        Connection conector = Conexion.conectar();
        try {
            Statement consulta = conector.createStatement();
            ResultSet result = consulta.executeQuery("call sp();");
            // ResultSetMetaData metaData = result.getMetaData();

            while (result.next()) {
                Agenda tmp = new Agenda(
                        result.getInt("id_agenda"),
                        result.getInt("partido_id"),
                        result.getDate("fecha_partido"),
                        result.getString("equipo_local"),
                        result.getTime("lugar_partido"),
                        result.getString("estado"));

                listaAgenda.add(tmp);
            }

            conector.close();

            return listaAgenda;
        } catch (SQLException e) {
            System.out.println("Error en listar las actas partido :" + e.getMessage());
            return null;
        }
    }

    /**
     * Eliminacion de agenda
     * @param id
     * @return
     */
    public String eliminarAgenda(Integer id) {
        String mensaje = "";

        if (id <= 0) {
            // throw new AgendaException("Id no puede ser menor o igual a cero");
        }

        Connection conector = Conexion.conectar();
        PreparedStatement consulta;
        try {
            consulta = conector.prepareStatement("call sp (?, ?);");
            consulta.setInt(1, id);
            consulta.setString(2, "E");

            if (consulta.executeUpdate() > 0) {
                mensaje = "Agenda eliminada correctamente";
            }

            conector.close();
            return mensaje;
        } catch (SQLException e) {
            mensaje = "No se pudo eliminar correctamente " + e.getMessage();
            System.out.println("Error en eliminar agenda con id: " + id);

            return mensaje;
        }
    }

}
