package test.SorteoTest;

import org.junit.Test;

import controlador.Controlador_sorteo;
import excepciones.SorteoException;
import modelo.Sorteo;

public class SorteoTest {
    
    Controlador_sorteo controlador_sorteo = new Controlador_sorteo();

    /**
     * 
     */
    @Test
    public void agregarSorteo(){
        Sorteo sorteo = new Sorteo();
        sorteo.setAgenda(null);
        
        try {
            controlador_sorteo.guardarSorteo(sorteo);
        } catch (SorteoException e) {
            System.out.println(e.getMessage());
        }
    }
}
