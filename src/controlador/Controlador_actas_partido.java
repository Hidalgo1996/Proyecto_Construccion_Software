
package controlador;

import java.sql.PreparedStatement;
import com.sun.jdi.connect.spi.Connection;
import conexion.Conexion;
import modelo.Actas_partido;


public class Controlador_actas_partido {
    
    
    //Metodo para registrar una nueva acta de partido
    //Metodo que recibe un objeto de tipo categoria
    public boolean guardar(Actas_partido object_acta){
        
        boolean respuesta= false;//Esta variable servira para indicar si la consulta 
                                 //es correcta o no.
        
        java.sql.Connection conector=  conexion.Conexion.conectar();
        
        PreparedStatement consulta;
        
        //Captura de errores al moemtno de guardar en la base de datos
        try {
            
            //Aqui se guardara la sentencia sql de ingresos
            consulta= conector.prepareStatement("INSERT INTO acta_partido VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            
            
            //Esto sirve para ir agregando cada uno de los valores en la tabla de las
            //actas.
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
            
            
            //Cambia el estado de la variable respuesta.
            if(consulta.executeUpdate() > 0){
                
                respuesta = true;
            }
            
            conector.close();//Sirve para cerrar la conexion.
   
            
            
        } catch (Exception e) {
            
            System.out.println("Error de ingreso en la base: "+e);
        }
        
        return respuesta;
    }
    
    
    
}
