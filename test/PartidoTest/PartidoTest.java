package test.PartidoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import controlador.Controlador_partido;
import modelo.Equipo_futbol;
import modelo.Partido;
import modelo.TipoBusqueda;

public class PartidoTest {

    Controlador_partido controlador_partido = new Controlador_partido();

    /**
     * 
     */
    @Test
    public void agregarPartido() {

        Partido partido = new Partido();
        partido.setClub_local(new Equipo_futbol(408));
        partido.setClub_local(new Equipo_futbol(407));

        String mensaje = controlador_partido.guardarPartido(partido);

        assertEquals("", mensaje);
    }

    /**
     * 
     */
    @Test
    public void cargarComboPartido() {

        List<Partido> tmpActa = Controlador_partido.cargarComboPartido(TipoBusqueda.ACTA);
        List<Partido> tmpSorteo = Controlador_partido.cargarComboPartido(TipoBusqueda.ACTA);

        assertNotNull("Listado Combo Actas", tmpActa);
        assertNotNull("Listado Combo Sorteo", tmpSorteo);
    }
}
