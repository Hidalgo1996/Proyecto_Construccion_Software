
package controlador;



import conexion.Conexion;
import modelo.Arbitro;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.*;

public class Controlador_rol_arbitro {
    
    //Metodo para inicio de sesion
    //este metodo recibira un parametro de tipo arbitro
    public boolean login_arbitro(Arbitro objeto){
        
        boolean respuesta= false;//Esta variable indicara si la conexion esta
                                //abierta o no.
        
                                
        //Dentro de esta variable se guardara el retorno del metodo
        //conectar de la clase conexion.
        java.sql.Connection conexion_arbitro= Conexion.conectar();
        
        //Aqui se guarda el query del login
        String sql= "select nombre_usuario, contrasenia from arbitro\n" +
                    " where nombre_usuario= '"+objeto.getNombre_usuario()+"' and contrasenia= '"+objeto.getContrasenia()+"';";
        
        Statement declaracion;
        
        
        //Captura errores de conexion
        try {
            
            declaracion= (Statement) conexion_arbitro.createStatement();
            ResultSet resultado= declaracion.executeQuery(sql);//Aqui se ejecuta el query que se paso
                                                              //como parametro sql.
                                                              
           
                                                              
                                                              
            //Bucle while que sirve para que al momento de ejcutar el query
            //la variable nombre_usuario y contraseña coinciden, entonces 
            //la variable respuesta cambia a true.
            while(resultado.next()){
                
                respuesta= true;
            }
            
        } catch (SQLException e) {
            
            System.out.println("Error al iniciar sesion"+e);
            JOptionPane.showMessageDialog(null, "Error de inicio de sesion");
        }
        
        return respuesta;//Al final se retorna el valor de la respuesta 
    }
    
}
