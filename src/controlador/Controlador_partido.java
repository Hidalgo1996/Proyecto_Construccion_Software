package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import modelo.Equipo_futbol;
import modelo.Partido;

public class Controlador_partido {

    List<Partido> listaPartido = new ArrayList<Partido>();

    /**
     * 
     * @param partido
     * @return Mensaje
     */
    public String guardarPartido(Partido partido) {
        String message = "";

        Connection conexion = Conexion.conectar();
        PreparedStatement consulta;

        try {

            consulta = conexion.prepareStatement("call PR_insertar_partido (?, ?, ?);");
            consulta.setInt(1, partido.getClub_local().getId_equipo());
            consulta.setInt(2, partido.getClub_rival().getId_equipo());
            consulta.setString(3, "A");

            ResultSet resultSet = consulta.executeQuery();

            while(resultSet.next()){
                message = resultSet.getString("id_partido").toString();
            }
            conexion.close();

            return message;
        } catch (SQLException e) {
            message = "Error en registro Partido";
            System.out.println("Error en registro Partido: " + e.getMessage());
            return message;
        }

    }

    /**
     * Actualizacion de registro Partido
     * 
     * @param Partido
     * @return Mensaje
     */
    public String actualizarPartido(Partido partido) {

        String mensaje = "";

        Connection conector = Conexion.conectar();
        PreparedStatement consulta;
        try {
            consulta = conector.prepareStatement("call PR_modificar_partido (?, ?, ?);");
            consulta.setInt(1, partido.getId_partido());
            consulta.setInt(2, partido.getClub_local().getId_equipo());
            consulta.setInt(3, partido.getClub_rival().getId_equipo());

            if (consulta.executeUpdate() > 0) {
                mensaje = "Partido actualizada correctamente";
            }
            conector.close();
            return mensaje;
        } catch (SQLException e) {
            mensaje = "Error en actualizar " + e.getMessage();
            System.out.println("Error en modificar Partido con id: " + partido.getId_partido());
            return mensaje;
        }
    }

    /**
     * 
     * @return Listado de Partidos
     */
    public List<Partido> listarPartidos() {

        Connection conector = Conexion.conectar();
        try {
            Statement consulta = conector.createStatement();
            ResultSet result = consulta.executeQuery("call PR_consultar_partido_();");
            // ResultSetMetaData metaData = result.getMetaData();

            while (result.next()) {
                Partido tmp = new Partido(
                        result.getInt("id_Partido"),
                        new Equipo_futbol(result.getInt("club_id_local")),
                        new Equipo_futbol(result.getInt("club_id_rival"))
                        );

                listaPartido.add(tmp);
            }

            conector.close();

            return listaPartido;
        } catch (SQLException e) {
            System.out.println("Error en listar las actas partido :" + e.getMessage());
            return null;
        }
    }

    /**
     * Eliminacion de Partido
     * 
     * @param id
     * @return
     */
    public String eliminarPartido(Integer id) {
        String mensaje = "";

        if (id <= 0) {
            // throw new PartidoException("Id no puede ser menor o igual a cero");
        }

        Connection conector = Conexion.conectar();
        PreparedStatement consulta;
        try {
            consulta = conector.prepareStatement("call PR_eliminar_partido (?);");
            consulta.setInt(1, id);

            if (consulta.executeUpdate() > 0) {
                mensaje = "Partido eliminada correctamente";
            }

            conector.close();
            return mensaje;
        } catch (SQLException e) {
            mensaje = "No se pudo eliminar correctamente " + e.getMessage();
            System.out.println("Error en eliminar Partido con id: " + id);

            return mensaje;
        }
    }

}