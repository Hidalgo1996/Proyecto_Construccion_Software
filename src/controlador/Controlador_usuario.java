package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import conexion.Conexion;
import modelo.Usuario;
import modelo.UsuarioCache;

public class Controlador_usuario {

    protected Usuario usuario = new Usuario();
    /**
     * Metodo para inicio de sesion
     * @param objeto usuario
     * @return boolean
     */
    public boolean login(Usuario objeto) {

        boolean respuesta = false;// Esta variable indicara si la conexion esta
                                  // abierta o no.
        String _usuario = objeto.getNombre_usuario();
        String _contrasenia = objeto.getContrasenia();

        // Dentro de esta variable se guardara el retorno del metodo
        // conectar de la clase conexion.
        Connection conexion_arbitro = Conexion.conectar();

        // Aqui se guarda el query del login
        String sql = "call pr_login(?, ?);";
        PreparedStatement declaracion;

        // Captura errores de conexion
        try {

            declaracion = conexion_arbitro.prepareStatement(sql);
            declaracion.setString(1, _usuario);
            declaracion.setString(2, _contrasenia);

            ResultSet resultado = declaracion.executeQuery();// Aqui se ejecuta el query que se paso
                                                             // como parametro sql.

            // Bucle while que sirve para que al momento de ejcutar el query
            // la variable nombre_usuario y contraseña coinciden, entonces
            // la variable respuesta cambia a true.
            while (resultado.next()) {
                /* usuario.setNombre_usuario(resultado.getString("nombre_usuario"));
                usuario.setEmail(resultado.getString("email")); */
                UsuarioCache.getUsuarioCache().setNombre_usuario(resultado.getString("nombre_usuario"));
                UsuarioCache.getUsuarioCache().setEmail(resultado.getString("email"));
                respuesta = true;
            }

        } catch (SQLException e) {

            System.out.println("Error al iniciar sesion" + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error de inicio de sesion");
        }

        return respuesta;// Al final se retorna el valor de la respuesta
    }
}
