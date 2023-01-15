package conexion;

import com.sun.jdi.connect.spi.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    //Metodo de tipo connection
    //Conexion local para la base de datos
    public static java.sql.Connection conectar() {

        //Captura de errores de tipo SQL
        try {

            //Dentro de este objeto de tipo Connection se guardara la ruta de mi base de datos
            java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/gestion_arbitros", "proyecto", "12345");

            return conexion;

        } catch (SQLException e) {

            System.out.println("Error de conexion " + e);
        }

        return null;//Esto es para que no nos marque error
    }

}
