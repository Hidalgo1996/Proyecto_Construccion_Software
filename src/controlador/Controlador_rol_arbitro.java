
package controlador;

import conexion.Conexion;
import modelo.Arbitro;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.*;

public class Controlador_rol_arbitro {
    
    Arbitro arbitro = new Arbitro();
    //Metodo para inicio de sesion
    //este metodo recibira un parametro de tipo arbitro
    public boolean login_arbitro(Arbitro objeto){
        
        boolean respuesta= false;//Esta variable indicara si la conexion esta
                                //abierta o no.
        String usuario = objeto.getNombre_usuario();
        String contrasenia = objeto.getContrasenia();
                                
        //Dentro de esta variable se guardara el retorno del metodo
        //conectar de la clase conexion.
        Connection conexion_arbitro = Conexion.conectar();
        
        //Aqui se guarda el query del login
        String sql= "call login(?, ?);";
        PreparedStatement declaracion;
        
        //Captura errores de conexion
        try {
            
            declaracion= conexion_arbitro.prepareStatement(sql);
            declaracion.setString(1, usuario);
            declaracion.setString(2, contrasenia);

            ResultSet resultado = declaracion.executeQuery();//Aqui se ejecuta el query que se paso
                                                              //como parametro sql.
                                                                                            
            //Bucle while que sirve para que al momento de ejcutar el query
            //la variable nombre_usuario y contraseña coinciden, entonces 
            //la variable respuesta cambia a true.
            while(resultado.next()){
                arbitro.setNombre_usuario(resultado.getString("nombre_usuario"));
                arbitro.setEmail(resultado.getString("email"));
                respuesta= true;
            }
            
        } catch (SQLException e) {
            
            System.out.println("Error al iniciar sesion"+ e.getMessage());
            JOptionPane.showMessageDialog(null, "Error de inicio de sesion");
        }
        
        return respuesta;//Al final se retorna el valor de la respuesta 
    }
    
}
