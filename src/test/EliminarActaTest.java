package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import controlador.Controlador_actas_partido;

public class EliminarActaTest {

    Controlador_actas_partido controller = new Controlador_actas_partido();

    @Test
    public void eliminar (){

        String mensaje = controller.eliminarActa(20);
        
        assertEquals("Acta eliminada correctamente", mensaje);        

    }
    
}
